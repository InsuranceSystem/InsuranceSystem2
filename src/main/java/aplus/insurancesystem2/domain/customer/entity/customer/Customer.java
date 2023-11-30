package aplus.insurancesystem2.domain.customer.entity.customer;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deletedAt is null")
@SQLDelete(sql = "UPDATE Customer SET deletedAt = current_timestamp WHERE customerID = ?")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    private Long id;
    private String loginId;
    private String address;
    private String customerName;
    private String job;
    private String pnumber;
    private String birth; // 생년월일(yyyy-mm-dd, String)
//    @Enumerated(EnumType.STRING)
    private EGender eGender; // 성별
    private LocalDateTime deletedAt;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Customer(String loginId, String address, String customerName, String job, String pnumber,
                    String password, String birth, EGender eGender) {
        this.loginId = loginId;
        this.address = address;
        this.customerName = customerName;
        this.job = job;
        this.pnumber = pnumber;
        this.password = password;
        this.birth = birth;
        this.eGender = eGender;
        this.deletedAt = null;
        this.role = Role.CUSTOMER;
    }
}
