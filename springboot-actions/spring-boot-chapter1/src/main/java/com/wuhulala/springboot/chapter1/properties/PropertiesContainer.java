package com.wuhulala.springboot.chapter1.properties;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/3/28<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Component
@Slf4j
public class PropertiesContainer {
    private final RandomValueProperties properties;

    private final MapValueProperties mapValueProperties;
    @Autowired
    public PropertiesContainer(RandomValueProperties properties, MapValueProperties mapValueProperties) {
        this.properties = properties;
        this.mapValueProperties = mapValueProperties;
    }


    @PostConstruct
    public void openConnection() {
        log.info("Random Value >>>>>>>>>>>>" + this.properties);
        log.info("MapValueProperties >>>>>>>>>>>>" + this.mapValueProperties);
    }


}
