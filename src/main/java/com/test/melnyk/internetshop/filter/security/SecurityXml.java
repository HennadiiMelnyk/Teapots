package com.test.melnyk.internetshop.filter.security;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "security")
public class SecurityXml {
    @XmlElement(name = "constraints")
    @XmlJavaTypeAdapter(MapAdapter.class)
    private Map<String, String> securityMap = new HashMap<>();

    public Map<String, List<String>> getSecurityMap() {
        Map<String, List<String>> result = new HashMap<>();
        for (Map.Entry entry : securityMap.entrySet()) {
            List<String> value = new ArrayList<>(Arrays.asList(entry.getValue().toString().split(";")));
            result.put(entry.getKey().toString(), value);
        }
        return result;
    }
}