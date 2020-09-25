package com.shiye.mir.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Getter
@Setter
public class RedisClusterProperties {

    /**
     * 集群节点
     */
    private List<String> nodes = new ArrayList<>();
}