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

public class ChallengeDay11 {
	public static void main(String[] args) {
		
		String inputFile = "./resources/InputDay11";
		partTwoResult(inputFile);
			
	}
	
	private static int partTwoResult(String path) {
		List<Monkey> monkeys = getApes(path);

		Map<Integer,Monkey> monkeyMap = new HashMap<>();
		for (Monkey monkey1 : monkeys) {
            monkeyMap.put(monkey1.getId(), monkey1);
        }
		
		for (int i = 0; i < 10000; i++) {
            if (i == 1 || i == 20 || i == 1000 ||
                i == 2000 || i == 3000 || i == 4000 ||
                i == 5000 || i == 6000 || i == 7000 ||
                i == 8000 || i == 9000 || i == 10000
            ) {
                System.out.println("== After round " + i + " ==");
                for (Monkey monkey1 : monkeys) {
                    monkey1.printInspections();
                }
                System.out.println();
            }

            for (Monkey monkey1 : monkeys) {
                monkey1.doRotation(monkeyMap);
            }
        }
		
		List<Integer> inspections = new ArrayList<>();
		for(Monkey ape : monkeys) {
			inspections.add(ape.getChecks());
			ape.printInspections();
		}
		
		inspections.sort((a,b) -> b - a);
		
		System.out.println(BigInteger.valueOf(inspections.get(0)).multiply(BigInteger.valueOf(inspections.get(1))));
		return 0;
	}
	
	public static List<Monkey> getApes(String pathFile){
		
		Monkey monkey = null;
		List<Monkey> monkeys = new ArrayList<>();
		int counter = 0;
		
		try {
			File myInput = new File(pathFile);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				String[] splitedApe = data.split(":");
				
				switch(counter % 7) {
				case 0:
					String monkeyNumber = splitedApe[0].split(" ")[1];
					monkey = new Monkey(monkeyNumber);
					monkeys.add(monkey);
					break;
				case 1:
					for (String item : splitedApe[1].split(",")) {
						int number = Integer.parseInt(item.trim());
						monkey.addItem(number);
					}
					break;
				case 2:
					String operator = splitedApe[1].split(" ")[4];
					if(splitedApe[1].split(" ")[5].equals("old")) {
						operator = "^";
					}else {
						monkey.addOperatorValue(splitedApe[1].split(" ")[5]);
					}
					monkey.addOperatorChar(operator);
					break;
				case 3:
					String modulo = splitedApe[1].split(" ")[3].trim();
					monkey.addModuloValue(modulo);
					break;
				case 4:
					String trueApeNumber = splitedApe[1].split(" ")[4].trim();
					monkey.addTrueValue(trueApeNumber);
					break;
				case 5:
					String falseApeNumber = splitedApe[1].split(" ")[4].trim();
					monkey.addFalseValue(falseApeNumber);
					break;
				case 6:
					break;
				default:
					System.out.println("Something went wrong: "+counter);
				}
				counter++;
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return monkeys;
	}
}