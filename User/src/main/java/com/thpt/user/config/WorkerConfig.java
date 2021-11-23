package com.thpt.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Getter
@Configuration
public class WorkerConfig {

    @Value("${elasticSearch.workerNum}")
    private Integer workerNum;
    
    @Value("${elasticSearch.waitQueueTime}")
    private Integer waitQueueTime;
}
