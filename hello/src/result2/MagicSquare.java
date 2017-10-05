package result2;

import java.util.Scanner;

public class MagicSquare {
	
		public static void main(String[] args) { // the main function of the program
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the size of the square");
		int size = input.nextInt();	// Input the size of the magic square
	    //check the size if it is less than 3 then end the program because the board size should be no less than 3
		if(size<=2) {
	    System.out.println("can not generate magic square because the board size should be > 2");
	    System.exit(0);
	    }
		//-------------------------------------------------------------------------------------------------------------------------
		int sum= (size*(size*size+1))/2; // calculate the magic square property which is the sum of each row and the sum of each column and the sum of each diagonal will give the same results as sum=n(n+1)/2
		System.out.println("The sum of the rows and the columns inaddition to the digonlas = "+sum);
		System.out.println("The magic square of zise= "+size+" is as following:-");
		System.out.println();
		int[][] squar = new int[size][size]; //define the magic square board
		//---------------------------------------------------------------------------------------------------------------------------
	    //check the size of the board to see whether it is odd or multiple of 4 or it is modulo operation by 2 ==o to
		// then move the execution for one of the magic square generation function 
		if (size % 2==1) 
			   odd(squar); // call the function of odd to build odd square if n is odd
		   else if(size%4 ==0) 
			   multiplof4(squar);	   // call the function of multiplof4 to build even square if (n/4=0)
	       else 
			Even(squar); // call the function of Even to build even square if (n/2=0)
			
		printmsq(squar); // move the execution to the print function to print the final magic square

	    } // end of the main function
//-------------------------------------------------------------------------------------------------------------------------
		// the odd magic square generation function
		public static void odd(int[][] square){
			int N = square.length;
			int number = 1;
		    int r = 0;
		    int c = N / 2;
		    int curr_r;
		    int curr_c;
		    while (number <= N * N) {
		    	square[r][c] = number;
		        number++;
		        curr_r = r;
		        curr_c = c;
		        r -= 1;
		        c += 1;
		        if (r == -1) {
		            r = N - 1;
		        }
		        if (c == N) {
		            c = 0;
		        }
		        if (square[r][c] != 0) {
		            r = curr_r + 1;
		            c = curr_c;
		            if (r == -1) {
		                r = N - 1;
		            }
		        }
		    }
			
		}	// the end of odd magic square generation function
	
//-------------------------------------------------------------------------------------------------------------------------
// multiple of 4 magic square generation function

		public static void multiplof4(int[][] square){
			int N = square.length; 
			int subsquare = N/4;        //size of boxes
			int count = 1; 	           //counter 1 to N*N
			int inversCount = N*N;    //counter N*N to 1
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
				   if(j>=subsquare && j<N-subsquare)
				   {
					if(i>=subsquare && i<N-subsquare)
						square[i][j] = count;    //central box
					else 
						square[i][j] = inversCount; // up & down boxes8
				   }
				   else if(i<subsquare || i>=N-subsquare)
				   {
					   square[i][j]=count;	            // 4 corners
				   }
				   else
					   square[i][j] = inversCount;  	// left & right boxes
				   count++;
				   inversCount--;
				}
			}
		
		}	// the end of odd magic square generation function
		
//-------------------------------------------------------------------------------------------------------------------------
// Even but not multiple of 4 magic square generation function
		public static void Even(int[][] square){
			int N = square.length;
			int N2 = N/2; //size of ABCD boxes
			int k = (N-2)/4; // to get 'noses' of A & D boxes
		        int temporary;
		        int [] changeColumn = new int[N]; // columns which need to change between C-B & A-D
		     	int index=0; // index of swapCol
		        int [][] subsquare =  new int [N2][N2];
		  	odd(subsquare);	//creating odd magic square for A box
			
			//creating 4 magic boxes
		  	for (int i=0; i<N2; i++)
		    	   for (int j=0; j<N2; j++){
		    		   square[i][j] = subsquare[i][j]; 	  		       //A box
		    		   square[i+N2][j+N2] = subsquare[i][j]+N2*N2;    //B box
		    		   square[i][j+N2] = subsquare[i][j]+2*N2*N2;    //C box
		    		   square[i+N2][j] = subsquare[i][j]+3*N2*N2;   //D box
		           }
		      for (int i=1; i<=k; i++)
		    	  changeColumn[index++] = i;

		      for (int i=N-k+2; i<=N; i++)
		    	  changeColumn[index++] = i;

		      //swapping values between C-B & A-D by known columns
		      for (int i=1; i<=N2; i++)
		        for (int j=1; j<=index; j++){
		          temporary=square[i-1][changeColumn[j-1]-1];
		          square[i-1][changeColumn[j-1]-1]=square[i+N2-1][changeColumn[j-1]-1];
		          square[i+N2-1][changeColumn[j-1]-1]=temporary;
		        }

		      //swapping noses
		      temporary=square[k][0]; 
		      square[k][0]=square[k+N2][0]; 
		      square[k+N2][0]=temporary;


		      temporary=square[k+N2][k]; 
		      square[k+N2][k]=square[k][k]; 
		      square[k][k]=temporary;
		      //end of swapping

		}	// end of Even but not multiple of 4 magic square generation function
		
//-------------------------------------------------------------------------------------------------------------------------
// Printing magic square function

		public static void printmsq(int[][] square){
			int N = square.length;
			 for (int i = 0; i < N; i++) {
		            for (int j = 0; j < N; j++) 
		            	System.out.printf("%2d ", square [i][j]);
		            
		            System.out.println();
		        }
		}	//End of Printing magic square function
}
