package ml224ec_assign3.count_words;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IdentifyWordsMain {

	public static void main(String[] args) {
		
		// code reused from the WarAndPeace exercise. For shame!
		String targetFile = "HistoryOfProgramming.txt";
		System.out.printf("Reading words from %s\n", targetFile);
		String text = readText(targetFile);
		String[] words = text.split(" ");
				
		System.out.printf("Filtering input...\n");
		Stream<String> stream = Arrays.stream(words);
		
		stream = stream.map(s -> 
		s.replaceAll("[^A-Za-z-\']", "") // REGEX remove all non-alphabetic except dash and apostrophe
				);
		
		//words = (String[]) stream.toArray();
		
		String outFile = "words.txt";
		System.out.printf("Printing result to %s\n", outFile);
		String outputText = stream.collect(Collectors.joining("\n"));
		writeText(outFile,outputText);
		
		System.out.printf("Done!\n");
	}

	// reused code from my solution for the WarAndPeace exercise featured in assignment 1
	private static String readText(String filename)
	{
		Path path = Paths.get(filename);
		try {
			return Files.readAllLines(path).stream().collect(Collectors.joining(" "))
					.replaceAll("—", " — "); // fixes EM-DASHES (NOT normal dashes which are "-", em-dashes are "—") not surrounded by spaces, causes a massive count error as words, seperated by a single em-dash and not a normal dash, are treated as one single word
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// I might be able to reuse these two functions someday
	private static void writeText(String filename, String text)
	{
		writeText(filename, new String[]{text});
	}
	
	private static void writeText(String filename, String[] content)
	{
		Path path = Paths.get(filename);
		try 
		{
			Files.write(path, Arrays.asList(content), 
					StandardOpenOption.CREATE, StandardOpenOption.WRITE);
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
