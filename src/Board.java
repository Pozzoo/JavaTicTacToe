import java.io.IOException;

public class Board {
    private int isWon = 0;
    private int[][] tab;
    private boolean player = true;
    private boolean mode;

    public int getIsWon() {
        return isWon;
    }

    public void setIsWon(int isWon) {
        this.isWon = isWon;
    }

    public void setPlayer(boolean player) {
        this.player = player;
    }

    public boolean getPlayer() {
        return player;
    }

    public int[][] getTab() {
        return tab;
    }

    public void setTab(int[][] tab) {
        this.tab = tab;
    }

    public boolean isMode() {
        return mode;
    }

    public void setMode(boolean mode) {
        this.mode = mode;
    }


    public int won() {
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

    public void computPlay() {
        int col, lin, dig = 0, dig2 = 0;

        for (int i = 0; i < 3; i++) {
            col = 0;
            lin = 0;

            for (int j = 0; j < 3; j++) {
                col += tab[i][j];
                lin += tab[j][i];

                if (i == j) {
                    dig += tab[i][j];
                }
                if (i + j == 2) {
                    dig2 += tab[i][j];
                }
            }
            if (col == 2 || col == 10) {

            }
        }
    }

    public void write(int linha, int coluna) {

        if (player) {
            tab[linha][coluna] = 1;
        } else {
            tab[linha][coluna] = 5;
        }
    }

    public void step() throws IOException, InterruptedException {
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
