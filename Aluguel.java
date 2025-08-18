// Aluguel.java
import java.time.LocalDate;

public class Aluguel {

    private int id;
    private int idFilme;
    private int idCliente;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;

    // Construtor
    public Aluguel(int idFilme, int idCliente) {
        this.idFilme = idFilme;
        this.idCliente = idCliente;
        this.dataAluguel = LocalDate.now(); // Pega a data atual
        this.dataDevolucao = null; // Inicia como nulo
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFilme() {
        return idFilme;
    }

    public void setIdFilme(int idFilme) {
        this.idFilme = idFilme;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}