package org.example.expert.domain.aop.repository;

import org.example.expert.domain.aop.entity.AccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {
}
