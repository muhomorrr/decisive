package kz.mn.decisive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    @Column(unique = true)
    private String iin;
    private String firstName;
    private String lastName;
    private String patronymic;

    @OneToMany(mappedBy = "requester")
    private List<RequestForm> requestFormList;
}
