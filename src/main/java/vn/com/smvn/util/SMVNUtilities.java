package vn.com.smvn.util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;

public class SMVNUtilities {

    private static String SECRET = "smvn_0030";

    public static String encryptString(String password) {

        String hash = null;
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(StringUtils.getBytesUtf8(SMVNUtilities.SECRET), "SHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(StringUtils.getBytesUtf8(password));
            hash = Hex.encodeHexString(bytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return hash;
    }
}
