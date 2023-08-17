package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class ChallengeDay16 {
	public static HashMap<String, Valve> valveList = new HashMap<>();

	public static void main(String[] args) throws FileNotFoundException {

		String inputFile = "./resources/InputDay16";
		File myInput = new File(inputFile);
		Scanner scanner = new Scanner(myInput);

		while (scanner.hasNext()) {
			String singleValveString = scanner.nextLine();
			String[] splitedValve = singleValveString.split(";");

			String valveName = splitedValve[0].split(" ")[1];
			int flowRate = Integer.parseInt(splitedValve[0].split("=")[1]);
			Valve singleValve = new Valve(valveName, flowRate);

			String[] tunnels = splitedValve[1].split(" ");
			for (int i = 5; i < tunnels.length; i++) {
				singleValve.addMove(tunnels[i].substring(0, 2));
			}
			valveList.put(valveName, singleValve);
		}

		scanner.close();
		List<String> openedValve = new ArrayList<>();
		int x = recursiveSearch("AA", openedValve, 30);
		System.out.println(x);
		int y = recursiveSearchSecondPart("AA","AA", openedValve, 26,26);
		System.out.println(y);
	}

	public static int recursiveSearch(String currentValve, List<String> openedValve, int timeLeft) {
		HashMap<String, int[]> possibleFlowOutcom = new HashMap<>();
		possibleFlowOutcom = getDistances(currentValve, possibleFlowOutcom, 0, timeLeft);
		int currentMax = 0;
		for (Map.Entry<String, int[]> entry : possibleFlowOutcom.entrySet()) {
			String key = entry.getKey();
			int[] val = entry.getValue();
			if (val[0] > 0 && !openedValve.contains(key)) {
				List<String> newOpenedValve = new ArrayList<>();
				newOpenedValve.addAll(openedValve);
				newOpenedValve.add(key);
				int outcome = recursiveSearch(key, newOpenedValve, timeLeft - val[1]);
				currentMax = Math.max(currentMax, val[0] + outcome);
			}
		}
		return currentMax;
	}

	public static int recursiveSearchSecondPart(String humanValve, String elephanValve, List<String> openedValve,
			int humanTimeLeft, int elephanTimeLeft) {
		
		HashMap<String, int[]> possibleFlowOutcomHuman = new HashMap<>();
		possibleFlowOutcomHuman = getDistances(humanValve, possibleFlowOutcomHuman, 0, humanTimeLeft);
		
		HashMap<String, int[]> possibleFlowOutcomElephan = new HashMap<>();
		possibleFlowOutcomElephan = getDistances(elephanValve, possibleFlowOutcomElephan, 0, elephanTimeLeft);
		
		int currentMax = 0;
		if(humanTimeLeft>elephanTimeLeft) {
			for (Map.Entry<String, int[]> entry : possibleFlowOutcomHuman.entrySet()) {
				String key = entry.getKey();
				int[] val = entry.getValue();
				if (val[0] > 0 && !openedValve.contains(key)) {
					List<String> newOpenedValve = new ArrayList<>();
					newOpenedValve.addAll(openedValve);
					newOpenedValve.add(key);
					int outcome = recursiveSearchSecondPart(key,elephanValve, newOpenedValve, humanTimeLeft - val[1],elephanTimeLeft);
					currentMax = Math.max(currentMax, val[0] + outcome);
				}
			}
		}else {
			for (Map.Entry<String, int[]> entry : possibleFlowOutcomElephan.entrySet()) {
				String key = entry.getKey();
				int[] val = entry.getValue();
				if (val[0] > 0 && !openedValve.contains(key)) {
					List<String> newOpenedValve = new ArrayList<>();
					newOpenedValve.addAll(openedValve);
					newOpenedValve.add(key);
					int outcome = recursiveSearchSecondPart(humanValve, key, newOpenedValve, humanTimeLeft, elephanTimeLeft - val[1]);
					currentMax = Math.max(currentMax, val[0] + outcome);
				}
			}
		}
		return currentMax;
	}

	public static HashMap<String, int[]> getDistances(String currentValve, HashMap<String, int[]> possibleFlowOutcom,
			int currentDistance, int timeLeft) {

		if (timeLeft < 2)
			return possibleFlowOutcom;
		if (!possibleFlowOutcom.containsKey(currentValve)) {
			int[] flowInfo = new int[2];
			flowInfo[0] = valveList.get(currentValve).flowRate * (timeLeft - 1);
			flowInfo[1] = currentDistance + 1;
			possibleFlowOutcom.put(currentValve, flowInfo);
		}
		valveList.get(currentValve).availableMoves.forEach(key -> {
			int[] flowInfo = new int[2];
			flowInfo[0] = valveList.get(key).flowRate * (timeLeft - 2);
			flowInfo[1] = currentDistance + 2;

			if (!possibleFlowOutcom.containsKey(key)) {
				possibleFlowOutcom.put(key, flowInfo);
				getDistances(key, possibleFlowOutcom, currentDistance + 1, timeLeft - 1);

			} else if (possibleFlowOutcom.get(key)[0] < valveList.get(key).flowRate * (timeLeft - 2)) {
				possibleFlowOutcom.put(key, flowInfo);
				getDistances(key, possibleFlowOutcom, currentDistance + 1, timeLeft - 1);
			} else if (possibleFlowOutcom.get(key)[0] == valveList.get(key).flowRate * (timeLeft - 2)) {
				if (possibleFlowOutcom.get(key)[1] > currentDistance + 2) {
					possibleFlowOutcom.put(key, flowInfo);
					getDistances(key, possibleFlowOutcom, currentDistance + 1, timeLeft - 1);
				}
			}
		});
		return possibleFlowOutcom;
	}
}

class Valve {
	String name;
	int flowRate;
	ArrayList<String> availableMoves = new ArrayList<>();

	Valve(String name, int flowRate) {
		this.name = name;
		this.flowRate = flowRate;
	}

	void addMove(String move) {
		availableMoves.add(move);
	}

}
