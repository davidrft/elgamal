package atividadesbasicas;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class Encrypter {
	private char[] key;
	public BigInteger vs = BigInteger.valueOf(26);
	private SecureRandom aleatorio;

	public Encrypter() {
		aleatorio = new SecureRandom();
		this.key = new char[26];
		Arrays.fill(this.key, '0');

		for (char c = 'a'; c <= 'z'; c++) {
			int code = aleatorio.nextInt(26);

			while (this.key[code] != '0') {
				code = aleatorio.nextInt(26);
			}

			this.key[code] = c;
		}

	}

	public BigInteger Encrypt(String message) {
		BigInteger encrypted = new BigInteger("0");
		int cipher = 0;
		message = message.toLowerCase();
		message = message.replaceAll("[^a-z]", "");
		for (int i = 0; i < message.length(); i++) {
			for (int j = 0; j < 26; j++) {
				if (this.key[j] == message.charAt(i)) {
					cipher = j + 1;
				}
			}
			encrypted = encrypted.add((BigInteger.valueOf(cipher).multiply(vs.pow(i + 1))));
		}
		return encrypted;
	}

	public String Decrypt(BigInteger message) {
		StringBuilder temp = new StringBuilder();
		int resto = 0;
		while (true) {
			message = message.divide(vs);
			resto = message.mod(this.vs).intValue();
			if (resto == 0 && message.compareTo(BigInteger.ZERO) == 0)
				break;
			else if (resto == 0 && message.compareTo(BigInteger.ZERO) != 0) {
				temp.append(this.key[25]);
				message = message.subtract(vs);
			} else {
				temp.append(this.key[resto - 1]);
				message = message.subtract(BigInteger.valueOf(resto));
			}
		}

		return temp.toString();

	}
}