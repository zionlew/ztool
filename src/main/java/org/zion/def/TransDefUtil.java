package org.zion.def;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TransDefUtil {
	private static final char[] c1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

	private static int func1(char paramChar) {
		if ((paramChar >= 'A') && (paramChar <= 'Z'))
			return paramChar - 'A';
		if ((paramChar >= 'a') && (paramChar <= 'z'))
			return paramChar - 'a' + 26;
		if ((paramChar >= '0') && (paramChar <= '9'))
			return paramChar - '0' + 26 + 26;
		switch (paramChar) {
		default:
			break;
		case '+':
			return 62;
		case '/':
			return 63;
		case '=':
			return 0;
		}
		throw new RuntimeException("unexpected code: " + paramChar);
	}

	public static String func2(byte[] paramArrayOfByte) {
		int m = paramArrayOfByte.length;
		StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 3 / 2);
		int k = 0;
		int i;
		for (int j = 0; k <= m - 3; j = i) {
			i = (paramArrayOfByte[k] & 0xFF) << 16 | (paramArrayOfByte[(k + 1)] & 0xFF) << 8
					| paramArrayOfByte[(k + 2)] & 0xFF;
			localStringBuffer.append(c1[(i >> 18 & 0x3F)]);
			localStringBuffer.append(c1[(i >> 12 & 0x3F)]);
			localStringBuffer.append(c1[(i >> 6 & 0x3F)]);
			localStringBuffer.append(c1[(i & 0x3F)]);
			k += 3;
			i = j + 1;
			if (j >= 14) {
				i = 0;
				localStringBuffer.append(" ");
			}
		}
		if (k == m + 0 - 2) {
			i = (paramArrayOfByte[k] & 0xFF) << 16 | (paramArrayOfByte[(k + 1)] & 0xFF) << 8;
			localStringBuffer.append(c1[(i >> 18 & 0x3F)]);
			localStringBuffer.append(c1[(i >> 12 & 0x3F)]);
			localStringBuffer.append(c1[(i >> 6 & 0x3F)]);
			localStringBuffer.append("=");
		} else if (k == m + 0 - 1) {
			i = (paramArrayOfByte[k] & 0xFF) << 16;
			localStringBuffer.append(c1[(i >> 18 & 0x3F)]);
			localStringBuffer.append(c1[(i >> 12 & 0x3F)]);
			localStringBuffer.append("==");
		}
		return localStringBuffer.toString();
	}

	private static void func3(String paramString, OutputStream paramOutputStream) {
		int i = 0;
		int j = paramString.length();
		while (true)
			if ((i < j) && (paramString.charAt(i) <= ' ')) {
				i += 1;
			} else {
				if (i == j)
					return;
				int k = (func1(paramString.charAt(i)) << 18) + (func1(paramString.charAt(i + 1)) << 12)
						+ (func1(paramString.charAt(i + 2)) << 6) + func1(paramString.charAt(i + 3));
				try {
					paramOutputStream.write(k >> 16 & 0xFF);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (paramString.charAt(i + 2) == '=')
					return;
				try {
					paramOutputStream.write(k >> 8 & 0xFF);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (paramString.charAt(i + 3) == '=')
					return;
				try {
					paramOutputStream.write(k & 0xFF);
				} catch (IOException e) {
					e.printStackTrace();
				}
				i += 4;
			}
	}

	public static byte[] func4(String paramString) throws IOException {
		ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
		func3(paramString, localByteArrayOutputStream);
		byte[] paramString1 = localByteArrayOutputStream.toByteArray();
		try {
			localByteArrayOutputStream.close();
			return paramString1;
		} catch (IOException localIOException) {
			System.err.println("Error while decoding BASE64: " + localIOException.toString());
		}
		return paramString1;
	}
}