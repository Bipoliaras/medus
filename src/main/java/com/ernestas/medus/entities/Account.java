package com.ernestas.medus.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "t_account")
public class Account {

  @Id
  @GeneratedValue
  @Column(name = "account_id")
  private Long id;

  @ManyToOne
  @JoinColumn(name="customer_id", nullable=false)
  @JsonIgnore
  private Customer customer;

  @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
  private List<PhoneNumber> phoneNumbers = new ArrayList<>();

  private String name;
  private String serviceType;
  private String description;

}
