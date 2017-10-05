package result2;

import java.util.*;

public class KnightsTour {
    private final static int[][] possiblemoves = {{1,-2},{2,-1},{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2}}; //the possible KT moves
    private static int[][] board; // this array is used to store the moves of the knight on the board
    private static int total; // used to store the total number of moves, this helps in program termination
 //------------------------------------------------------------------------------------------------------------------------------------------
 // the main function
    public static void main(String[] args) {
    	Scanner input = new Scanner(System.in);
		System.out.println("Please enter the size of the square");
		int size = input.nextInt()+4;
        board = new int[size][size]; //array definition 
        total = (size-4 ) * (size-4 ); // storing the total number of moves, here it's 64 since the board has 8*8 cells
      	//check if the moves  within the board, if not the corresponding cell is filled with -1
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                if (r < 2 || r > size - 3 || c < 2 || c > size - 3)                   
                	board[r][c] = -1;
        // Starting Position
        int r=  5;
        int c =  4;
        board[r][c] = 1;
         if (complete(r, c, 2)) // calling the function to find out the path
            printpath(); // displays the final path of the knight
        else System.out.println("there is no valid Result");
     }
     private static boolean complete(int r, int c, int count) {
        if (count > total) //  the Knights Tour is completed
            return true;

        List<int[]> nbrs = neighbors(r, c);  //Creates a list of neighbors from the current cell
        if (nbrs.isEmpty() && count != total) // if there are no neighbors returns false
            return false;
      
 		// compares the neighbors and selects the one with the least number of accessible positions from it.
        Collections.sort(nbrs, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return a[2] - b[2];
            }
        });
        for (int[] nb : nbrs) {
            r = nb[0];
            c = nb[1];
            board[r][c] = count;
            if (!validpath(count, r, c) && complete(r, c, count + 1))
                return true;
            board[r][c] = 0;
        }
        return false;
    }
 	// creates a list to keep track of the available neighbors from a particular cell
    private static List<int[]> neighbors(int r, int c) {
        List<int[]> nbrs = new ArrayList<>();
        for (int[] k : possiblemoves) {
            int x = k[0];
            int y = k[1];
            if (board[r + y][c + x] == 0) {
                int num = countNeighbors(r + y, c + x);
                nbrs.add(new int[]{r + y, c + x, num});
            }
        }
        return nbrs;
    }
 	//calculates the number of neighbors from a particular cell
    private static int countNeighbors(int r, int c) {
        int count = 0;
        for (int[] k : possiblemoves)
            if (board[r + k[1]][c + k[0]] == 0)
            	count++;
        return count;
    }
    //checks the accessibility of next positions from the current position
    //if there is a move present then it returns true
    private static boolean validpath(int cnt, int r, int c) {
        if (cnt < total - 1) {
            List<int[]> nbrs = neighbors(r, c);
            for (int[] nb : nbrs)
                if (countNeighbors(nb[0], nb[1]) == 0)
                    return true;
        }
        return false;
    }
  
 	//print the final board which contains the path of the knight traversed.
    private static void printpath() {
        for (int[] r : board) {
            for (int i : r) {
                if (i == -1) continue;
                System.out.printf("%2d ", i);
            }
            System.out.println();
        }
    }
}
