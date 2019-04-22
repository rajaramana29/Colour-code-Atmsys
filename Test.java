package com.atm;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.util.AESAlgorithm;

public class Test {

	public static void main(String[] args) throws InvalidKeyException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException {
		System.out.println("This is from main method.........");
		try {
			AESAlgorithm aesAlgo = new AESAlgorithm();
			String encry = aesAlgo.encrypt("121");
			System.out.println("encrypted==="+encry);
			String decry = aesAlgo.decrypt(encry);
			System.out.println("Decrypted==="+decry);
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
