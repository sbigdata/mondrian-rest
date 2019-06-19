## Server Start


```
$ mvn spring-boot:run

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.6.RELEASE)

[      main] org.ojbc.mondrian.rest.Application       INFO  Starting Application v1.1.0 on smc-mbp.local with PID 5150 (/Users/scott/git-repos/ojbc/mondrian-rest/target/mondrian-rest-executable.war started by scott in /Users/scott/git-repos/ojbc/mondrian-rest)
[      main] org.ojbc.mondrian.rest.Application       INFO  No active profile set, falling back to default profiles: default
[      main] ationConfigEmbeddedWebApplicationContext INFO  Refreshing org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@28c97a5: startup date [Tue Feb 13 12:08:46 PST 2018]; root of context hierarchy
[nd-preinit] ibernate.validator.internal.util.Version INFO  HV000001: Hibernate Validator 5.3.5.Final
[      main] ed.tomcat.TomcatEmbeddedServletContainer INFO  Tomcat initialized with port(s): 8080 (http)
[      main] org.apache.catalina.core.StandardService INFO  Starting service [Tomcat]
[      main] org.apache.catalina.core.StandardEngine  INFO  Starting Servlet Engine: Apache Tomcat/8.5.16
[tartStop-1] org.apache.jasper.servlet.TldScanner     INFO  At least one JAR was scanned for TLDs yet contained no TLDs. Enable debug logging for this logger for a complete list of JARs that were scanned but no TLDs were found in them. Skipping unneeded JARs during scanning can improve startup time and JSP compilation time.
[tartStop-1] e.ContainerBase.[Tomcat].[localhost].[/] INFO  Initializing Spring embedded WebApplicationContext
[tartStop-1] pringframework.web.context.ContextLoader INFO  Root WebApplicationContext: initialization completed in 2601 ms
[tartStop-1] boot.web.servlet.ServletRegistrationBean INFO  Mapping servlet: 'dispatcherServlet' to [/]
[tartStop-1] .boot.web.servlet.FilterRegistrationBean INFO  Mapping filter: 'characterEncodingFilter' to: [/*]
[tartStop-1] .boot.web.servlet.FilterRegistrationBean INFO  Mapping filter: 'hiddenHttpMethodFilter' to: [/*]
[tartStop-1] .boot.web.servlet.FilterRegistrationBean INFO  Mapping filter: 'httpPutFormContentFilter' to: [/*]
[tartStop-1] .boot.web.servlet.FilterRegistrationBean INFO  Mapping filter: 'requestContextFilter' to: [/*]
[      main] .ojbc.mondrian.MondrianConnectionFactory INFO  Working around Spring Boot / Tomcat bug that occurs in standalone mode, to adjust file path found via PathMatchingResourcePatternResolver
[      main] .ojbc.mondrian.MondrianConnectionFactory INFO  Processing connection definition json found at jar:file:/Users/scott/git-repos/ojbc/mondrian-rest/target/mondrian-rest-executable.war!/WEB-INF/classes/mondrian-connections.json
[      main] .ojbc.mondrian.MondrianConnectionFactory INFO  Adding valid connection test: connection string=jdbc:hsqldb:res:test, Mondrian schema path=jar:file:/Users/scott/git-repos/ojbc/mondrian-rest/target/mondrian-rest-executable.war!/WEB-INF/classes!/test.xml
[      main] .ojbc.mondrian.MondrianConnectionFactory INFO  Adding valid connection foodmart: connection string=jdbc:hsqldb:res:foodmart;set schema "foodmart", Mondrian schema path=https://raw.githubusercontent.com/pentaho/mondrian/lagunitas/demo/FoodMart.mondrian.xml
[      main] org.ehcache.xml.XmlConfiguration         INFO  Loading Ehcache XML configuration from file:/Users/scott/git-repos/ojbc/mondrian-rest/target/mondrian-rest-executable.war!/WEB-INF/classes!/ehcache-config.xml.
[      main] org.ehcache.core.EhcacheManager          INFO  Cache 'query-cache' created in EhcacheManager.
[      main] jbc.mondrian.rest.MondrianRestController INFO  Successfully registered request authorizer class org.ojbc.mondrian.rest.DefaultRequestAuthorizer
[      main] .annotation.RequestMappingHandlerAdapter INFO  Looking for @ControllerAdvice: org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext@28c97a5: startup date [Tue Feb 13 12:08:46 PST 2018]; root of context hierarchy
[      main] .annotation.RequestMappingHandlerMapping INFO  Mapped "{[/query],methods=[POST],consumes=[application/json],produces=[application/json]}" onto public org.springframework.http.ResponseEntity<java.lang.String> org.ojbc.mondrian.rest.MondrianRestController.query(org.ojbc.mondrian.rest.QueryRequest,javax.servlet.http.HttpServletRequest) throws java.lang.Exception
[      main] .annotation.RequestMappingHandlerMapping INFO  Mapped "{[/getConnections],methods=[GET],produces=[application/json]}" onto public java.lang.String org.ojbc.mondrian.rest.MondrianRestController.getConnections() throws java.lang.Exception
[      main] .annotation.RequestMappingHandlerMapping INFO  Mapped "{[/flushCache],methods=[GET]}" onto public org.springframework.http.ResponseEntity<java.lang.Void> org.ojbc.mondrian.rest.MondrianRestController.flushCache()
[      main] .annotation.RequestMappingHandlerMapping INFO  Mapped "{[/getSchema],methods=[GET],produces=[application/xml]}" onto public org.springframework.http.ResponseEntity<java.lang.String> org.ojbc.mondrian.rest.MondrianRestController.getSchema(java.lang.String) throws java.lang.Exception
[      main] .annotation.RequestMappingHandlerMapping INFO  Mapped "{[/error]}" onto public org.springframework.http.ResponseEntity<java.util.Map<java.lang.String, java.lang.Object>> org.springframework.boot.autoconfigure.web.BasicErrorController.error(javax.servlet.http.HttpServletRequest)
[      main] .annotation.RequestMappingHandlerMapping INFO  Mapped "{[/error],produces=[text/html]}" onto public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
[      main] .servlet.handler.SimpleUrlHandlerMapping INFO  Mapped URL path [/webjars/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
[      main] .servlet.handler.SimpleUrlHandlerMapping INFO  Mapped URL path [/**] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
[      main] .servlet.handler.SimpleUrlHandlerMapping INFO  Mapped URL path [/**/favicon.ico] onto handler of type [class org.springframework.web.servlet.resource.ResourceHttpRequestHandler]
[      main] xport.annotation.AnnotationMBeanExporter INFO  Registering beans for JMX exposure on startup
[      main] g.apache.coyote.http11.Http11NioProtocol INFO  Initializing ProtocolHandler ["http-nio-8080"]
[      main] g.apache.coyote.http11.Http11NioProtocol INFO  Starting ProtocolHandler ["http-nio-8080"]
[      main] g.apache.tomcat.util.net.NioSelectorPool INFO  Using a shared selector for servlet write/read
[      main] ed.tomcat.TomcatEmbeddedServletContainer INFO  Tomcat started on port(s): 8080 (http)
[      main] org.ojbc.mondrian.rest.Application       INFO  Started Application in 6.122 seconds (JVM running for 6.796)
```

