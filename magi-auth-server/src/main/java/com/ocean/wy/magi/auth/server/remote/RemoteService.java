package com.ocean.wy.magi.auth.server.remote;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import com.ocean.wy.magi.auth.core.remote.PermissionContext;
import com.ocean.wy.magi.auth.core.remote.RemoteServiceInterface;
import com.ocean.wy.magi.auth.server.service.AuthorizationService;

import java.io.Serializable;

/**
 * <p>User: Ocean.wy
 * <p>Date: 14-3-13
 * <p>Version: 1.0
 */
public class RemoteService implements RemoteServiceInterface {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private SessionDAO sessionDAO;

	public Session getSession(String appKey, Serializable sessionId) {
		// TODO Auto-generated method stub
		return sessionDAO.readSession(sessionId);
	}

	public Serializable createSession(Session session) {
		// TODO Auto-generated method stub
		return sessionDAO.create(session);
	}

	public void updateSession(String appKey, Session session) {
		// TODO Auto-generated method stub
		sessionDAO.update(session);
	}

	public void deleteSession(String appKey, Session session) {
		// TODO Auto-generated method stub
		sessionDAO.delete(session);
	}

	public PermissionContext getPermissions(String appKey, String username) {
		// TODO Auto-generated method stub
		PermissionContext permissionContext = new PermissionContext();
        permissionContext.setRoles(authorizationService.findRoles(appKey, username));
        permissionContext.setPermissions(authorizationService.findPermissions(appKey, username));
        return permissionContext;
	}

    
}
