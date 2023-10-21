package tn.esprit.pidev.bns.serviceInterface.hadir;

import tn.esprit.pidev.bns.entity.hadir.Category;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICategorieService {

    public List<Category> retrieveAllCategorys();

    public boolean updateCategory (Category  category);

    public  Category addCategory (Category category);

    public Category retrieveCategory (Integer  idCategory);

    public  boolean removeCategory(Integer idCategory);
    public  void affcterProduitACategorie(Integer idCategorie, Integer idProduct);
    String getClientIp(HttpServletRequest request);
}
