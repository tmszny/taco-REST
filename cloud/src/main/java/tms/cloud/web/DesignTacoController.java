package tms.cloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tms.cloud.Ingredient.Type;
import tms.cloud.Ingredient;
import tms.cloud.Order;
import tms.cloud.Taco;
import tms.cloud.data.IngredientRepository;
import tms.cloud.data.TacoRepository;
import tms.cloud.web.api.TacoResource;
import tms.cloud.web.api.TacoResourceAssembler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/design", produces = "application/json")
@SessionAttributes("order")
public class DesignTacoController {

    private TacoRepository tacoRepo;
    private TacoResourceAssembler tacoResourceAssembler;

    @Autowired
    public DesignTacoController(TacoRepository tacoRepo, TacoResourceAssembler tacoResourceAssembler) {
        this.tacoRepo = tacoRepo;
        this.tacoResourceAssembler = tacoResourceAssembler;}

    @GetMapping("/recent")
    public ResponseEntity<CollectionModel<TacoResource>> recentTaco() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        return new ResponseEntity<>(tacoResourceAssembler.toCollectionModel((tacoRepo.findAll(page))), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoResource> tacoById(@PathVariable("id") Long id){
        Optional<Taco> optTaco = tacoRepo.findById(id);
        if (optTaco.isPresent()) {
            return new ResponseEntity<>(tacoResourceAssembler.toModel(optTaco.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco) {
        return tacoRepo.save(taco);
    }
}
