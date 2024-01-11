package br.com.api.login.security;


import jakarta.annotation.PostConstruct;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

@Component
public class GeradorSecretyKey {

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA512");
            secretKey = keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getHmac512Key() {
        return Base64.encodeBase64String(secretKey.getEncoded());
    }

    public byte[] getHmac512KeyByte() {
        return secretKey.getEncoded();
    }
}
