package com.neon.springbootprodready.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Endpoint(id = "features")
public class FeatureEndpoint
{
    private Map<String, Feature> featureMap = new ConcurrentHashMap<>();

    public FeatureEndpoint() {
        featureMap.put("department", new Feature(true));
        featureMap.put("user", new Feature(false));
        featureMap.put("authentication", new Feature(false));
    }

    @ReadOperation
    public Map<String, Feature> features() {
        return featureMap;
    }

    @ReadOperation
    public Feature checkFeatureEnabled(@Selector String featureName) {
        return featureMap.getOrDefault(featureName, new Feature(false));
    }


    @AllArgsConstructor
    @Getter
    private static class Feature
    {
        private boolean isEnabled;
    }
}
