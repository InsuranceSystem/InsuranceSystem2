package aplus.insurancesystem2.domain.compensationClaim.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CREATE TABLE CarAccident (
 *   CCID BIGINT PRIMARY KEY,
 *   type VARCHAR(255),
 *   dateTime DATETIME,
 *   place VARCHAR(255),
 *   carNumber VARCHAR(255),
 *   driverName VARCHAR(255),
 *   licenseNumber VARCHAR(255),
 *   accidentDetail VARCHAR(255),
 *   FOREIGN KEY (CCID) REFERENCES CompensationClaim(CCID)
 *   on update cascade
 * );
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarAccident {

    @Id
    @Column(name = "CCID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CCID")
    private CompensationClaim compensationClaim;

    private String type;
    private LocalDateTime dateTime;
    private String place;
    private String carNumber;
    private String driverName;
    private String licenseNumber;
    private String accidentDetail;

    public CarAccident(CompensationClaim compensationClaim, String type, LocalDateTime dateTime, String place,
                       String carNumber, String driverName, String licenseNumber, String accidentDetail) {
        this.compensationClaim = compensationClaim;
        this.type = type;
        this.dateTime = dateTime;
        this.place = place;
        this.carNumber = carNumber;
        this.driverName = driverName;
        this.licenseNumber = licenseNumber;
        this.accidentDetail = accidentDetail;
    }
}
