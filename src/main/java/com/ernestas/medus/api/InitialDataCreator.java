package com.ernestas.medus.api;

import com.ernestas.medus.entities.Account;
import com.ernestas.medus.entities.Customer;
import com.ernestas.medus.entities.CustomerRepository;
import com.ernestas.medus.entities.PhoneNumber;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitialDataCreator {

  @Autowired
  private CustomerRepository customerRepository;

  @PostConstruct
  public void createData() {

    Customer customer = Customer.builder()
        .companyCode("Zenitech")
        .companyName("Zenitech")
        .personalCode(12345678901L)
        .name("Ernestas")
        .surname("Seminogovas")
        .customerAccounts(new ArrayList<>())
        .build();

    Account account = Account.builder()
        .description("first account")
        .name("wolfas560")
        .serviceType("mobile internet")
        .phoneNumbers(new ArrayList<>())
        .customer(customer)
        .build();

    Account account2 = Account.builder()
        .description("second account")
        .name("wolfas5600")
        .serviceType("mobile internet")
        .phoneNumbers(new ArrayList<>())
        .customer(customer)
        .build();

    PhoneNumber phoneNumber = PhoneNumber.builder()
        .activeFrom(Date.from(Instant.EPOCH))
        .activeTo(Date.from(Instant.EPOCH))
        .orderedServiceList(new ArrayList<>())
        .account(account)
        .orderedServiceList(new ArrayList<>())
        .build();

    account.getPhoneNumbers().add(phoneNumber);
    customer.getCustomerAccounts().add(account);
    customer.getCustomerAccounts().add(account2);

    customerRepository.saveAndFlush(customer);

  }



}
