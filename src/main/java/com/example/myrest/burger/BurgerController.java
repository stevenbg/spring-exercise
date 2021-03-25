package com.example.myrest.burger;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// todo error handler
@RestController
// todo extract to config
@RequestMapping(path="/v1/burgers")
public class BurgerController {
    private final BurgerRepository repository;

    public BurgerController(BurgerRepository r) {
        this.repository = r;
    }

    @GetMapping("")
    public List<Burger> list() {
        return repository.findAll();
    }

    @PostMapping("")
    public Burger create(@RequestParam(value = "name") String name) {
        return repository.save(new Burger(name));
    }

//    todo return proper 404
    @GetMapping("/random")
    public Optional<Burger> random() {
        return repository.findRandom();
    }

    @GetMapping("/{id}")
    public Optional<Burger> get(@PathVariable(value = "id") Long id) {
        return repository.findById(id);
    }

}
