// (Requisito 7) Filme agora herda de Midia e implementa Alugavel
// Garanta que seu arquivo Filme.java está EXATAMENTE assim

public class Filme extends Midia implements Alugavel {
    
    // Atributos específicos de Filme
    private String genero;
    private int anoLancamento;
    private StatusFilme status;

    // Sobrescrevendo o método abstrato da classe pai
    @Override
    public void exibirDetalhes() {
        System.out.println("Detalhes do Filme [Título: " + super.getTitulo() + ", Gênero: " + this.genero + "]");
    }

    // Implementando métodos da interface Alugavel
    @Override
    public void alugar() {
        if (this.status == StatusFilme.DISPONIVEL) {
            this.status = StatusFilme.ALUGADO;
            System.out.println("O filme '" + getTitulo() + "' foi alugado.");
        } else {
            System.out.println("O filme '" + getTitulo() + "' já está alugado.");
        }
    }
    
    @Override
    public void devolver() {
        this.status = StatusFilme.DISPONIVEL;
        System.out.println("O filme '" + getTitulo() + "' foi devolvido.");
    }
    
    // Construtor padrão (sem argumentos)
    public Filme() {}

    // Construtor sobrecarregado (com argumentos)
    public Filme(String titulo, String genero) {
        this.setTitulo(titulo); // Usando o setter da classe pai Midia
        this.genero = genero;
        this.status = StatusFilme.DISPONIVEL;
    }

    // Getters e Setters para os atributos de Filme
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public int getAnoLancamento() { return anoLancamento; }
    public void setAnoLancamento(int anoLancamento) { this.anoLancamento = anoLancamento; }
    public StatusFilme getStatus() { return status; }
    public void setStatus(StatusFilme status) { this.status = status; }
}