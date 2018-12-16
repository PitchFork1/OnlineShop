package ua.kh.epam.service.captcha;

public class CaptchaHolder {

    private String captchaValue;
    private String captchaValueCode;
    private long captchaGenerationTime;

    public CaptchaHolder(String captchaValue, String captchaValueCode, long captchaGenerationTime) {
        this.captchaValue = captchaValue;
        this.captchaValueCode = captchaValueCode;
        this.captchaGenerationTime = captchaGenerationTime;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public long getCaptchaGenerationTime() {
        return captchaGenerationTime;
    }

}
