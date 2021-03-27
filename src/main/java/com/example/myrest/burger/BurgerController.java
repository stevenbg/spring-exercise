package com.example.myrest.burger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

// todo service
@RestController
// todo extract to config
@RequestMapping(path="/v1/burgers")
public class BurgerController {
    private final BurgerRepository repository;

    public BurgerController(BurgerRepository r) {
        this.repository = r;
    }

    @GetMapping("")
    public List<Burger> find() {
        return repository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<Burger> create(@RequestBody Burger newBurger) {
        newBurger = repository.save(newBurger);
        URI location = linkTo(methodOn(BurgerController.class).one(newBurger.getId())).toUri();
        return ResponseEntity.created(location).body(newBurger);
    }

    @GetMapping("/random")
    public Burger random() {
        return repository.findRandom().orElseThrow(() -> new BurgerNotFoundException());
    }

    @GetMapping("/{id}")
    public Burger one(@PathVariable(value = "id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BurgerNotFoundException(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
    }

}
