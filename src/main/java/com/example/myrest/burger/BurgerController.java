package com.example.myrest.burger;

import org.springframework.web.bind.annotation.*;

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
    public List<Burger> list() {
        return repository.findAll();
    }

    @PostMapping("")
    public Burger create(@RequestParam(value = "name") String name) {
        return repository.save(new Burger(name));
    }

    @GetMapping("/random")
    public Burger random() {
        return repository.findRandom().orElseThrow(() -> new BurgerNotFoundException());
    }

    @GetMapping("/{id}")
    public Burger get(@PathVariable(value = "id") Long id) {
        return repository.findById(id).orElseThrow(() -> new BurgerNotFoundException());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
    }

}
