package ru.kataaas.ims.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kataaas.ims.dto.CategoryDTO;
import ru.kataaas.ims.dto.CreateProductDTO;
import ru.kataaas.ims.service.CartService;
import ru.kataaas.ims.service.ProductService;
import ru.kataaas.ims.service.UserService;
import ru.kataaas.ims.service.VendorService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

    private final CartService cartService;

    private final UserService userService;

    private final VendorService vendorService;

    private final ProductService productService;

    public ProductController(CartService cartService,
                             UserService userService,
                             VendorService vendorService,
                             ProductService productService) {
        this.cartService = cartService;
        this.userService = userService;
        this.vendorService = vendorService;
        this.productService = productService;
    }

    @GetMapping("/{id}/addToCart")
    public ResponseEntity<?> addProductToCart(@PathVariable Long id, HttpServletRequest request,
                                              @RequestParam(defaultValue = "1") int quantity) {
        if (quantity > productService.getQuantityById(id)) return ResponseEntity.badRequest().build();
        try {
            Long userId = userService.findIdByPhoneNumber(request.getRemoteUser());
            cartService.addProductToCart(userId, id, quantity);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductDTO productDTO, HttpServletRequest request) {
        try {
            Long vendorId = vendorService.findIdByPhoneNumber(request.getRemoteUser());
            return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(productDTO, vendorId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
        }
    }

    @GetMapping("/categories")
    public List<CategoryDTO> fetchAllCategories() {
        return productService.fetchAllCategories();
    }
}
