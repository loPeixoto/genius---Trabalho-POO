
import javax.swing.JOptionPane;
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
    /* Falta verificar se o nome foi deixado em branco */
    public Jogador bemVindo() {
        String n;
        n = JOptionPane.showInputDialog(null, "Qual o seu nome?", "Bem vindo!!!", JOptionPane.PLAIN_MESSAGE);
        return localizarJogador(n);
    }

    public Jogador recordista() {
        int recorde = 0;
        Jogador jogadorRecordista = null;

        for (Jogador j : this.jogadores) {
            if(j.getPontos() > recorde) {
                jogadorRecordista = j;
                recorde = j.getPontos();
            }
        }

        return jogadorRecordista;
    }

}
