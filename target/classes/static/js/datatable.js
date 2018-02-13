
$(document).ready(function() {
	
	var table = $("#employeesTable").DataTable ({  
		dom: 'Bfrtip',
		'sAjaxSource':  '/employees',
		'sAjaxDataProp': '',		
		columns: [
			{data: "id", title: "Id"},
            {data: "name", title: "Name"},
            {data: "email", title: "Email"},
            {data: "city", title: "City"},
            {data: "date", title: "Date"},
            {
                data:   "active",
                title: "Active",
                render: function ( data, type, row ) {
                    if ( type === 'display' ) {
                        return '<input type="checkbox" class="editor-active">';
                    }
                    return data;
                },
                className: "dt-body-center"
            },               
            {data:null, title:"Edit",
                "fnCreatedCell": function( nTd, sData, oData, iRow, iCol) {                  
                	$(nTd).html( "<button type='button' class='cEdit' data-toggle='modal'>Edit</button>" );                    
                }
            }
        ],   			
		'bProcessing': true,   
		"bSort": true,
		"bInfo" : false,                                
		"bPaginate": false,
		"bFilter": false,
		select: true,
		lengthChange: false	,
		rowCallback: function ( row, data ) {
            // Set the checked state of the checkbox in the table
            $('input.editor-active', row).prop( 'checked', data.active == 1 );
        },
	 });		
	
	 $('#employeesTable tbody').on( 'click', '.cEdit', function () {
		 var data = table.row( $(this).closest('tr') ).data();  
		 $(".update-modal-body #id").val( data.id );
		 $(".update-modal-body #name").val( data.name );
		 $(".update-modal-body #email").val( data.email );
		 $(".update-modal-body #cityList").val( data.city );
		 $(".update-modal-body #date-value").val( data.date );
		 $(".update-modal-body #active").val( data.active );
		 $(".update-modal-body #active").prop('checked', data.active);
		 $('#updateModal').modal('show');
	 } );		
	
	 $('#btnAdd').on('click', function(event) {
		  event.preventDefault(); // To prevent following the link (optional)
		  $('#addModal').modal('show');
	 });
	
	 $('#addModal').on('click','#btnAdd', function (e) {    	
	    	updateDb("/add",
	    			 "",
	    			 $(".add-modal-body #name").val(),
	    			 $(".add-modal-body #email").val(),
					 $(".add-modal-body #cityList").val(),
					 $(".add-modal-body #date-value").val(),
					 $(".add-modal-body #active").val());  
	    	
	    	$('#addModal').modal('toggle');
	 });			
	
	
	       
    $('#updateModal').on('click','#btnDelete', function (e) {    	
    	updateDb("/delete",
    			 $(".update-modal-body #id").val(),
    			 $(".update-modal-body #name").val(),
    			 $(".update-modal-body #email").val(),
				 $(".update-modal-body #cityList").val(),
				 $(".update-modal-body #date-value").val(),
				 $(".update-modal-body #active").val());  
    	
    	$('#updateModal').modal('toggle'); 
    });		
    
    $('#updateModal').on('click','#btnUpdate', function (e) {    	
    	updateDb("/update",
    			 $(".update-modal-body #id").val(),
    			 $(".update-modal-body #name").val(),
    			 $(".update-modal-body #email").val(),
				 $(".update-modal-body #cityList").val(),
				 $(".update-modal-body #date-value").val(),
				 $(".update-modal-body #active").val());  
    	
    	$('#updateModal').modal('toggle');
    });			
    
    $(".update-modal-body #active").click(function(){
	   var check = $(this).prop('checked');	   
	   $(".update-modal-body #active").val( check );	   	   
	});   
    
    $(".add-modal-body #active").click(function(){
 	   var check = $(this).prop('checked');	   
 	   $(".add-modal-body #active").val( check );	   	   
 	}); 
    
	function updateDb(url,id,name,email,city,date,active) {    
		
		$.ajax ({
		    url: url,
		    type: "POST",
		    data: JSON.stringify({
		    			id     : id,
		    			name   : name,
		    			email  : email,
		    			city   : city,
		    			date   : date,
		    			active : active}),
		    dataType: "json",
		    contentType: "application/json; charset=utf-8",
		    success: function (data) {
		          $.each(data, function(index, currEmp) {		       
		        	  table.ajax.reload();
		         });  
		          
		        },
		        error: function(jqXHR, exception) {
		            if (jqXHR.status === 0) {
		                alert('Not connect.\n Verify Network.');
		            } else if (jqXHR.status == 404) {
		                alert('Requested page not found. [404]');
		            } else if (jqXHR.status == 500) {
		                alert('Internal Server Error [500].');
		            } else if (exception === 'parsererror') {
		                alert('Requested JSON parse failed.');
		            } else if (exception === 'timeout') {
		                alert('Time out error.');
		            } else if (exception === 'abort') {
		                alert('Ajax request aborted.');
		            } else {
		                alert('Uncaught Error.\n' + jqXHR.responseText);
		            }
		            table.ajax.reload();
		        }
		});			
	}	
		
});
