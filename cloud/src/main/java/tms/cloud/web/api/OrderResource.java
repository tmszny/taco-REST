package tms.cloud.web.api;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import tms.cloud.Order;
import tms.cloud.User;

import java.util.Date;
import java.util.List;

public class OrderResource extends RepresentationModel<OrderResource> {
    private TacoResourceAssembler tacoResourceAssembler;
    @Getter
    private final User user;
    @Getter
    private final Date placedAt;
    @Getter
    private final String name;
    @Getter
    private final String street;
    @Getter
    private final String city;
    @Getter
    private final String zip;
    @Getter
    private final CollectionModel<TacoResource> tacos;

    public OrderResource(Order order) {
        this.user = order.getUser();
        this.placedAt = order.getPlacedAt();
        this.name = order.getName();
        this.street = order.getStreet();
        this.city = order.getCity();
        this.zip = order.getZip();
        this.tacos = tacoResourceAssembler.toCollectionModel(order.getTacos());
    }
}
