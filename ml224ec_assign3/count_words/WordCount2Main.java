package ml224ec_assign3.count_words;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Modified copy of <code>WordCount2Main</code> for Exercise 3.7
 * @author Martin Lyrå
 *
 */

public class WordCount2Main {

	/**
	 * Modified copy of <code>main()</code> from <code>IdentifyWordsMain.java</code>
	 * @param args
	 */
	public static void main(String[] args) {
		
		// code reused from the WarAndPeace exercise. For shame!
		String targetFile = "HistoryOfProgramming.txt";
		System.out.printf("Reading words from %s\n", targetFile);
		String text = readText(targetFile);
		String[] words = text.split(" ");
		
		// Filter the input first
		System.out.printf("Filtering input...\n");
		Stream<String> stream = Arrays.stream(words);
		
		stream = stream.map(s -> 
		s.replaceAll("[^A-Za-z-\']", "") // REGEX remove all non-alphabetic except dash and apostrophe
				);
				//.filter(s -> !s.isEmpty());
		// We are done filtering
		
		Object[] streamArray = stream.toArray();
		words = new String[streamArray.length];
		for (int i = 0; i < streamArray.length; i++)
			words[i] = (String) streamArray[i];
			
			
		
		WordSet hashSet = new HashWordSet();
		WordSet treeSet = new TreeWordSet();
		
		for (String wordString : words)
		{
			if (wordString.isEmpty())
				continue;
			
			Word word = new Word(wordString);
			
			hashSet.add(word);
			treeSet.add(word);
		}
		
		//System.out.println(hashSet);
		//System.out.println(treeSet);
		
		System.out.printf("\nWord count:\nHashSet: %d\nTreeSet: %d\n",
		hashSet.size(),
		treeSet.size());
		
		Word theWord = new Word("the");
		System.out.printf("\nContains the word '%s'?\nHashSet: %s\nTreeSet: %s\n",
				theWord,
				hashSet.contains(theWord),
				treeSet.contains(theWord));
		Word zWord = new Word("z");
		System.out.printf("\nContains the word '%s'?\nHashSet: %s\nTreeSet: %s\n",
				zWord,
				hashSet.contains(zWord),
				treeSet.contains(zWord));
		
		System.out.printf("\nAlphabetical order of words (iteration through TreeSet):\n");
		for (Word word : treeSet)
		{
			System.out.println(word);
		}
		
		System.out.printf("\nDone!\n");
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
	
}
