package com.ernestas.medus.services;

import com.ernestas.medus.entities.Account;
import com.ernestas.medus.entities.AccountRepository;
import com.ernestas.medus.entities.Customer;
import com.ernestas.medus.entities.CustomerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {


  private CustomerRepository customerRepository;

  private AccountRepository accountRepository;

  public void createCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  public Customer getCustomerById(Long id) {
    return customerRepository.findById(id).get();
  }

  public void updateCustomer(Long id, Customer customer) {

    Customer currentCustomer = customerRepository.findById(id).get();

    currentCustomer.setCompanyCode(customer.getCompanyCode());
    currentCustomer.setCompanyName(customer.getCompanyName());
    currentCustomer.setName(customer.getName());
    currentCustomer.setSurname(customer.getSurname());
    currentCustomer.setPersonalCode(customer.getPersonalCode());

    customerRepository.save(currentCustomer);
  }

  public void addAccount(Long id, Account account) {
    Customer customer = customerRepository.findById(id).get();
    customer.getCustomerAccounts().add(account);
    customerRepository.save(customer);
  }

  public void deleteAccount(Long id, Long accountId) {

    Account account = accountRepository.findById(accountId).get();

    Customer customer = customerRepository.findById(id).get();

    if(customer.getCustomerAccounts().contains(account)) {
      accountRepository.delete(account);
    }

  }

  public void deleteCustomerById(Long id) {
    customerRepository.deleteById(id);
  }

  @Autowired
  public void setCustomerRepository(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public AccountRepository getAccountRepository() {
    return accountRepository;
  }

  @Autowired
  public void setAccountRepository(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }
}
