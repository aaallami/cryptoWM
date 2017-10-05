package distributed;

public class huffman {
	static int getParity(int b[], int power) {
		int parity = 0;
		for(int i=0 ; i < b.length ; i++) {
			if(b[i] != 2) {
				// If 'i' doesn't contain an unset value,
				// We will save that index value in k, increase it by 1,
				// Then we convert it into binary:
				
				int k = i+1;
				String s = Integer.toBinaryString(k);
				
				//Nw if the bit at the 2^(power) location of the binary value of index is 1
				//Then we need to check the value stored at that location.
				//Checking if that value is 1 or 0, we will calculate the parity value.
				
				int x = ((Integer.parseInt(s))/((int) Math.pow(10, power)))%10;
				if(x == 1) {
					if(b[i] == 1) {
						parity = (parity+1)%2;
					}
				}
			}
		}
		return parity;
	}
	public static int[] generateCode(int a[]) {
		// We will return the array 'b'.
		int b[];
		
		// We find the number of parity bits required:
		int i=0, parity_count=0 ,j=0, k=0;
		while(i < a.length) {
			// 2^(parity bits) must equal the current position
			// Current position is (number of bits traversed + number of parity bits + 1).
			// +1 is needed since array indices start from 0 whereas we need to start from 1.
			
			if(Math.pow(2,parity_count) == i+parity_count + 1) {
				parity_count++;
			}
			else {
				i++;
			}
		}
		
		// Length of 'b' is length of original data (a) + number of parity bits.
		b = new int[a.length + parity_count];
		
		// Initialize this array with '2' to indicate an 'unset' value in parity bit locations:
		
		for(i=1 ; i <= b.length ; i++) {
			if(Math.pow(2, j) == i) {
			// Found a parity bit location.
			// Adjusting with (-1) to account for array indices starting from 0 instead of 1.
				
				b[i-1] = 2;
				j++;
			}
			else {
				b[k+j] = a[k++];
			}
		}
		for(i=0 ; i < parity_count ; i++) {
			// Setting even parity bits at parity bit locations:
			
			b[((int) Math.pow(2, i))-1] = getParity(b, i);
		}
		return b;
	}

}
