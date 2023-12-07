package aplus.insurancesystem.domain.compensationClaim.entity;

import aplus.insurancesystem.domain.contract.entity.Contract;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

/**
 * CREATE TABLE CompensationClaim (
 *   CCID BIGINT AUTO_INCREMENT PRIMARY KEY,
 *   contractID BIGINT,
 *   receptionistName VARCHAR(255),
 *   receptionistPNumber VARCHAR(255),
 *   relationshipOfContractor VARCHAR(255),
 *   documentFilePath VARCHAR(255),
 *   bank VARCHAR(255),
 *   accountNumber VARCHAR(255),
 *   accountHolderName VARCHAR(255),
 *   isSurveyed boolean,
 *   FOREIGN KEY (contractID) REFERENCES Contract(contractID)
 * );
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompensationClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CCID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractId")
    private Contract contract;

    private String receptionistName;
    private String receptionistPNumber;
    private String relationshipOfContractor;
    private String documentFilePath;
    private String bank;
    private String accountNumber;
    private String accountHolderName;
    private boolean isSurveyed;

    @Builder
    public CompensationClaim(Contract contract, String receptionistName,
                             String receptionistPNumber, String relationshipOfContractor,
                             String documentFilePath,
                             String bank, String accountNumber, String accountHolderName, boolean isSurveyed) {
        this.contract = contract;
        this.receptionistName = receptionistName;
        this.receptionistPNumber = receptionistPNumber;
        this.relationshipOfContractor = relationshipOfContractor;
        this.documentFilePath = documentFilePath;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.isSurveyed = isSurveyed;
    }
}
