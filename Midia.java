// (Requisito 8) Classe abstrata
public abstract class Midia {

    // (Requisito 13) Atributo 'protected'
    protected String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // (Requisito 8) Método abstrato
    public abstract void exibirDetalhes();
}