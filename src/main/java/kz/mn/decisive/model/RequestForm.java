package kz.mn.decisive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;


import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "request_forms")
public class RequestForm {
    @Id
    @SequenceGenerator(name="request_form_seq",
            sequenceName="request_form_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="request_form_seq")
    private Long id;
    private Long gatewayId;
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private Requester requester;
    private String originApp;
    private String content;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
}
