package elgamal;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class ElGamal {
	public BigInteger q;
	public BigInteger p;
	public BigInteger g;
	private BigInteger s;
	public BigInteger t;
	private SecureRandom aleatorio;

	public ElGamal() {
		// Achando um primo p de sophie germain (OK)
		aleatorio = new SecureRandom();
		while (true) {
			BigInteger temp = new BigInteger(1024, aleatorio);

			q = temp.nextProbablePrime();
			p = q.add(q).add(BigInteger.ONE);
			if (p.isProbablePrime(10))
				break;
		}
		// Achando uma raiz primitiva de p (OK)
		while (true) {
			BigInteger temp = new BigInteger(1024, aleatorio);

			g = temp.mod(p);
			temp = temp.modPow(q, p);
			if (temp.compareTo(p.subtract(BigInteger.ONE)) == 0)
				break;
		}
		// Gerando a chave privada
		do {
			s = new BigInteger(1024, aleatorio);
			s = s.mod(p.subtract(BigInteger.ONE));
		} while (s.compareTo(BigInteger.ONE) < 1);
		// Finalizando a chave pública
		t = g.modPow(s, p);
	}

	public BigInteger[] Encrypt(BigInteger message) {
		BigInteger k;

		do {
			k = new BigInteger(1024, aleatorio);
			k = k.mod(p.subtract(BigInteger.ONE));
		} while (k.compareTo(BigInteger.ONE) < 1);
		BigInteger[] encrypted = new BigInteger[2];
		encrypted[0] = g.modPow(k, p);
		encrypted[1] = t.modPow(k, p).multiply(message);
		encrypted[1] = encrypted[1].mod(p);

		return encrypted;
	}

	public BigInteger Decrypt(BigInteger[] encrypted) {
		BigInteger y;
		BigInteger yinverse;
		y = encrypted[0].modPow(s, p);
		yinverse = y.modInverse(p);
		return yinverse.multiply(encrypted[1]).mod(p);
	}
}