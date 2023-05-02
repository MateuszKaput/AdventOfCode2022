package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
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
		List<String> inputStrings = new ArrayList<>();
		List<String> sortedList = new ArrayList<>();
		int mainIndex = 1;
		int mainSum = 0;

		while (scanner.hasNextLine()) {
			String oneLine = scanner.nextLine();
			if (!(oneLine == "")) {
				inputStrings.add(oneLine);
			}
		}

		for (int i = 1; i < inputStrings.size() - 1; i += 2) {
			String left = inputStrings.get(i - 1);
			String right = inputStrings.get(i);
			if (compareStrings(left, right)) {
				mainSum += mainIndex;
			}
			mainIndex++;
		}

		for (int i = 0; i < inputStrings.size(); i++) {
			String currentChecking = inputStrings.get(i);
			boolean added = false;
			if (sortedList.size() == 0) {
				sortedList.add(currentChecking);
			} else {
				for (int j = 0; j < sortedList.size(); j++) {
					String sortedOne = sortedList.get(j);
					if (compareStrings(currentChecking, sortedOne)) {
						sortedList.add(j, currentChecking);
						added = true;
						break;
					}
				}
				if (!added)
					sortedList.add(currentChecking);
			}
		}
		
		String a = "[[2]]";
		String b = "[[6]]";

		for (int i = 0; i < sortedList.size(); i++) {
			String current = sortedList.get(i);
			if (compareStrings(a, current)) {
				sortedList.add(i, a);
				System.out.println("Adding first into index: " + i);
				break;
			}
		}

		for (int i = 0; i < sortedList.size(); i++) {
			String current = sortedList.get(i);
			if (compareStrings(b, current)) {
				sortedList.add(i, b);
				System.out.println("Adding second into index: " + i);
				break;
			}
		}

//		sortedList.forEach(line -> {
//			System.out.println(line);
//		});

		System.out.println(mainSum);
		scanner.close();
	}

	private static boolean compareStrings(String left, String right) {
		int lIndex = 0;
		int rIndex = 0;

		while (lIndex < left.length() && rIndex < right.length()) {
			char lChar = left.charAt(lIndex);
			char rChar = right.charAt(rIndex);

			if ((lChar == '[' && rChar == '[') || (lChar == ']' && rChar == ']') || (lChar == ',' && rChar == ',')) {
				lIndex++;
				rIndex++;
			}

			if (lChar == '[' && Character.isDigit(rChar))
				right = addList(right, rIndex);

			if (Character.isDigit(lChar) && rChar == '[')
				left = addList(left, lIndex);

			if ((Character.isDigit(lChar) && rChar == ']') || (lChar == '[' && rChar == ']')
					|| (lChar == ',' && rChar == ']')) {
				return false;
			}

			if ((lChar == ']' && Character.isDigit(rChar)) || (lChar == ']' && rChar == '[')
					|| (lChar == ']' && rChar == ',')) {
				return true;
			}

			if (Character.isDigit(lChar) && Character.isDigit(rChar)) {
				String lNum = getWholeNumber(left, lIndex);
				String rNum = getWholeNumber(right, rIndex);
				if (Integer.parseInt(lNum) < Integer.parseInt(rNum)) {
					return true;
				} else if (Integer.parseInt(rNum) < Integer.parseInt(lNum)) {
					return false;
				} else {
					lIndex += lNum.length();
					rIndex += rNum.length();
				}
			}
		}
		return true;
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
