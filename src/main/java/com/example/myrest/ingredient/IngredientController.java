package com.example.myrest.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="${myapi.path.root}${myapi.path.ingredients}")
public class IngredientController {

    @Autowired
    private IngredientService service;
    @Autowired
    private Environment env;

    @GetMapping("")
    public List<IngredientDto> list(@RequestParam(defaultValue = "") String name) {
        List<IngredientDto> result = service.findByName(name);
//        do stuff
        return result;
    }

    @PostMapping("")
    public ResponseEntity<IngredientDto> create(@Valid @RequestBody IngredientDto ingredient) {
        ingredient = service.add(ingredient);

        return ResponseEntity.created(getIngredientUri(ingredient.getId())).body(ingredient);
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

    private URI getIngredientUri(Long id) {
        URI uri;
        try {
            uri = new URI(env.getProperty("myapi.path.root") + env.getProperty("myapi.path.ingredients") + "/" + id);
        } catch (URISyntaxException e) {
            uri = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri();
        }
        return uri;
    }
}
