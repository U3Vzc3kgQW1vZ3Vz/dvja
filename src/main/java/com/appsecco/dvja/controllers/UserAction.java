package com.appsecco.dvja.controllers;

import com.appsecco.dvja.models.User;
import com.appsecco.dvja.services.SafeModeService;
import com.appsecco.dvja.services.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;


public class UserAction extends BaseController {

    private UserService userService;
    private User user;
    private String login;
    private String password;
    private String passwordConfirmation;
    private String email;
    private int userId;
    private boolean safeMode;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSafeMode() {
        return SafeModeService.isSafe();
    }

    public String edit() {
        if (StringUtils.isEmpty(getPassword()) || StringUtils.isEmpty(getPasswordConfirmation()))
            return INPUT;

        if (isSafeMode()) {
            HttpServletRequest request = ServletActionContext.getRequest();
            // Get token values from request and session
            String requestToken = request.getParameter("token");
            String sessionToken = (String) super.getSession().get("struts.tokens.token");

            // CSRF Validation: Check if the request token matches the session token

            if (getUserId() != sessionGetUser().getId() && getUserId() > 0) {
                addFieldError("userId", "You are not logged as the user");
                return INPUT;
            }
            if (sessionGetUser() != null) {
                setUser(sessionGetUser());
                setUserId(getUser().getId());
                setEmail(getUser().getEmail());
            } else return INPUT;
            if (sessionToken == null || requestToken == null || !sessionToken.equals(requestToken)) {
                addActionError("Invalid CSRF Token. Possible CSRF attack detected!");
                return INPUT; // Redirect to an error page
            }
        }

        if (!getPassword().equals(getPasswordConfirmation())) {
            addFieldError("password", "does not match confirmation");
            return INPUT;
        }
//IDOR Sink
        if (getUserId() <= 0) {
            user = userService.find(sessionGetUser().getId());
        } else {
            user = userService.find(getUserId());
        }
        if (user == null) {
            addActionError("Failed to find user with id: " + getUserId());
            return INPUT;
        }

        if (!StringUtils.isEmpty(getEmail()))
            user.setEmail(getEmail());

        user.setPassword(getPassword());
        user.setId(getUserId());
        userService.save(user);

        return SUCCESS;
    }

    public String search() {
        if (StringUtils.isEmpty(getLogin()))
            return INPUT;

        try {
            if (SafeModeService.isSafe()) {
                user = userService.findByLogin(getLogin());
            } else {
                user = userService.findByLoginUnsafe(getLogin());
            }
            if (user == null) {
                addFieldError("login", "User not found by login: " + getLogin());
                return INPUT;
            }

        } catch (Exception e) {
            addFieldError("login " ,e.getMessage());
            return INPUT;
        }


        return SUCCESS;
    }

}
