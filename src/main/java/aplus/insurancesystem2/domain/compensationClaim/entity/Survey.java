package aplus.insurancesystem2.domain.compensationClaim.entity;

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
 * CREATE TABLE Survey (
 *   CCID BIGINT PRIMARY KEY,
 *   managerName VARCHAR(255),
 *   reportFilePath VARCHAR(255),
 *   surveyFee INT,
 *   decisionMoney INT,
 *   responsibility BOOL,
 *   responsibilityReason VARCHAR(255),
 *   FOREIGN KEY (CCID) REFERENCES CompensationClaim(CCID)
 * );
 */

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Survey {

    @Id
    @Column(name = "CCID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CCID")
    private CompensationClaim compensationClaim;

    private String managerName;
    private String reportFilePath;
    private Integer surveyFee;
    private Integer decisionMoney;
    private Boolean responsibility;
    private String responsibilityReason;




}
