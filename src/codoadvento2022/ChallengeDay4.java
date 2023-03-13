package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChallengeDay4 {

	public static void main(String[] args) {
		String inputFile = "./resources/InputDay4";
		
		int partOne = partOneResult(inputFile);
		System.out.println("część pierwsza: "+partOne);
		
		int partTwo = partTwoResult(inputFile);
		System.out.println("część druga: "+partTwo);

	}

	private static int partOneResult(String path) {
		int result = 0;
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				
				String data = scanner.nextLine();
				String[] elfs = data.split(",");
				String[] firstElfSections = elfs[0].split("-");
				String[] secondElfSections = elfs[1].split("-");
				int firstStart = Integer.parseInt(firstElfSections[0]);
				int firstEnd = Integer.parseInt(firstElfSections[1]);
				int secondStart = Integer.parseInt(secondElfSections[0]);
				int secondEnd = Integer.parseInt(secondElfSections[1]);
				
				if(firstStart>=secondStart&&firstEnd<=secondEnd) result++;
				else if(firstStart<=secondStart&&firstEnd>=secondEnd) result++;
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return result;
	}

	private static int partTwoResult(String path) {
		int result = 0;
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				
				String data = scanner.nextLine();
				String[] elfs = data.split(",");
				String[] firstElfSections = elfs[0].split("-");
				String[] secondElfSections = elfs[1].split("-");
				int firstStart = Integer.parseInt(firstElfSections[0]);
				int firstEnd = Integer.parseInt(firstElfSections[1]);
				int secondStart = Integer.parseInt(secondElfSections[0]);
				int secondEnd = Integer.parseInt(secondElfSections[1]);

				if(firstStart<=secondStart&&firstEnd>=secondStart) result++;
				else if(secondStart<=firstStart&&secondEnd>=firstStart) result++;
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return result;
	}

}


