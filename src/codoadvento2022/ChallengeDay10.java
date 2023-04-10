package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class ChallengeDay10 {
	public static void main(String[] args) {
		
		String inputFile = "./resources/InputDay10";
		int partOne = partOneResult(inputFile);
		System.out.println("Part one: "+partOne);
		
		String partTwo = partTwoResult(inputFile);
		for (int i = 0; i < partTwo.length(); i++) {
			System.out.print(partTwo.charAt(i));
			if((i+1)%40==0&&i!=0)System.out.println("");
		}
	}
	
	private static int partOneResult(String path) {
		HashMap<Integer,Integer> cyclesWithSignalStrenghts = new HashMap<>();
		int size = 1;
		int suma = 0;
		cyclesWithSignalStrenghts.put(size,1);
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] oneMove = data.split(" ");
				int prev = cyclesWithSignalStrenghts.get(size);
				if(oneMove[0].equals("noop")) {
					cyclesWithSignalStrenghts.put(++size,prev);
				}
				if(oneMove[0].equals("addx")){
					cyclesWithSignalStrenghts.put(++size, prev);
					cyclesWithSignalStrenghts.put(++size, prev+Integer.parseInt(oneMove[1]));
				}
			}
			
			for (int i = 20; i < cyclesWithSignalStrenghts.size(); i+=40) {
				suma += cyclesWithSignalStrenghts.get(i)*i;
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return suma;
	}
	
	private static String partTwoResult(String path) {
		HashMap<Integer,Integer> cyclesWithSignalStrenghts = new HashMap<>();
		int size = 1;
		cyclesWithSignalStrenghts.put(size,1);
		String output = "";
		StringBuilder sb = new StringBuilder(output);
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] oneMove = data.split(" ");
				int prev = cyclesWithSignalStrenghts.get(size);
				if(oneMove[0].equals("noop")) {
					cyclesWithSignalStrenghts.put(++size,prev);
				}
				if(oneMove[0].equals("addx")){
					cyclesWithSignalStrenghts.put(++size, prev);
					cyclesWithSignalStrenghts.put(++size, prev+Integer.parseInt(oneMove[1]));
				}
				
			}
			for (int i = 0; i < cyclesWithSignalStrenghts.size()-1; i++) {
				if(i%40==cyclesWithSignalStrenghts.get(i+1)-1||i%40==cyclesWithSignalStrenghts.get(i+1)||i%40==cyclesWithSignalStrenghts.get(i+1)+1) {
					sb.append("#");
				}else {
					sb.append(".");
				}
			}
			output = sb.toString();
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return output;
	}
}