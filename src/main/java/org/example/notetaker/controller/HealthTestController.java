package org.example.notetaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/healthest")
public class HealthTestController {
    @GetMapping
    public String healthTest() {
        return "Note Controller app run Successfully";
    }
}
