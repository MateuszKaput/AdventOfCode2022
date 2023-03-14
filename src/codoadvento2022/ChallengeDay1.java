package codoadvento2022;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ChallengeDay1 {

	public static void main(String[] args) {
		String inputFile = "./resources/InputDay1";
		
		int partOne = findTopOne(inputFile);
		System.out.println(partOne);
		int partTwo = findTopThree(inputFile);
		System.out.println(partTwo);
	}
	
	public static int findTopOne (String path) {
		
		int tempCal = 0;
		int maxCal = 0;
		
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				
				String data = scanner.nextLine();
				if(data == "") {
					maxCal = Math.max(maxCal, tempCal);
					tempCal = 0;
				}else {
					tempCal += Integer.parseInt(data);
				}
			}
			
			scanner.close();
		}catch (FileNotFoundException e){
			
			System.out.println("No file");
			e.printStackTrace();
		}
		return maxCal;
	}
	
	public static int findTopThree (String path) {
		
		int totalSum = 0;
		int tempCal = 0;
		int[] topThree = new int [3];
		
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			while(scanner.hasNextLine()) {
				
				String data = scanner.nextLine();
				if(data == "") {
					topThree[2]= Math.max(tempCal, topThree[2]);
					
					if(topThree[2]>topThree[1])
						swap(topThree,1,2);
					
					if(topThree[1]>topThree[0])
						swap(topThree,0,1);
					
					tempCal = 0;
				}else {
					tempCal += Integer.parseInt(data);
				}
			}
			for(int i =0; i<3 ; i++) {
				totalSum+= topThree[i];
				System.out.println(topThree[i]);
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return totalSum;
	}
	
	public static int[] swap(int[] values, int pos1, int pos2) {
		int tempVal = values[pos1];
		values[pos1]= values[pos2];
		values[pos2] = tempVal;
		return values;
	}
}
