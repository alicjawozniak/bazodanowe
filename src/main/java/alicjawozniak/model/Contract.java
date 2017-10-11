package alicjawozniak.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Contract {
    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private LocalDate signDate;

    private long value;

}
