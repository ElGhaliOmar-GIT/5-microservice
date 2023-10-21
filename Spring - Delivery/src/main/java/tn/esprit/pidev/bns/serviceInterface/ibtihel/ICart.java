package tn.esprit.pidev.bns.serviceInterface.ibtihel;

import tn.esprit.pidev.bns.entity.ibtihel.Cart;

import java.util.List;

public interface ICart {

    //cart

    public Cart saveCart(Cart cart, Integer idCommandLine);

    public void assignCommandeLineToCart(Integer idCommandLine, Integer idCart);

    public Cart updateCart(Cart cart);
    public Cart deleteCart(Integer idCart);

    public List<Cart> ListCart();

    public Cart ListCartById(Integer idCart);





}
