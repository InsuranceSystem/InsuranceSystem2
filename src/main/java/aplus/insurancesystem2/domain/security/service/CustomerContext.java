package aplus.insurancesystem2.domain.security.service;

import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomerContext extends User {

    private Customer customer;

    public CustomerContext(Customer customer, Collection<? extends GrantedAuthority> authorities) {
        super(customer.getCustomerId(), customer.getPassword(), authorities);
        this.customer = customer;
    }

    public Customer getAccount() {
        return this.customer;
    }
}
