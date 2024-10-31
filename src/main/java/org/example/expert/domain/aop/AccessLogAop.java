package org.example.expert.domain.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.example.expert.domain.aop.entity.AccessLog;
import org.example.expert.domain.aop.repository.AccessLogRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;

@Aspect
@Component
@RequiredArgsConstructor
public class AccessLogAop {
    private final AccessLogRepository accessLogRepository;
    private final HttpServletRequest httpServletRequest;

    @Pointcut("execution(* org.example.expert.domain.comment.controller.CommentAdminController.*(..))")
    private void comment() {}

    @Pointcut("execution(* org.example..expert.domain.user.controller.UserAdminController.*(..))")
    private void user() {}

    @Around("comment() || user()")
    public Object logAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        // 요청 시각
        LocalDateTime requestTime = LocalDateTime.now();
        // 요청 정보들
        long userId = getAuthenticatedUserId();
        String requestBody = getRequestBody();
        String requestUrl = httpServletRequest.getRequestURI();
        Object response = null;
        try{
            response = joinPoint.proceed(); //위에 해당하는 컨트롤러의 API 가 수행될때
            return response;
        } finally {
            String responseToJson = convertResponseToJson(response);
            AccessLog accessLog = new AccessLog(userId, requestTime, requestBody, requestUrl, responseToJson);
            accessLogRepository.save(accessLog);
        }
    }

    //필터에서 userId 불러와서 로그에 저장
    private long getAuthenticatedUserId() {
        Object userId = httpServletRequest.getAttribute("userId");
        return userId !=null ? Long.parseLong(userId.toString()) : 0;
    }

    //필터에서 Body 를 가져와서 저장
    private String getRequestBody() throws IOException {
        StringBuilder requestBody = new StringBuilder();
        BufferedReader reader = httpServletRequest.getReader();
        String line;
        while( (line = reader.readLine()) != null ) {
            requestBody.append(line);
        }
        return requestBody.toString();
    }

    //Object 인 response 를 Json 으로 변경
    private String convertResponseToJson(Object response) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            return "Json 으로 변경 실패";
        }
    }
}
