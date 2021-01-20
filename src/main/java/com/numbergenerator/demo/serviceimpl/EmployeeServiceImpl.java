package com.numbergenerator.demo.serviceimpl;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.numbergenerator.demo.entity.Employee;
import com.numbergenerator.demo.repository.EmployeeRepository;
import com.numbergenerator.demo.service.EmployeeService;

@EnableAsync
@Service
@EnableCaching
public class EmployeeServiceImpl implements EmployeeService {

	private static Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	@Qualifier("fixedThreadPool")
	private ExecutorService executorService;

	static String inProgress = "";

	@Override
	public String uploadEmployeeData(String path) {
		Stream<String> lines = null;
		// List<Future<Employee>> statuses = new ArrayList<>();
		List<Employee> empList = new ArrayList<>();
		inProgress = "In Progress";
		try {

			lines = Files.lines(Paths.get(path));
			String content = lines.collect(Collectors.joining(System.lineSeparator()));
			String[] employeeDataArray = content.split("\n");
			Future<Employee> res = null;
			Thread.sleep(50000);
			for (String line : employeeDataArray) {
				String[] data = line.split(" ");
				res = executorService.submit(() -> parseEmployeeData(data));
				// statuses.add(res);
				empList.add(res.get());
			}
//			for (Future<Employee> status : statuses) {
//				try {
//					Employee result = status.get();
//					System.out.println("resultFromS4::"+ result);
//				} catch (InterruptedException | ExecutionException e) {
//					log.error(e.getMessage());
//					return "Failure";
//				}
//			}
			employeeRepository.saveAll(empList);
			inProgress = "Success";

		} catch (Exception e) {
			log.error(e.getMessage());
			inProgress = "Failed";
			return "Failed while parsing the data";
		} finally {
			lines.close();
		}
		return "Success";
	}

	public Employee parseEmployeeData(String[] data) {
		log.info(data.toString());
		Employee record = new Employee();
		record.setEmployeeName(data[0]);
		record.setAge(data[1]);
		log.info(data[0] + " " + data[1]);
		return record;

	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.save(employee);

	}

	@Override
	public Optional<Employee> getEmployeeById(Long id) {
		return employeeRepository.findById(id);
	}

	@Override
	public void deleteEmployee(Long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public String getStatus() {
		return inProgress;
	}

}
