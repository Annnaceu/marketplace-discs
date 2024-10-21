package com.example.marketplace.controller;

import com.example.marketplace.model.Disc;
import com.example.marketplace.repository.DiscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discs")
public class DiscController {

    @Autowired
    private DiscRepository discRepository;

    // Listar todos os discos
    @GetMapping
    public List<Disc> getAllDiscs() {
        return discRepository.findAll();
    }

    // Buscar disco por ID
    @GetMapping("/{id}")
    public ResponseEntity<Disc> getDiscById(@PathVariable Long id) {
        return discRepository.findById(id)
                .map(disc -> ResponseEntity.ok().body(disc))
                .orElse(ResponseEntity.notFound().build());
    }

    // Adicionar novo disco
    @PostMapping
    public Disc createDisc(@RequestBody Disc disc) {
        return discRepository.save(disc);
    }

    // Atualizar um disco existente
    @PutMapping("/{id}")
    public ResponseEntity<Disc> updateDisc(@PathVariable Long id, @RequestBody Disc discDetails) {
        return discRepository.findById(id)
                .map(disc -> {
                    disc.setTitle(discDetails.getTitle());
                    disc.setArtist(discDetails.getArtist());
                    disc.setGenre(discDetails.getGenre());
                    disc.setPrice(discDetails.getPrice());
                    disc.setStock(discDetails.getStock());
                    Disc updatedDisc = discRepository.save(disc);
                    return ResponseEntity.ok().body(updatedDisc);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Deletar um disco
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDisc(@PathVariable Long id) {
        return discRepository.findById(id)
                .map(disc -> {
                    discRepository.delete(disc);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
