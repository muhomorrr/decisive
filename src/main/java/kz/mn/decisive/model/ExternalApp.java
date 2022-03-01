package kz.mn.decisive.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "external_apps")
public class ExternalApp {
    @Id
    @SequenceGenerator(name="external_app_seq",
            sequenceName="external_app_seq",
            allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator="external_app_seq")
    private Long id;
    private String name;
    private Boolean isActive;
}
