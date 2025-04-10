#  Application Bootstrap

## Requirements

* Java 1.7+
* Maven 3.x+
* IntelliJ Community Edition \(2017\)

## Struts Overview

> Apache Struts is a free, open-source, MVC framework for creating elegant, modern Java web applications. It favors
> convention over configuration, is extensible using a plugin architecture, and ships with plugins to support REST, AJAX
> and JSON.

## Generating Application Skeleton

An empty struts2 application is generated using Maven _archetype_ using the command below:

```
mvn archetype:generate -B -DgroupId=com.appsecco \
                            -DartifactId=dvja \
                            -DarchetypeGroupId=org.apache.struts \
                            -DarchetypeArtifactId=struts2-archetype-blank \
                            -DarchetypeVersion=2.3.30 \
                            -DremoteRepositories=http://struts.apache.org
```

The Maven archetype will generate a minimal Java web app using Maven for dependency and build management and Struts2
framework integrated in the application. The archetype will also integrate the common libraries used in Java web
application development such as:

* Log4j
* JUnit
* Servlet

This minimal application has a default action that prints a pre-configured _message_ for testing purpose. The
application can be run for testing using the _Jetty_ web server configured in _pom.xml._

```
$ mvn jetty:run
```

![](/assets/struts2_hw.png)

## Packaging and Deployment

The application \_war \_file can be generated using Maven which in turn can be deployed in containers such as Tomcat.
The following command can be used to build and generate the application \_war \_file:

```
$ mvn clean package
```

The above command generates the deployable application _war_ in the following path relative to application source
directory:

```
target/dvja-1.0-SNAPSHOT.war
```

## Version Control

We will be using \_Git \_for version control and source code management. To setup a Git repository and commit the
generated application skeleton, we used the following commands from within the application root:

```
$ git init .
$ git add .
$ git commit -s -m "Adding struts2 archetype generated web app"
```

## IDE

The generate application can be imported in _IntelliJ Community Edition 2017_. The IDE will automatically detect the app
as a Maven project and accordingly resolve dependencies. The IDE will also automatically detect \_Git \_as the VCS Tool
and accordingly setup the required IDE configuration.

![](/assets/intellij_hw.png)

## Integrating and Configuring Spring Framework

In the execute method of many Struts 2 ActionSupport classes are statements that create objects and then have those
objects execute methods that perform needed tasks. Whenever one class creates an object of another class that introduces
a dependency between the two classes. The Spring framework makes it easier for the application developer to manage these
dependencies and helps make the application more flexible and maintainable. This tutorial will show you how to use
Struts 2 and Spring together to manage the dependencies between your ActionSupport classes and other classes in your
application.

Struts 2 provides a plugin that enables Spring to inject into the ActionSupport classes any dependent objects that are
specified in the Spring configuration file.

The SpringFramework can be integrated in a Struts2 project by adding following dependencies in _pom.xml_

```
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-core</artifactId>
    <version>${spring.version}</version>
</dependency>

<dependency>
    <groupId>org.apache.struts</groupId>
    <artifactId>struts2-spring-plugin</artifactId>
    <version>${struts2.version}</version>
</dependency>
```

The struts2 spring plugin can be activated by adding the following line in _web.xml:_

```
<listener>
  <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

The spring framework looks for its configuration file in `WEB-INF/applicationContext.xml`. A minimal configuration file
can be created as:

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
            http://www.springframework.org/schema/beans
            http://www.springframework.org/schema/beans/spring-beans.xsd">

  <bean id="userService" class="com.appsecco.dvja.services.UserService"/>
</beans>
```

This file will be used to map beans for dependency injection into ActionSupport classes.

## Integrating and Configuring Hibernate

TODO

```
<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-entitymanager</artifactId>
    <version>5.2.10.Final</version>
</dependency>

<dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-annotations</artifactId>
    <version>3.5.6-Final</version>
</dependency>
```

## References

* [https://struts.apache.org/docs/spring-and-struts-2.html](https://struts.apache.org/docs/spring-and-struts-2.html)
* [https://struts.apache.org/docs/struts-2-spring-2-jpa-ajax.html](https://struts.apache.org/docs/struts-2-spring-2-jpa-ajax.html)
* [https://struts.apache.org/docs/core-developers-guide.html](https://struts.apache.org/docs/core-developers-guide.html)



