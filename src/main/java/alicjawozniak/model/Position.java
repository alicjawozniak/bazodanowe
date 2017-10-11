package alicjawozniak.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    @Size(min = 0)
    private long salary;

}
