package com.jpeony.redis.spring.boot.property;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yihonglei
 */
@Setter
@Getter
public class ClusterProperties {

    private List<RedisProperties> clusters;

    public ClusterProperties() {
        this.clusters = new ArrayList<>();
    }
}
