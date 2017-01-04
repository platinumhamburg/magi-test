package com.ocean.wy.magi.auth.server.dao;

import java.util.List;

import com.ocean.wy.magi.auth.server.entity.Organization;

/**
 * <p>Organization: Ocean.wy
 * <p>Date: 16-1-28
 * <p>Version: 1.0
 */
public interface OrganizationDao {

    public Organization createOrganization(Organization organization);
    public Organization updateOrganization(Organization organization);
    public void deleteOrganization(Long organizationId);

    Organization findOne(Long organizationId);
    List<Organization> findAll();

    List<Organization> findAllWithExclude(Organization excludeOraganization);

    void move(Organization source, Organization target);
}
