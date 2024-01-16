package tms.cloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tms.cloud.Order;
import tms.cloud.Taco;
import tms.cloud.User;
import tms.cloud.data.OrderRepository;
import tms.cloud.web.api.OrderResource;
import tms.cloud.web.api.OrderResourceAssembler;

import java.util.Collection;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping(path = "/orders", produces="application/json")
@CrossOrigin(origins="*")
public class OrderController {

    private OrderRepository orderRepo;
    @Autowired
    private OrderResourceAssembler orderResourceAssembler;
    @Autowired
    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<CollectionModel<OrderResource>> allOrders() {
        return new ResponseEntity<>(orderResourceAssembler.toCollectionModel(orderRepo.findAll()), HttpStatus.OK);
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        return orderRepo.save(order);
    }
    @PatchMapping(value = "/{orderId}", consumes = "application/json")
    public ResponseEntity<Order> patchOrder(@PathVariable("orderId") Long id, @RequestBody Order patch) {
        Optional<Order> optOrder = orderRepo.findById(id);
        if (optOrder.isPresent()) {
            Order order = optOrder.get();

            if (patch.getName() != null) {
                order.setName(patch.getName());
            }
            if (patch.getStreet() != null) {
                order.setStreet(patch.getStreet());
            }
            if (patch.getCity() != null) {
                order.setCity(patch.getCity());
            }
            if (patch.getState() != null) {
                order.setCity(patch.getCity());
            }
            if (patch.getZip() != null) {
                order.setZip(patch.getZip());
            }
            if (patch.getCcNumber() != null) {
                order.setCcNumber(patch.getCcNumber());
            }
            if (patch.getCcExpiration() != null) {
                order.setCcExpiration(patch.getCcExpiration());
            }
            if (patch.getCcCVV() != null) {
                order.setCcCVV(patch.getCcCVV());
            }
            return new ResponseEntity<>(orderRepo.save(order), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
