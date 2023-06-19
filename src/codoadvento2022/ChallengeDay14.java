package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class ChallengeDay14 {

	public static boolean doContinue = true;
	public static int sandCounter = 0;

	public static void main(String[] args) throws FileNotFoundException {
		String inputFile = "./resources/InputDay14";
		File myInput = new File(inputFile);
		Scanner scanner = new Scanner(myInput);

		int maxX = 0;
		int maxY = 0;

		ArrayList<ArrayList<int[]>> rockFormations = new ArrayList<ArrayList<int[]>>();

		while (scanner.hasNext()) {
			String[] singleRockString = scanner.nextLine().split("->");
			ArrayList<int[]> singleRockFormation = new ArrayList<int[]>();

			for (String singleRock : singleRockString) {
				String[] stringCoords = singleRock.trim().split(",");
				int[] coords = new int[2];

				int currentX = Integer.parseInt(stringCoords[0]);
				int currentY = Integer.parseInt(stringCoords[1]);

				coords[0] = currentX;
				coords[1] = currentY;

				maxX = Math.max(maxX, currentX);
				maxY = Math.max(maxY, currentY);

				singleRockFormation.add(coords);
			}
			rockFormations.add(singleRockFormation);
		}
		scanner.close();

		int[][] caveMap = new int[maxX + 1 + maxY][maxY + 3];

		for (int i = 0; i < caveMap.length; i++) {
			for (int j = 0; j < caveMap[i].length; j++) {
				caveMap[i][j] = 0;
			}
		}

		for (int i = 0; i < maxX + 1 + maxY; i++) {
			caveMap[i][maxY + 2] = 5;
		}

		insertRocks(rockFormations, caveMap);

		while (doContinue) {
			checkPossibleMoves(500, maxX, 0, maxY, caveMap);
		}
		System.out.println(sandCounter);
//		for(int i=0; i<caveMap.length; i++) {
//			for( int j=0; j<caveMap[i].length; j++) {
//				System.out.print(caveMap[i][j]);
//			}
//			System.out.println("");
//		}
	}

	public static void checkPossibleMoves(int x, int maxX, int y, int maxY, int[][] caveMap) {
		if (caveMap[x][y + 1] == 0) {
			checkPossibleMoves(x, maxX, y + 1, maxY, caveMap);
		} else if (caveMap[x - 1][y + 1] == 0) {
			checkPossibleMoves(x - 1, maxX, y + 1, maxY, caveMap);
		} else if (caveMap[x + 1][y + 1] == 0) {
			checkPossibleMoves(x + 1, maxX, y + 1, maxY, caveMap);
		} else if(x==500 && y==0){
			sandCounter++;
			caveMap[x][y] = 5;
			doContinue = false;
		}else {
			caveMap[x][y] = 5;
			sandCounter++;
		}
	}

	private static void insertRocks(ArrayList<ArrayList<int[]>> rockFormations, int[][] caveMap) {
		for (ArrayList<int[]> rocks : rockFormations) {
			int currentX = rocks.get(0)[0];
			int currentY = rocks.get(0)[1];

			for (int[] oneRock : rocks) {
				if (currentX == oneRock[0]) {
					if (currentY == oneRock[1]) {
						// Start point
					} else if (oneRock[1] < currentY) {
						// rock is going left
						for (int i = currentY; i >= oneRock[1]; i--) {
							caveMap[currentX][i] = 1;
						}
						currentY = oneRock[1];
					} else {
						// rock is going right
						for (int i = currentY; i <= oneRock[1]; i++) {
							caveMap[currentX][i] = 1;
						}
						currentY = oneRock[1];
					}
				} else {
					if (oneRock[0] < currentX) {
						// rock is going up
						for (int i = currentX; i >= oneRock[0]; i--) {
							caveMap[i][currentY] = 1;
						}
						currentX = oneRock[0];
					} else {
						// rock is going down
						for (int i = currentX; i <= oneRock[0]; i++) {
							caveMap[i][currentY] = 1;
						}
						currentX = oneRock[0];
					}
				}
			}
		}
	}
}
