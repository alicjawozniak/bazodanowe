package alicjawozniak.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString(exclude = "id")
@Check(constraints = "email IS NOT NULL")
@Table(indexes = {@Index(name = "IDX_MYIDX1", columnList = "id,lastName,phoneNo,pesel")})
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @NotEmpty
    private String phoneNo;

    @NotEmpty
    private String pesel;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToOne(cascade = CascadeType.ALL)
    private Position position;

    @OneToOne(cascade = CascadeType.ALL)
    private CompanyCar companyCar;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Client> clients = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    @ManyToOne(cascade = CascadeType.ALL)
    private CompanyBranch companyBranch;

}
