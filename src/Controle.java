
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

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

    public boolean errou() {
        String[] opcoes = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(null, "Deseja começar um novo jogo?", "Fim do jogo!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);


        if(resposta == JOptionPane.YES_OPTION) {
            return true;
        } else {return false;}

    }

    public void bye(String nome, int pontos) {
        Jogador recordistaGeral = recordista();
        JOptionPane.showMessageDialog(null, "Recorde da sessão: " + nome + " - " + pontos + "ponto(s) Geral: " + recordistaGeral.getName() + " - " + recordistaGeral.getPontos() + " ponto(s)", "RECORDES", JOptionPane.PLAIN_MESSAGE);
    }

    public void carregarArq() {
        try{
            FileReader f = new FileReader("jogadores.txt");
            BufferedReader b = new BufferedReader(f);

            int t = Integer.parseInt( (b.readLine()) );

            for(int i=0; i<t; i++) {
                this.jogadores.add(new Jogador(b));
            }
            b.close();
            System.out.println(this.jogadores.size() + " jogadores carregados.");

        }catch(IOException e){
            this.jogadores = new ArrayList<>();
            System.out.println("Não foi encontrado nenhum jogador no sistema.");

        }

    }
}
