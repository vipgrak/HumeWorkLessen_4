import java.util.Random;
import java.util.Scanner;
public class Lessen_4 {
    public static char[][] map;
    public static final int SIZE = 3;
    public static final int DOTS_TO_WIN = 3;
    public static final char DOTS_EMPTY = '.';
    public static final char DOTS_X = 'X';
    public static final char DOTS_O = 'O';

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true){
            humanTurn();
            printMap();
            if (isWinner(DOTS_X)|| diagonal(DOTS_X)){
                System.out.println("Победил человек!");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья!");
                break;
            }
            aiTurn();
            printMap();
            if (isWinner(DOTS_O)|| diagonal(DOTS_O)){
                System.out.println("Победил терминатор!");
                break;
            }
            if (isMapFull()){
                System.out.println("Ничья!");
                break;
            }

        }
        System.out.println("Игра закончена!");

    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOTS_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void humanTurn() {
        Scanner scanner = new Scanner(System.in);
        int x;
        int y;
        do {
            System.out.println(" Введите координаты в формате X и Y");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y));

        map[x][y] = DOTS_X;
    }

    public static void aiTurn() {
        Random random = new Random();
        int x;
        int y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьтер сходил в точку" + (x + 1) + " " + (y + 1));

        map[x][y] = DOTS_O;
    }

    public static boolean isCellValid(int y, int x) {
        if (x >= SIZE || x < 0 || y >= SIZE || y < 0) {
            return false;
        }
        if (map[y][x] == DOTS_EMPTY) {
            return true;
        }
        return false;
    }

   public static boolean isWinner(char symb) {
       for (int i = 0; i < SIZE; i++) {
           int winner1 = 0;
           int winner2 = 0;
           for (int j = 0; j < SIZE; j++) {
               if (map[i][j] == symb) {
                   winner1++;
               } else {
                   winner1 = 0;
               }
               if (map[j][i] == symb) {
                   winner2++;
               } else {
                   winner2 = 0;
               }
           }
           if (winner1 == DOTS_TO_WIN || winner2 == DOTS_TO_WIN) {
               return true;
           }
       }
       return false;
   }
   public static boolean diagonal(char symb){
       int winnerdig1 = 0;
       int winnerdig2 = 0;
        for (int i = 0; i < SIZE; i++) {
            if (map[i][i] == symb) {
                winnerdig1++;
            } else {
                winnerdig1 = 0;
            }
            if (map[i][map[i].length - 1 - i] == symb) {
                winnerdig2++;
            } else {
                winnerdig2 = 0;
            }
            if (winnerdig1 == DOTS_TO_WIN || winnerdig2 == DOTS_TO_WIN) {
                return true;
            }
        }
        return false;
   }
   public static boolean isMapFull(){
       for (int i = 0; i < SIZE;i++) {
           for (int j = 0; j < SIZE; j++) {
               if (map[i][j] == DOTS_EMPTY) {
                   return false;
               }
           }
       }
       return true;
   }
}

