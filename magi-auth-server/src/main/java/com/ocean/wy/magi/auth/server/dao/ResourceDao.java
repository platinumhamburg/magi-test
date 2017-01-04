package com.ocean.wy.magi.auth.server.dao;

import java.util.List;

import com.ocean.wy.magi.auth.server.entity.Resource;

/**
 * <p>Resource: Ocean.wy
 * <p>Date: 16-1-28
 * <p>Version: 1.0
 */
public interface ResourceDao {

    public Resource createResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(Long resourceId);

    Resource findOne(Long resourceId);
    List<Resource> findAll();

}
