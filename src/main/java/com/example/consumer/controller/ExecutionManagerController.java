package com.example.consumer.controller;


import com.example.consumer.domain.model.LanguageTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/executionManager")
public class ExecutionManagerController {


    @GetMapping("/getLanguage")
    public List<LanguageTemplate>


}
