package com.mtlckj.base.system.service;

import java.util.Collection;
import java.util.List;

import javax.websocket.Session;

import org.springframework.stereotype.Service;

import com.mtlckj.base.system.domain.UserDO;
import com.mtlckj.base.system.domain.UserOnline;


@Service
public interface SessionService {
	List<UserOnline> list();

	List<UserDO> listOnlineUser();

	Collection<Session> sessionList();
	
	boolean forceLogout(String sessionId);
}
