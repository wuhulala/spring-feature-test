package com.wuhulala.springboot.chapter1.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/3/29<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Component
@ConfigurationProperties("rest")
public class MapValueProperties {

    private Map<String, String> group;

    private Map<String, String> version;


    public Map<String, String> getGroup() {
        return group;
    }

    public void setGroup(Map<String, String> group) {
        this.group = group;
    }

    public Map<String, String> getVersion() {
        return version;
    }

    public void setVersion(Map<String, String> version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "MapValueProperties{" +
                "group=" + group +
                ", version=" + version +
                '}';
    }
}
