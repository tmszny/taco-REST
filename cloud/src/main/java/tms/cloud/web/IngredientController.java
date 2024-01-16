package tms.cloud.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tms.cloud.Ingredient;
import tms.cloud.data.IngredientRepository;
import tms.cloud.web.api.IngredientResource;
import tms.cloud.web.api.IngredientsResourceAssembler;
import tms.cloud.web.api.TacoResourceAssembler;

import java.util.Collection;

@RestController
@RequestMapping(value = "/ingredients", produces = "application/json")
@CrossOrigin(origins = "*")
public class IngredientController {
    private IngredientRepository repo;
    @Autowired
    private IngredientsResourceAssembler tacoResourceAssembler;

    @Autowired
    public IngredientController(IngredientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<IngredientResource>> allIngredients() {
        return new ResponseEntity<>(tacoResourceAssembler.toCollectionModel(repo.findAll()), HttpStatus.OK);
    }
}
