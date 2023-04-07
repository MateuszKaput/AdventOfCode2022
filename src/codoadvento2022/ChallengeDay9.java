package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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
		HashSet<String> visitedPositions = new HashSet<>();
		HashMap<Integer,Point> allPoints = new HashMap<>();
		
		for (int i = 0; i < 10; i++) {allPoints.put(i,new Point(0,0));}
		
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] oneMove = data.split(" ");
				for (int j = 0; j < Integer.parseInt(oneMove[1]); j++) {
					for(int i=0; i<allPoints.size(); i++) {
						if(i==0) {
							if(oneMove[0].equals("L"))allPoints.get(0).x--;
							if(oneMove[0].equals("U"))allPoints.get(0).y++;
							if(oneMove[0].equals("R"))allPoints.get(0).x++;
							if(oneMove[0].equals("D"))allPoints.get(0).y--;
						}else {
							int xDiff = allPoints.get(i-1).x-allPoints.get(i).x;
							int yDiff = allPoints.get(i-1).y-allPoints.get(i).y;							
							if(xDiff==2) {
								allPoints.get(i).x++;
								if(yDiff==1) allPoints.get(i).y++;
								if(yDiff==-1)allPoints.get(i).y--;
							}
							if(xDiff==-2) {
								allPoints.get(i).x--;
								if(yDiff==1) allPoints.get(i).y++;
								if(yDiff==-1)allPoints.get(i).y--;
							}
							if(yDiff==2) {
								allPoints.get(i).y++;
								if(xDiff==1) allPoints.get(i).x++;
								if(xDiff==-1)allPoints.get(i).x--;
							}
							if(yDiff==-2) {
								allPoints.get(i).y--;
								if(xDiff==1) allPoints.get(i).x++;
								if(xDiff==-1)allPoints.get(i).x--;
							}
						}
					}
					String position = allPoints.get(9).x+","+allPoints.get(9).y;
					visitedPositions.add(position);
				}
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return visitedPositions.size();
	}
		
	public static class Point {
	    public int x = 0;
	    public int y = 0;
	    
	    public Point(int a, int b) {
	        x = a;
	        y = b;
	    }
	}
}