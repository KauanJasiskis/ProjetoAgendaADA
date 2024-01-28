import java.util.List;

public class Contato {
    private Long id;
    private String nome;
    private String sobreNome;
    private List<Telefone> telefones;
    public Contato(){}



    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNome(){
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public String getSobreNome(){
        return sobreNome ;
    }
    public void setSobreNome(String sobreNome){
        this.sobreNome = sobreNome;
    }
    public List<Telefone> getTelefones(){
        return telefones;
    }
    public void setTelefones(List<Telefone> telefones){
        this.telefones = telefones;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Sobrenome: " + sobreNome + ", Telefones: " + telefones;
    }
}