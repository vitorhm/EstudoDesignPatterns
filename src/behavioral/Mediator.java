package behavioral;

import java.util.ArrayList;
import java.util.Collection;

public class Mediator {

    public static void main(String[] args) {

        SalaContract sala = new Sala();

        Participante p1 = new Participante("vitor", sala);
        Participante p2 = new Participante("lorrant", sala);
        Participante p3 = new Participante("carlos", sala);
        Participante p4 = new Participante("joao", sala);
        Participante p5 = new Participante("odair", sala);
        Participante p6 = new Participante("vanice", sala);


        p1.enviaPrivado("Olá lorrant", "lorrant");
    }

}

class Participante {

    private final String user;
    private final SalaContract sala;

    Participante(String user, SalaContract sala) {
        this.user = user;
        this.sala = sala;
        sala.addParticipante(this);
    }

    public String getUser() {
        return user;
    }

    public void recebeMensagem(String from, String mensagem) {

        String finalMessage = "[Sala do " +
                user +
                "] " +
                from +
                ": " +
                mensagem;

        System.out.println(finalMessage);
    }

    public void envia(String mensagem) {
        sala.mensagem(mensagem, getUser());
    }

    public void enviaPrivado(String mensagem, String to) {
        sala.mensagemPrivada(getUser(), to, mensagem);
    }
}

interface SalaContract {

    public void addParticipante(Participante participante);

    public void mensagem(String mensagem, String from);

    public void mensagemPrivada(String from, String to, String mensagem);
}

class Sala implements SalaContract {

    private Collection<Participante> participantes = new ArrayList<>();

    @Override
    public void addParticipante(Participante participante) {

        mensagem(participante.getUser() + " entrou na sala", "Sala");

        participantes.add(participante);
    }

    @Override
    public void mensagem(String mensagem, String from) {

        for (Participante participante : participantes) {

            if (participante.getUser().equals(from)) {
                continue;
            }

            participante.recebeMensagem(from, mensagem);
        }
    }

    @Override
    public void mensagemPrivada(String from, String to, String mensagem) {

        participantes.stream()
                .filter(p -> p.getUser().equals(to))
                .findFirst()
                .ifPresent(p -> p.recebeMensagem(from, mensagem));
    }
}