package com.ocean.wy.magi.auth.server.dao;

import java.util.List;

import com.ocean.wy.magi.auth.server.entity.Authorization;

/**
 * <p>User: Ocean.wy
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface AuthorizationDao {

    public Authorization createAuthorization(Authorization authorization);
    public Authorization updateAuthorization(Authorization authorization);
    public void deleteAuthorization(Long authorizationId);

    public Authorization findOne(Long authorizationId);
    public List<Authorization> findAll();

    public Authorization findByAppUser(Long appId, Long userId);
}
