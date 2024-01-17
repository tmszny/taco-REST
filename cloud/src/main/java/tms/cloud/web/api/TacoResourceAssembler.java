package tms.cloud.web.api;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import tms.cloud.Taco;
import tms.cloud.web.DesignTacoController;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

@Component
public class TacoResourceAssembler extends RepresentationModelAssemblerSupport<Taco, TacoResource> {

    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    @Override
    public TacoResource toModel(Taco entity) {
        return new TacoResource(entity).add(WebMvcLinkBuilder.linkTo(DesignTacoController.class).slash(entity.getId()).withSelfRel());
    }

    @Override
    public CollectionModel<TacoResource> toCollectionModel(Iterable<? extends Taco> entities) {
        return super.toCollectionModel(entities).add(WebMvcLinkBuilder.linkTo(DesignTacoController.class).withSelfRel());
    }
}