#### API 사용법

`/getConnections`: 접속 가능한 Connection(Schemas/JDBC)의 목록을 불러온다.

Example:

```
$: curl http://localhost:58080/mondrian-rest/getConnections
{
  "test" : {
    "JdbcDrivers" : "org.hsqldb.jdbc.JDBCDriver",
    "Jdbc" : "jdbc:hsqldb:res:test",
    "Description" : "Main version of test connection",
    "ConnectionDefinitionSource" : "/opt/tomcat/webapps/mondrian-rest/WEB-INF/classes/mondrian-connections.json",
    "MondrianSchemaUrl" : "file:/opt/tomcat/webapps/mondrian-rest/WEB-INF/classes/test.xml"
  },
  "foodmart" : {
    "JdbcDrivers" : "org.hsqldb.jdbc.JDBCDriver",
    "Jdbc" : "jdbc:hsqldb:res:foodmart;set schema \"foodmart\"",
    "Description" : "Pentaho/Hyde FoodMart Database",
    "ConnectionDefinitionSource" : "/opt/tomcat/webapps/mondrian-rest/WEB-INF/classes/mondrian-connections.json",
    "MondrianSchemaUrl" : "https://raw.githubusercontent.com/pentaho/mondrian/lagunitas/demo/FoodMart.mondrian.xml"
  }
}
$:
```

