package org.example.expert.domain.aop.dto;

import lombok.Data;

@Data
public class AccessLogDto {
    private long userId;
    private long timestamp;
    private String requestUrl;
    private Object response;
    private String requestBody;
}
