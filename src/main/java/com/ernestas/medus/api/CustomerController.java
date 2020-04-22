package com.ernestas.medus.api;

import com.ernestas.medus.entities.Account;
import com.ernestas.medus.entities.Customer;
import com.ernestas.medus.services.CustomerService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  private CustomerService customerService;

  @GetMapping
  public List<Customer> getCustomers() {
    return customerService.getAllCustomers();
  }

  @GetMapping("/{customerId}")
  public Customer getCustomer(@PathVariable("customerId") Long customerId) {
     return customerService.getCustomerById(customerId);
  }

  @PostMapping
  public void createCustomer(@RequestBody @Valid Customer customer) {
    customerService.createCustomer(customer);
  }

  @PutMapping("/{customerId}")
  public void updateCustomer(@PathVariable("customerId") Long customerId, @Valid @RequestBody Customer customer) {
    customerService.updateCustomer(customerId, customer);
  }

  @PostMapping("/{customerId}/accounts")
  public void addAccount(@PathVariable("customerId") Long customerId, @Valid @RequestBody Account account) {
    customerService.addAccount(customerId, account);
  }

  @DeleteMapping("/{customerId}/accounts/{accountId}")
  public void deleteAccount(@PathVariable("customerId") Long customerId, @PathVariable("accountId") Long accountId) {
    customerService.deleteAccount(customerId, accountId);
  }


  @DeleteMapping("/{customerId}")
  public void deleteCustomer(@PathVariable("customerId") Long customerId) {
    customerService.deleteCustomerById(customerId);

  }

  @Autowired
  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }
}