`/getSchema?connectionName=[name]`: 해당 Connection의 Schemas를 확인 할 수 있다.

Example:

```
$: curl -s http://localhost:58080/mondrian-rest/getSchema?connectionName=foodmart | head -30
<?xml version='1.0'?>
<Schema name='FoodMart' metamodelVersion='4.0'>
    <!--
    == This software is subject to the terms of the Eclipse Public License v1.0
    == Agreement, available at the following URL:
    == http://www.eclipse.org/legal/epl-v10.html.
    == You must accept the terms of that agreement to use this software.
    ==
    == Copyright (C) 2000-2005 Julian Hyde
    == Copyright (C) 2005-2013 Pentaho and others
    == All Rights Reserved.
    -->

    <PhysicalSchema>
        <Table name='salary'/>
        <Table name='salary' alias='salary2'/>
        <Table name='department'>
            <Key>
                <Column name='department_id'/>
            </Key>
        </Table>
        <Table name='employee'>
            <Key>
                <Column name='employee_id'/>
            </Key>
        </Table>
        <Table name='employee_closure'>
            <Key>
                <Column name='employee_id'/>
            </Key>
$:
```

`/query`: POST 로 보내며, 3가지 형태의 옵션이 필요함:

* `connectionName` 쿼리를 보낼 Connection
* `query` 보낼 MDX Query
* `tidy` Output의 형태를 설정한다. (Object):
	* `enabled` tidy로 보낼 것 인지 (true / false)
	* `simplifyNames` dimension levels을 간단하게 표시 할 것 인지 (true / false)
	* `levelNameTranslationMap` 다른 간단한 이름으로 매핑할 것 인지.

tidy False example:

```
$: curl -s -X POST -H 'Content-Type: application/json' http://localhost:58080/mondrian-rest/query -d '{
>   "connectionName" : "foodmart",
>   "query" : "select { [Measures].[Units Shipped] } on columns, NON EMPTY [Store].[Store Type].members on rows from Warehouse"
> }'
{
  "cells" : [ {
    "formattedValue" : "207726.0",
    "value" : 207726,
    "ordinal" : 0,
    "coordinates" : [ 0, 0 ],
    "error" : null
  }, {
    "formattedValue" : "64804.0",
    "value" : 64804.0,
    "ordinal" : 1,
    "coordinates" : [ 0, 1 ],
    "error" : null
  }, {
    "formattedValue" : "10759.0",
    "value" : 10759.0,
    "ordinal" : 2,
    "coordinates" : [ 0, 2 ],
    "error" : null
  }, {
    "formattedValue" : "10589.0",
    "value" : 10589.0,
    "ordinal" : 3,
    "coordinates" : [ 0, 3 ],
    "error" : null
  }, {
    "formattedValue" : "5904.0",
    "value" : 5904.0,
    "ordinal" : 4,
    "coordinates" : [ 0, 4 ],
    "error" : null
  }, {
    "formattedValue" : "115670.0",
    "value" : 115670.0,
    "ordinal" : 5,
    "coordinates" : [ 0, 5 ],
    "error" : null
  } ],
  "axes" : [ {
    "ordinal" : 0,
    "name" : "COLUMNS",
    "positions" : [ {
      "memberDimensionNames" : [ "Measures" ],
      "memberDimensionCaptions" : [ "Measures" ],
      "memberDimensionValues" : [ "Units Shipped" ]
    } ]
  }, {
    "ordinal" : 1,
    "name" : "ROWS",
    "positions" : [ {
      "memberDimensionNames" : [ "Store" ],
      "memberDimensionCaptions" : [ "Store" ],
      "memberDimensionValues" : [ "All Store Types" ]
    }, {
      "memberDimensionNames" : [ "Store" ],
      "memberDimensionCaptions" : [ "Store" ],
      "memberDimensionValues" : [ "Deluxe Supermarket" ]
    }, {
      "memberDimensionNames" : [ "Store" ],
      "memberDimensionCaptions" : [ "Store" ],
      "memberDimensionValues" : [ "Gourmet Supermarket" ]
    }, {
      "memberDimensionNames" : [ "Store" ],
      "memberDimensionCaptions" : [ "Store" ],
      "memberDimensionValues" : [ "Mid-Size Grocery" ]
    }, {
      "memberDimensionNames" : [ "Store" ],
      "memberDimensionCaptions" : [ "Store" ],
      "memberDimensionValues" : [ "Small Grocery" ]
    }, {
      "memberDimensionNames" : [ "Store" ],
      "memberDimensionCaptions" : [ "Store" ],
      "memberDimensionValues" : [ "Supermarket" ]
    } ]
  } ]
}
$:
```

