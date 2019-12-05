package com.xiaoxiao.utils;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ���ַ�������MD5����
 * @author Think
 *
 */
public class MD5Utils {
	/**
	 * ���ַ������м���
	 * @param str
	 * @return
	 */
	public static String createMD5(String str){
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			//���ַ���ת��Ϊ�ֽ�����
			byte[] oldBytes=  str.getBytes("iso-8859-1");
			//���ֽ�������м���
			byte[] md5Bytes = messageDigest.digest(oldBytes);
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				byte b = md5Bytes[i];
				int val = ((int)b)&0xff;//��һ��16���Ƶ���ֵ����������
				if(val<10){
					sb.append(0);
				}
				sb.append(Integer.toHexString(val));//ת��Ϊ16����
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String hello="123";
		System.out.println(MD5Utils.createMD5(hello));
	}
}
