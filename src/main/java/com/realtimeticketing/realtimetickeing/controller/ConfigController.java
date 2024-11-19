package com.realtimeticketing.realtimetickeing.controller;

import com.realtimeticketing.realtimetickeing.model.SystemConfig;
import com.realtimeticketing.realtimetickeing.repository.SystemConfigRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
@CrossOrigin(origins = "http://localhost:5173")
public class ConfigController {
    private final SystemConfigRepository configRepository;

    public ConfigController(SystemConfigRepository configRepository) {
        this.configRepository = configRepository;
    }

    @PostMapping
    public SystemConfig saveConfig(@RequestBody SystemConfig config) {
        return configRepository.save(config);
    }

    @GetMapping
    public SystemConfig getConfig() {
        return configRepository.findAll().stream().findFirst().orElse(null);
    }
}
