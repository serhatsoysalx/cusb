package com.cusbservice.apigw.config;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class LoadBalancerConfig {

//    private static final Map<String, Integer> SERVICES = Map.of(
//            "auth-service", 8081
////            "user-service", 8082,
////            "order-service", 8083,
////            "payment-service", 8084
//    );
//
//    @Bean
//    public List<ServiceInstanceListSupplier> serviceInstanceSuppliers() {
//        return SERVICES.entrySet().stream()
//                .map(entry -> createServiceInstanceSupplier(entry.getKey(), "localhost", entry.getValue()))
//                .collect(Collectors.toList());
//    }
//
//    private ServiceInstanceListSupplier createServiceInstanceSupplier(String serviceId, String host, int port) {
//        return new ServiceInstanceListSupplier() {
//
//            @Override
//            public String getServiceId() {
//                return serviceId;
//            }
//
//            @Override
//            public Flux<List<ServiceInstance>> get() {
//                return Flux.just(List.of(
//                        new DefaultServiceInstance(serviceId, serviceId, host, port, false)
//                ));
//            }
//        };
//    }
}
