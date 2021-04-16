package ru.geek.service;

import ru.geek.persist.CategoryRepository;
import ru.geek.persist.Product;
import ru.geek.persist.ProductRepository;
import ru.geek.service.dto.ProductDto;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
public class ProductServiceImpl implements ProductService, ProductServiceRemote {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    @TransactionAttribute
    public void save(ProductDto productDto) {
        productRepository.save(new Product(productDto.getId(),
                productDto.getName(), productDto.getDescription(),
                productDto.getPrice(),
                categoryRepository.getReference(productDto.getCategoryId())
        ));
    }

    @Override
    @TransactionAttribute
    public void delete(Long id) {
        productRepository.delete(id);
    }

    @Override
    public ProductDto findById(Long id) {
        return createProductDtoWithCategory(productRepository.findById(id));
    }

    @Override
    public List<ProductDto> findAll() {
        return productRepository.findAll().stream()
                .map(ProductServiceImpl::createProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> findAllWithCategoryFetch() {
        return productRepository.findAllWithCategoryFetch().stream()
                .map(ProductServiceImpl::createProductDtoWithCategory)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public List<ProductDto> findAllRemote() {
        return findAllWithCategoryFetch();
    }

    public static ProductDto createProductDtoWithCategory(Product product) {
        return new ProductDto(product.getId(), product.getName(),
                product.getDescription(), product.getPrice(),
                product.getCategory() != null ? product.getCategory().getId() : null,
                product.getCategory() != null ? product.getCategory().getName() : null
        );
    }

    public static ProductDto createProductDto(Product product) {
        return new ProductDto(product.getId(), product.getName(),
                product.getDescription(), product.getPrice(),
                null, null
        );
    }
}
