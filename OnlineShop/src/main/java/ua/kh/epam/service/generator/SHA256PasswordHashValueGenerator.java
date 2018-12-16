package ua.kh.epam.service.generator;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256PasswordHashValueGenerator implements PasswordHashValueGenerator {

    private static final String SHA_HASH_VALUE_GENERATION_ALGORITHM = "SHA-256";

    @Override
    public String getPasswordHashValue(String password) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_HASH_VALUE_GENERATION_ALGORITHM);
        messageDigest.update(password.getBytes());

        byte byteData[] = messageDigest.digest();

        return DatatypeConverter.printHexBinary(byteData);
    }

}
