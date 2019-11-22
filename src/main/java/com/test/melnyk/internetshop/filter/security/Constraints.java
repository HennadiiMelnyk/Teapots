package com.test.melnyk.internetshop.filter.security;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "constraints")
public class Constraints {
    @XmlElement(name = "constraint")
    private List<ConstraintXml> list = new ArrayList<>();

    public List<ConstraintXml> getList() {
        return list;
    }
}
