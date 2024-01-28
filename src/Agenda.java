import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Agenda {
    public static boolean contatoComMesmoId(List<Contato> contatos, long id) {
        for (Contato contatoExistente : contatos) {
            if (contatoExistente.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static boolean telefoneJaCadastrado(List<Contato> contatos, Long numero,String ddd) {
        if (numero == null || ddd == null) {
            return false;
        }
        for (Contato contatoExistente : contatos) {
            if (contatoExistente.getTelefones() != null) {
                for (Telefone telefoneExistente : contatoExistente.getTelefones()) {
                    if (telefoneExistente.getDdd().equals(ddd) && telefoneExistente.getNumero() == numero) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void atualizarContato(File arquivo, List<Contato> contatos,Scanner scanner)  {


        System.out.println("Digite o ID do contato que deseja atualizar:");
        Long idAtualizar = scanner.nextLong();
        scanner.nextLine();


        Contato contatoExistente = obterContatoPorId(contatos, idAtualizar);

            if (contatoExistente != null) {
                System.out.println("Oq Deseja Alterar?");
                System.out.println("1 - Nome");
                System.out.println("2 - Sobrenome");
                System.out.println("3 - Telefone(s)");
                int decisao = scanner.nextInt();
                scanner.nextLine();
                switch (decisao) {
                    case 1:
                        System.out.println("Digite o novo nome do contato:");
                        String novoNome = scanner.nextLine();
                        contatoExistente.setNome(novoNome);
                        break;
                    case 2:
                        System.out.println("Digite o novo sobrenome do contato: ");
                        String novoSobreNome = scanner.nextLine();
                        contatoExistente.setSobreNome(novoSobreNome);
                        break;
                    case 3:
                    List<Telefone> telefones = contatoExistente.getTelefones();
                    if (!telefones.isEmpty()) {
                        System.out.println("Escolha o telefone a ser atualizado:");
                        for (int i = 0; i < telefones.size(); i++) {
                            System.out.println((i + 1) + " - " + telefones.get(i).getDdd() + " " + telefones.get(i).getNumero());
                        }

                        int escolhaTelefone = scanner.nextInt();
                        scanner.nextLine();

                        if (escolhaTelefone >= 1 && escolhaTelefone <= telefones.size()) {
                            Telefone telefoneEscolhido = telefones.get(escolhaTelefone - 1);

                            System.out.println("Digite o novo DDD:");
                            String novoDDD = scanner.nextLine();

                            System.out.println("Digite o novo número:");
                            Long novoNumero = scanner.nextLong();
                            scanner.nextLine();

                            boolean telefoneJaExiste = telefoneJaCadastrado(contatos, novoNumero, novoDDD);

                            if (telefoneJaExiste) {
                                System.out.println("Telefone já existe.");
                            } else {
                                telefoneEscolhido.setDdd(novoDDD);
                                telefoneEscolhido.setNumero(novoNumero);
                            }
                        } else {
                            System.out.println("Opção de telefone inválida.");
                        }
                    } else {
                        System.out.println("O contato não possui telefones cadastrados.");
                    }
                    break;
                        default:
                        System.out.println("Opcao Invalida");
                }
            } else {
            System.out.println("ID não encontrado. Não é possível atualizar o contato.");
        }

    }
    public static void excluirContato(File arquivo, List<Contato> contatos,Scanner scanner) throws IOException {


        System.out.println("Digite o ID do contato que deseja excluir:");
        Long idExcluir = scanner.nextLong();
        scanner.nextLine();


        Contato contatoExistente = obterContatoPorId(contatos, idExcluir);

        if (contatoExistente == null) {
            System.out.println("ID não encontrado. Não é possível excluir o contato.");
        } else {

            contatos.remove(contatoExistente);


            ManipulacaoDeArquivo.atualizarArquivo(arquivo, contatos);

            System.out.println("Contato excluído com sucesso!");
        }

    }
    public static Contato obterContatoPorId(List<Contato> contatos, long id) {
        for (Contato contato : contatos) {
            if (contato.getId() == id) {
                return contato;
            }
        }
        return null;
    }



}
