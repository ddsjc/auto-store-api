package com.example.ProjectUniver.service;

import com.example.ProjectUniver.entity.Address;
import com.example.ProjectUniver.entity.AddressType;
import com.example.ProjectUniver.entity.Application;
import com.example.ProjectUniver.entity.ServiceDop;
import com.example.ProjectUniver.repository.AddressRepository;
import com.example.ProjectUniver.repository.AddressTypeRepository;
import com.example.ProjectUniver.repository.ApplicationRepository;
import com.example.ProjectUniver.repository.ServiceDopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ServiceDopRepository serviceDopRepository;
    @Override
    public void createApplication(Application event, ServiceDop serviceDop) {
        //event.setCurrentDate(LocalDate.now());
        ServiceDop serviceDop1 = serviceDopRepository.save(serviceDop);
        Set<ServiceDop>serviceDopSet= new HashSet<>();
        serviceDopSet.add(serviceDop1);
        event.setServiceDop(serviceDopSet);
       // AddressType addressType1 = addressTypeRepository.save(addressType);
        //address.setAddressType(addressType1);
       // Address address1 = addressRepository.save(address);
        Application savedEvent = applicationRepository.save(event);
    }

    @Override
    public void save(Application event) {
            applicationRepository.save(event);
    }

}
