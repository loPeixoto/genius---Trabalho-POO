import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;

public class Jogador {
    private String nome;
    private int pontos;

    public Jogador(String n) {
        this.nome = n;
        this.pontos = 0;

    }

    public Jogador(BufferedReader b) {

        try {
            this.nome = b.readLine();
            this.pontos = Integer.parseInt(b.readLine());
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void atualizarRecorde(int p) {
        if(p > this.pontos) {
            this.pontos = p;
        }
    }

    public void salvarArq(BufferedWriter b) throws IOException {
        b.write(this.nome + "\n");
        b.write(this.pontos + "\n");

    }

    public String getName() {
        return nome;
    }

    public int getPontos() {
        return pontos;
    }
}
