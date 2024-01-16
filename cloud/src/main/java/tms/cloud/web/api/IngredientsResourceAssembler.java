package tms.cloud.web.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import tms.cloud.Ingredient;
import tms.cloud.web.IngredientController;

@Component
public class IngredientsResourceAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {

    public IngredientsResourceAssembler() {
        super(IngredientController.class, IngredientResource.class);
    }

    @Override
    public IngredientResource toModel(Ingredient entity) {
        return new IngredientResource(entity).add(WebMvcLinkBuilder.linkTo(IngredientController.class).slash(entity.getId()).withSelfRel());
    }

    @Override
    public CollectionModel<IngredientResource> toCollectionModel(Iterable<? extends Ingredient> entities) {
        return super.toCollectionModel(entities).add(WebMvcLinkBuilder.linkTo(IngredientController.class).withSelfRel());
    }
}
