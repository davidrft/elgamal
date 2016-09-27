package main;

import java.math.BigInteger;
import java.util.Scanner;
import atividadesbasicas.Encrypter;
import elgamal.ElGamal;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.printf("\tPROJETO DE SISTEMAS DISCRETOS");
		System.out.printf(" - ENCRIPTACAO SISTEMA ELGAMAL\n");
		System.out.printf("\t\tDavid Riff, Diego Hamilton e Natalia Soares\n");

		System.out.printf("\t\tEngenharia Eletronica - UFPE\n\n");

		System.out.println("Digite a mensagem a ser encriptada: ");
		String mensagem = sc.nextLine();
		Encrypter enc = new Encrypter();
		ElGamal eg = new ElGamal();
		BigInteger[] encriptada = eg.Encrypt(enc.Encrypt(mensagem));
		System.out.println("");
		System.out.println("p = " + eg.p.toString());
		System.out.println("g = " + eg.g.toString());
		System.out.println("t = " + eg.t.toString());
		System.out.printf("\nMensagem encriptada:\n");
		System.out.println("y = " + encriptada[0].toString());
		System.out.println("z = " + encriptada[1].toString());
		System.out.printf("\nDeseja desencriptar a mensagem? (s/n)\n");
		char opcao;
		do {
			opcao = sc.nextLine().charAt(0);
		} while (opcao != 's' && opcao != 'n');

		if (opcao == 's') {
			String desencriptada = enc.Decrypt(eg.Decrypt(encriptada));
			System.out.printf("\nMensagem desencriptada:\n%s", desencriptada);
		}
		
		System.out.printf("\n\nPressione ENTER para sair.");
		opcao = sc.nextLine().charAt(0);
		sc.close();
	}
}