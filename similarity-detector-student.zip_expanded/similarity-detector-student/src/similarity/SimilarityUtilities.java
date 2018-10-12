/*
 * Copyright 2017 Marc Liberatore.
 */

package similarity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import sets.SetUtilities;

public class SimilarityUtilities {
	/**
	 * Returns the set of non-empty lines contained in a text, trimmed of
	 * leading and trailing whitespace.
	 * 
	 * @param text
	 * @return the trimmed set of lines
	 */
	public static Set<String> trimmedLines(String text) {
		Set<String> trimmed = new HashSet<String>();
		
		if(text.length() == 0){
			return trimmed;
		}
		
		List<String> cut = new ArrayList<String>();
		cut.addAll(Arrays.asList(text.split("\\n")));
		
		for(int i = 0; i < cut.size(); i++){
			String slimmed = cut.get(i).trim();
			cut.set(i, slimmed);
		}
		
		for(int i = 0; i < cut.size(); i++){
			if(cut.get(i).length() == 0){
				cut.remove(i);
			}
		}
		
		trimmed.addAll(cut);
		return trimmed;
	}


	/**
	 * Returns a list of words in the text, in the order they appeared in the text, 
	 * converted to lowercase.
	 * 
	 * Words are defined as a contiguous sequence of letters and numbers.
	 *
	 * @param text
	 * @return a list of lowercase words
	 */
	public static List<String> asLowercaseWords(String text) {
		List<String> lowerCased = new ArrayList<String>();
		
		if(text.length() == 0) {
			return lowerCased;
		}
		
		lowerCased.addAll(Arrays.asList(text.split("\\W+")));
		
		for(int i = 0; i < lowerCased.size(); i++) {
			String lowerCase = lowerCased.get(i).toLowerCase();
			lowerCased.set(i, lowerCase);
		}
		
		for(int i = 0; i < lowerCased.size(); i++){
			if(lowerCased.get(i).length() == 0){
				lowerCased.remove(i);
			}
		}
		
		return lowerCased;
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2) {
		Set<String> one = new HashSet<String>();
		Set<String> two = new HashSet<String>();
		one = trimmedLines(text1);
		two = trimmedLines(text2);
		
		return SetUtilities.jaccardIndex(one, two);
	}

	/**
	 * Returns the line-based similarity of two texts.
	 * 
	 * The line-based similarity is the Jaccard index between each text's line
	 * set.
	 * 
	 * A text's line set is the set of trimmed lines in that text, as defined by
	 * trimmedLines, less the set of trimmed lines from the templateText. Removes
	 * the template text from consideration after trimming lines, not before.
	 * 
	 * @param text1
	 *            a text
	 * @param text2
	 *            another text
	 * @param templateText
	 *            a template, representing things the two texts have in common
	 * @return
	 */
	public static double lineSimilarity(String text1, String text2, String templateText) {
		Set<String> one = new HashSet<String>();
		Set<String> two = new HashSet<String>();
		Set<String> oneRemoved = new HashSet<String>();
		Set<String> twoRemoved = new HashSet<String>();
		Set<String> template = new HashSet<String>();
		
		one = trimmedLines(text1);
		two = trimmedLines(text2);
		template = trimmedLines(templateText);
		
		oneRemoved = SetUtilities.setDifference(one, template);
		twoRemoved = SetUtilities.setDifference(two, template);
		
		return SetUtilities.jaccardIndex(oneRemoved, twoRemoved);
	}

	/**
	 * Returns a set of strings representing the shingling of the given length
	 * of a list of words.
	 * 
	 * A shingling of length k of a list of words is the set of all k-shingles
	 * of that list.
	 * 
	 * A k-shingle is the concatenation of k adjacent words.
	 * 
	 * For example, a 3-shingle of the list: ["a" "very" "fine" "young" "man"
	 * "I" "know"] is the set: {"averyfine" "veryfineyoung" "fineyoungman"
	 * "youngmanI" "manIknow"}.
	 * 
	 * @param words
	 * @param shingleLength
	 * @return 
	 */
	public static Set<String> shingle(List<String> words, int shingleLength) {
		String shing = "";
		List<String> trimmedWords = new ArrayList<String>();
		Set<String> shingles = new HashSet<String>();
		
		for(int i = 0; i < words.size(); i++){
			trimmedWords.add(words.get(i).trim()); 	
		}
		
		for(int i = 0; i <= words.size() - shingleLength; i++){
			for(int j = 0; j < shingleLength; j++){
				shing += trimmedWords.get(i+j);
			}
			shingles.add(shing);
			shing = "";
		}
		return shingles;
	}

	/**
	 * Returns the shingled word similarity of two texts.
	 * 
	 * The shingled word similarity is the Jaccard index between each text's
	 * shingle set.
	 * 
	 * A text's shingle set is the set of shingles (of the given length) for the
	 * entire text, as defined by shingle and asLowercaseWords, 
	 * less the shingle set of the templateText. Removes the templateText 
	 * from consideration after shingling, not before.
	 * 
	 * @param text1
	 * @param text2
	 * @param templateText
	 * @param shingleLength
	 * @return
	 */
	public static double shingleSimilarity(String text1, String text2, String templateText, int shingleLength) {
		Set<String> one = new HashSet<String>();
		Set<String> two = new HashSet<String>();
		Set<String> template = new HashSet<String>();
		
		Set<String> oneRemoved = new HashSet<String>();
		Set<String> twoRemoved = new HashSet<String>();
		
		one = shingle(asLowercaseWords(text1), shingleLength);
		two = shingle(asLowercaseWords(text2), shingleLength);
		template = shingle(asLowercaseWords(templateText), shingleLength);
		
		oneRemoved = SetUtilities.setDifference(one, template);
		twoRemoved = SetUtilities.setDifference(two, template);
		
		return SetUtilities.jaccardIndex(oneRemoved, twoRemoved);
	}
}
