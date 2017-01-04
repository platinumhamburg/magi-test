package com.ocean.wy.magi.auth.server.service;

import java.util.List;

import com.ocean.wy.magi.auth.server.entity.Organization;

/**
 * <p>User: Ocean.wy
 * <p>Date: 16-1-28
 * <p>Version: 1.0
 */
public interface OrganizationService {


    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    Object findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
