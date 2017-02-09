package ml224ec_assign1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WarAndPeace {
	
	private static final boolean DEBUG = false;
	
	private static final int WORDS_TO_ONE_DOT = 10000;
	private static int wordCount = 0;

	public static void main(String[] args) {
		
		String text = readText("WarAndPeace.txt");   // My own method
		String[] words = text.split(" ");  // Simple split, will be cleaned up later on
		System.out.println("Initial word count: "+words.length);   // We found 577091
				
		Stream<String> stream = Arrays.stream(words);
		
		stream = stream.map(s -> 
		s.toLowerCase()
		.replaceAll("[^a-z-\']", "") // REGEX remove all non-alphabetic except dash and apostrophe
				);
		stream = stream.filter(s -> !s.isEmpty());
		stream = stream.distinct();
		
		int uniqueWordCount = (int)stream.count();
		
		System.out.printf("Estimated count of unique words found: %d", uniqueWordCount);
	}

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
	
}
