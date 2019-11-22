package com.test.melnyk.internetshop.filter.security;


import javax.xml.bind.annotation.XmlElement;

public class ConstraintXml {
    @XmlElement(name = "role")
    private String role;

    @XmlElement(name = "actions")
    private String actions;

    public String getRole() {
        return role;
    }

    public String getActions() {
        return actions;
    }
}
