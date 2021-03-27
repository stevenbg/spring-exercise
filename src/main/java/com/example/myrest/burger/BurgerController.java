package com.example.myrest.burger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
        newBurger.setId(null);
        newBurger = repository.save(newBurger);

        final URI uri =
                MvcUriComponentsBuilder
                        .fromMethod(BurgerController.class,
                                ClassUtils.getMethod(BurgerController.class, "one", null),
                                newBurger.getId())
                        .build()
                        .toUri();

        return ResponseEntity.created(uri).body(newBurger);
    }

    @GetMapping("/random")
    public Burger random() {
        return repository.findRandom()
            .orElseThrow(() -> new BurgerNotFoundException());
    }

    @GetMapping("/{id}")
    public Burger one(@PathVariable(value = "id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BurgerNotFoundException(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        }
        else {
            throw new BurgerNotFoundException(id);
        }
    }

}
