package com.example.ProjectUniver.service;

import com.example.ProjectUniver.entity.*;

public interface ApplicationService {

    void createApplication(Application event, ServiceDop serviceDop, User user);
    void save(Application event);

}
