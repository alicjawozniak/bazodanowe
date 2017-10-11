package alicjawozniak.model;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

}
