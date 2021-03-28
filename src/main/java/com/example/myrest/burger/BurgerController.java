package com.example.myrest.burger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

//@Validated
@RestController
// todo extract to config
@RequestMapping(path="/v1/burgers")
public class BurgerController {
    private final BurgerService service;

    public BurgerController(BurgerService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Burger> list(@Valid BurgerServiceFindParams listParams) {
        return service.find(listParams);
    }

    @PostMapping("")
    public ResponseEntity<Burger> create(@Valid @RequestBody Burger newBurger) {
        newBurger = service.add(newBurger);

        URI uri =
                MvcUriComponentsBuilder
                        .fromMethod(BurgerController.class,
                                ClassUtils.getMethod(BurgerController.class, "one", Long.class),
                                newBurger.getId())
                        .build()
                        .toUri();

        return ResponseEntity.created(uri).body(newBurger);
    }

    @GetMapping("/random")
    public Burger random() {
        return service.fetchOne();
    }

    @GetMapping("/{id}")
    public Burger one(@PathVariable(value = "id") Long id) {
        return service.fetchOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }

}
