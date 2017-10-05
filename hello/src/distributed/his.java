package distributed;

class histogram {
	public static void main(String args[]) {
		
		 String star ="*";
		 int [] h= {1, 3, 4, 7, 9, 14, 26, 10, 4, 1};
		 for (int i=0; i <h.length; i++) {
			for(int j = 0; j < h[i]; j++) 
			   System.out.print(star);
			System.out.println();
		}
	  }			
}

