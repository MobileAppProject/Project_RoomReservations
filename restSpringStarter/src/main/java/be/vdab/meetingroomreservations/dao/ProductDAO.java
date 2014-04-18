package be.vdab.meetingroomreservations.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import be.vdab.meetingroomreservations.model.Product;

import java.util.List;


@Repository
public class ProductDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Product> getActiveProducts() {

        return sessionFactory.getCurrentSession().createCriteria(Product.class).add(Restrictions.eq("active", "Y")).list();
    }

    public void updateProduct(Product product) {

        sessionFactory.getCurrentSession().update(product);
    }

    public void saveProduct(Product product) {

        sessionFactory.getCurrentSession().persist(product);
    }

}
