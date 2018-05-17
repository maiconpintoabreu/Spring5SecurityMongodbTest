# Spring Framework 5 - Security with Oauth2 -Restful - Mongodb - Testing
### Testing with Gradle 4.7
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
```
I will add screenshots soon as possible
```
## Dependencies
This project dependes of a external Mongodb on localhost, it will create a database named: TestDB, run the tests to create a sample. (new Feature add mongodb embedded)
### Plugins
```
* spring-context - 5.0.6.RELEASE
* spring-data-mongodb -2.0.7.RELEASE
* spring-boot-starter-web - 2.0.2.RELEASE
* spring-security-oauth2 - 2.3.3.RELEASE
* spring-security-web - 5.0.5.RELEASE
```
### Test plugins
```
* junit
* spring-boot-starter-test - 2.0.2.RELEASE
* spring-security-test - 5.0.5.RELEASE
```
### Others Informations
```
Java 8
gradle 4.7
```
