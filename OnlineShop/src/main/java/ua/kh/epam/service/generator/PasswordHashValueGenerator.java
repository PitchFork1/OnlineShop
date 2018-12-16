package ua.kh.epam.service.generator;

import java.security.NoSuchAlgorithmException;

public interface PasswordHashValueGenerator {

    String getPasswordHashValue(String password) throws NoSuchAlgorithmException;
}
