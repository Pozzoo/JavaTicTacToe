import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException {
        int[][] tab = new int[3][3];
        boolean player = true;
        int isWon;

        step(tab);

        do {
            turn(tab, player);
            player = !player;
            isWon = won(tab);
        } while (isWon == 0);

        if (isWon == 1) {
            System.out.println("X ganhou!!");
        } else if (isWon == 2) {
            System.out.println("O ganhou!!");
        } else {
            System.out.println("Deu véia!!");
        }

    }

    public static void turn(int[][] tab, boolean player) throws IOException, InterruptedException {
        int linha, coluna;
        boolean jogInv;

        do {
            jogInv = false;
            if (player) {
                System.out.println("Vez do X");
            } else {
                System.out.println("Vez do O");
            }

            System.out.println("Digite a linha");
            linha = (Integer.parseInt(input.nextLine()) - 1);

            System.out.println("Digite a coluna");
            coluna = (Integer.parseInt(input.nextLine()) - 1);

            if (linha > 2 || linha < 0 || coluna > 2 || coluna < 0) {
                jogInv = true;
            }

            if (!jogInv && tab[linha][coluna] == 0) {
                if (player) {
                    tab[linha][coluna] = 1;
                } else {
                    tab[linha][coluna] = 5;
                }
            } else {
                System.out.println("Jogada invalida!");
                System.out.println("----------------");
                jogInv = true;
            }
        } while (jogInv);
        step(tab);
    }

    public static int won(int[][] tab) {
        int col, lin, dig = 0, dig2 = 0, tud = 0;

        for (int i = 0; i < 3; i++) {
            col = 0;
            lin = 0;

            for (int j = 0; j < 3; j++) {
                col += tab[i][j];
                lin += tab[j][i];
                tud += tab[i][j];

                if (i == j) {
                    dig += tab[i][j];
                }
                if (i + j == 2) {
                    dig2 += tab[i][j];
                }
            }

            if (col == 3 || lin == 3 || dig == 3|| dig2 == 3) {
                return 1;
            } else if (col == 15 || lin == 15 || dig == 15 || dig2 == 15) {
                return 2;
            } else if (tud == 25) {
                return 3;
            }
        }
        return 0;
    }

    public static void step(int[][] tab) throws IOException, InterruptedException {
        String[][] tabS = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tab[i][j] == 1) {
                    tabS[i][j] = "X";
                } else if (tab[i][j] == 5) {
                    tabS[i][j] = "O";
                } else {
                    tabS[i][j] = " ";
                }
            }
        }

        String tabF1 = String.format(" %s | %s | %s ", tabS[0][0], tabS[0][1], tabS[0][2]);
        String tabF2 = String.format("---+---+---" + "\n" + " %s | %s | %s ", tabS[1][0], tabS[1][1], tabS[1][2]);
        String tabF3 = String.format("---+---+---" + "\n" + " %s | %s | %s ", tabS[2][0], tabS[2][1], tabS[2][2]);

        if (System.getProperty("os.name").contains("Windows"))
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        else
            Runtime.getRuntime().exec("clear");

        System.out.println("\n" + tabF1);
        System.out.println(tabF2);
        System.out.println(tabF3);
    }
}
