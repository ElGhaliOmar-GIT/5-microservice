package tn.esprit.pidev.bns.controller.ibtihel;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.bns.entity.ibtihel.PDFGenerator;
import tn.esprit.pidev.bns.entity.ibtihel.PurchaseOrder;
import tn.esprit.pidev.bns.service.ibtihel.OrderService;
import tn.esprit.pidev.bns.serviceInterface.ibtihel.ICart;
import tn.esprit.pidev.bns.serviceInterface.ibtihel.IOrder;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/OrderController")

public class OrderController {
    @Autowired
    IOrder iOrder;
    @Autowired
    ICart iCart;


    @Autowired
    public  PDFGenerator pdfGenerator;

    //////////order


    @PostMapping("/addOrder/{idCart}")
    public PurchaseOrder addPurchaseOrder(@RequestBody PurchaseOrder order,
                                          @PathVariable("idCart") int idCart )
    {
        order.setCart(iCart.ListCartById(idCart));
        return  iOrder.confirmPurchaseOrder(order);
    }



    @PutMapping("/updateOrder")
    public PurchaseOrder updatePurchaseOrder( @RequestBody PurchaseOrder order) {
        return  iOrder.updatePurchaseOrder(order);
    }


    @GetMapping("/GetListOrders")
    public List<PurchaseOrder> ListPurchaseOrder() {
        return iOrder.ListPurchaseOrder();
    }



    @GetMapping("/GetListOrderById/{id}")
    public PurchaseOrder ListOrderById( @PathVariable ("id") Integer idOrder) {
        return iOrder.ListOrderById(idOrder);
    }



    @PutMapping("/TotalOrdersTVA/{idOrder}")
    public int TotalOrdersTVA(@PathVariable("idOrder") int idOrder) {
        return iOrder.TotalOrdersTVA(idOrder);
    }










    /////// PDF ///////

    @GetMapping("/orders/pdf")
    public void exportToPDF()  {
        pdfGenerator.generatePdfReport();

    }














}
