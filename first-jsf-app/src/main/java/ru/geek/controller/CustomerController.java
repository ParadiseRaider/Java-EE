package ru.geek.controller;

import lombok.Getter;
import lombok.Setter;
import ru.geek.persist.Customer;
import ru.geek.persist.CustomerRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CustomerController implements Serializable {

    @Inject
    private CustomerRepository customerRepository;

    @Getter
    @Setter
    private Customer customer;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public String editCustomer(Customer customer) {
        this.customer = customer;
        return "/customer_form.xhtml?faces-redirect=true";
    }

    public void deleteCustomer(Customer customer) {
        customerRepository.delete(customer.getId());
    }

    public String saveCustomer() {
        customerRepository.save(customer);
        return "/customer.xhtml?faces-redirect=true";
    }

    public String addCustomer() {
        this.customer = new Customer();
        return "/customer_form.xhtml?faces-redirect=true";
    }
}
