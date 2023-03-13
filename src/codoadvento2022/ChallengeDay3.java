package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ChallengeDay3 {

	public static void main(String[] args) {
		String inputFile = "./resources/InputDay3";
		
		int partOne = partOneResult(inputFile);
		System.out.println("część pierwsza: "+partOne);
		
		int partTwo = partTwoResult(inputFile);
		System.out.println("część druga: "+partTwo);

	}
	
	public static int partOneResult (String path) {
		int result = 0;
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				
				Set<String> uniqueLetters = new HashSet<String>();

				
				String data = scanner.nextLine();
				String[] characters = data.split("");
				
				for(int i=0; i<characters.length/2; i++) {
					uniqueLetters.add(characters[i]);
				}
				
				for(int i=characters.length/2; i<characters.length; i++) {
					if(uniqueLetters.contains(characters[i])) {
						if(Character.isLowerCase(characters[i].charAt(0))) {
							result += characters[i].charAt(0)-96;
							break;
						}else {
							result += characters[i].charAt(0)-38;
							break;
						}
					}
				}
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return result;
	}
	
	public static int partTwoResult (String path) {
		
		int result = 0;
		int counter = 0;
		Set<String> uniqueLetters = new HashSet<String>();
		Set<String> secondRowSet = new HashSet<String>();
		
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			
			while(scanner.hasNextLine()) {
				
				String data = scanner.nextLine();
				String[] characters = data.split("");
				if(counter%3 == 0) {
					for(int i=0; i<characters.length; i++) {
						uniqueLetters.add(characters[i]);
					}
				}else if(counter%3 == 1){
					for(int i=0; i<characters.length; i++) {
						if (uniqueLetters.contains(characters[i])){
							secondRowSet.add(characters[i]);
						}
					}
				}else {
					for(int i=0; i<characters.length; i++) {
						if(secondRowSet.contains(characters[i])) {
							if(Character.isLowerCase(characters[i].charAt(0))) {
								result += characters[i].charAt(0)-96;
								break;
							}else {
								result += characters[i].charAt(0)-38;
								break;
							}
						}
					}
					uniqueLetters.clear();
					secondRowSet.clear();
				}
				counter++;
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return result;
	}
}
