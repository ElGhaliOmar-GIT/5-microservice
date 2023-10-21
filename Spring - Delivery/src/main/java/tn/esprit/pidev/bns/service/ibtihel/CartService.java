package tn.esprit.pidev.bns.service.ibtihel;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.webjars.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.pidev.bns.entity.ibtihel.Cart;
import tn.esprit.pidev.bns.entity.ibtihel.CommandLine;
import tn.esprit.pidev.bns.repository.ibtihel.CartRepo;
import tn.esprit.pidev.bns.repository.ibtihel.CommandLineRepo;
import tn.esprit.pidev.bns.serviceInterface.ibtihel.ICart;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartService implements ICart {

    @Autowired
    CartRepo cartRepo;

    @Autowired
    CommandLineRepo commandLineRepo;




    /// cart ///////////////////
    @Override
    public Cart saveCart(Cart cart, Integer idCommandLine) {


        CommandLine commandLine = commandLineRepo.findById(idCommandLine).orElseThrow(()
                -> new NotFoundException("ligne commande non trouvée"));
        commandLine.getCart();
        Set<CommandLine> commandLines = new HashSet<CommandLine>();
        commandLines.add(commandLine);
        cart.setCommandLines(commandLines);

        commandLineRepo.save(commandLine);

        return cartRepo.save(cart);


    }




    @Override
    public void assignCommandeLineToCart(Integer idCommandLine, Integer idCart) {
        Cart cart = cartRepo.findById(idCart).get();
        CommandLine commandLine = commandLineRepo.findById(idCommandLine).get();
        commandLine.setCart(cart);
        commandLineRepo.save(commandLine);

    }




    @Override
    public Cart updateCart(Cart cart) {

        return cartRepo.save(cart);
    }



    @Override
    public Cart deleteCart(Integer idCart) {
        cartRepo.deleteById(idCart);
        return null;
    }



    @Override
    public List<Cart> ListCart() {

        return (List<Cart>) cartRepo.findAll();
    }



    @Override
    public Cart ListCartById(Integer idCart) {

        return cartRepo.findById(idCart).get();
    }


}



