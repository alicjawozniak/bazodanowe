package alicjawozniak.dto;

import alicjawozniak.model.*;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeFilter {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNo;

    private String pesel;

    private String addressCity;

    private Long positionId;

    private String companyCarRegistrationNo;

    private Client client;//w requescie wystarczy podac id clienta

    private String bankAccountIban;

    private String companyBranchName;
}
