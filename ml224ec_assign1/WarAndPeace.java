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
		
		System.out.printf("Filtering unique words: (one dot = %d processed words)\n", WORDS_TO_ONE_DOT);
		List<String> seen = new ArrayList<String>();
		
		int uniqueWordCount = (int)stream.filter((string) -> 
		// start predicate
		{
			wordCount++;
			if (wordCount%WORDS_TO_ONE_DOT == 0)
				System.out.print('.');
			
			if (DEBUG)
				System.out.println(string);
			
			String str = string;
			if (str == null)
				return false;

			str = str
					.toLowerCase()
					.replaceAll("[^a-z-\']", ""); // REGEX remove all non-alphabetic except dash and apostrophe
			
			if (!str.isEmpty())
			{
				if (!seen.contains(str))
				{
					seen.add(str);
					if (DEBUG)
						System.out.printf("Unique \'%s\'\n", str);
					return true;
				}
				else if (DEBUG)
					System.out.printf("Ignoring \'%s\' ('%s'): Seen\n", string, str);
			}
			else if (DEBUG)
				System.out.printf("Ignoring '%s': Invaild\n", string);
			return false;		
		}
		// end predicate
		).count();
		System.out.println("");
		
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
