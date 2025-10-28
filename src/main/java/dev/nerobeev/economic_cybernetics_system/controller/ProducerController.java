package dev.nerobeev.economic_cybernetics_system.controller;

import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.material.MaterialResponse;
import dev.nerobeev.economic_cybernetics_system.dto.producer.ProducerCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.producer.ProducerResponse;
import dev.nerobeev.economic_cybernetics_system.service.ProducerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/producers")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService producerService;

    @GetMapping
    public ResponseEntity<List<ProducerResponse>> getAllProducers() {
        var producers = producerService.getAllProducers();
        return ResponseEntity.ok()
                .header("X-Total-Count", String.valueOf(producers.size())).body(producers);
    }

    @GetMapping("/{id}")
    public ProducerResponse getProducerById(@PathVariable Long id) {
        return producerService.getProducerById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProducerResponse createProducer(@RequestBody @Valid ProducerCreateRequest request) {
        return producerService.createProducer(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProducer(@PathVariable Long id) {
        producerService.deleteProducer(id);
    }

}
