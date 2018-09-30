/*
 * Copyright 2017 Marc Liberatore.
 */

package sequencer;

import java.util.List;
import java.util.ArrayList;

public class Assembler {
	public List<Fragment> Fragments = new ArrayList<Fragment>();
	/**
	 * Creates a new Assembler containing a list of fragments.
	 * 
	 * The list is copied into this assembler so that the original list
	 * will not be modified by the actions of this assembler.
	 * 
	 * @param fragments
	 */
	public Assembler(List<Fragment> fragments) {
		for(int i = 0; i < fragments.size(); i++){
			Fragments.add(fragments.get(i));
		}
	}
	
	/**
	 * Returns the current list of fragments this assembler contains.
	 * @return the current list of fragments
	 */
	public List<Fragment> getFragments() {
		return Fragments;
	}
	
	/**
	 * Attempts to perform a single assembly, returning true if an assembly was performed.
	 * 
	 * This method chooses the best assembly possible, that is, 
	 * it merges the two fragments with the largest overlap, breaking ties
	 * between merged fragments by choosing the shorter merged fragment.
	 * 
	 * Merges must have an overlap of at least 1.
	 * 
	 * After merging two fragments into a new fragment, the new fragment is inserted into
	 * the list of fragments in this assembler, and the two original fragments are removed
	 * from the list.
	 * 
	 * @return true iff an assembly was performed
	 */
	public boolean assembleOnce() {	
		int overlapForMerge = 0;
		int biggestOverlap = -1;
		
		Fragment fragmentFront = null;
		Fragment fragmentEnd = null; 
		Fragment merged = null;
		
		for(int i = 0; i < Fragments.size(); i ++){
				for(int j = 0; j < Fragments.size(); j ++){
					if(Fragments.get(i) == null || Fragments.get(j) == null){
						return false;
					}
					if(Fragments.get(i) != Fragments.get(j)){
						overlapForMerge = Fragments.get(i).calculateOverlap(Fragments.get(j));
						if(overlapForMerge > biggestOverlap){
							biggestOverlap = overlapForMerge;
							fragmentFront = Fragments.get(i);
							fragmentEnd = Fragments.get(j);
						}
					}
				}
		}
		
		if(biggestOverlap > 0){
			Fragments.remove(fragmentFront);
			Fragments.remove(fragmentEnd);
			merged = fragmentFront.mergedWith(fragmentEnd);
			Fragments.add(merged);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Repeatedly assembles fragments until no more assembly can occur.
	 */
	public void assembleAll() {
		while(assembleOnce() != false){
			assembleOnce();
		}
	}
}