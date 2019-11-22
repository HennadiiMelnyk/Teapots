package com.test.melnyk.internetshop.dao.entitydao;

import com.test.melnyk.internetshop.dao.CrudDao;
import com.test.melnyk.internetshop.model.Item;

import java.util.List;
import java.util.Map;

public interface ItemDao extends CrudDao<Item> {

    List<Item> getFilteredItems(int page, int perPage, Map<String, String[]> allFilters);

    int getNumberOfItems(Map parameters);

}
