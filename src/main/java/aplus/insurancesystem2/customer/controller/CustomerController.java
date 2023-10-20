package aplus.insurancesystem2.customer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aplus.insurancesystem2.customer.dto.response.CustomerInfoResponse;
import aplus.insurancesystem2.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{id}")
    public CustomerInfoResponse getCustomer(@PathVariable("id") String userId) {
        return customerService.getCustomerInfo(userId);
    }
}
