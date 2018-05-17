package com.maiconspas;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

import com.mongodb.MongoClient;

@Configuration
public class MongodbConfig extends AbstractMongoConfiguration {
	  /*
	   * Use the standard Mongo driver API to create a com.mongodb.MongoClient instance.
	   */
	   public @Bean MongoClient mongoClient() {
		   MongoClient client = new MongoClient("127.0.0.1");
	       return client;
	   }

	@Override
	protected String getDatabaseName() {
		return "TestDB";
	}
}