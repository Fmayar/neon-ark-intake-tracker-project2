package org.example.neonarkintaketracker.controller;

import jakarta.validation.Valid;
import org.example.neonarkintaketracker.dto.CreatureRequest;
import org.example.neonarkintaketracker.dto.CreatureResponse;
import org.example.neonarkintaketracker.service.CreatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creatures")
public class CreatureController {

    private final CreatureService service;

    public CreatureController(CreatureService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CreatureResponse>> getAllCreatures() {
        return ResponseEntity.ok(service.getAllCreatures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatureResponse> getCreatureById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCreatureById(id));
    }

    @PostMapping
    public ResponseEntity<CreatureResponse> createCreature(@Valid @RequestBody CreatureRequest request) {
        CreatureResponse created = service.createCreature(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
