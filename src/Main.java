import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File arquivo = new File("src//Agenda.txt");
        ManipulacaoDeArquivo.criarArquivo(arquivo);
        Menu.apresentaOperaMenu(arquivo,sc);
        sc.close();


    }
}


