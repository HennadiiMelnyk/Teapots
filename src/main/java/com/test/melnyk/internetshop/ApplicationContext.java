package com.test.melnyk.internetshop;

import com.test.melnyk.internetshop.cache.UserCacheProxy;
import com.test.melnyk.internetshop.dao.entitydao.ItemDao;
import com.test.melnyk.internetshop.dao.entitydao.OrderDao;
import com.test.melnyk.internetshop.dao.entitydao.UserDao;
import com.test.melnyk.internetshop.dao.entitydao.impl.ColorDaoImpl;
import com.test.melnyk.internetshop.dao.entitydao.impl.ItemDaoImpl;
import com.test.melnyk.internetshop.dao.entitydao.impl.LocationDaoImpl;
import com.test.melnyk.internetshop.dao.entitydao.impl.MaterialDaoImpl;
import com.test.melnyk.internetshop.dao.entitydao.impl.OrderDaoImpl;
import com.test.melnyk.internetshop.dao.entitydao.impl.TypeDaoImpl;
import com.test.melnyk.internetshop.dao.entitydao.impl.UserDaoImpl;
import com.test.melnyk.internetshop.filter.localeStrategy.LocaleKeeper;
import com.test.melnyk.internetshop.filter.localeStrategy.impl.LocaleKeeperCookie;
import com.test.melnyk.internetshop.filter.localeStrategy.impl.LocaleKeeperSession;
import com.test.melnyk.internetshop.repository.UserCartRepository;
import com.test.melnyk.internetshop.service.CartService;
import com.test.melnyk.internetshop.service.ItemService;
import com.test.melnyk.internetshop.service.OrderService;
import com.test.melnyk.internetshop.service.UserService;
import com.test.melnyk.internetshop.service.impl.CartServiceImpl;
import com.test.melnyk.internetshop.service.impl.ItemServiceImpl;
import com.test.melnyk.internetshop.service.impl.OrderServiceImpl;
import com.test.melnyk.internetshop.service.impl.UserServiceImpl;
import com.test.melnyk.internetshop.strategy.CaptchaContextStrategy;
import com.test.melnyk.internetshop.strategy.CaptchaSessionStrategy;
import com.test.melnyk.internetshop.strategy.CaptchaStrategy;
import com.test.melnyk.internetshop.transaction.ConnectionManager;
import com.test.melnyk.internetshop.transaction.TransactionManager;
import com.test.melnyk.internetshop.util.SQLBuilder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;

import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_STORAGE;
import static com.test.melnyk.internetshop.consts.Const.CAPTCHA_TIMEOUT_MAP;
import static com.test.melnyk.internetshop.consts.Const.CONNECTION_MANAGER;
import static com.test.melnyk.internetshop.consts.Const.CONTEXT_CAPTCHA;
import static com.test.melnyk.internetshop.consts.Const.SESSION_CAPTCHA;
import static com.test.melnyk.internetshop.consts.Const.STRATEGY_MAP;
import static com.test.melnyk.internetshop.consts.Const.TRANSACTION_MANAGER;
import static com.test.melnyk.internetshop.consts.Const.USER_CACHE_PROXY;
import static com.test.melnyk.internetshop.consts.Const.USER_DAO;
import static com.test.melnyk.internetshop.consts.Const.USER_SERVICE;

@WebListener
public class ApplicationContext implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent contextEvent) {
        PropertyResourceBundle propertyResourceBundle = null;
        try {
            File file = getFileFromResources("config.properties");
            FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
            propertyResourceBundle = new PropertyResourceBundle(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        File securityFilter = new File("security.xml");
        contextEvent.getServletContext().setAttribute("security", securityFilter);
        Map<String, LocaleKeeper> localeKeeperMap = new HashMap<>();
        localeKeeperMap.put("session", new LocaleKeeperSession());
        localeKeeperMap.put("cookie", new LocaleKeeperCookie());
        ColorDaoImpl colorDao = new ColorDaoImpl(propertyResourceBundle);
        MaterialDaoImpl materialDao = new MaterialDaoImpl(propertyResourceBundle);
        TypeDaoImpl typeDao = new TypeDaoImpl(propertyResourceBundle);
        LocationDaoImpl locationDao = new LocationDaoImpl(propertyResourceBundle);

        Map<String, CaptchaStrategy> captchaStrategyMap = new HashMap<>();
        captchaStrategyMap.put(CONTEXT_CAPTCHA, new CaptchaContextStrategy());
        captchaStrategyMap.put(SESSION_CAPTCHA, new CaptchaSessionStrategy());

        contextEvent.getServletContext().setAttribute(STRATEGY_MAP, captchaStrategyMap);
        Map<String, LocalDateTime> captchaTimeout = new HashMap<>();
        Map<String, String> captchaStorage = new HashMap<>();

        contextEvent.getServletContext().setAttribute(CAPTCHA_TIMEOUT_MAP, captchaTimeout);
        contextEvent.getServletContext().setAttribute(CAPTCHA_STORAGE, captchaStorage);

        ConnectionManager connectionManager = new ConnectionManager();
        contextEvent.getServletContext().setAttribute(CONNECTION_MANAGER, connectionManager);

        TransactionManager transactionManager = new TransactionManager(connectionManager);
        contextEvent.getServletContext().setAttribute(TRANSACTION_MANAGER, transactionManager);

        UserDao userDao = new UserDaoImpl(propertyResourceBundle);
        contextEvent.getServletContext().setAttribute(USER_DAO, userDao);

        UserCacheProxy userCacheProxy = new UserCacheProxy(userDao, transactionManager, connectionManager);
        contextEvent.getServletContext().setAttribute(USER_CACHE_PROXY, userCacheProxy);

        UserService userService = new UserServiceImpl(transactionManager, userCacheProxy);
        contextEvent.getServletContext().setAttribute(USER_SERVICE, userService);

        SQLBuilder sqlBuilder = new SQLBuilder(propertyResourceBundle);
        contextEvent.getServletContext().setAttribute("sqlBuilder", sqlBuilder);
        ItemDao itemDao = new ItemDaoImpl(propertyResourceBundle, sqlBuilder);
        contextEvent.getServletContext().setAttribute("itemDao", itemDao);
        UserCartRepository userCartRepository = new UserCartRepository();
        CartService cartService = new CartServiceImpl(userCartRepository);
        contextEvent.getServletContext().setAttribute("cartService", cartService);
        OrderDao orderDao = new OrderDaoImpl(propertyResourceBundle);
        OrderService orderService = new OrderServiceImpl(orderDao, transactionManager);
        contextEvent.getServletContext().setAttribute("orderService", orderService);

        ItemService itemService = new ItemServiceImpl(transactionManager, itemDao, colorDao, locationDao, typeDao, materialDao);
        contextEvent.getServletContext().setAttribute("itemService", itemService);

        contextEvent.getServletContext().setAttribute("localeKeeper", localeKeeperMap.get(contextEvent.getServletContext().getInitParameter("storage")));
        contextEvent.getServletContext().setAttribute("userRole", "guest");
    }

    @Override
    public void contextDestroyed(ServletContextEvent contextEvent) {
    }

    /**
     * get File from classpath
     *
     * @param fileName
     * @return
     */
    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
