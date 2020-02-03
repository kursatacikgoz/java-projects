import java.util.Scanner;

public class StringAnalyzer {
	
	//This program gives 6 options to the user and does that chosen.

	public static void main(String[] args) {
		
	Scanner input = new Scanner(System.in);
	while (true) {
		//menu for the program
		System.out.println("Welcome to our String Analyzer Program. ");
		System.out.println("\t 1. Count number of chars ");
		System.out.println("\t 2. Print the words in a sentence ");
		System.out.println("\t 3. Delete substring ");
		System.out.println("\t 4. Replace substring ");
		System.out.println("\t 5. Sort characters");
		System.out.println("\t 6. Hash code of a string ");
		
		//takes string from the user for the choice
		System.out.print("Please enter your menu choice: ");
		String Choice = input.nextLine();
		
		//if the choice is "exit" or "quit" stop the program
		if (Choice.equals("exit") || Choice.equals("quit")) {
			System.out.println("Program ends. Bye :)");
			System.exit(0);
		}
		
		//go to what will the program does as the choice 
		int Case = Choice.charAt(0) - '0' ;
		switch(Case) {
		case 1:
			//takes strings from the user
			System.out.print("Enter an input string: ");
			String FirstString = input.nextLine();
			System.out.print("Enter an input char: ");
			String FirstChar = input.nextLine();
			
			//shows the output and invoke the method
			System.out.println("The number of " + FirstChar.charAt(0) + " in " + FirstString 
					+ " is " + numOfChars(FirstString,FirstChar.charAt(0)) );
			//next line
			System.out.println();
			break;
		case 2:		
			//takes string from the user
			System.out.print("Enter an input string: ");
			String SecString = input.nextLine();
			//invoke the method
			printWords(SecString);
			//next line
			System.out.println();
			break;
		case 3:
			//takes strings from the user
			System.out.print("Enter an input string: ");
			String ThirdString = input.nextLine();			
			System.out.print("Enter a substring: ");
			String SubString = input.nextLine();
			//takes integer from the user
			System.out.print("Enter a type: ");
			int type = input.nextInt();
			//to pass the line
			input.nextLine();
			//invoke the method
			System.out.println(delete(ThirdString , SubString , type));
			//next line
			System.out.println();
			break;
		case 4:
			//takes strings from the user
			System.out.print("Enter an input string: ");
			String ForthString = input.nextLine();
			System.out.print("Enter the first substring: ");
			String SubStringFirst = input.nextLine();
			System.out.print("Enter the second substring: ");
			String SubStringSecond = input.nextLine();
			//invoke the method
			System.out.println(replace(ForthString, SubStringFirst, SubStringSecond));
			//next line
			System.out.println();
			break;
		case 5:
			//takes inputs from the user
			System.out.print("Enter an input string: ");
			String FifthString = input.nextLine();
			System.out.print("Enter a type: ");
			int FifthType = input.nextInt();
			//to pass the line
			input.nextLine();
			//invoke the method
			System.out.println(sortChars(FifthString, FifthType) );
			//next line
			System.out.println();
			break;
		case 6:
			//takes inputs from the user
			System.out.print("Enter an input string: ");
			String SixthString = input.nextLine();
			System.out.print("Enter a value for b: ");
			int SixthValue = input.nextInt();
			//to pass the line
			input.nextLine();
			//invoke the method
			System.out.println("The hash code for " + SixthString + " is " +
								hashCode(SixthString, SixthValue));
			//next line
			System.out.println();
			break;
		}
	}
}
	//numOfChars method
public static int numOfChars(String str, char ch) {
	/*looks the string char by char and when the program sees the char
	 * at first time return as it. If the program can not find a match
	 * return will be -1
	 */
	for ( int i = 0 ; i < str.length() ; i++) {
		if (str.charAt(i) == ch) 
			return i; 
	}
	return -1;
}

public static void printWords(String str) {
	/*This method prints the words of the string
	 * it describes begin and end integers for string's words
	 * and prints them line by line */
	int begin = -1;
	int end = 0;
	String message = "";
	for (int i = 0 ; i < str.length() ; i++) {
		if (Character.isLetter(str.charAt(i)) || Character.isDigit(str.charAt(i)) ) {
			if (end > begin) {
				begin = i ;
				while (i < str.length()) {
					if (Character.isLetter(str.charAt(i)) || Character.isDigit(str.charAt(i)) ) 
						i++;
					else
						break;
				}
				end = i;				
				message = str.substring(begin , end );				
				System.out.println(message);
			}
		}
	}	
}

public static String delete(String str, String subStr, int type) {
	String ThirdString = "";	
	/*if the type is 1 
	 * where the substring and string are equal pass them and
	 * add other to the ThirdString*/
	if (type == 1) {
		for(int i = 0 ; i < str.length() ;) {
			int j = 0 ;	
			if (str.charAt(i) != subStr.charAt(j)) {
				ThirdString += str.charAt(i);
				i++;
			}
			else if (str.charAt(i) == subStr.charAt(j))
				while ( j < subStr.length()) {
					if (str.charAt(i) == subStr.charAt(j)) {
					j++;
					i++;
					}					
				}				
		}
	}
	/* if the type is not 1 
	 * the count is 0 and if blocks will execute one time that where the subStr and string
	 * are equal pass these chars. After the first equality does not pass the
	 * chars.  */
	else {
		int count = 0 ;
		for(int i = 0; i < str.length() ; i++) {						
			if (count == 0 && (str.substring(i, i + subStr.length()).equals(subStr))) {
				i += subStr.length() - 1;
				count++;				
			}
			else
				ThirdString += str.charAt(i);
		}
	}
	
	/*At the last program looks to the ThirdString where there are double 
	 * spaces just print one of them */
	String LastThirdString = "";
	for (int i = 0; i < ThirdString.length() ; i++) {
		if (i < ThirdString.length() - 1) {
			if (ThirdString.charAt(i + 1) == ThirdString.charAt(i)) {
				if (ThirdString.charAt(i) == 32) {
					i++;
				}
			}
		}
		LastThirdString += ThirdString.charAt(i);
	}
	
	//return value is LastThirdString
	return LastThirdString;
}	

/* This method does that 
 * Look at the String where are the subStr1's change them 
 * to the subStr2 and return as ForthString  */
public static String replace(String str, String subStr1, String subStr2) {
	String ForthString ="";
	for(int i =0 ; i < str.length() ;) {
		if (i + subStr1.length() < str.length()){
			if (str.substring(i, i + subStr1.length()).equals(subStr1)){
				ForthString += subStr2;
				i += subStr1.length();
			}
			else {
				ForthString += str.charAt(i);
				i++;				
			}
		}
		else {
			ForthString += str.charAt(i);
			i++;
		}					
	}	
	return ForthString;
}

/* at this method the program creates an array size as length of the string
 * and if the type is 0 sort them for their ASCII codes
 * if the type is 1 sort them first lower case letters, upper case letters,
 * digits, and other characters. And sort them within themselves */
public static String sortChars(String str, int type) {
	String FifthString = "";
	
	int[] FifthStr = new int [str.length()];

//for type 0
	if (type == 0) {
	for(int i = 0 ; i < str.length() ; i++) 
		FifthStr[i]=str.charAt(i);
	
	for (int i = 0 ; i < str.length() ; i++) {
		int temp = FifthStr[i];
		for (int j = i+1 ; j < str.length() ; j++) {
			if (FifthStr[j] <= temp) {
				FifthStr[i] = FifthStr[j];
				FifthStr[j] = temp ;
				temp = FifthStr[i];			
			}
		}
	}
	}
//for type 1	
	else {
		int step = 0 ;		
		for(int i = 0 ; i < str.length() ; i++) 
			FifthStr[i]=str.charAt(i);
//lower letters		
		for (int i = step ; i < str.length(); i++) {
			int temp = FifthStr[i];
			int Value = 0;			
			if (FifthStr[i] >= 98 && FifthStr[i] <=122) {
			for (int j = i + 1 ; j < str.length() ; j++) {
				if (FifthStr[j] <= FifthStr[i] && FifthStr[j] >= 97 && FifthStr[j] <= 122) {
					Value = j ;
					FifthStr[i] = FifthStr[Value];
					FifthStr[Value] = temp;
					temp = FifthStr[i];
				}
			}
			step++;
			}
			else {
				if (FifthStr[step] < 97 || FifthStr[step] > 122) {
					for (int j = i + 1 ; j < str.length() ; j++) {
						if (FifthStr[j] >=97 && FifthStr[j] <= 122) {
							Value = j ;
							FifthStr[i] = FifthStr[Value];
							FifthStr[Value] = temp;
							i--;
							break;
						}
					}
				}
			}
		}
//for upper letters
		for (int i = step ; i < str.length(); i++) {
			int temp = FifthStr[i];
			int Value = 0;
			
			if (FifthStr[i] >= 65 && FifthStr[i] <=90) {
			for (int j = i + 1 ; j < str.length() ; j++) {
				if (FifthStr[j] <= FifthStr[i] && FifthStr[j] >= 65 && FifthStr[j] <= 90) {
					Value = j ;
					FifthStr[i] = FifthStr[Value];
					FifthStr[Value] = temp;
					temp = FifthStr[i];
				}
			}
			step++;
			}
			else {
				if (FifthStr[step] < 65 || FifthStr[step] > 90) {
					for (int j = i + 1 ; j < str.length() ; j++) {
						if (FifthStr[j] >=65 && FifthStr[j] <= 90) {
							Value = j ;
							FifthStr[i] = FifthStr[Value];
							FifthStr[Value] = temp;
							i--;
							break;
						}
					}
				}
			}

		}
//for digits
		for (int i = step ; i < str.length(); i++) {
			int temp = FifthStr[i];
			int Value = 0;
			
			if (FifthStr[i] >= 48 && FifthStr[i] <=57) {
			for (int j = i + 1 ; j < str.length() ; j++) {
				if (FifthStr[j] <= FifthStr[i] && FifthStr[j] >= 48 && FifthStr[j] <= 57) {
					Value = j ;
					FifthStr[i] = FifthStr[Value];
					FifthStr[Value] = temp;
					temp = FifthStr[i];
				}
			}
			step++;
			}
			else {
				if (FifthStr[step] < 48 || FifthStr[step] > 57) {
					for (int j = i + 1 ; j < str.length() ; j++) {
						if (FifthStr[j] >=48 && FifthStr[j] <= 57) {
							Value = j ;
							FifthStr[i] = FifthStr[Value];
							FifthStr[Value] = temp;
							i--;
							break;
						}
					}
				}
			}

		}
//for other characters
		for (int i = step ; i < str.length() ; i++) {
			int temp = FifthStr[i];
			for (int j = i+1 ; j < str.length() ; j++) {
				if (FifthStr[j] <= temp) {
					FifthStr[i] = FifthStr[j];
					FifthStr[j] = temp ;
					temp = FifthStr[i];					
				}
			}
		}			
		}				
		for (int i = 0; i < str.length(); i++)
		FifthString += (char)FifthStr[i];		
		return FifthString;
	}	

/* The hashCode method calculates the value of the string 
 * on the base of b */
public static int hashCode(String str, int b) {		
	int total = 0 ;	
	for (int power = str.length() - 1 , i = 0; power >= 0 ; power-- , i++) 
		total += str.charAt(i) * (int)Math.pow(b, power) ;
	return total ;
}
	
}
	
