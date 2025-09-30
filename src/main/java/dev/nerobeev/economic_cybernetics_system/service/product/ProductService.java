package dev.nerobeev.economic_cybernetics_system.service.product;

import dev.nerobeev.economic_cybernetics_system.dto.product.ProductCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.product.ProductResponse;
import dev.nerobeev.economic_cybernetics_system.exeption.ProductNotFoundExeption;
import dev.nerobeev.economic_cybernetics_system.mapper.ProductMapper;
import dev.nerobeev.economic_cybernetics_system.repository.ProductRepository;
import dev.nerobeev.economic_cybernetics_system.repository.SectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SectorRepository sectorRepository;

    public ProductResponse createProduct(ProductCreateRequest request) {

        var product = productMapper.toEntity(request);
        var sector = sectorRepository.findById(request.sector_id())
                .orElseThrow(() -> new ProductNotFoundExeption(product.getId()));
        product.setSector(sector);
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
                .orElseThrow(() -> new ProductNotFoundExeption(id));
        return productMapper.toResponse(product);
    }
}
