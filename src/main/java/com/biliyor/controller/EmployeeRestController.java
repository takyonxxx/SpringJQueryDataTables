package com.biliyor.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.biliyor.model.Employee;
import com.biliyor.repository.EmployeeRepository;
import com.biliyor.service.EmployeeService;

@RestController
public class EmployeeRestController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@RequestMapping("/")
	public ModelAndView index () {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("index");
	    return modelAndView;
	}
	
	@RequestMapping(path="/employees", method=RequestMethod.GET)
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	 @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable("id") long id){
		return employeeService.getEmployeeById(id);
	}
	
	@RequestMapping(path="/add", method=RequestMethod.POST, produces="application/json")	
	public @ResponseBody List<Employee> addRecord(@RequestBody Employee employee) {	
		
		employeeRepository.save(employee);		
		return employeeService.getAllEmployees();	
			
		/*public List<Employee> handleRequest(HttpServletRequest request) {		
		Enumeration<String> parameterNames = request.getParameterNames();
		if(parameterNames.hasMoreElements()) {
			for (Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
			    String name = entry.getKey();
			    String value = entry.getValue()[0];
			    System.out.println(name + " : " + value);
			}
		}  */ 		    	
	}	
	
	@RequestMapping(path="/delete", method=RequestMethod.POST, produces="application/json")	
	public @ResponseBody List<Employee> deleteRecord(@RequestBody Employee employee) {	
		
		if(employeeRepository.count() == 0)
		{
			return null;
		}
		
		employeeRepository.delete(employee.getId());
				
	    return employeeService.getAllEmployees();		
	}	
	
	@RequestMapping(path="/update", method=RequestMethod.POST, produces="application/json")	
	public @ResponseBody List<Employee> updateRecord(@RequestBody Employee employee) {		 
		
		employeeRepository.save(employee);
	    return employeeService.getAllEmployees();		
	}	
	
	@RequestMapping(path="/list", method=RequestMethod.GET)
	public List<Employee> listAllEmployees(){
		return employeeService.getAllEmployees();
	}   

}
