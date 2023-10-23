package tn.esprit.pidev.bns.controller.hadir;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.bns.entity.hadir.*;
import tn.esprit.pidev.bns.entity.hadir.Currency;
import tn.esprit.pidev.bns.repository.hadir.ProductRep;
import tn.esprit.pidev.bns.serviceInterface.hadir.IProductService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/Product")
public class ProductController {
    IProductService productService;
    @Autowired
    ProductRep productRep;

    @GetMapping("/retrieve-all-Products")
    public List<Product> getProducts() {
        return productService.retrieveAllProducts();
    }

    @GetMapping("/retrieve-Product/{product-id}")
    public Product retrieveProduct(@PathVariable("Product-id") Integer productId) {
        return productService.retrieveProduct(productId);
    }

    @PostMapping("/add-product/{idCategorie}")
    public Product addProduct(@RequestParam("name") String name,
                              @RequestParam("description") String description,
                              @RequestParam("price") int price,
                              @RequestParam("stock") int stock,
                              @PathVariable Integer idCategorie,
                              @RequestParam("image") String image) throws IOException {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setStock(stock);

        // Enregistrer l'image dans l'objet de produit
        product.setImage(image);
        Product p = productService.addProduct(product, idCategorie);
        return p;
    }

    @DeleteMapping("/remove-Product/{Product-id}")
    public void removeProduct(@PathVariable("Product-id") Integer idProduct) {
        productService.removeProduct(idProduct);
    }

    @PutMapping("/update-Product")
    public boolean updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }
   /* @PostMapping("/{idCategorie}/produits/{idProduit}")
    public void affecterProduitACategorie(@PathVariable Integer idCategorie, @PathVariable Integer idProduit) {
        productService.affcterProduitACategorie(idCategorie, idProduit);
    }*/

    @RequestMapping(value = "/currency-converter", produces = { "application/json" }, method = RequestMethod.POST)
    public ResponseEntity<Double> convertCurrencies(@RequestBody ConversionCurrency conversionCurrency) {
        Optional<Double> resultOptional = this.productService.convert(conversionCurrency);
        if (resultOptional.isPresent()) {
            return new ResponseEntity<>(resultOptional.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "/currencies", produces = { "application/json" }, method = RequestMethod.GET)
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        return new ResponseEntity<>(this.productService.getAllCurrencies(), HttpStatus.OK);
    }
    @PutMapping("/promotion/{id}/{pourcentage}")
    @ResponseBody
    public void promotion(@PathVariable("id") Integer id, @PathVariable("pourcentage") int pourcentage) {
        Product productpromotion = productService.retrieveProduct(id);
        double price = (double) productpromotion.getPrice();
        double newprice= price*(100-pourcentage)/100;
        productpromotion.setPrice(newprice);
        productService.updateProduct(productpromotion);
    }
}
