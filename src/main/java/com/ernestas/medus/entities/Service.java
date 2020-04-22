package com.ernestas.medus.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "t_service")
public class Service {

  @Id
  @GeneratedValue
  private Long id;

  private String name;
  private String description;

  @Enumerated(value = EnumType.STRING)
  private ServiceType serviceType;


}
