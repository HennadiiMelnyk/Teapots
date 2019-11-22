package com.test.melnyk.internetshop.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;

public final class SQLBuilder {

    private PropertyResourceBundle propertyResourceBundle;

    public SQLBuilder(PropertyResourceBundle propertyResourceBundle) {
        this.propertyResourceBundle = propertyResourceBundle;
    }

    public  String getFilteredItems(int page, int perPage,  Map<String, String[]> parameters) {
        int offset = (page -1)* perPage;
        StringBuilder sql = new StringBuilder(propertyResourceBundle.getString("SELECT_WITH_JOINS"));
        System.out.println(sql.toString());
        String where = generateWhereClause(parameters);
        if(!where.isEmpty()) {
            sql.append(" WHERE ");
            sql.append(where);
        }
        sql.append(" LIMIT " + offset + "," + perPage);
        return sql.toString();
    }

    public  String getNumberOfFilteredItemsQuery(Map<String, String[]> parameters) {
        String result1 = propertyResourceBundle.getString("SELECT_COUNT_FROM_ITEM");
        return result1;
    }

    private  String generateWhereClause(Map<String, String[]> filterProperties) {
        StringBuilder result = new StringBuilder();
        List<String> conditions = new ArrayList<>();
        for (Map.Entry<String, String[]> stringEntry : filterProperties.entrySet()) {
            if (stringEntry.getValue().length != 0) {
                conditions.add(String.format(" %s IN (\"%s\") ", stringEntry.getKey(), String.join("\", \"", stringEntry.getValue())));
            }
        }
        result.append(String.join(" AND ", conditions));
        return  result.toString();
    }


}
