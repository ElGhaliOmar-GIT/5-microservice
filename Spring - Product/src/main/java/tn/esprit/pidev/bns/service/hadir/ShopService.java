package tn.esprit.pidev.bns.service.hadir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.bns.entity.hadir.Product;
import tn.esprit.pidev.bns.entity.hadir.Shop;
import tn.esprit.pidev.bns.entity.hadir.Tva;
import tn.esprit.pidev.bns.repository.hadir.ProductRep;
import tn.esprit.pidev.bns.repository.hadir.ShopRep;
import tn.esprit.pidev.bns.repository.hadir.TvaRep;
import tn.esprit.pidev.bns.serviceInterface.hadir.IShopService;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class ShopService implements IShopService {
    @Autowired
    ShopRep shopRep;
    @Autowired
    ProductRep productRep;
    @Autowired
    TvaRep tvaRep;

    @Override
    public List<Shop> retrieveAllShops() {
        return (List<Shop>) shopRep.findAll();
    }

    @Override
    public boolean updateShop(Shop shop) {
        if (shopRep.existsById(shop.getIdShop())) {
            shopRep.save(shop);
            return true;
        }
        return false;
    }

    @Override
    public Shop addShop(Shop shop) {
        return shopRep.save(shop);
    }

    @Override
    public Shop retrieveShop(Integer idShop) {
        return shopRep.findById(idShop).orElse(null);
    }

    @Override
    public boolean removeShop(Integer idShop) {
        if (shopRep.existsById(idShop)) {
            shopRep.deleteById(idShop);
            return true;
        }
        return false;
    }

    @Override
    public Shop affecterProductToShop(Integer idShop, List<Integer> idProduct) {
        Shop shop = shopRep.findById(idShop).orElseThrow(() -> new EntityNotFoundException("shop non trouvée"));
        List<Product> products = productRep.findAllById(idProduct);
        shop.getProducts().addAll(products); // Ajoute les nouveaux produits à la liste existante
        shopRep.save(shop);
        return shop;

    }

    @Override
    public double beneficeBrut(int idS) {
        double valeur= 0;

        //Recup stock
        Shop s = this.retrieveShop(idS);
        //Parcous + calcul
        for (Product p : s.getProducts()) {
            valeur = valeur + p.getPrice();
        }

        return valeur;
    }

    @Override
    public Tva addTva(Tva tva) {
        if (tvaRep.count() > 0) {
            tvaRep.deleteAll();
        }
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date updatedDate = calendar.getTime();
        tva.setDateCreation(updatedDate);
        return tvaRep.save(tva);
    }

    @Override
    public double tauxTVA(int idS) {
        List<Tva>tvas=tvaRep.findAll();
        Tva premiereTva = tvas.get(0);
        double tauxTvaPremiereTva = premiereTva.getTauxTva();
        double valeurpropre = this.beneficeBrut(idS);
        return (valeurpropre * tauxTvaPremiereTva) / 100;
    }

    @Override
    public double valeurbeneficeApresTva(int idS) {
        return (this.beneficeBrut(idS)-this.tauxTVA(idS));
    }

    @Override
    public Shop shopRentable() {
        List <Shop> list = this.retrieveAllShops();
        Shop s =list.get(0);

        for (Shop s1 : list) {
            if (this.valeurbeneficeApresTva(s1.getIdShop()) > this.valeurbeneficeApresTva(s.getIdShop()))
            {
                s = s1;
            }
        }


        return s;
    }

    @Override
    public Shop shopMRentable() {
        List <Shop> list = this.retrieveAllShops();
        Shop s =list.get(0);

        for (Shop s1 : list) {
            if (this.valeurbeneficeApresTva(s1.getIdShop()) < this.valeurbeneficeApresTva(s.getIdShop()))
            {
                s = s1;
            }
        }

        return s;
    }

    @Override
    public Map<String, Double> getShopStatistics() {
        List<Shop> shops = this.retrieveAllShops();
        double totalBenefice = 0;
        double totalRentable = 0;
        double totalMRentable = 0;
        for (Shop shop : shops) {
            totalBenefice += this.valeurbeneficeApresTva(shop.getIdShop());
            if (this.shopRentable().getIdShop() == shop.getIdShop()) {
                totalRentable += 1;
            } else {
                totalMRentable += 1;
            }
        }
            double pourcentageRentable = (totalRentable / shops.size()) * 100;
            double pourcentageRentableMoyenne = (totalMRentable / shops.size()) * 100;
            Map<String, Double> pourcentages = new HashMap<>();
            pourcentages.put("Rentable", pourcentageRentable);
            pourcentages.put("rentableMoyenne", pourcentageRentableMoyenne);

            return pourcentages;
        }
    }
