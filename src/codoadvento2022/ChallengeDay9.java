package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class ChallengeDay9 {
	public static void main(String[] args) {
		
		String inputFile = "./resources/InputDay9";
		int partOne = partOneResult(inputFile);
		System.out.println("Part one: "+partOne);
		int partTwo = partTwoResult(inputFile);
		System.out.println("Part two: "+partTwo);
	}
	
	private static int partOneResult(String path) {
		HashSet<String> visitedPositions = new HashSet<>();
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			int headX = 0;
			int headY = 0;
			int tailX = 0;
			int tailY = 0;
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] oneMove = data.split(" ");
				
				if(oneMove[0].equals("L")) {
					for (int i = 0; i < Integer.parseInt(oneMove[1]); i++) {
						headX--;
						if(headX==tailX-2) {
							tailX--;
							if(headY==tailY+1)tailY++;
							if(headY==tailY-1)tailY--;
						}
						String position = tailX+","+tailY;
						visitedPositions.add(position);
					}
				}
				if(oneMove[0].equals("U")) {
					for (int i = 0; i < Integer.parseInt(oneMove[1]); i++) {
						headY++;
						if(headY==tailY+2) {
							tailY++;
							if(headX==tailX+1)tailX++;
							if(headX==tailX-1)tailX--;
						}
						String position = tailX+","+tailY;
						visitedPositions.add(position);
					}
				}
				if(oneMove[0].equals("R")) {
					for (int i = 0; i < Integer.parseInt(oneMove[1]); i++) {
						headX++;
						if(headX==tailX+2) {
							tailX++;
							if(headY==tailY+1)tailY++;
							if(headY==tailY-1)tailY--;
						}
						String position = tailX+","+tailY;
						visitedPositions.add(position);
					}
				}
				if(oneMove[0].equals("D")) {
					for (int i = 0; i < Integer.parseInt(oneMove[1]); i++) {
						headY--;
						if(headY==tailY-2) {
							tailY--;
							if(headX==tailX+1)tailX++;
							if(headX==tailX-1)tailX--;
						}
						String position = tailX+","+tailY;
						visitedPositions.add(position);
					}
				}
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return visitedPositions.size();
	}
	
	private static int partTwoResult(String path) {
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);

			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return 0;
	}
}