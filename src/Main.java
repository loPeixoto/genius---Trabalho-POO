import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String n;
        n = JOptionPane.showInputDialog(null, "Qual o seu nome?", "Bem vindo!!!", JOptionPane.PLAIN_MESSAGE);
        Jogador j1 = new Jogador(n);

    }
}