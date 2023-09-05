import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        Board board = new Board();
        int comput = 3;

        int[][] tab = new int[3][3];
        board.setTab(tab);

        boolean mode = menu();
        board.setMode(mode);

        board.step();

        if (mode) {
            umJogador(board, comput);
        } else
            doisJogadores(board);

        if (board.getIsWon() == 1) {
            System.out.println("X ganhou!!");
        } else if (board.getIsWon() == 2) {
            System.out.println("O ganhou!!");
        } else {
            System.out.println("Deu véia!!");
        }
    }

    public static boolean menu() throws IOException, InterruptedException {
        int option;

        do {
            System.out.println("""
                    [1] Um jogador
                    [2] Dois jogadores
                    """);
            System.out.println("Digite o modo de jogo desejado:");
            option = Integer.parseInt(input.nextLine());
        } while (option != 1 && option != 2);

        clearS();

        return option == 1;
    }

    public static void doisJogadores(Board board) throws IOException, InterruptedException {
        boolean onePlayer = false;
        int comput = 3;
        do {
            turn(board, comput);
            board.setPlayer(!board.getPlayer());
            board.setIsWon(board.won());
        } while (board.getIsWon() == 0);
    }

    public static void umJogador(Board board, int comput) throws IOException, InterruptedException {
        if (comput == 3) {
            comput = 2;
        } else if (comput == 2) {
            comput = 1;
        } else if (comput == 1) {
            comput = 2;
        }
        do {
            turn(board, comput);
            board.setPlayer(!board.getPlayer());

            board.setIsWon(board.won());

        } while (board.getIsWon() == 0);
    }

    public static void turn(Board board, int comput) throws IOException, InterruptedException {
        boolean jogInv;

        do {
            jogInv = false;
            if (board.getPlayer() && comput != 1) {
                System.out.println("Vez do X");
            } else if (comput != 2) {
                System.out.println("Vez do O");
            }


            jogInv = getPlay(board, jogInv);

        } while (jogInv);
        board.step();
    }

    public static boolean getPlay(Board board, boolean jogInv) {
        int linha, coluna;

        System.out.println("Digite a linha");
        linha = (Integer.parseInt(input.nextLine()) - 1);

        System.out.println("Digite a coluna");
        coluna = (Integer.parseInt(input.nextLine()) - 1);

        if (linha > 2 || linha < 0 || coluna > 2 || coluna < 0) {
            jogInv = true;
        }

        int[][] tab = board.getTab();

        if (!jogInv && tab[linha][coluna] == 0) {
            board.write(linha, coluna);
        } else {
            System.out.println("Jogada invalida!");
            System.out.println("----------------");
            jogInv = true;
        }
        return jogInv;
    }


    public static void clearS() throws IOException, InterruptedException {
        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");
    }
}
