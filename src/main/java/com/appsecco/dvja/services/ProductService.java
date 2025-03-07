package com.appsecco.dvja.services;

import com.appsecco.dvja.models.Product;
import com.appsecco.dvja.models.Product;
import com.appsecco.dvja.models.Product;
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
        ArrayList<Product> resultList = new ArrayList<Product>();
        Product newProduct = findByCode(product.getCode());

        if (DbConnectionService.open()) {

            try {
                if (newProduct == null) {
                    String queryString = "INSERT INTO products (id,name,description,code,tags) VALUES (default,?, ?, ?,?)";
                    pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                    pstmt.setString(2, product.getCode());
                    pstmt.setString(1, product.getName());
                    pstmt.setString(4, product.getDescription());
                    pstmt.setString(3, product.getTags());
                } else {
                    String queryString = "UPDATE products set description=?,name=?,code=?,tags=? where id=?";
                    pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                    pstmt.setString(3, product.getCode());
                    pstmt.setString(2, product.getName());
                    pstmt.setString(1, product.getDescription());
                    pstmt.setString(4, product.getTags());
                    pstmt.setInt(5, newProduct.getId());
                    System.out.println(pstmt.toString());
                }
                boolean product_added = pstmt.execute();
                System.out.println(product_added);
                System.out.println(pstmt.toString());

            } catch (SQLException e) {
                System.out.println(pstmt.toString());
            } finally {
                DbConnectionService.close(pstmt, rs);
            }
        }
    }
    public Product find(int id) {
        List<Product> resultList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String queryString = "SELECT * FROM products where id = ?";
        if (DbConnectionService.open()) {
            try {
                pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    Product product = new Product();
                    product.setCode(rs.getString("code"));
                    product.setDescription(rs.getString("description"));
                    product.setName(rs.getString("name"));
                    product.setTags(rs.getString("tags"));
                    product.setId(rs.getInt("id"));
                    resultList.add(product);
                }
            } catch (SQLException e) {
                System.out.println(pstmt.toString());
            } finally {
                DbConnectionService.close(pstmt, rs);
            }
        }
        if (resultList.size() > 0)
            return resultList.get(0);
        else
            return null;
    }
    public List<Product> findAll() {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ArrayList<Product> resultList = new ArrayList<>();
        String queryString = "SELECT * FROM products";
        if (DbConnectionService.open()) {
            try {
                pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                rs = pstmt.executeQuery();
                while (rs.next()) {    
                    Product product = new Product();
                    product.setCode(rs.getString("code"));
                    product.setDescription(rs.getString("description"));
                    product.setName(rs.getString("name"));
                    product.setTags(rs.getString("tags"));
                    product.setId(rs.getInt("id"));
                    resultList.add(product);
                }
            } catch (SQLException e) {
                System.out.println(pstmt.toString());
            } finally {
                DbConnectionService.close(pstmt, rs);
            }

        }

        return resultList;

    }

    public List<Product> findContainingName(String name) {



        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ArrayList<Product> resultList = new ArrayList<>();
        String queryString = "SELECT * FROM products where code like ?";
        if (DbConnectionService.open()) {
            try {
                pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                pstmt.setString(1, "%" + name + "%");
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Product product = new Product();
                    product.setCode(rs.getString("code"));
                    product.setDescription(rs.getString("description"));
                    product.setName(rs.getString("name"));
                    product.setTags(rs.getString("tags"));
                    product.setId(rs.getInt("id"));
                    resultList.add(product);
                }
            } catch (SQLException e) {
                System.out.println(pstmt.toString());
            } finally {
                DbConnectionService.close(pstmt, rs);
            }

        }

        return resultList;
    }
    public Product findByCode(String code) {

            List<Product> resultList = new ArrayList<>();
            ResultSet rs = null;
            PreparedStatement pstmt = null;
            String queryString = "SELECT * FROM products where code = ?";
            if (DbConnectionService.open()) {
                try {
                    pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                    pstmt.setString(1, code);
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        Product product = new Product();
                        product.setCode(rs.getString("code"));
                        product.setDescription(rs.getString("description"));
                        product.setName(rs.getString("name"));
                        product.setTags(rs.getString("tags"));
                        product.setId(rs.getInt("id"));
                        resultList.add(product);
                    }
                } catch (SQLException e) {
                    System.out.println(pstmt.toString());
                } finally {
                    DbConnectionService.close(pstmt, rs);
                }


            }
            if (resultList.size() > 0)
                return resultList.get(0);
            else
                return null;
        }
}
