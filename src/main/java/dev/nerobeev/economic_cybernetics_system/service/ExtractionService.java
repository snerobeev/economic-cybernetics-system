package dev.nerobeev.economic_cybernetics_system.service;

import dev.nerobeev.economic_cybernetics_system.dto.extraction.ExtractionCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.extraction.ExtractionResponse;
import dev.nerobeev.economic_cybernetics_system.mapper.ExtractionMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class ExtractionService {

  private final ExtractionMapper extractionMapper;

  public ExtractionResponse createExtraction(ExtractionCreateRequest request) {
    return null;
  }

}
