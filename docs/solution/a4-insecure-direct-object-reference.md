# Insecure Direct Object Reference

An Insecure Direct Object Reference \(IDOR\) vulnerability exists in _EditUser_ functionality. Particularly in
_UserAction.java_, the _editUser_ method fails to validate _userId_ parameter to ensure that the calling user has
appropriate access to the object. This issue can be exploited to reset information for any user identified by userId.

```java
user = userService.find(getUserId());
user.setPassword(getPassword());
user.setId(getUserId());
userService.save(user);
```

An example HTTP request such as below can be used to exploit this issue:

```http
http://localhost:8080/editUser?userId=19&password=pwned&passwordConfirmation=pwned
```

Adding this IDOR vulnerability with the XSS vulnerability in the list product page creates a devastating combo:

```html
<img src="http://localhost:8080/editUser?userId=19&password=pwned&passwordConfirmation=pwned">
```

Now every time someone views the product catalogue, the URL is triggered and the password is changed.

## Solution

Any request that results in access to any system object must be duly validated and authorized before granting access. In
case of identifier based object access, consider user supplied identifiers as untrusted. Appropriate scoped query must
be used in order to prevent IDOR vulnerabilities.

In this case, we can check for the session'user to authorize the user to access the URL. This can be implemented as such:

```java
//check if user supplied ID is the same as the session's user ID
            if (getUserId() != sessionGetUser().getId() && getUserId() > 0) {
                addFieldError("userId", "You are not logged as the user");
                return INPUT;
            }
//set user's the same as the session's user
            if (sessionGetUser() != null) {
                setUser(sessionGetUser());
                setUserId(getUser().getId());
                setEmail(getUser().getEmail());
            } else return INPUT;
```
