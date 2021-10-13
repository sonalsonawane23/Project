package net.javaguides.springboot.controller;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.service.serviceimpl.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class AppControllermockito {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmployeeServiceImpl EmployeeServiceImpl;
	
	@Test
	public void testCreateEmployee() throws Exception {
		
		Employee employee = new Employee();
		employee.setId(13131);
		employee.setFirstName("ABC");
		employee.setLastName("XYZ");
		employee.setEmailId("abc@gmail.com");
		
		
		String inputInJson = this.mapToJson(employee);
		
		String URI = "/api/v1/employees";
		
		Mockito.when(appServiceImplementation.saveDetails(Mockito.any(Employee.class))).thenReturn(employee);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(URI)
				.accept(MediaType.APPLICATION_JSON).content(inputInJson)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		String outputInJson = response.getContentAsString();
		
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	public void testGetEmployeetById() throws Exception {
		Employee employee = new Employee();
		employee.setId(12121);
		employee.setFirstName("PQR");
		employee.setLastName("BYX");
		employee.setEmailId("adT@gmail.com");
		
		
		Mockito.when(appServiceImplementation.getDetailById(Mockito.anyInt())).thenReturn(employee);
		
		String URI = "/api/v1/employees/12121";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(employee);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}

	@Test
	public void testGetAllBookedEmployees() throws Exception {

		Employee employee1 = new Employee();
		employee1.setId(13131);
		employee1.setFirstName("ABC");
		employee1.setLastName("XYZ");
		employee1.setEmailId("abc@gmail.com");
		

		
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(employee1);
		
		Mockito.when(appServiceImplementation.listDetails()).thenReturn(employeeList);
		
		String URI = "/api/v1/employees";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				URI).accept(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(employeeList);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	

	
	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}