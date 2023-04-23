package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class ChallengeDay12BruteForce {

	static String inputFile = "./resources/InputDay12";
	static List<String[]> rowsOfMap = getList(inputFile);
	static int maxRow = rowsOfMap.size();
	static int maxCol = rowsOfMap.get(0).length;

	static Node[][] node = new Node[maxRow][maxCol];
	static Node startNode;
	static Node goalNode;
	static Node currentNode;
	static ArrayList<Node> openList = new ArrayList<>();
	static ArrayList<Node> checkedList = new ArrayList<>();

	static boolean goalReached = false;

	public static void main(String[] args) {

		for (int i = 0; i < rowsOfMap.size(); i++) {
			for (int j = 0; j < rowsOfMap.get(i).length; j++) {
				int ascii = (int) rowsOfMap.get(i)[j].charAt(0);
				if (ascii == 83) {
					Node singleNode = new Node(i, j, 97);
					node[i][j] = singleNode;
					setStartNode(i, j);
				} else if (ascii == 69) {
					Node singleNode = new Node(i, j, 122);
					node[i][j] = singleNode;

					setGoalNode(i, j);
				} else {
					Node singleNode = new Node(i, j, ascii);
					node[i][j] = singleNode;
				}
			}
		}
		setCostOfNodes();
		search();
	}

	public static void search() {

		while (goalReached == false) {
			int row = currentNode.row;
			int col = currentNode.col;
			currentNode.setAsChecked();
			checkedList.add(currentNode);
			openList.remove(currentNode);

			if (row - 1 >= 0) {
				openNode(node[row - 1][col]);
			}
			if (col - 1 >= 0) {
				openNode(node[row][col - 1]);
			}
			if (row + 1 < maxRow) {
				openNode(node[row + 1][col]);
			}
			if (col + 1 < maxCol) {
				openNode(node[row][col + 1]);
			}

			int bestNodeIndex = 0;
			int bestNodeCost = 9999;

			for (int i = 0; i < openList.size(); i++) {
				if (openList.get(i).fCost < bestNodeCost) {
					bestNodeIndex = i;
					bestNodeCost = openList.get(i).fCost;
				}
			}
			currentNode = openList.get(bestNodeIndex);

			if (currentNode == goalNode) {
				goalReached = true;
			}
		}
		trackThePat();
	}

	private static void openNode(Node node) {
		if (node.open == false && node.checked == false && node.value - currentNode.value < 2) {
			node.setAsOpen();
			node.parent = currentNode;
			openList.add(node);
		}
	}

	public static class Node {
		Node parent = null;
		int value;
		int col;
		int row;
		int gCost;
		int hCost;
		int fCost;
		boolean start;
		boolean goal;
		boolean open;
		boolean checked;

		public Node(int row, int col, int value) {
			this.col = col;
			this.row = row;
			this.value = value;
		}

		public void setAsStart() {
			start = true;
		}

		public void setAsGoal() {
			goal = true;
		}

		public void setAsChecked() {
			checked = true;
		}

		public void setAsOpen() {
			open = true;
		}
	}

	private static void setStartNode(int row, int col) {
		node[row][col].setAsStart();
		startNode = node[row][col];
		currentNode = startNode;
	}

	private static void setGoalNode(int row, int col) {
		node[row][col].setAsGoal();
		goalNode = node[row][col];
	}

	private static void getCost(Node node) {

		int xDistanceS = Math.abs(node.col - startNode.col);
		int yDistanceS = Math.abs(node.row - startNode.row);
		node.gCost = xDistanceS + yDistanceS;
		int xDistanceE = Math.abs(node.col - goalNode.col);
		int yDistanceE = Math.abs(node.row - goalNode.row);
		node.hCost = xDistanceE + yDistanceE;
		node.fCost = node.gCost + node.hCost;
	}

	private static void setCostOfNodes() {
		int col = 0;
		int row = 0;
		while (row < maxRow && col < maxCol) {
			getCost(node[row][col]);
			col++;
			if (col == maxCol) {
				col = 0;
				row++;
			}
		}
	}

	private static void trackThePat() {
		Node current = goalNode;
		int pathLenght = 0;
		do {
			rowsOfMap.get(current.row)[current.col] = ".";
			current = current.parent;
			pathLenght++;
		} while (current != startNode);

		for (String[] node : rowsOfMap) {
			for (String string : node) {
				System.out.print(string+" ");
			}
			System.out.println("");
		}
		System.out.println("Lenght of path after " + pathLenght);
	}

	private static List<String[]> getList(String inputFile) {
		List<String[]> returnList = new ArrayList<>();

		
		try {
			File myInput = new File(inputFile);
			Scanner scanner;
			scanner = new Scanner(myInput);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				returnList.add(data.split(""));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return returnList;
	}
}