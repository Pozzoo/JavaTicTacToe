import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        Board board = new Board();
        board.setComput(3);

        int[][] tab = new int[3][3];
        board.setTab(tab);

        int mode;
        do {
            mode = menu();

            int opcao;
            do {
                if (mode == 3)
                    break;

                board.setMode(mode == 1);

                board.step();

                if (mode == 1) {
                    umJogador(board);
                } else
                    doisJogadores(board);

                if (board.getIsWon() == 1) {
                    System.out.println("X ganhou!!");
                } else if (board.getIsWon() == 2) {
                    System.out.println("O ganhou!!");
                } else {
                    System.out.println("Deu véia!!");
                }

                System.out.println("""
                        [1] Jogar novamente
                        [2] Menu
                        """);
                opcao = Integer.parseInt(input.nextLine());
            } while (opcao == 1);
        } while (mode != 3);
    }

    public static int menu() throws IOException, InterruptedException {
        int option;

        do {
            System.out.println("""
                    [1] Um jogador
                    [2] Dois jogadores
                    [3] Sair
                    """);
            System.out.println("Digite o modo de jogo desejado:");
            option = Integer.parseInt(input.nextLine());
        } while (option != 1 && option != 2 && option != 3);

        clearS();

        return option;
    }

    public static void doisJogadores(Board board) throws IOException, InterruptedException {
        board.setComput(3);
        do {
            turn(board);
            board.setPlayer(!board.getPlayer());
            board.setIsWon(board.won());
        } while (board.getIsWon() == 0);
    }

    public static void umJogador(Board board) throws IOException, InterruptedException {
        if (board.getComput() == 3) {
            board.setComput(2);
        } else if (board.getComput() == 2) {
            board.setComput(1);
        } else if (board.getComput() == 1) {
            board.setComput(2);
        }
        do {
            if (board.getComput() == 2)
                turn(board);
            else
                board.computPlay();

            board.setPlayer(!board.getPlayer());
            board.setRound(board.getRound() + 1);
            board.setIsWon(board.won());

            if (board.getIsWon() != 0)
                break;

            if (board.getComput() == 2)
                board.computPlay();
            else
                turn(board);

            board.setPlayer(!board.getPlayer());
            board.setIsWon(board.won());

        } while (board.getIsWon() == 0);
    }

    public static void turn(Board board) throws IOException, InterruptedException {
        boolean jogInv;

        do {
            jogInv = false;
            if (board.getPlayer() && board.getComput() != 1) {
                System.out.println("Vez do X");
            } else if (board.getComput() != 2) {
                System.out.println("Vez do O");
            }

            jogInv = getPlay(board, jogInv);

        } while (jogInv);
    }

    public static boolean getPlay(Board board, boolean jogInv) throws IOException, InterruptedException {
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
