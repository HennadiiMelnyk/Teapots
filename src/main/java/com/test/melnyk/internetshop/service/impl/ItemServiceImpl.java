package com.test.melnyk.internetshop.service.impl;

import com.test.melnyk.internetshop.dao.entitydao.ItemDao;
import com.test.melnyk.internetshop.dao.entitydao.impl.ColorDaoImpl;
import com.test.melnyk.internetshop.dao.entitydao.impl.LocationDaoImpl;
import com.test.melnyk.internetshop.dao.entitydao.impl.MaterialDaoImpl;
import com.test.melnyk.internetshop.dao.entitydao.impl.TypeDaoImpl;
import com.test.melnyk.internetshop.model.Item;
import com.test.melnyk.internetshop.model.product.Color;
import com.test.melnyk.internetshop.model.product.Location;
import com.test.melnyk.internetshop.model.product.Material;
import com.test.melnyk.internetshop.model.product.Type;
import com.test.melnyk.internetshop.service.ItemService;
import com.test.melnyk.internetshop.transaction.TransactionManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public class ItemServiceImpl implements ItemService {

    private TransactionManager transactionManager;
    private ItemDao itemDao;
    private ColorDaoImpl colorDao;
    private LocationDaoImpl locationDao;
    private TypeDaoImpl typeDao;
    private MaterialDaoImpl materialDao;

    public ItemServiceImpl(TransactionManager transactionManager, ItemDao itemDao, ColorDaoImpl colorDao, LocationDaoImpl locationDao, TypeDaoImpl typeDao, MaterialDaoImpl materialDao) {
        this.transactionManager = transactionManager;
        this.itemDao = itemDao;
        this.colorDao = colorDao;
        this.locationDao = locationDao;
        this.typeDao = typeDao;
        this.materialDao = materialDao;
    }

    @Override
    public void getAllItems(HttpServletRequest httpServletRequest) {
        List<Item> itemList = transactionManager.doTransaction(itemDao::findAll);
        List<Color> colors = transactionManager.doTransaction(colorDao::findAll);
        List<Material> materialList = transactionManager.doTransaction(materialDao::findAll);
        List<Location> locationList = transactionManager.doTransaction(locationDao::findAll);
        List<Type> typeList = transactionManager.doTransaction(typeDao::findAll);
        httpServletRequest.setAttribute("itemList", itemList);
        httpServletRequest.setAttribute("colorList", colors);
        httpServletRequest.setAttribute("locationList", locationList);
        httpServletRequest.setAttribute("typeList", typeList);
        httpServletRequest.setAttribute("materialList", materialList);
    }

    @Override
    public List<Item> filterByColor(String query) {
        return null;
    }

//    @Override
//    public List<Item> listItemPerPage(String pageIndex, Map<String, String[]> parameters) {
//
//        return transactionManager.doTransaction(() -> itemDao.listItemPerPage(PaginationUtil.setPageNumber(pageIndex, parameters.get("maxPage")[0]), parameters));
//    }

    public int getNumberOfPages(Map<String, String[]> parameters) {
        int numberOfItems = transactionManager.doTransaction(() -> itemDao.getNumberOfItems(parameters));
        int numberOfPages = (numberOfItems / Integer.parseInt(parameters.get("numberOfItems")[0])) + 1;
        return numberOfPages;
    }

    @Override
    public List<Item> allFilter(int page, int perPage, Map<String, String[]> allCondition) {
        return transactionManager.doTransaction(() -> itemDao.getFilteredItems(page, perPage, allCondition));
    }

    @Override
    public Item getItemById(int id) {
        return transactionManager.doTransaction(()->itemDao.select(id));
    }

}