package com.example.metricsdemo.controllers;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class IndexController {

    @Autowired
    private MeterRegistry meterRegistry1;

    @Autowired
    private MeterRegistry meterRegistry2;

    @Autowired
    private MeterRegistry meterRegistry3;

    private String message = "Custom Metric Demo";

    @RequestMapping("/")
    public String index(Model model) {
        // Custom Metric
        meterRegistry1.counter("custom_requests_total").increment();
        meterRegistry2.counter("custom_sales_total").increment(2);
        meterRegistry3.counter("custom_balance_total").increment(10);

        model.addAttribute("greeting", message );
        model.addAttribute("counter",  meterRegistry1.counter("custom_requests_total").count() );
        model.addAttribute("sales",  meterRegistry2.counter("custom_sales_total").count() );
        model.addAttribute("balance",  meterRegistry2.counter("custom_balance_total").count() );
        return "index";
    }
}