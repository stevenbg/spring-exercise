package com.example.myrest.burger;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="${myapi.path.root}${myapi.path.burgers}")
public class BurgerController {
    private final BurgerService service;

    public BurgerController(BurgerService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<BurgerDto> list(@Valid BurgerSearchParams listParams) {
        List<Burger> result = service.find(listParams);

        return result.stream().map(BurgerDto::new).collect(Collectors.toList());
    }

    @PostMapping("")
    public ResponseEntity<BurgerDto> create(@Valid @RequestBody BurgerDto incoming) {
        Burger burger = service.add(incoming.toBurger());

        URI uri = MvcUriComponentsBuilder
            .fromMethod(BurgerController.class,
                ClassUtils.getMethod(BurgerController.class, "one", Long.class),
                burger.getId())
            .build()
            .toUri();

        return ResponseEntity.created(uri).body(new BurgerDto(burger));
    }

    @GetMapping("/random")
    public BurgerDto random() {
        return new BurgerDto(service.fetchOne());
    }

    @GetMapping("/{id}")
    public BurgerDto one(@PathVariable(value = "id") Long id) {
        return new BurgerDto(service.fetchOne(id));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }

}
