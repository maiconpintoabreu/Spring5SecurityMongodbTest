# Spring Framework 5 - Security with Oauth2 -Restful - Mongodb - Testing

## Testing with Postman
*	Set the username with your {clientId}
*	Set the passwrod with your {clientSecret}
![alt text](https://raw.githubusercontent.com/maiconpintoabreu/Spring5SecurityMongodbTest/master/imgs/print1.png)
*	Select Header and add Content-Type : application/x-www-form-urlencoded
*	Select raw and add grant_type=password&username={username}&password={password}
![alt text](https://raw.githubusercontent.com/maiconpintoabreu/Spring5SecurityMongodbTest/master/imgs/print2.png)
*	Send the request the server will send the response with the access_token
![alt text](https://raw.githubusercontent.com/maiconpintoabreu/Spring5SecurityMongodbTest/master/imgs/print3.png)
*	Change the type request for Get url to localhost:8080/users
*	In Authorization select Bearer Token and copy the access_token on token field
*	Send the request and the server will response if you have the required role
![alt text](https://raw.githubusercontent.com/maiconpintoabreu/Spring5SecurityMongodbTest/master/imgs/print4.png)