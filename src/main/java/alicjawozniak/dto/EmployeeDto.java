package alicjawozniak.dto;

import alicjawozniak.model.*;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class EmployeeDto {

    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String phoneNo;

    @NotEmpty
    private String pesel;

    @NotNull
    private Address address;

    private Position position;

    private CompanyCar companyCar;

    private List<Client> clients;

    private BankAccount bankAccount;

    private CompanyBranch companyBranch;
}
