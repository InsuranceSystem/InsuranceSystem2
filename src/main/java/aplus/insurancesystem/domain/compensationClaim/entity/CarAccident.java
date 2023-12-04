package aplus.insurancesystem.domain.compensationClaim.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CCID")
    private CompensationClaim compensationClaim;

    private String type;
    private LocalDateTime dateTime;
    private String place;
    private String carNumber;
    private String driverName;
    private String licenseNumber;
    private String accidentDetail;

    @Builder
    public CarAccident(Long id,CompensationClaim compensationClaim, String type, LocalDateTime dateTime, String place,
                       String carNumber, String driverName, String licenseNumber, String accidentDetail) {
        this.id = id;
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
