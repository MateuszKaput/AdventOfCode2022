package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class ChallengeDay13 {
	public static void main(String[] args) throws FileNotFoundException {
		String inputFile = "./resources/InputDay13";
		File myInput = new File(inputFile);
		Scanner scanner = new Scanner(myInput);

		int mainIndex = 1;
		int mainSum = 0;
		while (scanner.hasNextLine()) {
			Stack<List<Integer>> leftStack = new Stack<>();
			Stack<List<Integer>> rightStack = new Stack<>();
			String left = scanner.nextLine();
			String right = scanner.nextLine();
			String empty = (scanner.hasNextLine()) ? scanner.nextLine() : "";

			int lIndex = 0;
			int rIndex = 0;

			while (lIndex < left.length() && rIndex < right.length()) {
				char lChar = left.charAt(lIndex);
				char rChar = right.charAt(rIndex);

				System.out.println("Checking pair:"+lChar+" "+rChar+" index: "+mainIndex);

				if ((lChar == '[' && rChar == '[') || (lChar == ']' && rChar == ']')
						|| (lChar == ',' && rChar == ',')) {
					lIndex++;
					rIndex++;
				}
				
				if (lChar == '[' && Character.isDigit(rChar))
					right = addList(right, rIndex);
				
				if (Character.isDigit(lChar) && rChar == '[')
					left = addList(left, lIndex);
				
				if (Character.isDigit(lChar) && rChar == ']')
					break;
				
				if (lChar == '[' && rChar == ']')
					break;
				
				if (lChar == ',' && rChar == ']')
					break;

				if (lChar == ']' && Character.isDigit(rChar)) {
					System.out.println("Correct " + mainIndex);
					mainSum+=mainIndex;
					break;
				}
				if (lChar == ']' && rChar == '[') {
					System.out.println("Correct " + mainIndex);
					mainSum+=mainIndex;
					break;
				}
				if (lChar == ']' && rChar == ',') {
					System.out.println("Correct " + mainIndex);
					mainSum+=mainIndex;
					break;
				}

				if (Character.isDigit(lChar) && Character.isDigit(rChar)) {
					String lNum = getWholeNumber(left, lIndex);
					String rNum = getWholeNumber(right, rIndex);
					if (Integer.parseInt(lNum) < Integer.parseInt(rNum)) {
						System.out.println("Correct " + mainIndex);
						mainSum+=mainIndex;
						break;
					} else if (Integer.parseInt(rNum) < Integer.parseInt(lNum)) {
						break;
					} else {
						lIndex += lNum.length();
						rIndex += rNum.length();
					}
				}
			}
			mainIndex++;
		}
		System.out.println(mainSum);
		scanner.close();
	}

	private static String addList(String oldString, int index) {
		String newString = "";
		if (oldString.charAt(index) == ']') {
			newString = oldString.substring(0, index) + "[]" + oldString.substring(index, oldString.length());
		} else {
			String number = getWholeNumber(oldString, index);
			newString = oldString.substring(0, index) + "[" + number + "]"
					+ oldString.substring(index + number.length(), oldString.length());
		}

		return newString;
	}

	private static String getWholeNumber(String right, int rIndex) {
		String number = "";
		while (!(right.charAt(rIndex) == ',' || right.charAt(rIndex) == ']')) {
			number += right.charAt(rIndex);
			rIndex++;
		}
		return number;
	}
}
