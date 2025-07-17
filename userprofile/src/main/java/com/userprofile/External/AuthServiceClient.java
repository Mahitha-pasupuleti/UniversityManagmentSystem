package com.userprofile.External;

import com.userprofile.Response.AuthUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpHeaders;

@Component
@FeignClient(name = "auth-client", url = "http://localhost:8081/api/v1/university")
public interface AuthServiceClient {

    @GetMapping("/username")
    AuthUserResponse getUserByUsername(@RequestHeader("Authorization") String token);



}
