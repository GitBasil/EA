package bank.DTO.adapters;

import bank.DTO.CustomerDTO;
import bank.domain.Customer;

public class CustomerAdapter {
    public static Customer getCustomerFromCustomerDTO(CustomerDTO customerDTO) {
        return new Customer(customerDTO.id(),customerDTO.name());
    }

    public static CustomerDTO getCustomerDTOFromCustomer(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName());
    }
}