# Cross-site Request Forgery

## Add / Edit product

The application is vulnerable to a Cross-site in _Add or Edit Product_ feature. The application fails to implement
anti-CSRF token to prevent forced browsing.

```
http://dvja:8080/addEditProduct.action
```

This issue can be exploited by an attacker to force an unintended action in the web application by hosting a page with
automated form submission and enticing an user of the application to visit the page while logged in to the vulnerable
application.

### Solution

We can implement CSRF token using the  builtin struts2 csrf token. To do this we need to add a token in our form:

```java
<s:token name="token"></s:token>
```

Then we implement our token checking logic in our Action class:

```java
            HttpServletRequest request = ServletActionContext.getRequest();
            // Get token values from request and session
            String requestToken = request.getParameter("token");
            String sessionToken = (String) super.getSession().get("struts.tokens.token");
            // CSRF Validation: Check if the request token matches the session token
            if (sessionToken == null || requestToken == null || !sessionToken.equals(requestToken)) {
                addActionError("Invalid CSRF Token. Possible CSRF attack detected!");
                return INPUT; 
            }
```

Or we use the Struts 2 token interceptor, but the interceptor doesn't mesh well with our safe mode logic. This is why we went with our own logic checking; however, you can still implement it yourself using these resources.

* [Struts2 TokenSessionInterceptor](https://stackoverflow.com/questions/22802225/how-to-implement-csr-forgery-prevention-code-on-struts2) 

