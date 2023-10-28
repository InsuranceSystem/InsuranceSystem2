package aplus.insurancesystem2.Insurance.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Guarantee {
    @Id
    @Column(name = "id")
    private String id;

    private String insuranceID;
    private String termsID;
}
