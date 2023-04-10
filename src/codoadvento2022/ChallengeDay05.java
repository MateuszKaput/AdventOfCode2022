package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ChallengeDay05 {
	public static void main(String[] args) {
		String inputFile = "./resources/InputDay05";
		
		partOneResult(inputFile);
		partTwoResult(inputFile);

	}
	
	private static void partOneResult(String path) {
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			List<List<Character>> listOfLists = new ArrayList<>();
			List<String[]> moves = new ArrayList<String[]>();
			int counter = 0;
			
			//get data
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				List<Character> singleList = new ArrayList<>();

				//get starting positions of containers
				if(counter<8) {
					for(int i=1; i<data.length(); i+=4) {
						singleList.add(data.charAt(i));
					}
					listOfLists.add(singleList);
					counter++;
				}else if(counter>=8&&counter<=9){
					//skip unnesesary lanes
					counter++;
				}else {
					//get moves we need to make
					moves.add(data.split(" "));
				}
			}

			//make empty list of stacks
			List<Stack<Character>> listOfStacks = new Stack<>();
			for(int i=0; i<listOfLists.get(0).size(); i++) {
				listOfStacks.add(new Stack<>());
			}
			
			//push data from Lists to Stacks
			for(int i=listOfLists.size()-1; i>=0; i--) {
				for(int j=0; j<listOfLists.get(i).size(); j++) {
					if(Character.compare(listOfLists.get(i).get(j),' ')!=0) {
						listOfStacks.get(j).push(listOfLists.get(i).get(j));
					}
				}
			}
			
			//make all moves
			for(String[] move: moves) {
				for(int i = 0; i< Integer.parseInt(move[1]);i++) {
					listOfStacks.get(Integer.parseInt(move[5])-1).push(listOfStacks.get(Integer.parseInt(move[3])-1).pop());
				}
			}
			
			//display result
			System.out.print("Result part one: ");
			for(Stack<Character> stack: listOfStacks) {
				System.out.print(stack.get(stack.size()-1));
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
			List<List<Character>> listOfLists = new ArrayList<>();
			List<String[]> moves = new ArrayList<String[]>();
			int counter = 0;
			
			//get data
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				List<Character> singleList = new ArrayList<>();

				//get starting positions of containers
				if(counter<8) {
					for(int i=1; i<data.length(); i+=4) {
						singleList.add(data.charAt(i));
					}
					listOfLists.add(singleList);
					counter++;
				}else if(counter>=8&&counter<=9){
					//skip unnesesary lanes
					counter++;
				}else {
					//get moves we need to make
					moves.add(data.split(" "));
				}
			}

			//make empty list of stacks
			List<Stack<Character>> listOfStacks = new Stack<>();
			for(int i=0; i<listOfLists.get(0).size(); i++) {
				listOfStacks.add(new Stack<>());
			}
			
			//push data from Lists to Stacks
			for(int i=listOfLists.size()-1; i>=0; i--) {
				for(int j=0; j<listOfLists.get(i).size(); j++) {
					if(Character.compare(listOfLists.get(i).get(j),' ')!=0) {
						listOfStacks.get(j).push(listOfLists.get(i).get(j));
					}
				}
			}
			
			//make all moves
			for(String[] move: moves) {
				Stack<Character> tempStack = new Stack<>();
				for(int i = 0; i< Integer.parseInt(move[1]);i++) {
					tempStack.push(listOfStacks.get(Integer.parseInt(move[3])-1).pop());
				}
				for(int i = 0; i< Integer.parseInt(move[1]);i++) {
					listOfStacks.get(Integer.parseInt(move[5])-1).push(tempStack.pop());
				}
			}
			
			//display result
			System.out.println("");
			System.out.print("Result part two: ");
			for(Stack<Character> stack: listOfStacks) {
				System.out.print(stack.get(stack.size()-1));
			}
			
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
	}
}
