package tn.esprit.pidev.bns.service.ibtihel;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.bns.entity.ibtihel.Delivery;
import tn.esprit.pidev.bns.entity.ibtihel.PurchaseOrder;
import tn.esprit.pidev.bns.repository.ibtihel.DeliveryRepo;
import tn.esprit.pidev.bns.repository.ibtihel.PurchaseOrderRepo;
import tn.esprit.pidev.bns.serviceInterface.ibtihel.IDelivery;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DeliveryService implements IDelivery {



    @Autowired
    DeliveryRepo deliveryRepo;

    @Autowired
    PurchaseOrderRepo purchaseOrderRepo;



    @Override
    public Delivery addDelivery(Delivery delivery) {
        deliveryRepo.save(delivery);
        sendSMS(delivery);
        return delivery;
    }


    @Override
    public Delivery updateDelivery(Delivery delivery) {
        return deliveryRepo.save(delivery);
    }

    @Override
    public Delivery deleteDelivery(Integer idDelivery) {
        deliveryRepo.deleteById(idDelivery);
        return null;
    }



    @Override
    public List<Delivery> ListDelivery() {
        return (List<Delivery>) deliveryRepo.findAll();
    }



    @Override
    public Delivery ListDeliveryById(Integer idDelivery) {
        return deliveryRepo.findById(idDelivery).get();
    }



    @Override
    public int notstarted(int id) {
        return deliveryRepo.notstarted(id);
    }




    @Override
    public int delivered(int id) {
        return deliveryRepo.delivered(id);
    }


    @Override
    public int inProgress(int id) {
        return deliveryRepo.inProgress(id);
    }



    @Override
    public String availableDelivery(int id) {
        int ns=deliveryRepo.notstarted(id);
        int del=deliveryRepo.delivered(id);
        int ip=deliveryRepo.inProgress(id);

        float pns= ((float)ns/((float)del+(float)ip+(float)ns))*100;
        float pdel= ((float)del/((float)del+(float)ip+(float)ns))*100;
        float pip= ((float)ip/((float)del+(float)ip+(float)ns))*100;

        String sns=String.valueOf(ns);
        String sdel=String.valueOf(del);
        String sip=String.valueOf(ip);
        String t=String.valueOf(deliveryRepo.total(id));
        return "delivery Not Started : "+sns+"  :   "+String.valueOf((int)pns)+
                "%    delivery In Progress : "+sip+"  :  "+String.valueOf((int)pip)+"%"+
                "%    delivery delivered : "+sdel+"  :  "+String.valueOf((int)pdel)+"% "+
                "\n" +" Total deliveries : "+t;

    }







    @Override
    public void assignDelivererToDelivery(Integer idDelivery, Integer idDeliverer) {
       /* Deliverer deliverer =delivererRepo.findById( idDeliverer).get();
        Delivery delivery= deliveryRepo.findById(idDelivery).get();
        delivery.setDeliverer(deliverer);
        deliveryRepo.save(delivery);*/
    }





    @Override
    public void assignDeliveryToOrder(Integer idOrder, Integer idDelivery) {
        Delivery delivery= deliveryRepo.findById(idDelivery).get();
        PurchaseOrder purchaseOrder= purchaseOrderRepo.findById(idOrder).get();
        purchaseOrder.setDelivery(delivery);
        purchaseOrderRepo.save(purchaseOrder);

    }








    ///// ******** SMS ***********

    @Autowired
    public static final String ACCOUNT_SID = "AC62f2665fc874d6b5b8e686e25c25e442";
    @Autowired
    public static final String AUTH_TOKEN = "e3d4a5fdb8d1815f74ecebac0bd01c76";



    public  void sendSMS(Delivery delivery) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber(delivery.getPhoneNumber()),
                new PhoneNumber("+15183045334"),
                "New Delivery has been added "+
                        "\r"+"Delivery Date : "+delivery.getDeliveryDate()+
                        "\r"+" Arrival  Date :"+delivery.getArrivalDate()+
                        "\r"+"Adresse :"+delivery.getAddress()+
                        "\r"+"Total price :"+delivery.getTotalPrice()+
                        "\r"+"Delivery Status :"+delivery.getDeliveryStatus()

        ).create();

        System.out.println(message.getSid());
    }





}
