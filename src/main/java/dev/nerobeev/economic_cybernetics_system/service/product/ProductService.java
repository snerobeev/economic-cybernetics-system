package dev.nerobeev.economic_cybernetics_system.service.product;

import dev.nerobeev.economic_cybernetics_system.dto.product.ProductCreateRequest;
import dev.nerobeev.economic_cybernetics_system.dto.product.ProductResponse;
import dev.nerobeev.economic_cybernetics_system.exeption.ProductNotFoundException;
import dev.nerobeev.economic_cybernetics_system.exeption.SectorNotFoundException;
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
        var sectorId = request.sector_id();
        var sector = sectorRepository.findById(sectorId)
                .orElseThrow(() -> new SectorNotFoundException(sectorId));
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
                .orElseThrow(() -> new ProductNotFoundException(id));
        return productMapper.toResponse(product);
    }
}
