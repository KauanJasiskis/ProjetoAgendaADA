public class Telefone
{
    private Long id;
    private String ddd;

    private Long numero;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getDdd(){
        return ddd;
    }
    public void setDdd(String ddd){
        this.ddd = ddd;
    }
    public Long getNumero(){
        return numero;
    }
    public void setNumero(Long numero){
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", DDD: " + ddd + ", Número: " + numero;
    }
}
