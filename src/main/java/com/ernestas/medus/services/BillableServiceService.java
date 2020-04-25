package com.ernestas.medus.services;

import com.ernestas.medus.entities.orderedservice.OrderedServiceRepository;
import com.ernestas.medus.entities.service.BillableService;
import com.ernestas.medus.entities.service.BillableServiceRepository;
import com.ernestas.medus.error.BadRequestException;
import com.ernestas.medus.error.NotFoundException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillableServiceService {

  private BillableServiceRepository billableServiceRepository;

  private OrderedServiceRepository orderedServiceRepository;

  @Autowired
  public BillableServiceService(BillableServiceRepository billableServiceRepository,
      OrderedServiceRepository orderedServiceRepository) {
    this.billableServiceRepository = billableServiceRepository;
    this.orderedServiceRepository = orderedServiceRepository;
  }

  public List<BillableService> getAllBillableServices() {
    return billableServiceRepository.findAll();
  }

  @Transactional
  public void createBillableService(BillableService billableService) {
    billableServiceRepository.saveAndFlush(billableService);
  }

  public void updateBillableService(Long serviceId, BillableService billableService) {

    BillableService currentService = billableServiceRepository.findById(serviceId)
        .orElseThrow(() -> new NotFoundException("Billable service not found"));

    if (orderedServiceRepository.findAllByBillableService_Id(serviceId).isEmpty()) {
      currentService.setName(billableService.getName());
      currentService.setDescription(billableService.getDescription());
      currentService.setBillableServiceType(billableService.getBillableServiceType());
      billableServiceRepository.saveAndFlush(currentService);
    } else {
      throw new BadRequestException("Service cannot be updated if it is currently ordered");
    }
  }


  public void deleteBillableServiceById(Long serviceId) {
    if (orderedServiceRepository.findAllByBillableService_Id(serviceId).isEmpty()) {
      billableServiceRepository.deleteById(serviceId);
    } else {
      throw new BadRequestException("Service cannot be deleted if it is currently ordered");
    }

  }

  public BillableService getBillableServiceById(Long id) {
    return billableServiceRepository.findById(id).orElseThrow(() ->
        new NotFoundException("Billable service not found"));
  }


}
