package com.biliyor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.biliyor.repository.EmployeeRepository;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {	
		
		/*Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String sDate = dateFormat.format(date);	
		java.sql.Date sqlDate = java.sql.Date.valueOf(sDate);
		
		Employee  temp = new Employee("Türkay Biliyor","turkaybiliyor@hotmail.com",CityNames.Ankara,sqlDate,true);
		employeeRepository.save(temp);
		*/
	}
	
}
