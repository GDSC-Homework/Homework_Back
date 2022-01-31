package joon.homework.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import joon.homework.dto.google.UserInfoDto;
import joon.homework.exception.IdTokenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoogleService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public UserInfoDto getUserInfoByIdToken(String idToken) {
        final String tokenInfoUri = "https://oauth2.googleapis.com/tokeninfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id_token", idToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(tokenInfoUri, request, String.class);

            UserInfoDto userInfoDto = objectMapper.readValue(response.getBody(), UserInfoDto.class);

            return userInfoDto;
        } catch (RestClientException | JsonProcessingException e) {
            log.error(e.getMessage());

            throw new IdTokenException();
        }
    }
}
