package net.pureessence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class ArrayExample {
	// constants
	private static final String LINE_DELIMITER = ",,";
	
	private static final String POPULATION_FILE = "population.txt";
	private static final String CITIES_FILE = "cities.txt";
	private static final String FOUND_FILE = "found.txt";
	
	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	
	public static void main(String[] args) {
		
		String populationText = getFileContent(POPULATION_FILE);
		String citiesText = getFileContent(CITIES_FILE);
		
		// split population file by delimiter
		String[] populationLines = populationText.split(LINE_DELIMITER);
		
		// initialize a cities array to hold just the city names
		String[] cityNames = new String[populationLines.length];
		// initialize a population array to hold just the population
		String[] populations = new String[populationLines.length];
		
		// the two arrays will correspond by their index
		for(int i = 0; i < populationLines.length; i++) {
			String populationLine = populationLines[i];
			String[] cityWithPopulation = populationLine.split(","); // because the string contains stuff like: Philadelphia,1536471
			
			cityNames[i] = cityWithPopulation[0];
			populations[i] = cityWithPopulation[1];
		}

		// parse the cities to search for
		String[] citiesToSearch = citiesText.split(LINE_DELIMITER);
		
		// initialize found content to be written to output file
		String foundContent = "";
		
		// for each city to search for
		for(String cityToSearch : citiesToSearch) {
			// find that city's index in the city array
			int cityIndex = getCityIndex(cityToSearch, cityNames);
			
			String foundText = "";
			
			// if the index is -1, it means the city is not found
			if(cityIndex == -1) {
				foundText = String.format("population for city '%s' is NOT FOUND", cityToSearch);
			} else {
				// otherwise, get the corresponding population from the population array with the same index as the city array
				String populationForTheCity = populations[cityIndex];
				foundText = String.format("population for city '%s' is '%s'", cityToSearch, populationForTheCity);
			}
			
			foundContent += foundText + LINE_SEPARATOR;
		}
		
		// write found
		writeToFile(FOUND_FILE, foundContent);
		
	}
	
	/**
	 * get the city index or return -1 if the city is not found
	 * @param cityToSearch
	 * @param cityNames
	 * @return int
	 */
	public static int getCityIndex(String cityToSearch, String[] cityNames) {
		for(int i = 0; i < cityNames.length; i++) {
			if(cityNames[i].equals(cityToSearch)) {
				return i;
			}
		}
		return -1; // to indicate that the city is NOT FOUND
	}
	

	/**
	 * write file
	 * @param filename
	 * @param toWrite
	 */
	public static void writeToFile(String filename, String toWrite) {
		try {
			// Create file
			FileWriter fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(toWrite);
			// Close the output stream
			out.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e);
		}
	}
	
	/**
	 * Read out of the file content as a string
	 * @param filename
	 * @return String
	 */
	public static String getFileContent(String filename) {
		String fileContent = "";
		try {
			// Open the file that is the first
			// command line parameter
			FileInputStream fstream = new FileInputStream(filename);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				
				fileContent += strLine + LINE_DELIMITER;
				
			}
			// Close the input stream
			in.close();
		} catch (Exception e) {// Catch exception if any
			System.err.println("Error: " + e);
		}
		return fileContent;
	}
}
