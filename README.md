
[![Build Status](https://api.travis-ci.org/maiconpintoabreu/Spring5SecurityMongodbTest.svg?branch=master)](https://travis-ci.org/maiconpintoabreu/Spring5SecurityMongodbTest)
# Spring Framework 5 - Security with Oauth2 -Restful - Mongodb - Testing
## Embedded Mongodb
*	To desactivate the embedded Mongodb on Production add the line: 
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
File: application.prod.properties

## Importing with Eclipse
*	Import...
*	Select Gradle -> Existing Gradle Project
*	In Project root directory set the root of the project
*	Finish

## Testing with Gradle
```
#gradle test
> Task :test
BUILD SUCCESSFUL in 9s
4 actionable tasks: 2 executed, 2 up-to-date
```
## Testing with Curl
```
#curl sampleId:sampleSecret@localhost:8080/oauth/token -d grant_type=password -d username=test -d password=test
{
  "access_token":"cc72d59d-aeb6-45e0-8852-3d9f5edf3750",
  "token_type":"bearer",
  "refresh_token":"1bca6680-84db-49b1-a176-8a9b1fa08af4",
  "expires_in":43027,"scope":"read write openid"
}

#curl localhost:8080/users -H "Authorization: Bearer cc72d59d-aeb6-45e0-8852-3d9f5edf3750"
[
  {"id":"6e63ad2b-ff6e-4e34-b511-50672a7748c4","name":"login","userName":"test","roles":["USER"]},
  {"id":"2a9091ab-797c-4fcb-87e1-83a85a88b583","name":"Test1","userName":"test1","roles":["USER"]},
  {"id":"b877ea7b-9c19-44fb-b8d0-14b699320269","name":"Test2","userName":"test2","roles":["USER"]}
]
```
## Testing with Postman
*	Set the username with your {clientId}
*	Set the passwrod with your {clientSecret}
![alt text](https://raw.githubusercontent.com/maiconpintoabreu/Spring5SecurityMongodbTest/master/READMEImgs/print1.png)
*	Select Header and add Content-Type : application/x-www-form-urlencoded
*	Select raw and add grant_type=password&username={username}&password={password}
![alt text](https://raw.githubusercontent.com/maiconpintoabreu/Spring5SecurityMongodbTest/master/READMEImgs/print2.png)
*	Send the request the server will send the response with the access_token
![alt text](https://raw.githubusercontent.com/maiconpintoabreu/Spring5SecurityMongodbTest/master/READMEImgs/print3.png)
*	Change the type request for Get url to localhost:8080/users
*	In Authorization select Bearer Token and copy the access_token on token field
*	Send the request and the server will response if you have the required role
![alt text](https://raw.githubusercontent.com/maiconpintoabreu/Spring5SecurityMongodbTest/master/READMEImgs/print4.png)
### Plugins
```
*	spring-context - 5.0.6.RELEASE
*	spring-data-mongodb -2.0.7.RELEASE
*	spring-boot-starter-web - 2.0.2.RELEASE
*	spring-security-oauth2 - 2.3.3.RELEASE
*	spring-security-web - 5.0.5.RELEASE
```
### Test plugins
```
*	junit
*	spring-boot-starter-test - 2.0.2.RELEASE
*	spring-security-test - 5.0.5.RELEASE
```
### Others Informations
```
Java 8
gradle 4.7
```
