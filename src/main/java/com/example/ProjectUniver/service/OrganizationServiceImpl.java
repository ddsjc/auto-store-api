package com.example.ProjectUniver.service;

import com.example.ProjectUniver.entity.*;
import com.example.ProjectUniver.repository.AddressRepository;
import com.example.ProjectUniver.repository.AddressTypeRepository;
import com.example.ProjectUniver.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressTypeRepository addressTypeRepository;
    @Override
    public void createOrganization(Organization organization, Address address, AddressType addressType) {
        AddressType addressType1 = addressTypeRepository.save(addressType);
        address.setAddressType(addressType1);
        address.setOrganization(organizationRepository.save(organization));
        addressRepository.save(address);
    }
}
