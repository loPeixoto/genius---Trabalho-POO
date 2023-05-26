
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Random;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;

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

    public Jogador bemVindo() {
        String n;
        
        n = JOptionPane.showInputDialog(null, "Qual o seu nome?", "Bem vindo!!!", JOptionPane.PLAIN_MESSAGE);
        while (n == null || n.length()==0 ) {
            n = JOptionPane.showInputDialog(null, "Qual o seu nome?", "Nome inválido", JOptionPane.PLAIN_MESSAGE);
        }
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

    public boolean errou( int pontoRodada ) {
        String[] opcoes = {"Sim", "Não"};
        int resposta = JOptionPane.showOptionDialog(null, " Você acertou " + pontoRodada + " pontos, deseja começar um novo jogo?", "Fim do jogo!", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[1]);


        if(resposta == 0) {
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

        }catch ( FileNotFoundException e){
            this.jogadores = new ArrayList<>();
            System.out.println("Arquivo de jogadores nao foi encontrado");

        } catch(IOException e){
            this.jogadores = new ArrayList<>();
            System.out.println("Não foi encontrado nenhum jogador no sistema.");
        
        } catch ( NullPointerException e){
            this.jogadores = new ArrayList<>();
            System.out.println("Arquivo vazio ou formato invalido");
        } 
    }

    public void salvaArq() {
        try{
            FileWriter f = new FileWriter("jogadores.txt");
            BufferedWriter b = new BufferedWriter(f);

            b.write(this.jogadores.size() + "\n");

            for(Jogador j : this.jogadores) {
                j.salvarArq(b);
            }
            b.close();

        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo de jogadores");
        }

    }
    
    public void jogo() {
        String tentativa = "";
        int sorteado;
        int pontoRodada;
        carregarArq();
        int RecordeSessao = 0 ;
        String JogadorRecordeSessao = "" ;

        boolean i = true;
        while(i == true){
            
            pontoRodada = 0;
            tentativa = "";
            this.correta = "";

            while(tentativa.equals(this.correta)) {
                sorteado = sortear();
                this.correta += sorteado;
                tentativa = JOptionPane.showInputDialog(null, "O novo número é: " + sorteado, "Digite a sequência completa.", JOptionPane.PLAIN_MESSAGE);

                if(tentativa.equals(this.correta)) {
                    pontoRodada += 1;
                }
            }
            
            this.atual.atualizarRecorde(pontoRodada);

            if ( pontoRodada > RecordeSessao){
                RecordeSessao = pontoRodada;
                JogadorRecordeSessao = this.atual.getName();
            }
            i = errou(pontoRodada);
            if( i == true){
                this.atual = bemVindo();
            }
        }
        
        salvaArq();
        bye(JogadorRecordeSessao, RecordeSessao);
    }
}
