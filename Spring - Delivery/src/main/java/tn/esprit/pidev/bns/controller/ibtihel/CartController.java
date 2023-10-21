package tn.esprit.pidev.bns.controller.ibtihel;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.bns.entity.ibtihel.Cart;
import tn.esprit.pidev.bns.serviceInterface.ibtihel.ICart;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/CartController")

public class CartController {

    @Autowired
    ICart iCart;



    ////////cart

    @PostMapping("/saveCart/{idCommandLine}")
    public Cart saveCart(@RequestBody Cart cart, @PathVariable("idCommandLine") Integer idCommandLine) {
        Cart cart1=iCart.saveCart(cart,idCommandLine);
        return cart1;

    }


    @PutMapping("/assignCommandeLineToCart")
    public void assignCommandeLineToCart(@RequestParam("idCommandLine") Integer idCommandLine,
                                         @RequestParam("idCart") Integer idCart) {
        System.err.println(idCommandLine);
        System.err.println(idCart);
        iCart.assignCommandeLineToCart(idCommandLine,idCart);
    }



    @PutMapping("/updateCart")
    public Cart updateCart( @RequestBody Cart cart) {
        return  iCart.updateCart(cart);
    }



    @PutMapping("/deleteCart/{id}")
    public Cart deleteCart( @PathVariable("id") Integer idCart) {
        return iCart.deleteCart(idCart);
    }



    @GetMapping("/GetListCart")
    public List<Cart> getListCart() {
        List <Cart> cartList = iCart.ListCart();
        return cartList;
    }

    @GetMapping("/GetListCartById/{id}")
    public Cart ListCartById(@PathVariable("id") Integer idCart) {

        return iCart.ListCartById(idCart);
    }








}
