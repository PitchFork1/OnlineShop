package ua.kh.epam.service.captcha.generator;

import java.awt.image.BufferedImage;

public interface CaptchaGenerator {

    String generateCaptchaValue();

    BufferedImage generateCaptchaImage(String captchaValue);

}
