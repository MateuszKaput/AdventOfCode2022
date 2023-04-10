package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ChallengeDay06 {

	public static void main(String[] args) {
		
		String inputFile = "./resources/InputDay06";
		partOneResult(inputFile);
		partTwoResult(inputFile);

	}
	
	private static void partOneResult(String path) {
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			String data = scanner.nextLine();
			int lettersToFind = 4;
			for(int i=lettersToFind; i<data.length(); i++) {
				Set<Character> lastFourLetters = new HashSet<>();
				String substringToCheck = data.substring(i-lettersToFind,i);
				for(int j=0; j<lettersToFind; j++) {
					lastFourLetters.add(substringToCheck.charAt(j));
				}
				if(lastFourLetters.size()==lettersToFind) {
					System.out.println("letters: "+lastFourLetters+" index: "+i);
					break;
				}
			}
			
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
	}
	
	private static void partTwoResult(String path) {
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			String data = scanner.nextLine();
			int lettersToFind = 14;
			for(int i=lettersToFind; i<data.length(); i++) {
				Set<Character> outputLetters = new HashSet<>();
				String substringToCheck = data.substring(i-lettersToFind,i);
				for(int j=0; j<lettersToFind; j++) {
					outputLetters.add(substringToCheck.charAt(j));
				}
				if(outputLetters.size()==lettersToFind) {
					System.out.println("letters: "+outputLetters+" index: "+i);
					break;
				}
			}
			
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
	}
}
