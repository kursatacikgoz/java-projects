import java.util.Scanner;

/* This program takes input as string from the user which has numbers separated by '-''s
 * Change the base of these numbers from 10 to 2 and assign them to an array and apply following rules.
 * if a cell contains the value of ‘1’ in the first array, there are three rules to update its value in the second array:  
 * if a cell has fewer than two ‘1’ neighbor cells, it is converted to ‘0’. 
 * if a cell has more than three ‘1’ neighbor cells, it is converted to ‘0’. 
 * if a cell has exactly two or three ‘1’ neighbor cells, it remains as ‘1’. 
 * if a cell contains the value of ‘0’ in the first array, the following rule applies to update its value in the second array: 
 * if a cell has exactly three ‘1’ neighbor cells, it is converted to ‘1’.
 * Otherwise, it remains as ‘0’. */

public class converting {

	public static void main(String[] args) {
		
		// Prints welcome message, Creates a new scanner object, takes an input as string
		System.out.println("Welcome to our program.");
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a string: ");
		String NumberStr = input.nextLine();
		
		int count = 1;
		
		//counts how many integer are there 
		for (int i = 0 ; i < NumberStr.length() ; i++) {
			if(NumberStr.charAt(i) == '-')
				count++;
		}
		
		//Decimals array holds the Decimal numbers of the string
		int[] Decimals = StrToDec(NumberStr,count);
		
		/*This nested loop check the string, if there is an error
		 * as first char is "-" or there are adjacent "-"'s
		 * or bigger than to write it in this size takes another input from the user */
		for (int i = 0 ; i < Decimals.length ; i++) {
			for (int j = 1 ; j < NumberStr.length() ;j++) {
				if((NumberStr.charAt(j-1) == NumberStr.charAt(j) && NumberStr.charAt(j) == '-') 
					|| (NumberStr.charAt(0) == '-' || (NumberStr.charAt(NumberStr.length() - 1)== '-' ))) {
				System.out.println("Please, enter the correct format.");
				System.out.print("Enter a string: ");
				NumberStr = input.nextLine();
				count = 1;
				for (int k = 0 ; k < NumberStr.length() ; k++) {
					if(NumberStr.charAt(k) == '-')
						if ((NumberStr.charAt(j-1) != NumberStr.charAt(j)) ||
								NumberStr.charAt(0) != '-')
							count++;
				}
				j = 1;
				Decimals = StrToDec(NumberStr,count);
			}
			}
			for (int j = 0 ; j < NumberStr.length() ;) {
				if(Character.isDigit(NumberStr.charAt(j)) || (NumberStr.charAt(j) == '-')) 
					j++;
				else {
					System.out.println("Please, enter the correct format.");
					System.out.print("Enter a string: ");
					NumberStr = input.nextLine();
					count = 1;
					for (int k = 0 ; k < NumberStr.length() ; k++) {
						if(NumberStr.charAt(k) == '-')
							if ((NumberStr.charAt(j-1) != NumberStr.charAt(j)) ||
									NumberStr.charAt(0) != '-')
								count++;
				}
				j = 1;
				Decimals = StrToDec(NumberStr,count);
			}	
			}
			
			if (Decimals[i] > (Math.pow(2 , Decimals.length) - 1)) {
				System.out.println("The number " + Decimals[i] + " cannot be represented with " +
						Decimals.length + "x" + Decimals.length + " array!");
				System.out.print("Enter a string: ");
				NumberStr = input.nextLine();
				count = 1;
				for (int j = 1 ; j < NumberStr.length() ; j++) {
					if(NumberStr.charAt(j) == '-')
						if ((NumberStr.charAt(j-1) != NumberStr.charAt(j)) ||
								NumberStr.charAt(0) != '-')
							count++;
				}
				Decimals = StrToDec(NumberStr,count);
				i = -1;
			}
		}
		
		//Takes input from the user how many time will implement the rules
		System.out.print("Enter a number of steps: ");
		int time = input.nextInt();
		
		//Creates an square 2D array 
		int[][] FirstArray = new int [count][count];
		
		//This loop invoke the convertDectoB
		for (int i = 0 ; i < count ; i++) 
			FirstArray[i] = convertDectoB(Decimals[i],count);
		
		//This loops prints frame of arrays and prints the 2D array
		for (int i = 0 ; i < 4 * count + 1 ; i++)
			System.out.print("-");
		System.out.println();		
		
		for (int i = 0 ; i < count ; i++) {
			for(int j = 0 ; j < count ; j++) {
				System.out.print("| " + FirstArray[i][j] + " ");
			}
			System.out.println("|");
			for (int k = 0 ; k < 4 * count + 1 ; k++)
				System.out.print("-");
			System.out.println();	
		}
		
		//Creates a new 2D array
		int[][] SecondArray = new int [count][count];
		
		//While loop implement the program as said
		int t = 1;
		while(t <= time) {
			
			//messages before each table
			System.out.print("It is converted to the following table after " + t);
			if (t % 10 == 1  && t != 11)
				System.out.print("st step: ");
			else if (t % 10 == 2 && t != 12)
				System.out.print("nd step: ");
			else if (t % 10 ==3 && t!= 13)
				System.out.print("rd step: ");
			else 
				System.out.print("th step: ");
			System.out.println();
			
		/*check the surrounding of each cell that how many 1's are there, apply the rule
			and change the cell of second array as calculated */
		for ( int i = 0 ; i < count ; i++) {
			for ( int j = 0; j < count; j++) {
				int Calc = 0 ;
				/*
				 * These if's check the values of i's and j's. Are these
				 * less then zero or more than length pass these cells. 
				 * */
				if (FirstArray[i][j] == 1) {
					if(i > 0) {
						if(j-1 >= 0) {
							if(FirstArray[i-1][j-1] == 1)
								Calc++;
						}
						if(FirstArray[i-1][j] == 1)
							Calc++;
						if(j+1 <= count - 1) {
							if(FirstArray[i-1][j+1] == 1)
								Calc++;
						}
				}
					if(j-1 >= 0) {
						if(FirstArray[i][j-1] == 1)
							Calc++;
					}
					if (j+1 <= count-1) {
						if(FirstArray[i][j+1] == 1)
							Calc++;
					}
					if(i+1 <= count-1) {
						if(j-1 >= 0) {
							if(FirstArray[i+1][j-1] == 1)
								Calc++;
						}
						if(FirstArray[i+1][j] == 1)
							Calc++;
						if(j+1 <= count-1) {
							if(FirstArray[i+1][j+1] == 1)
								Calc++;
						}
					}
					
					if (Calc == 2 || Calc == 3) 
						SecondArray[i][j] = 1 ;
					else 
						SecondArray[i][j] = 0 ;
					
					
				}
				else if (FirstArray[i][j] == 0) {
					if(i > 0) {
						if(j-1 >= 0) {
							if(FirstArray[i-1][j-1] == 1)
								Calc++;
						}
						if(FirstArray[i-1][j] == 1)
							Calc++;
						if(j+1 <= count - 1) {
							if(FirstArray[i-1][j+1] == 1)
								Calc++;
						}
				}
					if(j-1 >= 0) {
						if(FirstArray[i][j-1] == 1)
							Calc++;
					}
					if (j+1 <= count-1) {
						if(FirstArray[i][j+1] == 1)
							Calc++;
					}
					if(i+1 <= count-1) {
						if(j-1 >= 0) {
							if(FirstArray[i+1][j-1] == 1)
								Calc++;
						}
						if(FirstArray[i+1][j] == 1)
							Calc++;
						if(j+1 <= count-1) {
							if(FirstArray[i+1][j+1] == 1)
								Calc++;
						}
					}
					
					if (Calc == 3) 
						SecondArray[i][j] = 1 ;
					else 
						SecondArray[i][j] = 0 ;
				}
			}
		}
		//invoke the method
		CopyingTheDatas(FirstArray, SecondArray, count);
		
		//This loops prints frame of arrays and prints the 2D array
		for (int i = 0 ; i < 4 * count + 1 ; i++)
			System.out.print("-");
		System.out.println();		
		
		//prints the loop and frame
		for (int i = 0 ; i < count ; i++) {
			for(int j = 0 ; j < count ; j++) {
				System.out.print("| " + FirstArray[i][j] + " ");
			}
			System.out.println("|");
			for (int k = 0 ; k < 4 * count + 1 ; k++)
				System.out.print("-");
			System.out.println();	
		}
		t++;
		}
		
		//Prints the values of binaries in the decimal form after all of sections.
		System.out.print("The decimal value for the second table after " 
				+ time + " steps: " + convertBtoDec(SecondArray,0));
		
		for (int i = 1 ; i < SecondArray.length ; i++) {
			System.out.print("-" + convertBtoDec(SecondArray,i) );
		}	
	}
	
