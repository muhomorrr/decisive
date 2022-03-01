package kz.mn.decisive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deciders")
public class Decider {
    @Id
    @SequenceGenerator(name="decider_seq",
            sequenceName="decider_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="decider_seq")
    private Long id;
    private String username;
    private String password;
    private Boolean isActive;
}
