package aplus.insurancesystem2.domain.customer.entity;

import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import jakarta.persistence.Entity;
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
 * CREATE TABLE FamilyHistory(
 *   id BIGINT AUTO_INCREMENT PRIMARY KEY,
 *   customerID BIGINT,
 *   diseaseName VARCHAR(255),
 *   relationship VARCHAR(255),
 *   FOREIGN KEY (customerID) REFERENCES Customer(customerID)
 * );
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FamilyHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;

    private String diseaseName;
    private String relationship;

    public FamilyHistory(Customer customer, String diseaseName, String relationship) {
        this.customer = customer;
        this.diseaseName = diseaseName;
        this.relationship = relationship;
    }
}