	//This method copy the values of Second array to the first array
	public static void CopyingTheDatas(int[][] First , int[][] Second,int length) {
		for (int i = 0 ; i < length ; i++) {
			for (int j = 0 ; j < length ; j++)
				First[i][j] = Second[i][j];
		}
	}
	
	//This method assign the values of decimals from string to an array
	public static int[] StrToDec(String Str , int length) {
		int[] Decimal = new int [length];
		for(int i = 0, j = 0, k = 0 ; i < Str.length(); i++) {
			if(Str.charAt(i) == '-') {
				if (i==j) {
					j++;
					continue;
				}
				Decimal[k] = Integer.parseInt(Str.substring(j, i));
				j = i + 1 ;
				k++;
			}
			else if (i == Str.length() - 1) {
				Decimal[k] = Integer.parseInt(Str.substring(j));
				j = i + 1 ;
				k++;
			}
		}
		return Decimal;
	}
	
	//This array converts decimal to binary
	public static int[] convertDectoB(int number, int size) {
		int[] BinArray = new int[size];
		
		for(int i = 0, j = BinArray.length - 1 ; i < BinArray.length ; i++ , j--) {
			BinArray[j] = number % 2;
			number /= 2 ;
		}
		return BinArray;
	}
	
	//This array converts binary to decimal
	public static int convertBtoDec(int[][] Second, int row) {
		int sum = 0;
		int power = 0 ;
		for(int j = 1 ; j <= Second[row].length; j++) 
			sum += Math.pow(2 , j-1) * Second[row][Second[row].length - j];
		
		return sum;
	}
	
}
