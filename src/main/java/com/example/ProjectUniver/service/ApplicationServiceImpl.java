package com.example.ProjectUniver.service;

import com.example.ProjectUniver.entity.Application;
import com.example.ProjectUniver.entity.ServiceDop;
import com.example.ProjectUniver.repository.ApplicationRepository;
import com.example.ProjectUniver.repository.ServiceDopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<ServiceDop> serviceDops = new ArrayList();
        ServiceDop serviceDop1 = serviceDopRepository.save(serviceDop);
        serviceDops.add(serviceDop1);
        event.setServiceDops(serviceDops);
        Application savedEvent = applicationRepository.save(event);
    }

    @Override
    public void save(Application event) {
            applicationRepository.save(event);
    }

}
