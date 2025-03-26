# Damn Vulnerable Java Application

## Requirements

* Java 1.7+
* Maven 3.x
* MySQL Server

## Configuration

### Database

Create MySQL database and credentials and configure the same in:

```
./src/main/webapp/WEB-INF/config.properties
```

### Schema Import

Import the schema into MySQL database:

```
$ mysql -u USER -pPASSWORD dvja < ./db/schema.sql
```

## Build

```
$ mvn clean package
```

The deployable `war` file is generated in targets directory.

## Run with Jetty

```
$ mvn jetty:run
```

This will start the `Jetty` server on port 8080. If your jetty server can't login then perhaps your server can't connect to the mysql server. To fix this you need to move config.properties to the target folder.
```bash
mv dvja
cp src/main/webapp/WEB-INF/config.properties target/ 
```

## Deploy in Tomcat Server

* Build app
* Copy targets/dvja.war to Tomcat webapps directory
* To serve as root application, copy as `ROOT.war` to Tomcat webapps directory.

