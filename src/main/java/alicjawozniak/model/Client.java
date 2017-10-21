package alicjawozniak.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String phoneNo;

    @NotEmpty
    private String email;

    //employees taking careof a client
    @ManyToMany
    @JsonIgnore
    private List<Employee> employees;

    @OneToMany
    private List<Contract> contracts;
}
