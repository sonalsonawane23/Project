package net.javaguides.springboot;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.repository.EmployeeRepository;
import com.mindtree.employeemanagerapp.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServicesTest {

	@Autowired
	private  EmployeeService appServices;
	
	@MockBean
	private EmployeeRepository appRepository;
	
	@Test
	public void testCreateEmployee(){

		Employee employee = new Employee();
		employee.setId(12121);
		employee.setFirstName("Aditi");
		employee.setLastName("Shah");
		employee.setEmailId("aditishah@gmail.com");
		
		
	    Mockito.when(appRepository.save(employee)).thenReturn(employee);
	    
	    assertThat(appServices.saveDetails(employee)).isEqualTo(employee);
	
	}
	
	
	@Test
	public void testGetEmployeeById(){
		Employee employee = new Employee();
		employee.setId(13131);
		employee.setFirstName("ABC");
		employee.setLastName("XYZ");
		employee.setEmailId("abc@gmail.com");
		
	    Mockito.when(appRepository.findOne(13131)).thenReturn(employee);
	    assertThat(appServices.getDetailById(13131)).isEqualTo(employee);
	}
	
	@Test
	public void testGetAllBookedEmployee(){
		Employee employee = new Employee();
		employee.setId(13131);
		employee.setFirstName("ABC");
		employee.setLastName("XYZ");
		employee.setEmailId("abc@gmail.com");
		
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee);
		
		Mockito.when(appRepository.findAll()).thenReturn(employeeList);
		
		assertThat(appServices.listDetails()).isEqualTo(employeeList);
	}
	
	
	@Test
	public void testDeleteEmployee(){
		Employee employee = new Employee();
		employee.setId(12121);
		employee.setFirstName("Aditi");
		employee.setLastName("Shah");
		employee.setEmailId("aditishah@gmail.com");
		
		
		
	    Mockito.when(appRepository.findOne(12121)).thenReturn(employee);
	    Mockito.when(appRepository.exists(employee.getEmployee_id())).thenReturn(false);
	   assertFalse(appRepository.exists(employee.getEmployee_id()));
	}
	
	