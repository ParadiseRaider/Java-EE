package ru.geek.service;

import ru.geek.service.dto.ProductDto;

import java.util.List;

public interface ProductServiceRemote {
    List<ProductDto> findAllRemote();
}
