package aplus.insurancesystem.domain.Insurance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Terms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "termsID")
    private Long termsID;
    private String termsName;
    private String calculatedMoneyMethod;
    private String termsContent;

    @Builder
    public Terms(String termsName, String calculatedMoneyMethod, String termsContent) {
        this.termsName = termsName;
        this.calculatedMoneyMethod = calculatedMoneyMethod;
        this.termsContent = termsContent;
    }
}
