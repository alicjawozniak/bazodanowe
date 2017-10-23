package alicjawozniak.service;

import alicjawozniak.dto.EmployeeDto;
import alicjawozniak.dto.EmployeeFilter;
import alicjawozniak.model.Employee;
import alicjawozniak.model.QEmployee;
import alicjawozniak.repository.EmployeeRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> readEmployees(Pageable pageable, EmployeeFilter employeeFilter) {
        BooleanBuilder builder = new BooleanBuilder();
        if (!StringUtils.isEmpty(employeeFilter.getId())) {
            builder.and(QEmployee.employee.id.eq(employeeFilter.getId()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getFirstName())) {
            builder.and(QEmployee.employee.firstName.eq(employeeFilter.getFirstName()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getLastName())) {
            builder.and(QEmployee.employee.lastName.eq(employeeFilter.getLastName()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getEmail())) {
            builder.and(QEmployee.employee.email.eq(employeeFilter.getEmail()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getPhoneNo())) {
            builder.and(QEmployee.employee.phoneNo.eq(employeeFilter.getPhoneNo()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getPesel())) {
            builder.and(QEmployee.employee.pesel.contains(employeeFilter.getPesel()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getAddressCity())) {
            builder.and(QEmployee.employee.address.city.eq(employeeFilter.getAddressCity()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getPositionId())) {
            builder.and(QEmployee.employee.position.id.eq(employeeFilter.getPositionId()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getCompanyCarRegistrationNo())) {
            builder.and(QEmployee.employee.companyCar.registrationNo.contains(employeeFilter.getCompanyCarRegistrationNo()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getClient())) {
            builder.and(QEmployee.employee.clients.contains(employeeFilter.getClient()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getBankAccountIban())) {
            builder.and(QEmployee.employee.bankAccount.iban.contains(employeeFilter.getBankAccountIban()));
        }
        if (!StringUtils.isEmpty(employeeFilter.getCompanyBranchName())) {
            builder.and(QEmployee.employee.companyBranch.name.contains(employeeFilter.getCompanyBranchName()));
        }

        return employeeRepository.findAll(builder.getValue(), pageable);
    }

    public Employee createEmployee(EmployeeDto dto) {
        Employee employee = null;

        if (dto.getId() != null) {
            employee = employeeRepository.findOne(dto.getId());
        }

        if (employee == null) {
            employee = new Employee();
        }

        employee.setId(dto.getId());
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNo(dto.getPhoneNo());
        employee.setPesel(dto.getPesel());
        employee.setAddress(dto.getAddress());
        employee.setPosition(dto.getPosition());
        employee.setCompanyCar(dto.getCompanyCar());
        employee.setClients(dto.getClients());
        employee.setBankAccount(dto.getBankAccount());
        employee.setCompanyBranch(dto.getCompanyBranch());

        return employeeRepository.save(employee);
    }
}
