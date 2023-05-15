
import java.util.ArrayList;
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
}
