package kz.mn.decisive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "requesters")
public class Requester {
    @Id
    @SequenceGenerator(name="requester_seq",
            sequenceName="requester_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="requester_seq")
    private Long id;
    private String iin;
    private String firstName;
    private String lastName;
    private String patronymic;
}
