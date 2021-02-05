package com.example.metricsdemo.controllers;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {

    @Autowired
    private MeterRegistry meterRegistry;

    private String message = "Custom Metric Demo";

    @RequestMapping("/")
    public String index(Model model) {
        // Custom Metric
        meterRegistry.counter("custom_requests_total", "app_name", "Spring App", "app_env", "staging");
        meterRegistry.counter("custom_requests_total").increment();

        model.addAttribute("greeting", message );
        model.addAttribute("counter",  meterRegistry.counter("custom_requests_total").count() );
        return "index";
    }
}