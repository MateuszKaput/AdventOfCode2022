package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import org.w3c.dom.ls.LSOutput;

public class ChallengeDay17 {
	public static void main(String[] args) throws FileNotFoundException {

		String inputFile = "./resources/InputDay17";
		File myInput = new File(inputFile);
		Scanner scanner = new Scanner(myInput);

		String[] directions = scanner.nextLine().split("");
		scanner.close();
		HashMap<Integer, Integer> pieceHeight = new HashMap<>();
		int maxY = 0;
		long partOneLenght = 2022;
		long partTwoLenght = 1_000_000_000_000L;
		long currentLenght = partTwoLenght;

		HashMap<Integer, HashSet<Integer>> points = new HashMap<>();
		maxY = calculateHeight(8000, directions, maxY, points, pieceHeight);
		repeatedBlock repeatedBlockLenght = repeatBlockLenght(points);
		int offsetRockAmount = getKeyByValue(pieceHeight, repeatedBlockLenght.offset);
		int blockRockAmount = getKeyByValue(pieceHeight, repeatedBlockLenght.size + repeatedBlockLenght.offset)
				- offsetRockAmount;
		long moduloRocks = (currentLenght - offsetRockAmount) % blockRockAmount;
		int moduloHeight = pieceHeight.get((int) (moduloRocks + offsetRockAmount)) - repeatedBlockLenght.offset;
		long blockAmount = (currentLenght - offsetRockAmount - moduloRocks) / blockRockAmount;
		long total = repeatedBlockLenght.offset + repeatedBlockLenght.size * blockAmount + moduloHeight;
		System.out.println("total: "+total);
	}

	private static int calculateHeight(long pieceAmount, String[] directions, int maxY,
			HashMap<Integer, HashSet<Integer>> points, HashMap<Integer, Integer> pieceHeight) {
		final int spawnX = 3;
		int directionCount = 0;
		for (int pieceCount = 0; pieceCount < pieceAmount; pieceCount++) {
			HashMap<Integer, Set<Integer>> shapePoints = new HashMap<>();
			shapePoints = getPiece(pieceCount, spawnX, maxY);
			int movesCounter = 0;
			while (true) {
				if (movesCounter % 2 == 0) {
					int moveDir = (directions[directionCount % directions.length].equals("<")) ? -1 : 1;
					boolean canMove = canMoveByWind(moveDir, points, shapePoints);
					HashMap<Integer, Set<Integer>> newShapePoints = new HashMap<>(shapePoints);
					if (canMove) {
						for (int key : shapePoints.keySet()) {
							Set<Integer> movedSet = new HashSet<>();
							for (int singlePoint : shapePoints.get(key)) {
								movedSet.add(singlePoint + moveDir);
							}
							newShapePoints.put(key, movedSet);
						}
					}
					shapePoints = newShapePoints;
					directionCount++;
				} else {
					boolean canFall = canFall(points, shapePoints);
					if (canFall) {
						HashMap<Integer, Set<Integer>> newShapePoints = new HashMap<>();
						for (int key : shapePoints.keySet()) {
							newShapePoints.put(key - 1, shapePoints.get(key));
						}
						shapePoints = newShapePoints;
					} else {
						for (int key : shapePoints.keySet()) {
							maxY = Math.max(maxY, key);
							if (points.containsKey(key)) {
								for (int point : shapePoints.get(key)) {
									points.get(key).add(point);
								}
							} else {
								HashSet<Integer> movedSet = new HashSet<>();
								for (int point : shapePoints.get(key)) {
									movedSet.add(point);
								}
								points.put(key, movedSet);
							}
						}
						break;
					}
				}
				movesCounter++;
			}
			pieceHeight.put(pieceCount + 1, maxY);
		}
		return maxY;
	}

	private static HashMap<Integer, Set<Integer>> getPiece(int pieceCount, int spawnX, int maxY) {
		if (pieceCount % 5 == 0)
			return new HLane(spawnX, maxY + 4).points;
		if (pieceCount % 5 == 1)
			return new Plus(spawnX, maxY + 4).points;
		if (pieceCount % 5 == 2)
			return new ReverseL(spawnX, maxY + 4).points;
		if (pieceCount % 5 == 3)
			return new VLane(spawnX, maxY + 4).points;
		if (pieceCount % 5 == 4)
			return new Square(spawnX, maxY + 4).points;
		return null;
	}

