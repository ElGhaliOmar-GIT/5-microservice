package tn.esprit.pidev.bns.controller.hadir;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.bns.entity.hadir.Shop;
import tn.esprit.pidev.bns.entity.hadir.Tva;
import tn.esprit.pidev.bns.repository.hadir.ShopRep;
import tn.esprit.pidev.bns.serviceInterface.hadir.IShopService;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/Shop")
public class ShopController {
    @Autowired
    IShopService shopService;
    @Autowired
    ShopRep shopRep;
    @GetMapping("/retrieve-all-Shops")
    public List<Shop> getShops() {
        List<Shop> listShops = shopService.retrieveAllShops();
        return listShops;
    }
    @GetMapping("/retrieve-Shop/{Shop-id}")
    public Shop retrieveShop(@PathVariable("Shop-id") Integer shopId) {

        return shopService.retrieveShop(shopId);
    }
    @PostMapping("/add-Shop")
    public Shop addShop(@RequestBody Shop s) {
        Shop shop = shopService.addShop(s);
        return shop;
    }
    @DeleteMapping("/remove-Shop/{Shop-id}")
    public void removeShop(@PathVariable("Shop-id") Integer idShop) {
        shopService.removeShop(idShop);
    }
    @PutMapping("/update-Shop")
    public boolean updateShop(@RequestBody Shop s) {
        return shopService.updateShop(s);
    }

    @PutMapping("/{idBoutique}/produits")
    public Shop affecterProduitsABoutique(@PathVariable Integer idBoutique, @RequestBody List<Integer> idProduits) {
        return shopService.affecterProductToShop(idBoutique, idProduits);
    }

    @GetMapping("/beneficeBrut/{shop-id}")
    @ResponseBody
    public double beneficeBrut(@PathVariable("shop-id")int ids)
    {
        return shopService.beneficeBrut(ids);
    }

    @PostMapping("/add-TVA")
    public Tva addProduct(@RequestBody Tva tva) {
        return shopService.addTva(tva);
    }
    @GetMapping("/tauxTVA/{shop-id}")
    public double tauxTVA(@PathVariable("shop-id")int ids)
    {
        return shopService.tauxTVA(ids);
    }
    @GetMapping("/valeurbeneficeApresTva/{shop-id}")
    public double valeurbeneficeApresTva(@PathVariable("shop-id")int ids)
    {
        return shopService.valeurbeneficeApresTva(ids);
    }
    @GetMapping("/shop-plusrentable")
    @ResponseBody
    public Shop shopRentable()
    {
        return shopService.shopRentable();
    }
    @GetMapping("/shop-Mrentable")
    @ResponseBody
    public Shop shopNonRentable()
    {
        return shopService.shopMRentable();
    }
    @GetMapping("/statistiques")
    public Map<String, Double> getStockStatistics() {
        return shopService.getShopStatistics();
    }

    }

