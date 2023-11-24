package aplus.insurancesystem2.domain.counsel.entity;

import java.time.LocalDate;

import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CREATE TABLE Counsel (
 *   counselID BIGINT PRIMARY KEY,
 *   customerID BIGINT,
 *   content VARCHAR(255),
 *   managerName VARCHAR(255),
 *   dateOfCounsel DATE,
 *   FOREIGN KEY (customerID) REFERENCES Customer(customerID),
 *   FOREIGN KEY (counselID) REFERENCES CounselApplication(counselID)
 * );
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Counsel {

    @Id
    @Column(name = "counselID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counselID")
    private CounselApplication counselApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    private String content;
    private String managerName;
    private LocalDate dateOfCounsel;


}
