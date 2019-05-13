import java.io.*;
import java.util.*;

class LargestConnectedColours {
    static int[][] visited =  new int [1000][1000];
    static int howfarwehavereached = 0;

    static void initialize(int x, int y) {
        for(int i = 0; i < x; i++) {
            for(int j = 0; j < y; j++) {
                visited[i][j] = 0;
            }
        }
    }

    static boolean isValid(int[][]grid, int newrow, int newcol, int row, int column, int xlen, int ylen) {

        if((newrow >= 0 && newrow < xlen) && (newcol >= 0 && newcol < ylen) && (grid[newrow][newcol] == grid[row][column]) && visited[newrow][newcol] == 0) {
            return true;
        }
        return false;
    }

    static void bfs(int[][]grid, int row, int column, int xlen, int ylen) {

        int[] xmove = {1, -1, 0, 0};
        int[] ymove = {0, 0, 1, -1};

        visited[row][column] = 1;
        howfarwehavereached += 1;

        for(int i = 0; i < 4; i++) {
            int newx = row + xmove[i];
            int newy = column + ymove[i];

            if(isValid(grid, newx, newy, row, column, xlen, ylen)) {
                bfs(grid, newx, newy, xlen, ylen);
            }
        }
    }

    static int getLargestConnected(int[][] grid, int xlen, int ylen) throws IOException {
        int maxRegion = -1;
        initialize(xlen, ylen);

        for(int row = 0; row < xlen; row++) {
            for(int column = 0; column < ylen; column++) {
                
                howfarwehavereached = 0;
                bfs(grid, row, column, xlen, ylen);

                if(maxRegion < howfarwehavereached) {
                    maxRegion = howfarwehavereached;
                }

            }
        }
        return maxRegion;
    }
    public static void main(String[] args) throws IOException {

        int[][]grid = new int[1000][1000];

        Scanner reader = new Scanner(System.in);
        int xlen = reader.nextInt();
        int ylen = reader.nextInt();

        for(int i = 0; i < xlen; i++) {
            for(int j = 0; j < ylen; j++) {
                grid[i][j] = reader.nextInt();
            }
        }
        reader.close();
        
        // int[][]grid= { { 1, 4, 4, 4, 4, 3, 3, 1 }, 
        //                 { 2, 1, 1, 4, 3, 3, 1, 1 }, 
        //                 { 3, 2, 1, 1, 2, 3, 2, 1 }, 
        //                 { 3, 3, 2, 1, 2, 2, 2, 2 }, 
        //                 { 3, 1, 3, 1, 1, 4, 4, 4 }, 
        //                 { 1, 1, 3, 1, 1, 4, 4, 4 } }; 

        // int[][]grid = { {1,1,1},
        //                 {2,1,3},
        //                 {2,3,3}};
        
        System.out.println("The largest connected: " + getLargestConnected(grid, xlen, ylen));
        
    }
}