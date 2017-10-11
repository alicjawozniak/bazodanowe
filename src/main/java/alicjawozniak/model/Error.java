package alicjawozniak.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Error {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String errorCode;

    @NotEmpty
    private LocalDate date;

}
