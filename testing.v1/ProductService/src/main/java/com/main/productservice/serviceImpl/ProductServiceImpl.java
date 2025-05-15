package com.main.productservice.serviceImpl;

import com.main.productservice.dto.ProductDTO;
import com.main.productservice.entity.Product;
import com.main.productservice.repository.ProductRepository;
import com.main.productservice.serice.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;
    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        logger.info("inserting product : {}", productDTO);
        return mapToProductDTO(productRepository.insert(mapToProduct(productDTO)));
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        logger.info("getting all products");
        return productRepository.findAll().stream()
                .map(this::mapToProductDTO).collect(Collectors.toList());
    }

    private Product mapToProduct(ProductDTO productDTO) {
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .build();
    }

    private ProductDTO mapToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
