package com.gpv.gestion.web.controller;

import com.gpv.gestion.domain.Product;
import com.gpv.gestion.domain.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Operation(summary="Mostrar Productos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode ="200", description = "Busqueda de Producto",
                  content = { @Content(mediaType = "application/json",
                        schema  = @Schema(implementation =Product.class)) }),
            @ApiResponse(responseCode ="400", description = "Objeto Producto invalido",
                    content=@Content),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content) })
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/api/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") int productId){
        return productService.getproduct(productId)
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/api/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("/save")
    public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }
    @DeleteMapping("/api/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") int productId){
      if(productService.delete(productId)){
          return new ResponseEntity<>(HttpStatus.OK);
      }else{
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }

    }

}
