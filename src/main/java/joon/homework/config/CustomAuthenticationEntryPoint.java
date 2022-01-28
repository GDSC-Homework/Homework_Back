package joon.homework.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import joon.homework.dto.ResponseDto;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper objectMapper = new ObjectMapper();

        response.setStatus(401);
        response.setContentType("application/json;charset=utf-8");
        ResponseDto responseDto = ResponseDto.builder()
                .status(401)
                .message("로그인이 되지 않은 사용자입니다.")
                .data(null)
                .build();

        PrintWriter out = response.getWriter();
        String jsonResponse = objectMapper.writeValueAsString(responseDto);
        out.print(jsonResponse);
    }
}