package com.test.melnyk.internetshop.servlet;

import com.test.melnyk.internetshop.model.Item;
import com.test.melnyk.internetshop.service.CartService;
import com.test.melnyk.internetshop.service.ItemService;
import com.test.melnyk.internetshop.util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.test.melnyk.internetshop.consts.Path.ITEM_PAGE;

@WebServlet("/items")
public class ItemServlet extends HttpServlet {
    private ItemService itemService;
    private Map<String, String> errors;

    @Override
    public void init() throws ServletException {
        itemService = (ItemService) getServletContext().getAttribute("itemService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameters = ParamUtil.getParamMap(req);
        if (req.getParameter("numberOfItems") == null || req.getParameter("numberOfItems").equals("")) {
            parameters.put("numberOfItems", new String[]{"8"});
            parameters.put("maxPage", new String[]{"1"});
        }
        req.getServletContext().setAttribute("numOfItems", parameters.get("numberOfItems")[0]);
        req.getServletContext().setAttribute("queryParams", parameters);

        req.setAttribute("pages", itemService.getNumberOfPages(parameters));
        itemService.getAllItems(req);
        Map<String, String[]> properties = getProperties(req);
        req.setAttribute("properties", properties);
        int page = getPageNo(req);
        req.setAttribute("page", page);
        List<Item> filterList = itemService.allFilter(page,getPerPage(req), properties);
        req.setAttribute("pageItems", filterList);
        System.out.println(req.getParameter("itemPerPage"));
        req.getRequestDispatcher(ITEM_PAGE).forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        int itemId = Integer.parseInt(httpServletRequest.getParameter("id"));
        CartService cartService = (CartService) httpServletRequest.getServletContext().getAttribute("cartService");
        ItemService itemService = (ItemService) httpServletRequest.getServletContext().getAttribute("itemService");
        cartService.addItemToCart(itemService.getItemById(itemId));
    }

    private int getPageNo(HttpServletRequest request){
        Integer page = tryParseInt(request.getParameter("page"));
        if(Optional.ofNullable(page).isPresent()){
            return page;
        }
        return 1;
    }

    private int getPerPage(HttpServletRequest request){
        Integer page = tryParseInt(request.getParameter("perPage"));
        if(Optional.ofNullable(page).isPresent()){
            return page;
        }
        return 10;
    }

    private Integer tryParseInt(String value) {
        Integer intValue = null;
        if (value != null) {
            try {
                intValue = Integer.parseInt(value);
            } catch (NumberFormatException e) {
            }
        }
        return intValue;
    }

    private Map<String, String[]> getProperties(HttpServletRequest httpServletRequest) {
        Map<String, String[]> properties = new HashMap<>();
        Map<String, String> map = new HashMap<>();
        map.put("color", "color");
        map.put("location", "country");
        map.put("type", "type");
        map.put("material", "material");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Optional.ofNullable(httpServletRequest.getParameterValues(entry.getKey())).ifPresent(c -> properties.put(entry.getValue(), c));
        }
        return properties;
    }
}