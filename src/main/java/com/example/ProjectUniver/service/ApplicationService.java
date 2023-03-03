package com.example.ProjectUniver.service;

import com.example.ProjectUniver.entity.Address;
import com.example.ProjectUniver.entity.AddressType;
import com.example.ProjectUniver.entity.Application;
import com.example.ProjectUniver.entity.ServiceDop;

public interface ApplicationService {

    void createApplication(Application event, ServiceDop serviceDop);
    void save(Application event);

}
