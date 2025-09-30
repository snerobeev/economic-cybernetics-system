package dev.nerobeev.economic_cybernetics_system.service.sector;

import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.SectorCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.economic_sector.SectorResponse;
import dev.nerobeev.economic_cybernetics_system.exeption.SectorNotFoundExeption;
import dev.nerobeev.economic_cybernetics_system.mapper.SectorMapper;
import dev.nerobeev.economic_cybernetics_system.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SectorService {

    private final SectorRepository sectorRepository;
    private final SectorMapper sectorMapper;

    public List<SectorResponse> getAllSectors(){
      return sectorRepository.findAll().stream()
          .map(sectorMapper::toResponse)
          .toList();
    }

    public SectorResponse getSectorById(Long id) {
      var sector = sectorRepository.findById(id)
          .orElseThrow(() -> new SectorNotFoundExeption(id));
      return sectorMapper.toResponse(sector);
    }

    public SectorResponse createSector(SectorCreateRequest request) {
        var sector = sectorMapper.toEntity(request);
        var savedSector = sectorRepository.save(sector);
        return sectorMapper.toResponse(savedSector);
    }
}
