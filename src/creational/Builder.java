package creational;

import java.util.Date;

public class Builder {

    public static void main(String[] args) {

        Metadados metadados = new MetadadosBuilder("Meu arquivo")
                .withAutor("Vitor Messias")
                .withDataAlteracao(new Date())
                .withDataCriacao(new Date())
                .buildMetadados();

        Pessoa pessoa = new PessoaBuilder()
                .trabalha()
                    .em("Invillia")
                    .ganha("R$ 99,90")
                .vive()
                    .em("Araraquara")
                    .na("Av. XXXX, 1520")
                .build();
    }
}

class JavaBuilder {

    public void stringBuilder() {

        StringBuilder builder = new StringBuilder();
        builder.append("First String");
        builder.append("Second String");

        String builtString = builder.toString();

    }

}

class Metadados {
    private String nome;
    private Date dataCriacao;
    private Date dataAlteracao;
    private String autor;
    private String tipoConteudo;

    public Metadados(String nome, Date dataCriacao, Date dataAlteracao, String autor, String tipoConteudo) {
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.dataAlteracao = dataAlteracao;
        this.autor = autor;
        this.tipoConteudo = tipoConteudo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTipoConteudo() {
        return tipoConteudo;
    }

    public void setTipoConteudo(String tipoConteudo) {
        this.tipoConteudo = tipoConteudo;
    }
}

// Fluent Builder
class MetadadosBuilder {

    private String nome;
    private Date dataCriacao;
    private Date dataAlteracao;
    private String autor;
    private String tipoConteudo;

    MetadadosBuilder(String nome) {
        this.nome = nome;
    }

    public MetadadosBuilder withDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
        return this;
    }

    public MetadadosBuilder withDataAlteracao(Date dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
        return this;
    }

    public MetadadosBuilder withAutor(String autor) {
        this.autor = autor;
        return this;
    }

    public Metadados buildMetadados() {
        return new Metadados(nome, dataCriacao, dataAlteracao, autor, tipoConteudo);
    }

}

// Faceted Builder
class Pessoa {

    private String nome;
    private String endereco;
    private String cidade;
    private String empresa;
    private String salario;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cidade='" + cidade + '\'' +
                ", empresa='" + empresa + '\'' +
                ", salario='" + salario + '\'' +
                '}';
    }
}

class PessoaBuilder {

    protected Pessoa pessoa = new Pessoa();

    public Pessoa build() {
        return pessoa;
    }

    public EnderecoBuilder vive() {
        return new EnderecoBuilder(pessoa);
    }

    public EmpresaBuilder trabalha() {
        return new EmpresaBuilder(pessoa);
    }

}

class EnderecoBuilder extends PessoaBuilder {

    public EnderecoBuilder(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public EnderecoBuilder na(String endereco) {
        pessoa.setEndereco(endereco);
        return this;
    }

    public EnderecoBuilder em(String cidade) {
        pessoa.setCidade(cidade);
        return this;
    }

}

class EmpresaBuilder extends PessoaBuilder {

    public EmpresaBuilder(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public EmpresaBuilder em(String empresa) {
        this.pessoa.setEmpresa(empresa);
        return this;
    }

    public EmpresaBuilder ganha(String salario) {
        this.pessoa.setSalario(salario);
        return this;
    }
}
