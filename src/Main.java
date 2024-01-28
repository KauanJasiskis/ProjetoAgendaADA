import java.io.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //para o programa funcionar corretamente troque o caminho do arquivo para onde voce deseja que ele seja criado.
        File arquivo = new File("C:\\Users\\User\\IdeaProjects\\Projeto2\\Agenda.txt");
        ManipulacaoDeArquivo.criarArquivo(arquivo);
        Menu.apresentaOperaMenu(arquivo,sc);
        sc.close();


    }
}