	private static repeatedBlock repeatBlockLenght(HashMap<Integer, HashSet<Integer>> points) {
		repeatedBlock block = new repeatedBlock();
		for (int i = 1; i <= points.size() - 1; i++) {
			for (int j = i + 10; j < points.size(); j++) {
				if (points.get(i).equals(points.get(j))) {
					boolean isGood = true;
					int blockSize = j - i;
					for (int x = i; x < j - 1; x++) {
						boolean x1 = points.get(x).equals(points.get(x + blockSize));
						boolean x2 = points.get(x).equals(points.get(x + blockSize * 2));
						boolean x3 = points.get(x).equals(points.get(x + blockSize * 3));
						if (!(x1 && x2 && x3)) {
							isGood = false;
							break;
						}
					}
					if (isGood) {
						block.size = blockSize;
						block.offset = i;
						return block;
					}
				}
			}
		}

		return block;
	}

	public static int getKeyByValue(HashMap<Integer, Integer> map, int value) {
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (Objects.equals(value, entry.getValue())) {
				return entry.getKey();
			}
		}
		return 0;
	}

	public static boolean canMoveByWind(int direction, HashMap<Integer, HashSet<Integer>> mainPoints,
			HashMap<Integer, Set<Integer>> shapePoints) {

		for (int key : shapePoints.keySet()) {
			if (mainPoints.containsKey(key)) {
				for (int singlePoint : shapePoints.get(key)) {
					if (mainPoints.get(key).contains(singlePoint + direction)
							|| singlePoint + direction == 0 | singlePoint + direction == 8) {
						// containt rock on spot wehre we want to move - cant move
						return false;
					}
				}
			} else {
				for (int singlePoint : shapePoints.get(key)) {
					if (singlePoint + direction == 0 | singlePoint + direction == 8) {
						// containt rock on spot wehre we want to move - cant move
						return false;
					}
				}
			}
		}
		return true;
	}

	public static boolean canFall(HashMap<Integer, HashSet<Integer>> mainPoints,
			HashMap<Integer, Set<Integer>> shapePoints) {
		for (int key : shapePoints.keySet()) {
			if (key - 1 == 0) {
				return false;
			}
			if (mainPoints.containsKey(key - 1)) {
				for (int singlePoint : shapePoints.get(key)) {
					if (mainPoints.get(key - 1).contains(singlePoint)) {
						// containt rock on spot wehre we want to move - cant move
						return false;
					}
				}
			}
		}
		return true;
	}

}

class repeatedBlock {
	int size;
	int offset;
}

class HLane {
	HashMap<Integer, Set<Integer>> points = new HashMap<>();

	HLane(int spawnX, int spawnY) {
		points.put(spawnY, Set.of(spawnX, spawnX + 1, spawnX + 2, spawnX + 3));
	}
}

class Plus {
	HashMap<Integer, Set<Integer>> points = new HashMap<>();

	Plus(int spawnX, int spawnY) {

		points.put(spawnY, Set.of(spawnX + 1));
		points.put(spawnY + 1, Set.of(spawnX, spawnX + 1, spawnX + 2));
		points.put(spawnY + 2, Set.of(spawnX + 1));
	}
}

class ReverseL {
	HashMap<Integer, Set<Integer>> points = new HashMap<>();

	ReverseL(int spawnX, int spawnY) {
		points.put(spawnY, Set.of(spawnX, spawnX + 1, spawnX + 2));
		points.put(spawnY + 1, Set.of(spawnX + 2));
		points.put(spawnY + 2, Set.of(spawnX + 2));
	}
}

class VLane {
	HashMap<Integer, Set<Integer>> points = new HashMap<>();

	VLane(int spawnX, int spawnY) {
		points.put(spawnY, Set.of(spawnX));
		points.put(spawnY + 1, Set.of(spawnX));
		points.put(spawnY + 2, Set.of(spawnX));
		points.put(spawnY + 3, Set.of(spawnX));
	}
}

class Square {
	HashMap<Integer, Set<Integer>> points = new HashMap<>();

	Square(int spawnX, int spawnY) {
		points.put(spawnY, Set.of(spawnX, spawnX + 1));
		points.put(spawnY + 1, Set.of(spawnX, spawnX + 1));
	}
}