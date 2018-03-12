package com.example.eshop.repository;

import com.example.eshop.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<UserSession, Integer> {

    UserSession getBySessionId(String sessionId);

}
