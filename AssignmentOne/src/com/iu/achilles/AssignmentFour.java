package com.iu.achilles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class AssignmentFour {
	
	public static HashMap<Character, Integer> hash = new HashMap<>();
	public static final String FILE_NAME = "/Users/balajirajaram/Desktop/indiana_univ/alogs-course/assignment-4/carl-the-trailer.txt";
	public static final char[] VALID_CHARACTERS = "abcdefghijklmnopqrstuvwxyz .,!?'".toCharArray();
	
	static void countFrequency() throws IOException {
		
		
		for (char c: VALID_CHARACTERS) {
			hash.put(c,0);
		}
		
		BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
		
		while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            for (int i = 0; i < line.length(); i++) {
                char c = line.toLowerCase().charAt(i);
                int value = hash.getOrDefault(c, -1);
                if (value >= 0) {
                    hash.put(c, value + 1);
                }
            }
        }

        // Close object.
        reader.close();
        
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		countFrequency();
		for (char key : hash.keySet()) {
            System.out.println( key + ": " + hash.get(key));
        }
	}

}
