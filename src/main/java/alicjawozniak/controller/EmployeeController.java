package alicjawozniak.controller;

import alicjawozniak.dto.EmployeeDto;
import alicjawozniak.dto.EmployeeFilter;
import alicjawozniak.model.Employee;
import alicjawozniak.model.Error;
import alicjawozniak.repository.ErrorRepository;
import alicjawozniak.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;
    private final ErrorRepository errorRepository;

    @PostMapping(value = "/api/employees")
    public Page<Employee> readEmployees(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
                                        @RequestBody EmployeeFilter filter) {
        return employeeService.readEmployees(pageable, filter);
    }

    @GetMapping(value = "/{id}")
    public Employee getEmployee(@PathVariable("id") Employee employee) {
        return employee;
    }

    @PostMapping("/api/employee")
    public Employee createEmployee(@Valid @RequestBody EmployeeDto dto, BindingResult bindingResult) throws BindException {
        if(bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(fieldError -> {
                Error error = new Error();
                error.setErrorCode(fieldError.getField() + ": " + fieldError.getDefaultMessage());
                error.setDate(LocalDate.now());
                errorRepository.save(error);
            });

            throw new BindException(bindingResult);
        }

        return employeeService.createEmployee(dto);
    }
}
