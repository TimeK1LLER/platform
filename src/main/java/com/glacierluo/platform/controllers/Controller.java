package com.glacierluo.platform.controllers;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@RestController
public class Controller {

    @RequestMapping("/test")
    public String test(){
        RestTemplate template = new RestTemplate();
//        String response = template.getForObject("https://www.baidu.com", String.class);
        template.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return template.getForObject("http://www.baidu.com", String.class);
    }
}
