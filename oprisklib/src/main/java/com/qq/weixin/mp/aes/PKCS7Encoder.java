/**
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package com.qq.weixin.mp.aes;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * PKCS7
 */
class PKCS7Encoder {
	static Charset CHARSET = Charset.forName("utf-8");
	static int BLOCK_SIZE = 32;

	/**
	 * 
	 * 
	 * @param count 
	 * @return 
	 */
	static byte[] encode(int count) {
		// 
		int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
		if (amountToPad == 0) {
			amountToPad = BLOCK_SIZE;
		}
		// 
		char padChr = chr(amountToPad);
		String tmp = new String();
		for (int index = 0; index < amountToPad; index++) {
			tmp += padChr;
		}
		return tmp.getBytes(CHARSET);
	}

	/**
	 * 
	 * 
	 * @param decrypted 
	 * @return 
	 */
	static byte[] decode(byte[] decrypted) {
		int pad = (int) decrypted[decrypted.length - 1];
		if (pad < 1 || pad > 32) {
			pad = 0;
		}
		return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
	}

	/**
	 * ASCII
	 * 
	 * @param a 
	 * @return 
	 */
	static char chr(int a) {
		byte target = (byte) (a & 0xFF);
		return (char) target;
	}

}
