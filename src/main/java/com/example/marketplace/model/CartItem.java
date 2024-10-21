package com.example.marketplace.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.io.Serializable;

@Entity
public class CartItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Campo id para identificar cada item do carrinho

    @ManyToOne
    @JoinColumn(name = "cart_id") // Referência ao carrinho
    private Cart cart; 

    @ManyToOne
    @JoinColumn(name = "disc_id") // Referência ao disco
    private Disc disc; 

    private int quantity; // Quantidade do disco no carrinho

    // Construtores
    public CartItem() {}

    public CartItem(Cart cart, Disc disc, int quantity) {
        this.cart = cart;
        this.disc = disc;
        this.quantity = quantity;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Disc getDisc() {
        return disc;
    }

    public void setDisc(Disc disc) {
        this.disc = disc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}


