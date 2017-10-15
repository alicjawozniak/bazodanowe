package alicjawozniak.service;

import alicjawozniak.dto.EmployeeFilter;
import alicjawozniak.model.Employee;
import alicjawozniak.model.QEmployee;
import alicjawozniak.repository.EmployeeRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Page<Employee> readEmployees(Pageable pageable, EmployeeFilter employeeFilter) {
        BooleanBuilder builder = new BooleanBuilder();
        if (employeeFilter.getId() != null) {
            builder.and(QEmployee.employee.id.eq(employeeFilter.getId()));
        }
        if (employeeFilter.getFirstName() != null) {
            builder.and(QEmployee.employee.firstName.eq(employeeFilter.getFirstName()));
        }
        if (employeeFilter.getLastName() != null) {
            builder.and(QEmployee.employee.lastName.eq(employeeFilter.getLastName()));
        }
        if (employeeFilter.getEmail() != null) {
            builder.and(QEmployee.employee.email.eq(employeeFilter.getEmail()));
        }
        if (employeeFilter.getPhoneNo() != null) {
            builder.and(QEmployee.employee.phoneNo.eq(employeeFilter.getPhoneNo()));
        }
        if (employeeFilter.getPesel() != null) {
            builder.and(QEmployee.employee.pesel.contains(employeeFilter.getPesel()));
        }
        if (employeeFilter.getAddressCity() != null) {
            builder.and(QEmployee.employee.address.city.eq(employeeFilter.getAddressCity()));
        }
        if (employeeFilter.getPositionId() != null) {
            builder.and(QEmployee.employee.position.id.eq(employeeFilter.getPositionId()));
        }
        if (employeeFilter.getCompanyCarRegistrationNo() != null) {
            builder.and(QEmployee.employee.companyCar.registrationNo.contains(employeeFilter.getCompanyCarRegistrationNo()));
        }
        if (employeeFilter.getClient() != null) {
            builder.and(QEmployee.employee.clients.contains(employeeFilter.getClient()));
        }
        if (employeeFilter.getBankAccountIban() != null) {
            builder.and(QEmployee.employee.bankAccount.iban.contains(employeeFilter.getBankAccountIban()));
        }
        if (employeeFilter.getCompanyBranchName() != null) {
            builder.and(QEmployee.employee.companyBranch.name.contains(employeeFilter.getCompanyBranchName()));
        }

        return employeeRepository.findAll(builder.getValue(), pageable);
    }
}
