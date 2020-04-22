package com.ernestas.medus.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "t_phone_number")
public class PhoneNumber {

  @Id
  @GeneratedValue
  @Column(name = "phone_number_id")
  private Long id;

  private Date activeFrom;

  private Date activeTo;

  @ManyToOne
  @JoinColumn(name="account_id", nullable=false)
  @JsonIgnore
  private Account account;

  @OneToMany(mappedBy = "phoneNumber")
  private List<OrderedService> orderedServiceList = new ArrayList<>();




}
