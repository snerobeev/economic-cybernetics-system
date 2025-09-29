package dev.nerobeev.economic_cybernetics_system.controller.economicsector;

import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.EconomicSectorCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.EconomicSectorResponse;
import dev.nerobeev.economic_cybernetics_system.service.economicsector.EconomicSectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sectors")
public class EconomicSectorController {

    private final EconomicSectorService sectorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EconomicSectorResponse createSector(@RequestBody @Valid EconomicSectorCreateRequest request) {
        return sectorService.createSector(request);
    }

}
