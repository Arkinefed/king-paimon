package com.arkinefed.kingpaimonrest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class X {
    @GetMapping("/a")
    public ResponseEntity<?> x() {
        return ResponseEntity.ok("x");
    }
}
