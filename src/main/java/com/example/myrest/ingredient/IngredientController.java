package com.example.myrest.ingredient;

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
@RequestMapping(path="${myapi.path.root}${myapi.path.ingredients}")
public class IngredientController {
    private final IngredientService service;

    public IngredientController(IngredientService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<IngredientDto> list(@RequestParam(defaultValue = "") String name) {
        List<IngredientDto> result = service.findByName(name);
//        do stuff
        return result;
    }

    @PostMapping("")
    public ResponseEntity<IngredientDto> create(@Valid @RequestBody IngredientDto ingredient) {
        ingredient = service.add(ingredient);

        URI uri = MvcUriComponentsBuilder
            .fromMethod(IngredientController.class,
                ClassUtils.getMethod(IngredientController.class, "one", Long.class),
                ingredient.getId())
            .build()
            .toUri();

        return ResponseEntity.created(uri).body(ingredient);
    }

    @GetMapping("/{id}")
    public IngredientDto one(@PathVariable(value = "id") Long id) {
        return service.fetchOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
    }

}
