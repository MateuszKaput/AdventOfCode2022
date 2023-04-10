package codoadvento2022;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChallengeDay02 {

	public static void main(String[] args) {
		String inputFile = "./resources/InputDay02";
		
		int partOne = partOneResult(inputFile);
		System.out.println("Part one: "+partOne);
		
		int partTwo = partTwoResult(inputFile);
		System.out.println("Part two: "+partTwo);
	}
	
	public static int partOneResult (String path) {
		int result = 0;
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				if(data.equals("A X"))result += 4;
				if(data.equals("A Y"))result += 8;
				if(data.equals("A Z"))result += 3;
				if(data.equals("B X"))result += 1;
				if(data.equals("B Y"))result += 5;
				if(data.equals("B Z"))result += 9;
				if(data.equals("C X"))result += 7;
				if(data.equals("C Y"))result += 2;
				if(data.equals("C Z"))result += 6;
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
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				if(data.equals("A X"))result += 3;
				if(data.equals("B X"))result += 1;
				if(data.equals("C X"))result += 2;
				if(data.equals("A Y"))result += 4;
				if(data.equals("B Y"))result += 5;
				if(data.equals("C Y"))result += 6;
				if(data.equals("A Z"))result += 8;
				if(data.equals("B Z"))result += 9;
				if(data.equals("C Z"))result += 7;
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return result;
	}

}
