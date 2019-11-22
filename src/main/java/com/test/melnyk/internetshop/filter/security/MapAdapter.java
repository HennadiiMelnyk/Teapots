package com.test.melnyk.internetshop.filter.security;


import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.HashMap;
import java.util.Map;

public class MapAdapter extends XmlAdapter<Constraints, Map<String, String>> {

    @Override
    public Map<String, String> unmarshal(Constraints v) throws Exception {
        Map<String, String> result = new HashMap<>();
        for (ConstraintXml constraintXml : v.getList()) {
            result.put(constraintXml.getRole(), constraintXml.getActions());
        }
        return result;
    }

    @Override
    public Constraints marshal(Map<String, String> v) throws Exception {
        return null;
    }
}
