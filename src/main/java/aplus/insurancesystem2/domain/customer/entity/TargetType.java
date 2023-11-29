package aplus.insurancesystem2.domain.customer.entity;

import java.util.List;
import java.util.function.Function;

import aplus.insurancesystem2.domain.customer.entity.customer.Customer;
import aplus.insurancesystem2.domain.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor

public enum TargetType {
	EXPIRED_CONTRACTS("만기계약대상자", CustomerRepository::findAllExpiredContracts),
	UNPAID_CUSTOMERS("미납대상자", CustomerRepository::findAllUnpaidCustomers),
	RESURRECT_CANDIDATES("부활대상자", CustomerRepository::findAllResurrectCandidates);

	private final String name;
	private final Function<CustomerRepository, List<Customer>> function;

	public List<Customer> applyFunction(CustomerRepository customerRepository) {
		return function.apply(customerRepository);
	}
}

