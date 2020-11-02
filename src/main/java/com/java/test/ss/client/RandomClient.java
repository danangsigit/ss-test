package com.java.test.ss.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "random-client", url = "https://randomuser.me/")
public interface RandomClient {
    @RequestMapping(method = RequestMethod.GET, value = "api")
    ResponseEntity<Object> getRandom();
}
