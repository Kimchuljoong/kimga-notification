package kr.co.kimga.config.mongo;

import com.mongodb.ConnectionString;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Profile("local")
@Configuration
public class LocalMongoConfig {

    private static final String MONGO_HOST = "localhost";
    private static final int MONGO_PORT = 27017;
    private static final String MONGO_DATABASE = "notification";


    @Bean(name ="notificationMongoFactory")
    public MongoDatabaseFactory notificationMongoFactory() {
        return new SimpleMongoClientDatabaseFactory(connectionString());
    }

    private ConnectionString connectionString() {
        return new ConnectionString("mongodb://" + MONGO_HOST + ":" + MONGO_PORT + "/" + MONGO_DATABASE);
    }
}