`tidy` True Example : 
```
$: curl -s -X POST -H 'Content-Type: application/json' http://localhost:58080/mondrian-rest/query -d '{
>   "connectionName" : "foodmart",
>   "query" : "select { [Measures].[Units Shipped] } on columns, NON EMPTY [Store].[Store Type].children on rows from Warehouse",
>   "tidy" : {
>     "enabled" : true
>   }
> }'
{
  "values" : [ {
    "[Store].[Store Type].[Store Type]" : "Deluxe Supermarket",
    "Units Shipped" : 64804.0
  }, {
    "[Store].[Store Type].[Store Type]" : "Gourmet Supermarket",
    "Units Shipped" : 10759.0
  }, {
    "[Store].[Store Type].[Store Type]" : "Mid-Size Grocery",
    "Units Shipped" : 10589.0
  }, {
    "[Store].[Store Type].[Store Type]" : "Small Grocery",
    "Units Shipped" : 5904.0
  }, {
    "[Store].[Store Type].[Store Type]" : "Supermarket",
    "Units Shipped" : 115670.0
  } ]
}
$:
```

`tidy` True     
`simplyNames` True   
Example
```
$: curl -s -X POST -H 'Content-Type: application/json' http://localhost:58080/mondrian-rest/query -d '{
>    "connectionName" : "foodmart",
>    "query" : "select { [Measures].[Units Shipped] } on columns, NON EMPTY [Store].[Store Type].children on rows from Warehouse",
>    "tidy" : {
>      "enabled" : true,
>      "simplifyNames" : true
>    }
> }'
{
  "values" : [ {
    "Units Shipped" : 64804.0,
    "Store Type" : "Deluxe Supermarket"
  }, {
    "Units Shipped" : 10759.0,
    "Store Type" : "Gourmet Supermarket"
  }, {
    "Units Shipped" : 10589.0,
    "Store Type" : "Mid-Size Grocery"
  }, {
    "Units Shipped" : 5904.0,
    "Store Type" : "Small Grocery"
  }, {
    "Units Shipped" : 115670.0,
    "Store Type" : "Supermarket"
  } ]
}
$:
```

`tidy` :True  
`levelNameTranslationMap`:True  
Example
```
$: curl -s -X POST -H 'Content-Type: application/json' http://localhost:58080/mondrian-rest/query -d '{
>     "connectionName" : "foodmart",
>     "query" : "select { [Measures].[Units Shipped] } on columns, NON EMPTY [Store].[Store Type].children on rows from Warehouse",
>     "tidy" : {
>       "enabled" : true,
>       "simplifyNames" : true,
>       "levelNameTranslationMap" : {
>         "[Store].[Store Type].[Store Type]" : "Kind of establishment"
>       }
>     }
> }'
{
  "values" : [ {
    "Units Shipped" : 64804.0,
    "Kind of establishment" : "Deluxe Supermarket"
  }, {
    "Units Shipped" : 10759.0,
    "Kind of establishment" : "Gourmet Supermarket"
  }, {
    "Units Shipped" : 10589.0,
    "Kind of establishment" : "Mid-Size Grocery"
  }, {
    "Units Shipped" : 5904.0,
    "Kind of establishment" : "Small Grocery"
  }, {
    "Units Shipped" : 115670.0,
    "Kind of establishment" : "Supermarket"
  } ]
}
$:
```
