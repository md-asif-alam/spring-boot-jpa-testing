package net.javaGuides.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.aspectj.weaver.patterns.IfPointcut.IfFalsePointcut;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTest {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//JUnit test for save Employee
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveEmployeeTest(){
		Employee employee=Employee.builder()
				.firstName("Ramese")
				.lastName("Fadatre")
				.email("ramesh@gmail.com")
				.build();
		employeeRepository.save(employee);
		assertThat(employee.getId()).isGreaterThan(0);
	}
	
	@Test
	@Order(2)
	public void getEmployeeTest() {
		Employee employee = employeeRepository.findById(1L).get();
		assertThat(employee.getId()).isEqualTo(1L);
	}
	@Test
	@Order(3)
	public void getListOfEmployeesTest() {
		List<Employee> employees=employeeRepository.findAll();
		
		assertThat(employees.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateEmployeeTest() {
		Employee employee = employeeRepository.findById(1L).get();
		employee.setEmail("ram@gmail.com");
		
		Employee updatedEmployee = employeeRepository.save(employee);
		
		assertThat(updatedEmployee.getEmail()).isEqualTo("ram@gmail.com");
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteEmployeeTest() {
		Employee employee=employeeRepository.findById(1L).get();
		employeeRepository.delete(employee);
		
//		employeeRepository.deleteById(1L);
		
		Employee employee1=null;
		
		Optional<Employee> optionalEmployee=employeeRepository.findByEmail("ram@gmail.com");
		
		if(optionalEmployee.isPresent()) {
			employee1=optionalEmployee.get();
		}
		
		assertThat(employee1).isNull();
	}
	
	
	
	
	
	
	
	
	
	
	
}
