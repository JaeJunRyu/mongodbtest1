package com.mongodb.test.mongodbtest.config;

import com.mongodb.Block;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.connection.SslSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

@RequiredArgsConstructor
@Configuration
public class MongoDBConfiguration  {

    private final MongoMappingContext mongoMappingContext;

    private final String userName = "nateen";
    private final String dataBase = "admin";
    private final String passWord = "123456a";


    //mongodb://[username]:[password]@[hostname]:[port number]/[database]
    @Bean
    public MongoClient mongoClient() {
//        MongoCredential credential = MongoCredential.createCredential(userName,dataBase,passWord.toCharArray());
//        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");
//        final var sslSettings = SslSettings.builder().enabled(false).build();
//        final var mongoClientSettings = MongoClientSettings
//                .builder()
//                .applyToSslSettings((Block<SslSettings.Builder>) sslSettings)
//                .credential(credential)
//                .applyConnectionString(connectionString)
//                .build();
//        return MongoClients.create(mongoClientSettings);

        return MongoClients.create("mongodb://localhost:27017");
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(mongoClient(), dataBase);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoDatabaseFactory(), mappingMongoConverter());
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter() {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory());
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext );
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }



}
