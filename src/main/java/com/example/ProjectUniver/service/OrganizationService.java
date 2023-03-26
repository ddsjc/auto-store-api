package com.example.ProjectUniver.service;

import com.example.ProjectUniver.entity.Address;
import com.example.ProjectUniver.entity.AddressType;
import com.example.ProjectUniver.entity.Organization;

public interface OrganizationService {
    void createOrganization(Organization organization, Address address, AddressType addressType);
    public Organization findOrganizationByLogin(String login);
}
