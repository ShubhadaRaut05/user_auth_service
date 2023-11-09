package com.shubhada.user_authorization_service.repositories;

import com.shubhada.user_authorization_service.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {

    boolean existsByToken(String token);
}
