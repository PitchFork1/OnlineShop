package ua.kh.epam.task11;

import org.junit.Before;
import org.junit.Test;
import ua.kh.epam.entity.User;
import ua.kh.epam.entity.UserRegistrationBean;
import ua.kh.epam.exception.Messages;
import ua.kh.epam.service.UserService;
import ua.kh.epam.service.captcha.generator.CaptchaGenerator;
import ua.kh.epam.service.captcha.storage.CaptchaStorageService;
import ua.kh.epam.service.validator.InputValidator;
import ua.kh.epam.web.Path;
import ua.kh.epam.web.servlet.RegistrationServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class RegistrationServletTest {

    private RegistrationServlet registrationServlet = new RegistrationServlet();
    private UserService userService;
    private CaptchaStorageService captchaStorageService;
    private CaptchaGenerator captchaGenerator;
    private InputValidator inputValidator;

    private HttpServletRequest request;
    private HttpServletResponse response;

    private RequestDispatcher requestDispatcher;

    @Before
    public void create() throws NoSuchFieldException, IllegalAccessException, ServletException, IOException {
        userService = mock(UserService.class);
        Field registrationServletUserServiceField = RegistrationServlet.class.getDeclaredField("userService");
        registrationServletUserServiceField.setAccessible(true);
        registrationServletUserServiceField.set(registrationServlet, userService);

        captchaStorageService = mock(CaptchaStorageService.class);
        Field registrationServletCaptchaStorageServiceField = RegistrationServlet.class.getDeclaredField("captchaStorageService");
        registrationServletCaptchaStorageServiceField.setAccessible(true);
        registrationServletCaptchaStorageServiceField.set(registrationServlet, captchaStorageService);

        captchaGenerator = mock(CaptchaGenerator.class);
        Field registrationServletCaptchaGeneratorField = RegistrationServlet.class.getDeclaredField("captchaGenerator");
        registrationServletCaptchaGeneratorField.setAccessible(true);
        registrationServletCaptchaGeneratorField.set(registrationServlet, captchaGenerator);

        inputValidator = mock(InputValidator.class);
        Field registrationServletInputValidatorField = RegistrationServlet.class.getDeclaredField("inputValidator");
        registrationServletInputValidatorField.setAccessible(true);
        registrationServletInputValidatorField.set(registrationServlet, inputValidator);

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        requestDispatcher = mock(RequestDispatcher.class);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        mockDoGetBehaviour();

        registrationServlet.doGet(request, response);

        verifyDoGetBehaviour();
    }

    @Test
    public void testDoPostValidRegistration() throws ServletException, IOException {
        when(inputValidator.validateRegistration(any(HttpServletRequest.class), any(UserRegistrationBean.class))).thenReturn(new HashMap<>());
        doNothing().when(userService).add(any(User.class));
        doNothing().when(response).sendRedirect(Path.PAGE_MAIN);

        registrationServlet.doPost(request, response);

        verify(inputValidator, times(1)).validateRegistration(any(HttpServletRequest.class), any(UserRegistrationBean.class));
        verify(userService, times(1)).add(any(User.class));
        verify(response, times(1)).sendRedirect(Path.PAGE_MAIN);
    }

    @Test
    public void testDoPostInvalidRegistration() throws ServletException, IOException {
        Map<String, String> registrationErrors = new HashMap<>();
        registrationErrors.put("password", Messages.EXCEPTION_REGISTRATION_PASSWORD_EXISTS);
        registrationErrors.put("roge1995@gmail.com", Messages.EXCEPTION_REGISTRATION_EMAIL_EXISTS);
        when(inputValidator.validateRegistration(any(HttpServletRequest.class), any(UserRegistrationBean.class))).thenReturn(registrationErrors);
        doNothing().when(request).setAttribute(anyString(), anyObject());
        mockDoGetBehaviour();

        registrationServlet.doPost(request, response);

        verify(inputValidator, times(1)).validateRegistration(any(HttpServletRequest.class), any(UserRegistrationBean.class));
        verify(request, times(2)).setAttribute(anyString(), anyObject());
        verifyDoGetBehaviour();
    }

    @Test
    public void testDoPostRegistrationTimeout() throws ServletException, IOException {
        Map<String, String> registrationErrors = new HashMap<>();
        registrationErrors.put("timeout", Messages.EXCEPTION_REGISTRATION_TIMEOUT);
        when(inputValidator.validateRegistration(any(HttpServletRequest.class), any(UserRegistrationBean.class))).thenReturn(registrationErrors);
        doNothing().when(captchaStorageService).remove(request);
        doNothing().when(request).setAttribute(anyString(), anyObject());
        mockDoGetBehaviour();

        registrationServlet.doPost(request, response);

        verify(inputValidator, times(1)).validateRegistration(any(HttpServletRequest.class), any(UserRegistrationBean.class));
        verify(captchaStorageService, times(1)).remove(request);
        verify(request, times(1)).setAttribute(anyString(), anyObject());
        verifyDoGetBehaviour();
    }

    private void mockDoGetBehaviour() throws ServletException, IOException {
        when(captchaGenerator.generateCaptchaValue()).thenReturn("");
        doNothing().when(captchaStorageService).add(request, response, "");
        when(request.getRequestDispatcher(any(String.class))).thenReturn(requestDispatcher);
        doNothing().when(requestDispatcher).forward(request, response);
    }

    private void verifyDoGetBehaviour() throws ServletException, IOException {
        verify(captchaGenerator, times(1)).generateCaptchaValue();
        verify(captchaStorageService, times(1)).add(request, response, "");
        verify(request, times(1)).getRequestDispatcher(Path.PAGE_REGISTRATION);
        verify(requestDispatcher, times(1)).forward(request, response);
    }

}
