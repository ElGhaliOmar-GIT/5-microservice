package tn.esprit.pidev.bns.serviceInterface.ibtihel;

import tn.esprit.pidev.bns.entity.ibtihel.Delivery;

import java.util.List;

public interface IDelivery {
    //delivery

    public Delivery addDelivery(Delivery delivery);
    public Delivery updateDelivery(Delivery delivery);

    public Delivery deleteDelivery(Integer idDelivery);

    List<Delivery> ListDelivery();

    public Delivery ListDeliveryById(Integer idDelivery);

    public int notstarted(int id);
    public int delivered(int id);
    public int inProgress(int id);

    String availableDelivery(int id);





    public void assignDelivererToDelivery(Integer idDelivery, Integer idDeliverer);
    public void assignDeliveryToOrder(Integer idOrder, Integer idDelivery);
}
