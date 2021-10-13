package net.javaguides.springboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;
import com.mindtree.employeemanagerapp.model.Employee;
import com.mindtree.employeemanagerapp.repository.EmployeeRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTests {
	@Autowired
    private EmployeeRepository employeeRepository;
	@Autowired
	private TestEntityManager entityManager;	
	
	@Test
	public void testSaveEmployee(){
		
		Employee employee = getEmployee();
		
		Employee savedInDb = entityManager.persist(employee);
		
		
		Employee getFromDb = employeeRepository.findOne(savedInDb.getEmployee_id());
		
		assertThat(getFromDb).isEqualTo(savedInDb);
	}
	
	
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setId(12121);
		employee.setFirstName("ABC");
		employee.setLastName("XYZ");
		employee.setEmailId("abc@gmail.com");
		
		return employee;
	}


@Test
public void testGetEmployeeById(){
	Employee employee = new Employee();
	employee.setId(13131);
	employee.setFirstName("ABC");
	employee.setLastName("XYZ");
	employee.setEmailId("abc@gmail.com");
	Employee employeeSavedInDb = entityManager.persist(employee);
	
	Employee employeeFromInDb = appRepository.findOne(employeeSavedInDb.getEmployee_id());
	assertThat(employeeSavedInDb).isEqualTo(employeeFromInDb);
  }



@Test
public void testDeleteEmployeeById(){
	Employee employee1 = new Employee();
	employee1.setId(12121);
	employee1.setFirstName("ABC");
	employee1.setLastName("XYZ");
	employee1.setEmailId("abc@gmail.com");
	
	
	Employee persist = entityManager.persist(employee1);
	entityManager.persist(employee1);
	
	entityManager.remove(persist);
	
	Iterable<Employee> allEmployeesFromDb = appRepository.findAll();
	List<Employee> employeeList = new ArrayList<>();
	
	for (Employee employee : allEmployeesFromDb) {
		employeeList.add(employee);
	}
	assertThat(employeeList.size()).isEqualTo(1);
 }


}
