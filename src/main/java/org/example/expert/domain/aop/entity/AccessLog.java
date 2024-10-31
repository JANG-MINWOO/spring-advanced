package org.example.expert.domain.aop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AccessLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userId;
    private LocalDateTime requestTime;
    private String requestUrl;
    private Object response;
    private String requestBody;

    public AccessLog(long userId, LocalDateTime requestTime, String requestBody, String requestUrl, Object response) {
        this.userId = userId;
        this.requestTime = requestTime;
        this.requestUrl = requestUrl;
        this.response = response;
        this.requestBody = requestBody;
    }

}
