package dev.nerobeev.economic_cybernetics_system.dto.material;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

@Getter
@Setter
public class MaterialUpdateRequest {
  private JsonNullable<String> name;
  private JsonNullable<String> code;
  private JsonNullable<Long> costPerUnit;
  private JsonNullable<Long> pricePerUnit;
}
