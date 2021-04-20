package ru.geek.service;

import ru.geek.service.dto.ProductDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {
    void save(ProductDto productDto);
    void delete(Long id);
    ProductDto findById(Long id);
    List<ProductDto> findAll();
    List<ProductDto> findAllWithCategoryFetch();
    long count();
}
