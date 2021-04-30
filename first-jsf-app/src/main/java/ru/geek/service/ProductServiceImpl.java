package ru.geek.service;

import ru.geek.persist.Category;
import ru.geek.persist.CategoryRepository;
import ru.geek.persist.Product;
import ru.geek.persist.ProductRepository;
import ru.geek.rest.ProductResource;
import ru.geek.service.dto.ProductDto;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@Remote(ProductServiceRemote.class)
@PermitAll
public class ProductServiceImpl implements ProductService, ProductServiceRemote, ProductResource {

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    @RolesAllowed({"ADMIN"})
    @TransactionAttribute
    public void save(ProductDto productDto) {
        productRepository.save(new Product(productDto.getId(),
                productDto.getName(), productDto.getDescription(),
                productDto.getPrice(),
                categoryRepository.getReference(productDto.getCategoryId())
        ));
    }

    @Override
    @RolesAllowed({"ADMIN"})
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

    @Override
    public void insert(ProductDto productDto) {
        if (productDto.getId()!=null) {
            throw new IllegalArgumentException("Not null id in the inserted Product");
        }
        save(productDto);
    }

    @Override
    public void update(ProductDto productDto) {
        if (productDto.getId()==null) {
            throw new IllegalArgumentException("Null id in the inserted Product");
        }
        save(productDto);
    }

    @Override
    public void addCategory(Category category) {
        if (category.getId()!=null) {
            throw new IllegalArgumentException("Not null id in the inserted Category");
        }
        categoryRepository.save(category);
    }

    @Override
    public ProductDto findByName(String name) {
        return createProductDtoWithCategory(productRepository.findByName(name));
    }

    @Override
    public List<ProductDto> findAllProductWithCategoryId(Long id) {
        return productRepository.findAllProductWithCategoryId(id).stream()
                .map(ProductServiceImpl::createProductDtoWithCategory)
                .collect(Collectors.toList());
    }
}
