package com.example.ProjectUniver.service;

import com.example.ProjectUniver.entity.*;

public interface ApplicationService {

    void createApplication(Application event, ServiceDop serviceDop);
    void save(Application event);

}
