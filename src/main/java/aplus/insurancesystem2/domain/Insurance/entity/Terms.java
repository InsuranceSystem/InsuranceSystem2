package aplus.insurancesystem2.domain.Insurance.entity;

import aplus.insurancesystem2.domain.Insurance.dto.request.termsCreateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
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
    public Terms(termsCreateRequest terms){
        this.termsID = terms.getTermsID();
        this.termsContent = terms.getTermsContent();
        this.termsName = terms.getTermsName();
        this.calculatedMoneyMethod = terms.getCalculatedMoneyMethod();
    }
}