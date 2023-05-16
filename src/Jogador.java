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

    public String getName() {
        return name;
    }
}
