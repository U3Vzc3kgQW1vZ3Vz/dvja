package com.appsecco.dvja.services;

import com.appsecco.dvja.models.Product;
import com.appsecco.dvja.models.User;
import com.mysql.jdbc.PreparedStatement;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.DigestUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class ProductService {
    private static final Logger logger = Logger.getLogger(ProductService.class);
    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.entityManager = em;
    }
    public EntityManager getEntityManager() { return this.entityManager; }

    public void save(Product product) {
        logger.debug("Saving product with name: " + product.getName());
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ArrayList<User> resultList = new ArrayList<User>();
        if (user.getPassword() != null)
            user.setPassword(hashEncodePassword(user.getPassword()));
        User newUser = findByLogin(user.getLogin());

        if (DbConnectionService.open()) {

            try {
                if (newUser == null) {
                    String queryString = "INSERT INTO users (id,name,login,email,password) VALUES (default,?, ?, ?,?)";
                    pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                    pstmt.setString(2, user.getLogin());
                    pstmt.setString(1, user.getName());
                    pstmt.setString(4, user.getPassword());
                    pstmt.setString(3, user.getEmail());
                }
                else {
                    String queryString = "UPDATE users set password=? where id=?";
                    pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                    pstmt.setString(1, user.getPassword());
                    pstmt.setInt(2, newUser.getId());
                }
                boolean user_added = pstmt.execute();
                System.out.println(pstmt.toString());

            } catch (SQLException e) {
                System.out.println(pstmt.toString());
            } finally {
                DbConnectionService.close(pstmt, rs);
            }
    }

    public Product find(int id) {
        return entityManager.find(Product.class, id);
    }

    public List<Product> findAll() {
        Query query = entityManager.createQuery("SELECT p FROM Product p");
        List<Product> resultList = query.getResultList();

        return resultList;
    }

    public List<Product> findContainingName(String name) {
        Query query = entityManager.createQuery("SELECT p FROM Product p WHERE p.name LIKE '%" + name + "%'");
        List<Product> resultList = query.getResultList();

        return resultList;
    }
}
