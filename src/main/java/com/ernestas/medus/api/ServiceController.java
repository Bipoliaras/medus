package com.ernestas.medus.api;

import com.ernestas.medus.entities.Service;
import com.ernestas.medus.entities.ServiceRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/services")
public class ServiceController {

  @Autowired
  private ServiceRepository serviceRepository;

  @GetMapping
  public List<Service> getService() {
    return serviceRepository.findAll();
  }


  @PostMapping
  public void createService(@RequestBody Service service) {
      serviceRepository.save(service);
  }

  @DeleteMapping("/{id}")
  public void deleteService(@PathVariable ("id") Long id) {
    serviceRepository.deleteById(id);
  }
}
