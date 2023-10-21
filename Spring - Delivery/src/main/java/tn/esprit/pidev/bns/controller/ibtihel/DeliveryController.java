package tn.esprit.pidev.bns.controller.ibtihel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.bns.entity.ibtihel.Delivery;
import tn.esprit.pidev.bns.serviceInterface.ibtihel.IDelivery;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/DeliveryController")

public class DeliveryController {

    @Autowired
    IDelivery iDelivery;





    ////////delivery
    //hethi bech nbadalha ba3d kima mta l order
    @PostMapping("/addDelivery")
    public Delivery addDelivery(@RequestBody Delivery delivery)
    {
        return iDelivery.addDelivery(delivery);
    }


    @PutMapping("/updateDelivery")
    public Delivery updateDelivery(@RequestBody Delivery delivery) {
        return iDelivery.updateDelivery(delivery);
    }



    @PutMapping("/deleteDelivery/{id}")
    public Delivery deleteDelivery(@PathVariable("id") Integer idDelivery) {
        return iDelivery.deleteDelivery(idDelivery);
    }


    @GetMapping("/GetListDelivery")
    public List<Delivery> ListDelivery() {
        return iDelivery.ListDelivery();
    }


    @GetMapping("/GetListDeliveryById/{id}")
    public Delivery ListDeliveryById( @PathVariable("id") Integer idDelivery) {
        return iDelivery.ListDeliveryById(idDelivery);
    }



    @PutMapping("/assignDelivererToDelivery")
    public void assignDelivererToDelivery(@RequestParam("idDelivery") Integer idDelivery,
                                          @RequestParam("idDeliverer") Integer idDeliverer) {
        System.err.println(idDelivery);
        System.err.println(idDeliverer);
        iDelivery.assignDelivererToDelivery(idDelivery,idDeliverer);
    }



    @PutMapping("/assignDeliveryToOrder")
    public void assignDeliveryToOrder(@RequestParam ("idOrder") Integer idOrder,
                                      @RequestParam("idDelivery") Integer idDelivery) {

        System.err.println(idOrder);
        System.err.println(idDelivery);
        iDelivery.assignDeliveryToOrder(idOrder,idDelivery);

    }



    @GetMapping("/availableDelivery/{id}")
    public String availableDelivery(@PathVariable("id") int id) {
        return iDelivery.availableDelivery(id);
    }







}
