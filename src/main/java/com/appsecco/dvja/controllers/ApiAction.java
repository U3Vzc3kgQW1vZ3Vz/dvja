package com.appsecco.dvja.controllers;


import com.appsecco.dvja.models.User;
import com.appsecco.dvja.services.SafeModeService;
import com.appsecco.dvja.services.UserService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class ApiAction extends BaseController {

    private UserService userService;
    private String login;
    private int role;

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String adminShowUsers() {
        Map results = new HashMap();
        boolean isAdmin = false;

        if (SafeModeService.isSafe()) {
            if (sessionGetUser().getName().equals("admin")) {
                isAdmin = true;
            }
        } else {
            for (Cookie c : getServletRequest().getCookies()) {
//            IDOR sink
                if (c.getName().equals("admin") && c.getValue().equals("1")) {
                    isAdmin = true;
                    break;
                }
            }
        }

        if (isAdmin) {
            List<Map<String, String>> userList = new ArrayList<Map<String, String>>();

            for (User u : userService.findAllUsers()) {
                Map<String, String> m = new HashMap<String, String>();

                m.put("id", Integer.toString(u.getId()));
                m.put("login", u.getLogin());
                m.put("email", u.getEmail());
                m.put("role", u.getRole());

                userList.add(m);
            }

            results.put("count", userList.size());
            results.put("users", userList);
        } else {
            results.put("count", 0);
            results.put("error", "Not authorized");
        }

        return renderJSON(results);
    }

    public String ping() {
        Map<String, String> results = new HashMap<String, String>();

        if (StringUtils.isEmpty(getLogin())) {
            results.put("error", "Login not set");
            return renderJSON(results);
        }
        if (SafeModeService.isSafe()) {
            if (sessionGetUser() == null) {
                results.put("error", "Not authenticated, please login first");
                return renderJSON(results);
            }
        }
        if (role == 0) {
            results.put("role:", "no role");
        } else {
            results.put("role:", Integer.toString(role));
        }
        User user = userService.findByLogin(getLogin());

        if (user != null) {
            results.put("login", getLogin());
            results.put("present", "true");
            results.put("email", user.getEmail());

        } else {
            results.put("present", "false");
        }

        return renderJSON(results);
    }

    //    Server HPP sink
    public String pingWrapper() {
        Map<String, String> results = new HashMap<>();
        if (StringUtils.isEmpty(getLogin())) {
            results.put("error", "Login not set");
            return renderJSON(results);
        }
        if (SafeModeService.isSafe()) {
            if (sessionGetUser() == null) {
                results.put("error", "Not authenticated, please login first");
                return renderJSON(results);
            }
        }
        String uri = "http://localhost:8088/api/ping?login=" + getLogin();
        try {
            URL url = new URL(uri);
            if (SafeModeService.isSafe()) {
                Map<String, String> query_pairs = new LinkedHashMap<String, String>();
                String query = url.getQuery();
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    int idx = pair.indexOf("=");
                    if (!URLDecoder.decode(pair.substring(0, idx), "UTF-8").equals("login")) {
                        results.put("error", "Invalid query " + pair);
                        return renderJSON(results);
                    }
                    if (!URLDecoder.decode(pair.substring(idx + 1), "UTF-8").equals(sessionGetUser().getName().toLowerCase())) {
                        results.put("error", "login query does not match session user");
                        return renderJSON(results);
                    }
                }
                return ping();
            }
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            int responseCode = conn.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
//               System.out.println(response.toString());
                results.put("response", response.toString());
            } else {
                System.out.println("GET request did not work.");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
        return renderJSON(results);
    }
}
