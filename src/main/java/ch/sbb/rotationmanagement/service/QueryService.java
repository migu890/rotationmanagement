package ch.sbb.rotationmanagement.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class QueryService {

    private final Properties rotationQueries;

    public QueryService(@Qualifier("rotationQueries") Properties rotationQueries) {
        this.rotationQueries = rotationQueries;
    }

    public String getQuery(String key) {
        String query = rotationQueries.getProperty(key);

        if (query == null) {
            throw new IllegalArgumentException("Query not found: " + key);
        }

        return query;
    }
}