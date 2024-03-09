package com.cj.dynamicdatasource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class DataSourceController {

    @GetMapping("/changeDataSource")
    public void setDataSource(String dsName, HttpSession session){
        session.setAttribute("dsName",dsName);
    }
}
