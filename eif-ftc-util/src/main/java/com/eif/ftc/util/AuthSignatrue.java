package com.eif.ftc.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 使用 HMAC-SHA1 签名方法对对encryptText进行签名并返回Base64字符串
 * 
 * @author jiangweifeng
 *
 */
public class AuthSignatrue {

	/**
	 * 加密类型
	 */
	public static final String ALGORITHM = "HmacSHA1";
	
	/**
	 * 字符编码
	 */
	public static final String ENCODING = "UTF-8";
//	
//	/**
//	 * 获取签名
//	 * @param data
//	 * @param key
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 * @throws InvalidKeyException
//	 * @throws NoSuchAlgorithmException
//	 */
//	public static String getSignatrue(String data, String key) 
//			throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
//		return getSignatrue(data, key, ENCODING, ALGORITHM);
//	}
//	
//	/**
//	 * 获取签名
//	 * @param data
//	 * @param key
//	 * @param encoding
//	 * @param algorithm
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 * @throws InvalidKeyException
//	 * @throws NoSuchAlgorithmException
//	 */
//	public static String getSignatrue(String data, String key, String encoding, String algorithm) 
//			throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
//		if (encoding == null || encoding.isEmpty()) {
//			encoding = ENCODING;
//		}
//		if (algorithm == null || algorithm.isEmpty()) {
//			algorithm = ALGORITHM;
//		}
//		System.out.println(data);
//		System.out.println(key);
//		System.out.println(encoding);
//		System.out.println(algorithm);
//		
////		byte[] encKey = key.getBytes(encoding);
////		byte[] encData = data.getBytes(encoding);
//		return getSignatrue(key.getBytes(encoding), data.getBytes(encoding), algorithm);
//	}

	/**
	 * 获取签名
	 * @param data
	 * @param key
	 * @return
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSignatrue(byte[] data, byte[] key) 
			throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
        return getSignatrue(data, key, ALGORITHM);
	}

	/**
	 * 获取签名
	 * @param data
	 * @param key
	 * @param algorithm
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getSignatrue(byte[] data, byte[] key, String algorithm) 
			throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		SecretKeySpec signingKey = new SecretKeySpec(key, algorithm);  
        Mac mac = Mac.getInstance(algorithm);  
        mac.init(signingKey); 
        
        byte[] rawHmac = mac.doFinal(data);
        return Base64.encodeBase64String(byte2hex(rawHmac).toLowerCase().getBytes(ENCODING));
	}
	
//	
//	public static byte[] hex2bytes(String hexString) {
//		char[] hex = hexString.toCharArray();
//		// 转rawData长度减半
//		int length = hex.length / 2;
//		byte[] rawData = new byte[length];
//		for (int i = 0; i < length; i++) {
//			// 先将hex转10进位数值
//			int high = Character.digit(hex[i * 2], 16);
//			int low = Character.digit(hex[i * 2 + 1], 16);
//			// 將第一個值的二進位值左平移4位,ex: 00001000 => 10000000 (8=>128)
//			// 然后与第二个值的二进位值作联集ex: 10000000 | 00001100 => 10001100 (137)
//			int value = (high << 4) | low;
//			// 与FFFFFFFF作补集
//			if (value > 127) {
//				value -= 256;
//			}
//			// 最后转回byte就OK
//			rawData[i] = (byte) value;
//		}
//		return rawData;
//	}
//	
	
	//二行制转字符串  
	public static String byte2hex(byte[] b) {
	    StringBuilder hs = new StringBuilder();
	    String stmp;
	    for (int n = 0; b!=null && n < b.length; n++) {
	        stmp = Integer.toHexString(b[n] & 0XFF);
	        if (stmp.length() == 1)
	            hs.append('0');
	        hs.append(stmp);
	    }
	    return hs.toString();
	}
	
	public static void main(String[] args) {
		String key = "DTQfWtM1JQxX73j3yTxh3uiiy1695m5L";
		String data = "Fri, 23 Jun 2017 14:59:43 GMT";
//		System.out.println(data);
		
		try {
			System.out.println(AuthSignatrue.getSignatrue(data.getBytes(AuthSignatrue.ENCODING), key.getBytes(AuthSignatrue.ENCODING)));
		} catch (InvalidKeyException | UnsupportedEncodingException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
