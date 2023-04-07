package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ChallengeDay8 {
	public static void main(String[] args) {
		
		String inputFile = "./resources/InputDay8";
		int partOne = partOneResult(inputFile);
		System.out.println("Part one: "+partOne);
		int partTwo = partTwoResult(inputFile);
		System.out.println("Part two: "+partTwo);
	}
	
	private static int partOneResult(String path) {
		int allVisibleTrees =0;
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			List<String[]> rowList = new ArrayList<>();
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] rowStrings = data.split("");
				rowList.add(rowStrings);
			}
			
			int[][] forest = new int[rowList.size()][rowList.get(0).length];
			boolean[][] visibleTrees = new boolean[rowList.size()][rowList.get(0).length];
			
			
			for(int i=0 ; i<rowList.size(); i++) {
				String[] row = rowList.get(i);
				for( int j = 0 ; j<row.length ; j++) {
					forest[i][j]= Integer.parseInt(row[j]);
				}
			}
			for(int i=0 ; i<visibleTrees.length; i++) {
				for( int j = 0 ; j<visibleTrees[0].length ; j++) {
					visibleTrees[i][j] = false;
				}
			}
			int maxTree =-1;
			for(int i=0 ; i<forest.length; i++) {
				for( int j = 0 ; j<forest[0].length ; j++) {
					if(forest[i][j]>maxTree) {
						maxTree=forest[i][j];
						if(!visibleTrees[i][j]) {
							visibleTrees[i][j]=true;
							allVisibleTrees++;
						}
					}
				}
				maxTree =-1;
			}
			maxTree =-1;
			for(int i=0 ; i<forest[0].length; i++) {
				for( int j = 0 ; j<forest.length ; j++) {
					if(forest[j][i]>maxTree) {
						maxTree=forest[j][i];
						if(!visibleTrees[j][i]) {
							visibleTrees[j][i]=true;
							allVisibleTrees++;
						}
					}
				}
				maxTree =-1;
			}
			maxTree =-1;
			for(int i=0 ; i<forest.length; i++) {
				for( int j=forest[0].length-1; j>=0; j--) {
					if(forest[i][j]>maxTree) {
						maxTree=forest[i][j];
						if(!visibleTrees[i][j]) {
							visibleTrees[i][j]=true;
							allVisibleTrees++;
						}
					}
				}
				maxTree =-1;
			}
			maxTree =-1;
			for(int i=0 ; i<forest[0].length; i++) {
				for( int j = forest.length-1 ; j>=0 ; j--) {
					if(forest[j][i]>maxTree) {
						maxTree=forest[j][i];
						if(!visibleTrees[j][i]) {
							visibleTrees[j][i]=true;
							allVisibleTrees++;
						}
					}
				}
				maxTree =-1;
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return allVisibleTrees;
	}
	
	private static int partTwoResult(String path) {
		int highestScore = 0;
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			List<String[]> rowList = new ArrayList<>();
			
			
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] rowStrings = data.split("");
				rowList.add(rowStrings);
			}
			
			int[][] forest = new int[rowList.size()][rowList.get(0).length];
			
			for(int i=0 ; i<rowList.size(); i++) {
				String[] row = rowList.get(i);
				for( int j = 0 ; j<row.length ; j++) {
					forest[i][j]= Integer.parseInt(row[j]);
				}
			}
			
			for (int i = 0; i < forest.length; i++) {
				for (int j = 0; j < forest[i].length; j++) {
					int left =0;
					int top =0;
					int right=0;
					int bottom =0;
					
					for(int x=j; x>0; x--) {
						left++;
						if(forest[i][x-1]>=forest[i][j])break;
					}
					for(int x=j; x<forest[i].length-1; x++) {
						right++;
						if(forest[i][x+1]>=forest[i][j])break;
					}
					for(int x=i; x>0; x--) {
						top++;
						if(forest[x-1][j]>=forest[i][j])break;
					}
					for(int x=i; x<forest.length-1; x++) {
						bottom++;
						if(forest[x+1][j]>=forest[i][j])break;
					}
					int currentScore = left*top*right*bottom;
					highestScore = Math.max(highestScore, currentScore);	
				}
			}
			
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return highestScore;
	}
}