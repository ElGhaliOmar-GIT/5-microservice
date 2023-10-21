package tn.esprit.pidev.bns.service.hadir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.webjars.NotFoundException;
import tn.esprit.pidev.bns.entity.hadir.Category;
import tn.esprit.pidev.bns.entity.hadir.Product;
import tn.esprit.pidev.bns.repository.hadir.CategorieRep;
import tn.esprit.pidev.bns.repository.hadir.ProductRep;
import tn.esprit.pidev.bns.serviceInterface.hadir.ICategorieService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CategorieService implements ICategorieService {
    @Autowired
    CategorieRep categorieRep;
    @Autowired
    ProductRep productRep;
    @Override
    public List<Category> retrieveAllCategorys() {
        return (List<Category>) categorieRep.findAll();
    }

    @Override
    public boolean updateCategory(Category category) {
        if(categorieRep.existsById(category.getIdCategory())){
            categorieRep.save(category);
            return true;
        }
        return false;
    }

    @Override
    public Category addCategory(Category category) {
        return categorieRep.save(category);
    }

    @Override
    public Category retrieveCategory(Integer idCategory) {
        return categorieRep.findById(idCategory).orElse(null);
    }

    @Override
    public boolean removeCategory(Integer idCategory) {
        if(categorieRep.existsById(idCategory)) {
            categorieRep.deleteById(idCategory);
            return true;
        }
        return false;

    }

    @Override
    public void affcterProduitACategorie(Integer idCategorie, Integer idProduct) {
        Category category = categorieRep.findById(idCategorie)
                .orElseThrow(() -> new NotFoundException("Catégorie non trouvée"));

        Product product = productRep.findById(idProduct)
                .orElseThrow(() -> new NotFoundException("Produit non trouvé"));

        category.getProducts().add(product);
        product.setCategory(category);

        categorieRep.save(category);
        productRep.save(product);
    }
    @Override
    public String getClientIp(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("https://api.ipify.org", String.class);
        return response.getBody();
    }
}
