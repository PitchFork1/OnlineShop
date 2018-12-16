package ua.kh.epam.web.listener;

import ua.kh.epam.dao.db.UserDAO;
import ua.kh.epam.dao.memory.UserDAOImpl;
import ua.kh.epam.entity.User;
import ua.kh.epam.service.UserService;
import ua.kh.epam.service.captcha.storage.CookiesCaptchaStorageService;
import ua.kh.epam.service.captcha.storage.HiddenFieldCaptchaStorageService;
import ua.kh.epam.service.captcha.storage.SessionCaptchaStorageService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();

        servletContext.setAttribute("captchaStorageService", new HiddenFieldCaptchaStorageService());

        UserDAO userDAO = new ua.kh.epam.dao.db.memory.UserDAOImpl();
        UserService userService = new UserService(userDAO);
        User admin = new User("Yehor", "Tiurin", "Yehor", "roge1995@gmail.com", "anthony222");
        userService.add(admin);

        servletContext.setAttribute("userService", userService);

        long registrationTimeout = 30000;
        servletContext.setAttribute("registrationTimeout", registrationTimeout);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }

}
