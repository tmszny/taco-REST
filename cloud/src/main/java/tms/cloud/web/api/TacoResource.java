package tms.cloud.web.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import tms.cloud.Ingredient;
import tms.cloud.Taco;

import java.util.Date;
import java.util.List;

public class TacoResource extends RepresentationModel<TacoResource> {

    @Getter
    private final String name;
    @Getter
    private final Date createdAt;
    @Getter
    private final List<Ingredient> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = taco.getIngredients();
    }
}
