package aplus.insurancesystem2.domain.counsel.entity;

import java.time.LocalDate;

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

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CounselApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counselID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerID")
    private Customer customer;

    private String category;
    private LocalDate dateOfFirst;
    private LocalDate dateOfSecond;
    private String requirement;

    public CounselApplication(Customer customer, String category, LocalDate dateOfFirst, LocalDate dateOfSecond,
                              String requirement) {
        this.customer = customer;
        this.category = category;
        this.dateOfFirst = dateOfFirst;
        this.dateOfSecond = dateOfSecond;
        this.requirement = requirement;
    }
}
