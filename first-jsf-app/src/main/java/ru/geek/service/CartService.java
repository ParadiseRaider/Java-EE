package ru.geek.service;

import ru.geek.service.dto.ProductDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CartService {
    void add(ProductDto productDto);
    void remove(ProductDto productDto);
    List<ProductDto> findAll();
    String count();
}
