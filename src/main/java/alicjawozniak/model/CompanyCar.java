package alicjawozniak.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class CompanyCar {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String registrationNo;

    @Size(min = 0)
    private long mileage;

    @OneToOne
    private Employee employee;

}
