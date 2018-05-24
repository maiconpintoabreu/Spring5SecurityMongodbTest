[![Build Status](https://api.travis-ci.org/maiconpintoabreu/Spring5SecurityMongodbTest.svg?branch=master)](https://travis-ci.org/maiconpintoabreu/Spring5SecurityMongodbTest)
# Spring Framework 5 - Security with Oauth2 -Restful - Mongodb - Testing

## Importing with Eclipse
*	Import...
*	Select Gradle -> Existing Gradle Project
*	In Project root directory set the root of the project
*	Finish

### Dependencies
```
*	spring-context - 5.0.6.RELEASE
*	spring-data-mongodb -2.0.7.RELEASE
*	spring-boot-starter-web - 2.0.2.RELEASE
*	spring-security-oauth2 - 2.3.3.RELEASE
*	spring-security-web - 5.0.5.RELEASE
```
### Test dependencies
```
*	junit
*	spring-boot-starter-test - 2.0.2.RELEASE
*	spring-security-test - 5.0.5.RELEASE
*	de.flapdoodle.embed.mongo - 2.0.1
```
### Others Informations
```
Java 8
Gradle 4.7
MongoDB 3.2.6
```
## Mongodb Configuration
*	You will find the configurations in [application.(prod or test).properties](https://github.com/maiconpintoabreu/Spring5SecurityMongodbTest/tree/master/src/main/resources)
```
//To configure the database name
spring.data.mongodb.database=SampleSpringTest
//To configure host of the database
spring.data.mongodb.host=localhost
//To configure the port
spring.data.mongodb.port=21234
//Remove this line to enable the Embedded MongoDB
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration
```

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

Please read [POSTMAN.md](https://github.com/maiconpintoabreu/Spring5SecurityMongodbTest/blob/master/POSTMAN.md) for details.
