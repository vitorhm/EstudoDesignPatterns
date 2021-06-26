package structure;

public class Bridge {

    public static void main(String[] args) {

        MeioComunicacao sms = new Sms();
        MeioComunicacao email = new Email();

        Comunicacao pessoal = new ComunicacaoPessoal(email);
        System.out.println(pessoal.comunica());

        Comunicacao trabalho = new ComunicacaoTrabalho(email);
        System.out.println(trabalho.comunica());
    }
}

interface MeioComunicacao {
    public String enviaMensagem();
}

class Sms implements MeioComunicacao {

    @Override
    public String enviaMensagem() {
        return "SMS";
    }
}

class Email implements MeioComunicacao {

    @Override
    public String enviaMensagem() {
        return "Email";
    }
}

abstract class Comunicacao {

    protected MeioComunicacao meioComunicacao;

    public Comunicacao(MeioComunicacao meioComunicacao) {
        this.meioComunicacao = meioComunicacao;
    }

    public abstract String comunica();
}

class ComunicacaoPessoal extends Comunicacao {

    public ComunicacaoPessoal(MeioComunicacao meioComunicacao) {
        super(meioComunicacao);
    }

    @Override
    public String comunica() {
        return "Comunica através do " + meioComunicacao.enviaMensagem() + " pessoal";
    }
}

class ComunicacaoTrabalho extends Comunicacao {

    public ComunicacaoTrabalho(MeioComunicacao meioComunicacao) {
        super(meioComunicacao);
    }

    @Override
    public String comunica() {
        return "Comunica através do " + meioComunicacao.enviaMensagem() + " de trabalho";
    }
}
