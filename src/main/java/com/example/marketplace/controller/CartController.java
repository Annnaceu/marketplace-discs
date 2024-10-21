package com.example.marketplace.controller;

import com.example.marketplace.model.Cart;
import com.example.marketplace.model.CartItem;
import com.example.marketplace.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    // Criar um novo carrinho
    @PostMapping
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    // Adicionar um item ao carrinho
    @PostMapping("/{cartId}/items")
    public ResponseEntity<Cart> addItemToCart(@PathVariable Long cartId, @RequestBody CartItem item) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.addItem(item);
                    cartRepository.save(cart);
                    return ResponseEntity.ok(cart);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Adicionar vários itens ao carrinho
    @PostMapping("/{cartId}/items/bulk")
    public ResponseEntity<Cart> addItemsToCart(@PathVariable Long cartId, @RequestBody List<CartItem> items) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    for (CartItem item : items) {
                        cart.addItem(item);
                    }
                    cartRepository.save(cart);
                    return ResponseEntity.ok(cart);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Remover um item do carrinho
    @DeleteMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        return cartRepository.findById(cartId)
                .map(cart -> {
                    cart.removeItem(itemId);
                    cartRepository.save(cart);
                    return ResponseEntity.ok(cart);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Listar itens do carrinho
    @GetMapping("/{cartId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long cartId) {
        return cartRepository.findById(cartId)
                .map(cart -> ResponseEntity.ok(cart.getItems()))
                .orElse(ResponseEntity.notFound().build());
    }
}
