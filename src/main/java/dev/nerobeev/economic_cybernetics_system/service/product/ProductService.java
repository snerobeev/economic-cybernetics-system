package dev.nerobeev.economic_cybernetics_system.service.product;

import dev.nerobeev.economic_cybernetics_system.dto.product.ProductCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.product.ProductResponse;
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

  public ProductResponse createProduct(ProductCreateRequest request) {
    var product = productMapper.toEntity(request);
    var savedProduct = productRepository.save(product);
    return productMapper.toResponse(savedProduct);
  }

  public List<ProductResponse> getAllProducts() {
    return productRepository.findAll().stream()
                            .map(productMapper::toResponse)
                            .toList();
  }
}
