package org.example.logistics.controller;

import jakarta.validation.Valid;
import org.example.logistics.service.LogisticsService;
import org.example.logistics.data.OptimizedLoad;
import org.example.logistics.data.LoadInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogisticsController {

    @Autowired
    LogisticsService logisticsService;

    @RequestMapping(value = "/api/v1/load-optimizer/optimize", method = RequestMethod.POST)
    public OptimizedLoad optimizeTheLoad(@Valid @RequestBody LoadInfo loadInfo){



        return logisticsService.optimizeTheLoad(loadInfo);
    }
}
