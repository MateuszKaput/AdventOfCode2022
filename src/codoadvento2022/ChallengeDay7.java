package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class ChallengeDay7 {
	
	public static int maxSize = 100000;
	public static int requiredCapacity = 30000000;
	public static int maxCapacity = 70000000;
	public static int totalSize = 0;
	static List<Integer> allValues1 = new ArrayList<>();
	static List<Integer> allValues2 = new ArrayList<>();
	public static void main(String[] args) {
		
		String inputFile = "./resources/InputDay7";
		int partOne = partOneResult(inputFile);
		System.out.println("Part one: "+partOne);
		int partTwo = partTwoResult(inputFile);
		System.out.println("Part two: "+partTwo);

	}
	
	private static int partOneResult(String path) {
		int sumForFirstPart = 0;
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			Stack<String> currentPath = new Stack<>();
			Directory mainDirectory = new Directory("/");
			
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				Directory currentDirectory = mainDirectory;
				String command = data.split(" ")[0];
				if(command.equals("$")) {
					if(data.split(" ")[1].equals("cd")) {
						if(data.split(" ")[2].equals("..")) {
							currentPath.pop();
						}else if(data.split(" ")[2].equals("/")){
							currentPath.clear();
							currentPath.push(data.split(" ")[2]);
						}else {
							currentPath.push(data.split(" ")[2]);
						}
					}
				}else if(command.equals("dir")){
					for(int i=1; i<currentPath.size(); i++) {
						currentDirectory = currentDirectory.dirsInside.get(currentPath.elementAt(i));
					}
					String name = data.split(" ")[1];
					currentDirectory.dirsInside.put(name, new Directory(name));
				}else {
					for(int i=1; i<currentPath.size(); i++) {
						currentDirectory = currentDirectory.dirsInside.get(currentPath.elementAt(i));
					}
					currentDirectory.filesInside.put(data.split(" ")[1],Integer.parseInt(data.split(" ")[0]));
				}
			}
			mainDirectory.getSize(1);
			for(int value : allValues1) {
				if(value<=maxSize) {
					sumForFirstPart+=value;
				}
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return sumForFirstPart;
	}
	
	private static int partTwoResult(String path) {
		SortedSet<Integer> bigEounghtDirs = new TreeSet<>();
		try {
			File myInput = new File(path);
			Scanner scanner = new Scanner(myInput);
			Stack<String> currentPath = new Stack<>();
			Directory mainDirectory = new Directory("/");
			
			while(scanner.hasNextLine()) {
				String data = scanner.nextLine();
				Directory currentDirectory = mainDirectory;
				String command = data.split(" ")[0];
				if(command.equals("$")) {
					if(data.split(" ")[1].equals("cd")) {
						if(data.split(" ")[2].equals("..")) {
							currentPath.pop();
						}else if(data.split(" ")[2].equals("/")){
							currentPath.clear();
							currentPath.push(data.split(" ")[2]);
						}else {
							currentPath.push(data.split(" ")[2]);
						}
					}
				}else if(command.equals("dir")){
					for(int i=1; i<currentPath.size(); i++) {
						currentDirectory = currentDirectory.dirsInside.get(currentPath.elementAt(i));
					}
					String name = data.split(" ")[1];
					currentDirectory.dirsInside.put(name, new Directory(name));
				}else {
					for(int i=1; i<currentPath.size(); i++) {
						currentDirectory = currentDirectory.dirsInside.get(currentPath.elementAt(i));
					}
					totalSize+=Integer.parseInt(data.split(" ")[0]);
					currentDirectory.filesInside.put(data.split(" ")[1],Integer.parseInt(data.split(" ")[0]));
				}
			}
			mainDirectory.getSize(2);
			int freeCap = maxCapacity-totalSize;
			int missingCap = requiredCapacity-freeCap;
			for(int value : allValues2) {
				if(value>=missingCap) {
					bigEounghtDirs.add(value);
				}
			}
			scanner.close();
		}catch (FileNotFoundException e){
			System.out.println("No file");
			e.printStackTrace();
		}
		return bigEounghtDirs.first();
	}
	
	public static class Directory {
		String name;
		HashMap<String,Directory> dirsInside = new HashMap<>();
		HashMap<String,Integer> filesInside = new HashMap<>();
		
		private int getSize(int x) {
			int size =0;
			
			for(Integer f : filesInside.values()) {
				size+=f;
			}
			for(Directory d : dirsInside.values()) {
				size +=d.getSize(x);
			}

			if(x==1) {
				if(size<=maxSize) {
					allValues1.add(size);
				}
			}else {
				allValues2.add(size);
			}

			return size;
		}
		public Directory(String name) {
			this.name= name;
		}
	}
}
