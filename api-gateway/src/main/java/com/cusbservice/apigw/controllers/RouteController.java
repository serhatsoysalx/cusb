package com.cusbservice.apigw.controllers;

import com.cusbservice.apigw.services.DynamicRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @Autowired
    private DynamicRouteService dynamicRouteService;

    @PostMapping("/add")
    public Mono<String> addRoute(@RequestParam String id, @RequestParam String path, @RequestParam String uri) {
        return dynamicRouteService.addRoute(id, path, uri).then(Mono.just("Route added successfully"));
    }

    @DeleteMapping("/delete")
    public Mono<String> deleteRoute(@RequestParam String id) {
        return dynamicRouteService.deleteRoute(id).then(Mono.just("Route deleted successfully"));
    }
}

