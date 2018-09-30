/*
 * Copyright 2017 Marc Liberatore.
 */

package sequencer;

public class Fragment {
	private String nucleotides;
	/**
	 * Creates a new Fragment based upon a String representing a sequence of nucleotides, 
	 * containing only the uppercase characters G, C, A and T.
	 * @param nucleotides
	 * @throws IllegalArgumentException if invalid characters are in the sequence of nucleotides 
	 */
	public Fragment(String nucleotides) throws IllegalArgumentException {
		boolean valid;
		
		for(int i = 0; i < nucleotides.length(); i++){			
			if(nucleotides.charAt(i) == 'A'|| 
			   nucleotides.charAt(i) == 'G'||
			   nucleotides.charAt(i) == 'C'||
			   nucleotides.charAt(i) == 'T'){
				valid = true;
			}
			else{
				valid = false;
			}		
			if(valid == false){
				throw new IllegalArgumentException("Dosent work");
			}	
		}
		
		this.nucleotides = nucleotides;
	}
	
	/**
	 * Returns the length of this fragment.
	 * 
	 * @return the length of this fragment
	 */
	public int length() {
		return nucleotides.length();
	}
	
	/**
	 * Returns a String representation of this fragment, exactly as was passed to the constructor.
	 * 
	 * @return a String representation of this fragment
	 */
	@Override
	public String toString() {
		return nucleotides;
	}
	
	/**
	 * Return true if and only if this fragment contains the same sequence of nucleotides
	 * as another sequence.
	 */
	@Override
	public boolean equals(Object o) {
	    if (!(o instanceof Fragment)) {
	        return false;
	    }

	    Fragment f = (Fragment)o;
	    return this.nucleotides.equals(f.nucleotides);
	}
	
	/**
	 * Returns the number of nucleotides of overlap between the end of this fragment and
	 * the start of another fragment, f.
	 * 
	 * The largest overlap is found, for example, CAA and AAG have an overlap of 2, not 1.
	 * 
	 * @param f the other fragment
	 * @return the number of nucleotides of overlap
	 */
	public int calculateOverlap(Fragment f) {
		int overlap = 0;
		
		if(f.toString().equals(nucleotides)) {
			return f.length();
		}
		
	    for(int i = 0; i < f.length(); i++){
	    	if(this.nucleotides.endsWith(f.toString().substring(0, i))){
	    		overlap = i;
	    	}
	    }
	    
	    return overlap;
	}
	
	/**
	 * Returns a new fragment based upon merging this fragment with another fragment f.
	 * 
	 * This fragment will be on the left, the other fragment will be on the right; the
	 * fragments will be overlapped as much as possible during the merge.
	 *  
	 * @param f the other fragment
	 * @return a new fragment based upon merging this fragment with another fragment 
	 */
	public Fragment mergedWith(Fragment f) {
		if(f.toString().equals(nucleotides)) {
			return f;
		}
		else {
			return new Fragment(this.nucleotides + f.toString().substring(calculateOverlap(f)));
		}
	}
}
