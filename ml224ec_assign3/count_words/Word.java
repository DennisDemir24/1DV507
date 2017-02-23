package ml224ec_assign3.count_words;

public class Word implements Comparable<Word> 
{
	private String word;
	
	public Word (String word)
	{
		this.word = word.toLowerCase();
	}
	
	public String toString() 
	{
		return word;
	}
	
	public int hashCode()
	{
		/*int hash = 16;
		
		for (char c : word.toCharArray())
			hash += c;*/
		
		//hash += super.hashCode(); // get Object's hashcode()
		//hash *= 5  + word.hashCode();
		//hash += 9  + (word.length() == 0 ? hash%16 : word.charAt(0)); // add modulus 16 of hash if the word contains nothing, else the first letter of the word
		
		int hash = word.hashCode();
		return hash;
	}
	
	public boolean equals(Object other)
	{
		if (other instanceof Word)
			return hashCode() == other.hashCode();
		return false;
	}
	
	@Override
	public int compareTo(Word other) {
		return word.compareTo(other.word); // words are ALWAYS lowercase
	}
}
