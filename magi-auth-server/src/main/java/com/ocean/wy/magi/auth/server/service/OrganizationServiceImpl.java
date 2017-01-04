package com.ocean.wy.magi.auth.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocean.wy.magi.auth.server.dao.OrganizationDao;
import com.ocean.wy.magi.auth.server.entity.Organization;

import java.util.List;

/**
 * <p>User: Ocean.wy
 * <p>Date: 16-2-14
 * <p>Version: 1.0
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationDao organizationDao;

    public Organization createOrganization(Organization organization) {
        return organizationDao.createOrganization(organization);
    }

    public Organization updateOrganization(Organization organization) {
        return organizationDao.updateOrganization(organization);
    }

    public void deleteOrganization(Long organizationId) {
        organizationDao.deleteOrganization(organizationId);
    }

    public Organization findOne(Long organizationId) {
        return organizationDao.findOne(organizationId);
    }

    public List<Organization> findAll() {
        return organizationDao.findAll();
    }

    public List<Organization> findAllWithExclude(Organization excludeOraganization) {
        return organizationDao.findAllWithExclude(excludeOraganization);
    }

    public void move(Organization source, Organization target) {
        organizationDao.move(source, target);
    }
}
