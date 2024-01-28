import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ManipulacaoDeArquivo {
    public static void criarArquivo(File arquivo) throws IOException {
        if (!arquivo.exists()) {
            arquivo.createNewFile();
        }

    }
    public static List<Contato> lerArquivo(File arquivo) throws IOException {
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);
        List<Contato> contatos = new ArrayList<>();

        while (br.ready()) {
            String linha = br.readLine();
            String[] partes = linha.split("\\|");
            Contato contato = new Contato();
            contato.setId(Long.parseLong(partes[0].trim()));
            contato.setNome(partes[1]);
            contato.setSobreNome(partes[2]);

            List<Telefone> telefones = new ArrayList<>();
            for (int i = 3; i < partes.length; i += 2) {
                Telefone telefone = new Telefone();
                telefone.setId(Long.parseLong(partes[0].trim()));
                telefone.setDdd(partes[i]);
                telefone.setNumero(Long.parseLong(partes[i + 1].trim()));
                telefones.add(telefone);
            }

            contato.setTelefones(telefones);
            contatos.add(contato);
        }

        br.close();
        fr.close();
        return contatos;
    }
    public static void adicionarContato(File arquivo, List<Contato> contatos,Scanner sc1) throws IOException {

        Long novoId;

        if(contatos.isEmpty()){
            System.out.println("Digite o ID do novo contato:");
            novoId = sc1.nextLong();
        }
        else{
            Long ultimoId = contatos.get(contatos.size()-1).getId();
            novoId = ultimoId + 1;
            System.out.println("O proximo id sera automaticamente atribuido: " + novoId);

        }

            sc1.nextLine();


        System.out.println("Digite o nome do novo contato:");
        String novoNome = sc1.nextLine();

        System.out.println("Digite o sobrenome do novo contato:");
        String novoSobrenome = sc1.nextLine();


        System.out.println("Digite o DDD do novo telefone:");
        String novoDDD = sc1.nextLine();

        System.out.println("Digite o número do novo telefone:");
        Long novoNumero = sc1.nextLong();

        System.out.println("Deseja cadastrar mais um telefone? (Digite 'S' para Sim ou 'N' para Não)");
        sc1.nextLine();
        String resposta = sc1.nextLine();


        String novoDDD2 = null;
        Long novoNumero2 = null;

        if (resposta.equalsIgnoreCase("S")) {
            System.out.println("Digite o DDD do segundo novo telefone:");
            novoDDD2 = sc1.nextLine();
            System.out.println("Digite o número do segundo novo telefone:");
            novoNumero2 = sc1.nextLong();

        }

        boolean idExistente = Agenda.contatoComMesmoId(contatos,novoId);
        boolean telefoneExistente = Agenda.telefoneJaCadastrado(contatos,novoNumero,novoDDD);
        boolean telefoneExistente2 = Agenda.telefoneJaCadastrado(contatos, novoNumero2, novoDDD2);
        if (idExistente) {
            System.out.println("ID já existente. Não é possível adicionar o contato.");
        }
        else if (telefoneExistente || telefoneExistente2 ) {
        System.out.println("Pelo menos um dos telefones já existe. Não é possível adicionar o contato.");
    }
        else {

            Contato novoContato = new Contato();
            novoContato.setId(novoId);
            novoContato.setNome(novoNome);
            novoContato.setSobreNome(novoSobrenome);

            novoContato.setTelefones(new ArrayList<>());

            Telefone novoTelefone = new Telefone();
            novoTelefone.setId(novoId);
            novoTelefone.setDdd(novoDDD);
            novoTelefone.setNumero(novoNumero);
            novoContato.getTelefones().add(novoTelefone);
            if (resposta.equalsIgnoreCase("S")) {
                Telefone telefone2 = new Telefone();
                telefone2.setDdd(novoDDD2);
                telefone2.setNumero(novoNumero2);
                novoContato.getTelefones().add(telefone2);
            }

            contatos.add(novoContato);


            FileWriter fw = new FileWriter(arquivo, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(novoId + "|" + novoNome + "|" + novoSobrenome);
            for (Telefone telefone : novoContato.getTelefones()) {
                bw.write("|" + telefone.getDdd() + "|" + telefone.getNumero());
            }
            bw.newLine();
            bw.close();
            fw.close();



            System.out.println("Novo contato adicionado com sucesso!");
        }


    }

    public static void atualizarArquivo(File arquivo, List<Contato> contatos) throws IOException {
        FileWriter fw = new FileWriter(arquivo, false);
        BufferedWriter bw = new BufferedWriter(fw);

        for (Contato contato : contatos) {
            bw.write(contato.getId() + "|" + contato.getNome() + "|" + contato.getSobreNome());
            if (contato.getTelefones() != null) {
                for (Telefone telefone : contato.getTelefones()) {
                    bw.write("|"  + telefone.getDdd() + "|" + telefone.getNumero());
                }
            }
            bw.newLine();
        }

        bw.close();
        fw.close();
    }
    public static void apresentaArquivo(File arquivo) throws IOException{
        FileReader fr = new FileReader(arquivo);
        BufferedReader br = new BufferedReader(fr);

        while(br.ready()){
            String linha = br.readLine();
            System.out.println(linha);
        }
        br.close();
        fr.close();


    }

}
