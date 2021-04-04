package com.example.myrest.burger;

import com.example.myrest.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path="${myapi.path.root}${myapi.path.burgers}")
public class BurgerController {

    @Autowired
    private Environment env;

    @Autowired
    private BurgerService burgerService;

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("")
    public List<BurgerDto> list(@Valid BurgerSearchParams listParams) {
        List<BurgerDto> result = burgerService.find(listParams);

        return result;
    }

    @PostMapping("")
    public ResponseEntity<BurgerDto> create(@Valid @RequestBody BurgerDto burger) {
        burger = burgerService.add(burger);

        return ResponseEntity.created(getBurgerUri(burger.getId())).body(burger);
    }

    @PostMapping("/{id}/${myapi.path.ingredients}")
    public ResponseEntity<BurgerDto> ingredients(@PathVariable(value = "id") Long burger_id, @Valid @RequestBody List<Long> ingredient_ids) {
        BurgerDto burger = burgerService.fetchOne(burger_id);
        burger.setIngredients(ingredientService.fetchMany(ingredient_ids));
        burger = burgerService.save(burger);

        return ResponseEntity.created(getBurgerUri(burger.getId())).body(burger);
    }

    @GetMapping("/random")
    public BurgerDto random() {
        return burgerService.fetchOne();
    }

    @GetMapping("/{id}")
    public BurgerDto one(@PathVariable(value = "id") Long id) {
        return burgerService.fetchOne(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable(value = "id") Long id) {
        burgerService.delete(id);
    }

    private URI getBurgerUri(Long id) {
//        todo stopped working with the externally configured paths
//        URI uri = MvcUriComponentsBuilder.fromMethod(BurgerController.class,
//                    ClassUtils.getMethod(BurgerController.class, "one", Long.class),
//                    id)
//                .build().toUri();

//        base?

        URI uri;
        try {
            uri = new URI(env.getProperty("myapi.path.root") + env.getProperty("myapi.path.burgers") + "/" + id);
        } catch (URISyntaxException e) {
            uri = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUri();
        }
        return uri;
    }
}
