package ru.geek.controller;

import lombok.Getter;
import lombok.Setter;
import ru.geek.persist.Customer;
import ru.geek.persist.CustomerRepository;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CustomerController implements Serializable {

    @EJB
    private CustomerRepository customerRepository;

    @Getter
    @Setter
    private Customer customer;

    private List<Customer> customerList;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        this.customerList = customerRepository.findAll();
    }

    public List<Customer> findAll() {
        return customerList;
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
