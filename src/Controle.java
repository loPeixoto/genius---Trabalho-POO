
import java.util.ArrayList;
import java.util.Random;

public class Controle {
    private ArrayList <Jogador> jogadores;
    private Jogador atual;
    private String correta;

    public Controle() {
        this.correta = "";
        this.jogadores = new ArrayList<>();
        recordista();
        this.atual = bemVindo();
    }

    public int sortear() {
        Random r = new Random();

        return r.nextInt(9) + 1;
    }

    public Jogador localizarJogador(String n) {
        for(Jogador j : this.jogadores) {
            if(n.equals(j.getName())) {
                return j;
            }
        }
        Jogador jogador = new Jogador(n);
        this.jogadores.add(jogador);
        return jogador;

    }
}
