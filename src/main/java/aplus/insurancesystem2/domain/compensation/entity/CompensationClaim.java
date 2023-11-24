package aplus.insurancesystem2.domain.compensation.entity;

import aplus.insurancesystem2.domain.Insurance.entity.Insurance;
import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CREATE TABLE CompensationClaim (
 *   CCID BIGINT AUTO_INCREMENT PRIMARY KEY,
 *   insuranceID BIGINT,
 *   customerID BIGINT,
 *   receptionistName VARCHAR(255),
 *   receptionistPNumber VARCHAR(255),
 *   relationshipOfContractor VARCHAR(255),
 *   documentFilePath VARCHAR(255),
 *   bank VARCHAR(255),
 *   accountNumber VARCHAR(255),
 *   accountHolderName VARCHAR(255),
 *   FOREIGN KEY (customerID) REFERENCES Customer(customerID),
 *   FOREIGN KEY (insuranceID) REFERENCES Insurance(insuranceID)
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
    @JoinColumn(name = "insuranceID")
    private Insurance insurance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    private String receptionistName;
    private String receptionistPNumber;
    private String relationshipOfContractor;
    private String documentFilePath;
    private String bank;
    private String accountNumber;
    private String accountHolderName;

    public CompensationClaim(Insurance insurance, Customer customer, String receptionistName,
                             String receptionistPNumber, String relationshipOfContractor,
                             String documentFilePath,
                             String bank, String accountNumber, String accountHolderName) {
        this.insurance = insurance;
        this.customer = customer;
        this.receptionistName = receptionistName;
        this.receptionistPNumber = receptionistPNumber;
        this.relationshipOfContractor = relationshipOfContractor;
        this.documentFilePath = documentFilePath;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
    }
}
