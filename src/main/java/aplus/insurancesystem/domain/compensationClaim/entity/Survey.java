package aplus.insurancesystem.domain.compensationClaim.entity;

import jakarta.persistence.*;
import lombok.*;

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


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CCID")
    private CompensationClaim compensationClaim;

    private String managerName;
    private String reportFilePath;
    private Integer surveyFee;
    private Integer decisionMoney;
    private Boolean responsibility;
    private String responsibilityReason;

    @Builder
    public Survey(Long id, CompensationClaim compensationClaim, String managerName, String reportFilePath, Integer surveyFee,
                       Integer decisionMoney, Boolean responsibility, String responsibilityReason) {
        this.id = id;
        this.compensationClaim = compensationClaim;
        this.managerName = managerName;
        this.reportFilePath = reportFilePath;
        this.surveyFee = surveyFee;
        this.decisionMoney = decisionMoney;
        this.responsibility = responsibility;
        this.responsibilityReason = responsibilityReason;
    }
}
