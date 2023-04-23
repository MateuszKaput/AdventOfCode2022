package codoadvento2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class ChallengeDay12 {
	static List<Integer> end = null;
	static Map<List<Integer>,Set<List<Integer>>> nww;
	
	public static void main(String[] args) throws FileNotFoundException {
		nww = new HashMap<>();
		Map<List<Integer>, Integer> map = new HashMap<>();
		String inputFile = "./resources/InputDay12";
		File myInput = new File(inputFile);
		Scanner scanner = new Scanner(myInput);
		List<Integer> start = null;
		List<List<Integer>> starts = new ArrayList<>();
		int x, y = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (x = 0; x < line.length(); ++x) {
				char c = line.charAt(x);
				if (c == 'S') {
					c = 'a';
					start = List.of(x, y);
				} else if (c == 'E') {
					c = 'z';
					end = List.of(x, y);
				}
				if(c=='a') {
					starts.add(List.of(x,y));
				}
				map.put(List.of(x, y), c - 'a');
				nww.put(List.of(x, y), new HashSet<>());
			}
			++y;
		}
		
		for(List<Integer> node : map.keySet()) {
            x = node.get(0);
            y = node.get(1);
            for(List<Integer> coords : List.of(List.of(0, 1), List.of(1, 0), List.of(0, -1), List.of(-1, 0))) {
                int xx = x + coords.get(0);
                int yy = y + coords.get(1);
                List<Integer> newCoords = List.of(xx, yy);
                if(map.containsKey(newCoords) && map.get(newCoords) <= map.get(node) + 1)
                    nww.get(node).add(newCoords);
            }
        }
		System.out.println(bfs(List.of(start)));
		System.out.println(bfs(starts));
	}
	static int bfs(List<List<Integer>> starts) {
        Queue<List<Integer>> q = new LinkedList<>(starts);
        Map<List<Integer>, Integer> dist = new HashMap<>();
        for(List<Integer> start : starts) {
            dist.put(start, 0);
        }
        while(!q.isEmpty()) {
        	List<Integer> node = q.poll();
            if(node.equals(end)) {
                return dist.get(node);
            }
            for(List<Integer> nb : nww.get(node)) {
                if(!dist.containsKey(nb)) {
                    dist.put(nb, dist.get(node) + 1);
                    q.add(nb);
                }
            }
        }
        return -1;
    }
	
	
}