package tn.esprit.pidev.bns.service.ibtihel;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.bns.entity.ibtihel.*;

import tn.esprit.pidev.bns.repository.ibtihel.PaymentRepo;
import tn.esprit.pidev.bns.repository.ibtihel.PurchaseOrderRepo;

import tn.esprit.pidev.bns.serviceInterface.ibtihel.IOrder;

import java.util.*;


@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)


public class OrderService implements IOrder {



    @Autowired
    PurchaseOrderRepo purchaseOrderRepo;
    @Autowired
    PaymentRepo paymentRepo;







    @Override
    public PurchaseOrder confirmPurchaseOrder(PurchaseOrder order) {

        purchaseOrderRepo.save(order);
      //  sendmail(order);
        return order;
    }



    @Override
    public PurchaseOrder updatePurchaseOrder(PurchaseOrder order) {
        return purchaseOrderRepo.save(order);
    }



    @Override
    public List<PurchaseOrder> ListPurchaseOrder() {

        return  (List<PurchaseOrder>) purchaseOrderRepo.findAll();
    }



    @Override
    public PurchaseOrder ListOrderById(Integer idOrder) {
        return purchaseOrderRepo.findById(idOrder).get();
    }



    @Override
    public int TotalOrdersTVA(int idOrder) {
        PurchaseOrder purchaseOrder= purchaseOrderRepo.findById(idOrder).orElse(null);
        int total = 0;
        total = (int) ((purchaseOrder.getCart().getTotalCart()*purchaseOrder.getTax())+ purchaseOrder.getCart().getTotalCart());
        return total;
    }
}
