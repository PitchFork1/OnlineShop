package ua.kh.epam.web.servlet;

import ua.kh.epam.service.captcha.generator.CaptchaGenerator;
import ua.kh.epam.service.captcha.generator.SixDigitsCaptchaGeneratorImpl;
import ua.kh.epam.service.captcha.storage.CaptchaStorageService;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/view_captcha")
public class ViewCaptchaServlet extends HttpServlet {

    private CaptchaStorageService captchaStorageService;
    private CaptchaGenerator captchaGenerator;

    @Override
    public void init() throws ServletException {
        captchaStorageService = (CaptchaStorageService) getServletContext().getAttribute("captchaStorageService");
        captchaGenerator = new SixDigitsCaptchaGeneratorImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captchaValue = captchaStorageService.get(request);

        BufferedImage image = captchaGenerator.generateCaptchaImage(captchaValue);

        OutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "jpeg", outputStream);
        outputStream.close();
    }

}
