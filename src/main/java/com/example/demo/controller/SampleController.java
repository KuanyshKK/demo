package com.example.demo.controller;

import com.example.demo.model.Sample;
import com.example.demo.repository.SampleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.In;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/samples")
public class SampleController {

    private final SampleRepository repository;

    @GetMapping(  "/status")
    public ResponseEntity<List<Sample>> getAllByStatus(
            @Spec(path = "status", params = "statuses", spec = In.class) Specification<Sample> spec) {
        return ResponseEntity.ok(repository.findAll(spec));
    }

    @GetMapping()
    public ResponseEntity<List<Sample>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}
