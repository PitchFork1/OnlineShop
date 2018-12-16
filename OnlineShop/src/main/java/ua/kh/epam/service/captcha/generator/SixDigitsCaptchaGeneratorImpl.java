package ua.kh.epam.service.captcha.generator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class SixDigitsCaptchaGeneratorImpl implements CaptchaGenerator {

    private static final int CAPTCHA_HEIGHT = 25;
    private static final int CAPTCHA_WIDTH = 100;

    @Override
    public String generateCaptchaValue() {
        Random random = new Random();

        return Long.toString(Math.abs(random.nextInt())).substring(0, 6);
    }

    @Override
    public BufferedImage generateCaptchaImage(String captchaValue) {
        BufferedImage image = new BufferedImage(CAPTCHA_WIDTH, CAPTCHA_HEIGHT, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = image.createGraphics();

        Color[] color = {Color.RED, Color.BLUE,
                new Color(0.6662f, 0.4569f, 0.3232f), Color.BLACK,
                Color.LIGHT_GRAY, Color.YELLOW, Color.LIGHT_GRAY, Color.cyan,
                Color.GREEN, Color.black, Color.DARK_GRAY, Color.MAGENTA};
        graphics2D.setColor(color[4]); // or the background color u want

        graphics2D.fillRect(0, 0, CAPTCHA_WIDTH, CAPTCHA_HEIGHT);

        int f = 0;
        GradientPaint gp = new GradientPaint(30, 30, color[2 << f], 15, 25, color[3 << f], true);
        graphics2D.setPaint(gp);

        Font font = new Font("Verdana", Font.CENTER_BASELINE, 20);
        graphics2D.setFont(font);

        graphics2D.drawString(captchaValue, 5, 20);
        graphics2D.dispose();

        return image;
    }

}
