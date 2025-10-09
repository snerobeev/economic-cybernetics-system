package dev.nerobeev.economic_cybernetics_system.controller;

import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.component.ComponentResponse;
import dev.nerobeev.economic_cybernetics_system.service.ComponentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/components")
@RequiredArgsConstructor
public class ComponentController {

  private final ComponentService componentService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ComponentResponse createComponent(@RequestBody @Valid ComponentCreateRequest request) {
    return componentService.createComponent(request);
  }

  @GetMapping
  public ResponseEntity<List<ComponentResponse>> getAllComponents() {
    var components = componentService.getAllComponents();
    return ResponseEntity.ok().header(
        "X-Total-Count",
        String.valueOf(components.size())
    ).body(components);
  }


}
