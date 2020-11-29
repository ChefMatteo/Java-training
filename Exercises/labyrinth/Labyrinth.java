import java.util.Scanner;

public class Labyrinth {
    public static void main(String[] args) {
        char [][] labyrinth = {
                {'-','-','W','W','-'},
                {'P','W','-','W','-'},
                {'-','W','-','W','W'},
                {'-','-','W','-','E'},
                {'W','-','-','-','W'}};
        Scanner sc = new Scanner(System.in);
        char mov = ' ';
        stampa(labyrinth);

        while(movimento(mov, labyrinth) == false){
            mov = sc.next().charAt(0);
        }
    }

    public static void stampa(char[][] array){
        for(int row = 0; row < array.length; row++) {
            for (int col = 0; col < array[0].length; col++) {
                System.out.print(array[row][col] + " ");
                if(col == array.length-1)
                    System.out.println("");


            }
        }
    }

    public static boolean movimento(char x, char [][] array ){
        boolean flag = false;
        char mov = Character.toUpperCase(x);
        int r =0;
        int c =0;
        for(int row = 0; row < array.length; row++){
            for(int col = 0; col < array[0].length; col++){
                if(array[row][col] == 'P'){
                    r = row;
                    c = col;
                }
            }
        }
        if(mov == 'W'){
            if(r-1 < 0 || array[r-1][c] == 'W'){
                array[r][c] = 'P';
                System.out.println("O hai preso er muro fratellì o non c'è niente!");
            }
            else if (array[r-1][c] == 'E'){
                array[r][c] = '-';
                array[r][c] = 'P';
                stampa(array);
                System.out.println("Hai vinto!");
                flag = true;
            }
            else {
                array[r][c] = '-';
                array[r - 1][c] = 'P';
            }
        }
        if(mov == 'S'){
            if(r+1 < 0 || array[r+1][c] == 'W'){
                array[r][c] = 'P';
                System.out.println("O hai preso er muro fratellì o non c'è niente!");
            }
            else if (array[r+1][c] == 'E'){
                array[r][c] = '-';
                array[r][c] = 'P';
                stampa(array);
                System.out.println("Hai vinto!");
                flag = true;
            }
            else {
                array[r][c] = '-';
                array[r + 1][c] = 'P';
            }
        }
        if(mov == 'A'){
            if(c-1 < 0 || array[r][c-1] == 'W'){
                array[r][c] = 'P';
                System.out.println("O hai preso er muro fratellì o non c'è niente!");
            }
            else if (array[r][c-1] == 'E'){
                array[r][c] = '-';
                array[r][c-1] = 'P';
                stampa(array);
                System.out.println("Hai vinto!");
                flag = true;
            }
            else {
                array[r][c] = '-';
                array[r][c-1] = 'P';
            }
        }
        if(mov == 'D') {
            if (c + 1 < 0 || array[r][c + 1] == 'W') {
                array[r][c] = 'P';
                System.out.println("O hai preso er muro fratellì o non c'è niente!");
            } else if (array[r][c + 1] == 'E') {
                array[r][c] = '-';
                array[r][c + 1] = 'P';
                stampa(array);
                System.out.println("Hai vinto!");
                flag = true;

            } else {
                array[r][c] = '-';
                array[r][c + 1] = 'P';
            }
        }
        stampa(array);
        return flag;
    }

}
