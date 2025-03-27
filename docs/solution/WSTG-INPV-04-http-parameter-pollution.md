# HTTP Parameter Pollution

The application fails to perform any validation on the query strings as it takes the query and creates an url connection to access the ping API in the [ping wrapper API](http://localhost:8080/api/wrap). Using URL encoded special characters, we can cause undefined behaviours in the api.

In this instance, we will be using %26 to add another parameter even though the wrap api only takes one parameter. So accessing _http://127.0.0.1:8080/api/wrap?login=bog%26role=3_ is equivalent to _http://127.0.0.1:8080/api/ping?login=bog%26role=3_

## Solution

To fix this, we should avoid using another http connection to get to the ping api but instead call the internal function ping. If we have to use http connections then we wrap the path in an URL object and parse manually all of the queries. If there is other query than our wrap api's parameters than we return an error. A possible implementation is as follows:

```java
            String uri = "http://"+getServletRequest().getLocalAddr()+":"+getServletRequest().getLocalPort()+"/api/ping?login=" + getLogin();
            URL url = new URL(uri);
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
```
