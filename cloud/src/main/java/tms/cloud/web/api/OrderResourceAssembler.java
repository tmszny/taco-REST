package tms.cloud.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;
import tms.cloud.Order;
import tms.cloud.web.OrderController;

@Component
public class OrderResourceAssembler extends RepresentationModelAssemblerSupport<Order, OrderResource> {


    public OrderResourceAssembler() {
        super(OrderController.class, OrderResource.class);
    }

    @Override
    public OrderResource toModel(Order entity) {
        return new OrderResource(entity);
    }

    @Override
    public CollectionModel<OrderResource> toCollectionModel(Iterable<? extends Order> entities) {
        return super.toCollectionModel(entities);
    }
}
