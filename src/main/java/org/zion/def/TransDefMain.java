package org.zion.def;

import java.io.IOException;

public class TransDefMain {
	public static void main(String[] args) {
		String paramString = "F3hVGnVXF1By";

		try {
			paramString = new String(f(TransDefUtil.func4(paramString)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(paramString);

	}

	public static byte[] f(byte[] paramArrayOfByte) {
		if (paramArrayOfByte == null)
			return null;
		int j = paramArrayOfByte.length;
		byte[] arrayOfByte = new byte[j];
		int i = 0;
		while (true) {
			if (i >= j)
				return arrayOfByte;
			arrayOfByte[i] = ((byte) (paramArrayOfByte[i] ^ 0xFFFFFFFF));
			i += 1;
		}
	}
}
