package tms.cloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tms.cloud.Ingredient;
import tms.cloud.data.IngredientRepository;
import tms.cloud.web.api.IngredientResource;
import tms.cloud.web.api.IngredientsResourceAssembler;
import tms.cloud.web.api.TacoResourceAssembler;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
    private IngredientRepository repo;
    @Autowired
    private IngredientsResourceAssembler ingredientResourceAssembler;

    @Autowired
    public IngredientController(IngredientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<IngredientResource>> allIngredients() {
        return new ResponseEntity<>(ingredientResourceAssembler.toCollectionModel(repo.findAll()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientResource> ingredientById(@PathVariable String id) {
        Optional<Ingredient> optIngredient = repo.findById(id);

        if (optIngredient.isPresent()) {
            return new ResponseEntity<>(ingredientResourceAssembler.toModel(optIngredient.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
