package ru.geek.rest;

import ru.geek.persist.Category;
import ru.geek.service.dto.ProductDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/product")
public interface ProductResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> findAllWithCategoryFetch();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductDto productDto);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductDto productDto);

    @POST
    @Path("/category")
    @Consumes(MediaType.APPLICATION_JSON)
    void addCategory(Category category);

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findByName(@PathParam("name") String name);

    @GET
    @Path("/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> findAllProductWithCategoryId(@PathParam("id") Long id);

    @DELETE
    @Path("/{id}")
    void delete(@PathParam("id") Long id);
}
