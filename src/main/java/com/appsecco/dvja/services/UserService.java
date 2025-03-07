package com.appsecco.dvja.services;

import com.appsecco.dvja.models.User;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.DigestUtils;


import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);



    public void save(User user) {
        logger.debug("Saving user with login: " + user.getLogin() + " id: " + user.getId());
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

    }

    public User find(int id) {
        List<User> resultList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String queryString = "SELECT * FROM users where id = ?";
        if (DbConnectionService.open()) {
            try {
                pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    User user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setId(rs.getInt("id"));
                    resultList.add(user);
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

    public boolean checkPassword(User user, String password) {
        if (user == null)
            return false;
        if (StringUtils.isEmpty(password))
            return false;

        return user.getPassword().equals(hashEncodePassword(password));
    }

    public List<User> findAllUsers() {
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        ArrayList<User> resultList = new ArrayList<>();
        String queryString = "SELECT * FROM users";
        if (DbConnectionService.open()) {
            try {
                pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    User user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setId(rs.getInt("id"));
                    resultList.add(user);
                }
            } catch (SQLException e) {
                                System.out.println(pstmt.toString());
            } finally {
                DbConnectionService.close(pstmt, rs);
            }

        }

        return resultList;
    }

    public User findByLogin(String login) {

        List<User> resultList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String queryString = "SELECT * FROM users where login = ?";
        if (DbConnectionService.open()) {
            try {
                pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                pstmt.setString(1, login);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    User user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setId(rs.getInt("id"));
                    resultList.add(user);
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

    public User findByLoginUnsafe(String login) {
        List<User> resultList = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        String queryString = "SELECT * FROM users where login = '"+login+"'";
        if (DbConnectionService.open()) {
            try {
                pstmt = (PreparedStatement) DbConnectionService.cnn.prepareStatement(queryString);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    User user = new User();
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                    user.setName(rs.getString("name"));
                    user.setEmail(rs.getString("email"));
                    user.setRole(rs.getString("role"));
                    user.setId(rs.getInt("id"));
                    resultList.add(user);
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

        public boolean resetPasswordByLogin (String login, String key,
                String password, String passwordConfirmation){

            if (!StringUtils.equals(password, passwordConfirmation))
                return false;

            if (!StringUtils.equalsIgnoreCase(DigestUtils.md5DigestAsHex(login.getBytes()), key))
                return false;

            logger.info("Changing password for login: " + login +
                    " New password: " + password);

            User user = findByLogin(login);
            if (user != null) {
                user.setPassword(password);
                save(user);

                return true;
            }

            logger.info("Failed to find user with login: " + login);
            return false;
        }

        private String hashEncodePassword (String password){
            return DigestUtils.md5DigestAsHex(password.getBytes());
        }
    }
