package aocDay2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	static boolean check(String game, Integer blue, Integer red, Integer green) {
		boolean valid = false;
		int index;
		int count;
		String blueCount = "";
		String redCount = "";
		String greenCount = "";
		game = game.replaceAll("\\s", "");
		// Check blue
		if (game.contains("blue")) {
			index = game.indexOf("blue");
			if (index - 2 >= 0) {
				if (game.charAt(index - 2) >= 48 && game.charAt(index - 2) <= 57) {
					blueCount += game.charAt(index - 2);
					blueCount += game.charAt(index - 1);
				}
				else {
					blueCount += game.charAt(index - 1);
				}
			}
			else {
				blueCount += game.charAt(index - 1);
			}
			count = Integer.parseInt(blueCount);
			if (count > blue) {
				//System.out.println("Failed " + blueCount + " Blue");
				return valid;
			}
		}
		// Check red
		if (game.contains("red")) {
			index = game.indexOf("red");
			if (index - 2 >= 0) {
				if (game.charAt(index - 2) >= 48 && game.charAt(index - 2) <= 57) {
					redCount += game.charAt(index - 2);
					redCount += game.charAt(index - 1);
				}
				else {
					redCount += game.charAt(index - 1);
				}
			}
			else {
				redCount += game.charAt(index - 1);
			}
			count = Integer.parseInt(redCount);
			if (count > red) {
				//System.out.println("Failed " + redCount + " Red");
				return valid;
			}
		}
		// Check green
		if (game.contains("green")) {
			index = game.indexOf("green");
			if (index - 2 >= 0) {
				if (game.charAt(index - 2) >= 48 && game.charAt(index - 2) <= 57) {
					greenCount += game.charAt(index - 2);
					greenCount += game.charAt(index - 1);
				}
				else {
					greenCount += game.charAt(index - 1);
				}
			}
			else {
				greenCount += game.charAt(index - 1);
			}
			count = Integer.parseInt(greenCount);
			if (count > green) {
				//System.out.println("Failed " + greenCount + " Green");
				return valid;
			}
		}
		//System.out.println("Blue: " + blueCount + " Red: " + redCount + " Green: " + greenCount);
		valid = true;
		return valid;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileReader reader = new FileReader("input.txt");
			int data = reader.read();
			
			ArrayList<Integer> games = new ArrayList<Integer>();
			String line = "";
			String gameString = "";
			String setString = "";
			String gameNumber = "";
			String set = "";
			// Game parameters
			int BLUE = 14;
			int RED = 12;
			int GREEN = 13;
			
			while (data != -1) {
				line += (char)data;
				if ((char)data == '\n') {
					// Parse game number
					gameString = line.substring(0, line.indexOf(':'));
					setString = line.substring(line.indexOf(':') + 2);
					for (int i = 0; i < gameString.length(); i++) {
						int asciiValue = line.charAt(i);
						if (asciiValue >= 48 && asciiValue <= 57) {
							gameNumber += line.charAt(i);
						}
					}
					int currentGame = Integer.parseInt(gameNumber);
					
					// Check each set
					int endIndex = setString.indexOf(';');
					set = setString.substring(0, endIndex);
					boolean test = check(set, BLUE, RED, GREEN);
					if (test) {
						int startIndex = endIndex + 1;
						setString = setString.substring(startIndex);
						endIndex = setString.indexOf(';');
						while (endIndex != -1 && test) {
							set = setString.substring(0, endIndex);
							test = check(set, BLUE, RED, GREEN);
							startIndex = endIndex + 1;
							setString = setString.substring(startIndex);
							endIndex = setString.indexOf(';');
						}
						if (test) {
							boolean finalTest = check(setString, BLUE, RED, GREEN);
							if (finalTest) {
								games.add(currentGame);
								//System.out.println("Game " + currentGame + " added");
							}
						}
					}
					gameNumber = "";
					line = "";
				}
				data = reader.read();
			}
			reader.close();
			int total = 0;
			for (int i = 0; i < games.size(); i++) {
				total += games.get(i);
			}
			System.out.println("Total: " + total);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
