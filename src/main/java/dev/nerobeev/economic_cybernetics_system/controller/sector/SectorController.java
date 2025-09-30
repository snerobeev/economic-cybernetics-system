package dev.nerobeev.economic_cybernetics_system.controller.sector;

import dev.nerobeev.economic_cybernetics_system.dto.sector.SectorCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.sector.SectorResponse;
import dev.nerobeev.economic_cybernetics_system.service.sector.SectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sectors")
public class SectorController {

  private final SectorService sectorService;

  @GetMapping
  public ResponseEntity<List<SectorResponse>> getAllSectors() {
    var sectors = sectorService.getAllSectors();
    return ResponseEntity.ok().header("X-Total-Count", String.valueOf(sectors.size())).body(sectors);
  }

  @GetMapping("{id}")
  public SectorResponse getSectorById(@PathVariable Long id) {
    return sectorService.getSectorById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public SectorResponse createSector(@RequestBody @Valid SectorCreateRequest request) {
    return sectorService.createSector(request);
  }

}
