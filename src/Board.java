import java.io.IOException;
import java.util.Random;

import static java.lang.Math.random;

public class Board {
    private int isWon = 0;
    private int comput;
    private int[][] tab;
    private int round = 1;
    private boolean player = true;
    private boolean mode;

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getComput() {
        return comput;
    }

    public void setComput(int comput) {
        this.comput = comput;
    }

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

    public void computPlay() throws IOException, InterruptedException {
        Random random = new Random();
        int col = 0, lin = 0, dig = 0, dig2 = 0;
        int j;
        boolean played = false;

        for (int i = 0; i < 3; i++) {
            col = 0;
            lin = 0;

            for (j = 0; j < 3; j++) {
                col += tab[j][i];
                lin += tab[i][j];

                if (i == j) {
                    dig += tab[i][j];
                }
                if (i + j == 2) {
                    dig2 += tab[i][j];
                }
            }

            switch (comput) {
                case 1 -> {
                    if (col == 10) {
                        playCol(i);
                        played = true;
                    } else if (lin == 10) {
                        playLin(i);
                        played = true;
                    } else if (dig == 10) {
                        playDig();
                        played = true;
                    } else if (dig2 == 10) {
                        playDig2();
                        played = true;
                    }
                }
                case 2 -> {
                    if (col == 2) {
                        playCol(i);
                        played = true;
                    } else if (lin == 2) {
                        playLin(i);
                        played = true;
                    } else if (dig == 2) {
                        playDig();
                        played = true;
                    } else if (dig2 == 2) {
                        playDig2();
                        played = true;
                    }
                }
            }
        }

        if (round == 1) {
            int option = random.nextInt(0, 4);

            switch (option) {
                case 0 -> write(0, 0);
                case 1 -> write(0, 2);
                case 2 -> write(1, 1);
                case 3 -> write(2, 0);
                case 4 -> write(2, 2);
            }
        } else if (round == 2 && !played) {
            do {
                lin = random.nextInt(0, 2);
                col = random.nextInt(0, 2);
            } while (tab[lin][col] != 0);

            write(lin, col);
        } else if (!played) {
            boolean playable = false;
            for (int i = 0; i < 18; i++) {

                lin = random.nextInt(0, 2);
                col = random.nextInt(0, 2);

                if (tab[lin][col] == 0) {
                    playable = true;
                    break;
                }
            }
            if (playable){
                write(lin, col);
            }

        }
    }
    private void playLin(int i) throws IOException, InterruptedException {
        for (int j = 0; j < 3; j++) {
            if (tab[i][j] == 0) {
                write(i, j);
            }
        }
    }
    private void playCol(int i) throws IOException, InterruptedException {
        for (int j = 0; j < 3; j++) {
            if (tab[j][i]== 0) {
                write(j, i);
            }
        }
    }
    private void playDig() throws IOException, InterruptedException {
        for (int i = 0; i < 3; i++) {
            if (tab[i][i] == 0) {
                write(i, i);
            }
        }
    }
    private void playDig2() throws IOException, InterruptedException {
        for (int i = 0; i < 3; i++) {
            if (tab[i][2 - i] == 0) {
                write(i, 2 - i );
            }
        }
    }

    public void write(int linha, int coluna) throws IOException, InterruptedException {

        if (player) {
            tab[linha][coluna] = 1;
        } else {
            tab[linha][coluna] = 5;
        }
        step();
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
    private void debugTable() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tab[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}
