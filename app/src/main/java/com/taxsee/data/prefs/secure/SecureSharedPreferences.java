package com.taxsee.data.prefs.secure;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.fabric.sdk.android.BuildConfig;

public class SecureSharedPreferences {
    private static final String CHARSET = "UTF-8";
    private static final String KEY_TRANSFORMATION = "AES/ECB/PKCS5Padding";
    private static final String SECRET_KEY_HASH_TRANSFORMATION = "SHA-256";
    private static final String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private final boolean encryptKeys;
    private final Cipher keyWriter;
    private final SharedPreferences preferences;
    private final Cipher reader;
    private final Cipher writer;

    public static class SecurePreferencesException extends RuntimeException {
        private static final long serialVersionUID = 1;

        public SecurePreferencesException(Throwable e) {
            super(e);
        }
    }

    public SecureSharedPreferences(Context context, String preferenceName, String secureKey, boolean encryptKeys) throws SecurePreferencesException {
        try {
            this.writer = Cipher.getInstance(TRANSFORMATION);
            this.reader = Cipher.getInstance(TRANSFORMATION);
            this.keyWriter = Cipher.getInstance(KEY_TRANSFORMATION);
            initCiphers(secureKey);
            this.preferences = context.getSharedPreferences(preferenceName, 0);
            this.encryptKeys = encryptKeys;
        } catch (GeneralSecurityException e) {
            throw new SecurePreferencesException(e);
        } catch (UnsupportedEncodingException e2) {
            throw new SecurePreferencesException(e2);
        }
    }

    protected void initCiphers(String secureKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        IvParameterSpec ivSpec = getIv();
        SecretKeySpec secretKey = getSecretKey(secureKey);
        this.writer.init(1, secretKey, ivSpec);
        this.reader.init(2, secretKey, ivSpec);
        this.keyWriter.init(1, secretKey);
    }

    protected IvParameterSpec getIv() {
        byte[] iv = new byte[this.writer.getBlockSize()];
        System.arraycopy("fldsjfodasjifudslfjdsaofshaufihadsf".getBytes(), 0, iv, 0, this.writer.getBlockSize());
        return new IvParameterSpec(iv);
    }

    protected SecretKeySpec getSecretKey(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return new SecretKeySpec(createKeyBytes(key), TRANSFORMATION);
    }

    protected byte[] createKeyBytes(String key) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(SECRET_KEY_HASH_TRANSFORMATION);
        md.reset();
        return md.digest(key.getBytes(CHARSET));
    }

    public void put(String key, String value) {
        if (value == null) {
            this.preferences.edit().remove(toKey(key)).commit();
        } else {
            putValue(toKey(key), value);
        }
    }

    public boolean containsKey(String key) {
        return this.preferences.contains(toKey(key));
    }

    public void removeValue(String key) {
        this.preferences.edit().remove(toKey(key)).commit();
    }

    public String getString(String key) throws SecurePreferencesException {
        if (this.preferences.contains(toKey(key))) {
            return decrypt(this.preferences.getString(toKey(key), BuildConfig.FLAVOR));
        }
        return null;
    }

    public void clear() {
        this.preferences.edit().clear().commit();
    }

    private String toKey(String key) {
        if (this.encryptKeys) {
            return encrypt(key, this.keyWriter);
        }
        return key;
    }

    private void putValue(String key, String value) throws SecurePreferencesException {
        this.preferences.edit().putString(key, encrypt(value, this.writer)).commit();
    }

    protected String encrypt(String value, Cipher writer) throws SecurePreferencesException {
        try {
            return Base64.encodeToString(convert(writer, value.getBytes(CHARSET)), 2);
        } catch (UnsupportedEncodingException e) {
            throw new SecurePreferencesException(e);
        }
    }

    protected String decrypt(String securedEncodedValue) {
        try {
            return new String(convert(this.reader, Base64.decode(securedEncodedValue, 2)), CHARSET);
        } catch (UnsupportedEncodingException e) {
            throw new SecurePreferencesException(e);
        }
    }

    private static byte[] convert(Cipher cipher, byte[] bs) throws SecurePreferencesException {
        try {
            return cipher.doFinal(bs);
        } catch (Exception e) {
            throw new SecurePreferencesException(e);
        }
    }
}
