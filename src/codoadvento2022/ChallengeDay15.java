package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ChallengeDay15 {

	public static void main(String[] args) throws FileNotFoundException {

		String inputFile = "./resources/InputDay15";
		File myInput = new File(inputFile);
		Scanner scanner = new Scanner(myInput);

		ArrayList<Sensor> sensorList = new ArrayList<>();
		ArrayList<Beacon> beaconList = new ArrayList<>();

		while (scanner.hasNext()) {
			String[] oneLane = scanner.nextLine().split(" ");
			int sensorX = Integer.parseInt(oneLane[2].substring(2, oneLane[2].length() - 1));
			int sensorY = Integer.parseInt(oneLane[3].substring(2, oneLane[3].length() - 1));
			int beaconX = Integer.parseInt(oneLane[8].substring(2, oneLane[8].length() - 1));
			int beaconY = Integer.parseInt(oneLane[9].substring(2, oneLane[9].length()));

			Beacon beacon = new Beacon(beaconX, beaconY);
			Sensor sensor = new Sensor(sensorX, sensorY);

			sensor.setRange(beacon);

			beaconList.add(beacon);
			sensorList.add(sensor);
		}

		scanner.close();
		System.out.println("Beacons: ");
		for (Beacon beacon : beaconList) {
			System.out.println("x: " + beacon.x + " y: " + beacon.y);
		}
		System.out.println("Beacons: ");
		for (Sensor sensor : sensorList) {
			System.out.println("x: " + sensor.x + " y: " + sensor.y + " range: " + sensor.range);
		}
		long solutionX = 0;
		long solutionY = 0;

		for (int i = 0; i < 4000000; i++) {
			for (int j = 0; j < 4000000; j++) {
				boolean isSolution = true;
				for (Sensor sensor : sensorList) {
					int distance = Math.abs(i - sensor.x) + Math.abs(j - sensor.y);
					if (distance <= sensor.range) {
						j += sensor.range - distance;
						isSolution = false;
					}
				}
				if (isSolution) {
					solutionX = i;
					solutionY = j;
				}
			}
			if (i % 40000 == 0) {
				System.out.println(i / 40000 + "%");
			}
		}
		System.out.println(solutionX*4000000+solutionY);
	}
}

class Sensor {
	int x;
	int y;
	int range;

	Sensor(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void setRange(Beacon beacon) {
		range = Math.abs(beacon.x - x) + Math.abs(beacon.y - y);
	}
}

class Beacon {
	int x;
	int y;

	Beacon(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
