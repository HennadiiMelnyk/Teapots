package com.test.melnyk.internetshop.service;

import com.test.melnyk.internetshop.model.Item;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ItemService {


    void getAllItems(HttpServletRequest httpServletRequest);

    List<Item> filterByColor(String query);

    int getNumberOfPages(Map<String, String[]> parameters);

    List<Item> allFilter(int page, int perPage,Map<String,String[]> allCondition);

    Item getItemById(int id);
}
