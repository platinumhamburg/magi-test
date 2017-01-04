/**
 * Copyright (c) 2005-2012 https://github.com/ocean.wy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.ocean.wy.magi.auth.core.remote;

import org.apache.shiro.session.Session;

import java.io.Serializable;

/**
 * <p>User: Ocean.wy
 * <p>Date: 16-3-13
 * <p>Version: 1.0
 */
public interface RemoteServiceInterface {

    public Session getSession(String appKey, Serializable sessionId);
    Serializable createSession(Session session);
    public void updateSession(String appKey, Session session);
    public void deleteSession(String appKey, Session session);

    public PermissionContext getPermissions(String appKey, String username);
}
