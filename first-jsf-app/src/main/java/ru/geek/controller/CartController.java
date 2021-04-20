package ru.geek.controller;

import ru.geek.service.CartService;
import ru.geek.service.dto.ProductDto;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class CartController implements Serializable {

    @EJB
    private CartService cartService;

    public void add(ProductDto productDto) {
        cartService.add(productDto);
    }

    public void remove(ProductDto productDto) {
        cartService.remove(productDto);
    }

    public List<ProductDto> findAll() {
        return cartService.findAll();
    }

    public String cartCount() {
        return cartService.count();
    }
}
