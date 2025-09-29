package dev.nerobeev.economic_cybernetics_system.service.economicsector;

import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.EconomicSectorCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.EconomicSectorResponse;
import dev.nerobeev.economic_cybernetics_system.mapper.EconomicSectorMapper;
import dev.nerobeev.economic_cybernetics_system.repository.EconomicSectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EconomicSectorService {

    private final EconomicSectorRepository sectorRepository;
    private final EconomicSectorMapper sectorMapper;

    public EconomicSectorResponse createSector(EconomicSectorCreateRequest request) {
        var sector = sectorMapper.toEntity(request);
        var savedSector = sectorRepository.save(sector);
        return sectorMapper.toResponse(savedSector);
    }
}
