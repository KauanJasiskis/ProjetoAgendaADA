import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void apresentaOperaMenu(File arquivo,Scanner sc) throws IOException {

        int decisao ;


        while (true) {
            System.out.println("""
                    ##################
                    ##### AGENDA #####
                    ##################""");
            System.out.println(">>>> Contatos <<<<");
            System.out.println("Id|Nome|Sobrenome|DDD|Telefone");
            ManipulacaoDeArquivo.apresentaArquivo(arquivo);
            System.out.printf(" \n>>>> Menu <<<<\n");
            System.out.println("1 - Adicionar Contato");
            System.out.println("2 - Remover Contato");
            System.out.println("3 - Editar Contato");
            System.out.println("4 - Sair");
            System.out.println("Escolha a opcao referente a atividade que deseja realizar: ");


            decisao = sc.nextInt();
            if (decisao == 1) {
                List<Contato> contatos = ManipulacaoDeArquivo.lerArquivo(arquivo);
                ManipulacaoDeArquivo.adicionarContato(arquivo, contatos,sc);

            }
            else if (decisao == 2) {
                List<Contato> contatos = ManipulacaoDeArquivo.lerArquivo(arquivo);
                Agenda.excluirContato(arquivo, contatos,sc);
            } else if (decisao == 3) {
                List<Contato> contatos = ManipulacaoDeArquivo.lerArquivo(arquivo);
                Agenda.atualizarContato(arquivo, contatos,sc);
                ManipulacaoDeArquivo.atualizarArquivo(arquivo, contatos);
            }
           else if(decisao == 4){
                System.exit(0);

            }
           else{
                System.out.println("Entrada Invalida");
            }



        }



    }

}









