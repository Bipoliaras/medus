package com.ernestas.medus.api;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.ernestas.medus.entities.Account;
import com.ernestas.medus.entities.AccountRepository;
import com.ernestas.medus.entities.PhoneNumber;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  @Autowired
  private AccountRepository accountRepository;

  @GetMapping
  public List<Account> getAccounts() {
    return accountRepository.findAll();
  }

  @GetMapping("/{id}")
  public EntityModel<Account> getAccountById(@PathVariable ("id") Long id) {

    return new EntityModel<>(accountRepository.findById(id).get(),
        linkTo(methodOn(AccountController.class).getAccountById(id)).withSelfRel(),
        linkTo(methodOn(AccountController.class).getAccounts()).withRel("accounts"));
  }

  @PostMapping("/{id}/phoneNumber")
  public void addPhoneNumber(@PathVariable ("id") Long id, @RequestBody PhoneNumber phoneNumber) {

  }

  @DeleteMapping("/{id}/phoneNumber/{id}")
  public void deletePhoneNumber(@PathVariable ("id") Long id, @PathVariable ("id") Long phoneId) {

  }




}


