package ml224ec_assign1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PrintJavaMain {

	private static int depth = 0;
	
	public static void main(String[] args) {
		
		Path targetDirectory = Paths.get(args[0]);
		
		printAllJavaFiles(targetDirectory.toFile());
		
	}
	
	private static void printAllJavaFiles(File directory)
	{
		if (!directory.exists())
		{
			System.out.printf("Fatal error: Selected directory (%s) couldn't be found!\n",
					directory.toString());
			return;
		}
		
		String space = "";
		for (int i = 0; i < depth; i++)
			space += " ";
		
		System.out.println(space + directory.getName());
		depth++;
		
		for (File f : directory.listFiles())
		{
			try {
				if (f.isDirectory())
					printAllJavaFiles(f);
				else if (f.getName().endsWith(".java"))
					printJavaFileDetails(f);
			}
			catch (Exception e)
			{
				System.out.printf("Error reading %s in %s; %s\n", 
						f.getName(),
						directory.getName(),
						e.toString());
			}
		}
		
		depth--;
	}
	
	private static void printJavaFileDetails(File file) throws IOException
	{
		//int size = Files.readAllBytes(file.toPath()).length;
		int lines = Files.readAllLines(file.toPath()).size();
		
		String space = "";
		for (int i = 0; i < depth; i++)
			space += " ";
		
		System.out.printf("%s%s (%d lines)\n",
				space,
				file.getName(),
				lines);
		/*
		System.out.printf("%s%s (%d lines) (%d bytes)\n",
				space,
				file.getName(),
				lines,
				size);
				*/
	}
	
}
