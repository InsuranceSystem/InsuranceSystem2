package aplus.insurancesystem2.domain.customer.entity.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerID")
    private Long id;

    @Column(name = "id")
    private String customerId;
    private String address;
    private String customerName;
    private String job;
    private String pnumber;
    private String birth; // 생년월일(yyyy-mm-dd, String)
    private EGender eGender; // 성별
}