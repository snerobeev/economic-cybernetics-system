package dev.nerobeev.economic_cybernetics_system.service.product;

import dev.nerobeev.economic_cybernetics_system.domain.newv.markerator.MarkingGenerator;
import dev.nerobeev.economic_cybernetics_system.domain.newv.markerator.MarkingType;
import dev.nerobeev.economic_cybernetics_system.dto.product.ProductCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.product.ProductResponse;
import dev.nerobeev.economic_cybernetics_system.exeption.ProductNotFoundException;
import dev.nerobeev.economic_cybernetics_system.mapper.ProductMapper;
import dev.nerobeev.economic_cybernetics_system.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductMapper productMapper;
  private final MarkingGenerator markingGenerator;

  public ProductResponse createProduct(ProductCreateRequest request) {

    var product = productMapper.toEntity(request, markingGenerator);
    product.setUCode(markingGenerator.generate(MarkingType.PRODUCT));
    var savedProduct = productRepository.save(product);

    return productMapper.toResponse(savedProduct);

  }

  public List<ProductResponse> getAllProducts() {
    return productRepository.findAll().stream()
                            .map(productMapper::toResponse)
                            .toList();
  }

  public ProductResponse getProductById(Long id) {
    var product = productRepository.findById(id)
                                   .orElseThrow(() -> new ProductNotFoundException(id));
    return productMapper.toResponse(product);
  }
}
