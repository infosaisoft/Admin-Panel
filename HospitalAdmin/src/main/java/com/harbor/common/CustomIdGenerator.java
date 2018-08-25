package com.harbor.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CustomIdGenerator {

	// 10 Digit Unique ID Creator
	   private static final long LIMIT = 10000000000L;
	   private static long last = 0;
	   public static long getID() {
	     // 10 digits.
	     long id = System.currentTimeMillis() % LIMIT;
	     if ( id <= last ) {
	       id = (last + 1) % LIMIT;
	     }
	     return last = id;
	   }
	   
	   
	   // hash password
	   public String generateHash(String password) {
	       StringBuilder hash = new StringBuilder();

	       try {
	           MessageDigest sha = MessageDigest.getInstance("SHA-1");
	           byte[] hashedBytes = sha.digest(password.getBytes());
	           char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
	                           'a', 'b', 'c', 'd', 'e', 'f' };
	           for (int idx = 0; idx < hashedBytes.length; ++idx) {
	                   byte b = hashedBytes[idx];
	                   hash.append(digits[(b & 0xf0) >> 4]);
	                   hash.append(digits[b & 0x0f]);
	           }
	       } catch (NoSuchAlgorithmException e) {
	            System.out.println(e);
	       }

	       return hash.toString();
	   }
}
