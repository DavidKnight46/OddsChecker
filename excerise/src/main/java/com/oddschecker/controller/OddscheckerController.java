package com.oddschecker.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class OddscheckerController {
	
	public StringBuilder assembledText = new StringBuilder();

	/**
	 * Check that file Path supplied is valid
	 * 
	 * @param filePath
	 * @return
	 */
	public void assemble(String filePath) throws IOException {

		try (BufferedReader reader = new BufferedReader(new FileReader(filePath)
				)) {
			String line;

			while ((line = reader.readLine()) != null) {
				assembledText.append(reassembleLine(line) + "\n");
			}
			
			System.out.println(assembledText);

		}

	}
	
	/**
	 * Reassemble each line of text
	 * 
	 * @param line
	 * @return
	 */
	private String reassembleLine(String line) {

        // Each line contains text fragments separated by a semicolon.
        String[] fragments = line.split(";");
        List<String> fragmentList = new ArrayList<String>(Arrays.asList(fragments));

        // Sort the fragments by size
        sortTheCollection(fragmentList);

        // Pick the first fragment
        String firstFragment = fragmentList.get(0);
        fragmentList.remove(0);

        for (int fragmentListCountr = fragmentList.size() - 1; fragmentListCountr >= 0; fragmentListCountr--) {
            int max = 0;
            int index = 0;
            int match = 0;
            int firstFragmentLength = 0;
            int currentFragmentLength = 0;
            
            //
            for (int fragmentListCounter = fragmentList.size() - 1; fragmentListCounter >= 0; fragmentListCounter--) {
                String currentFragment = fragmentList.get(fragmentListCounter);
                firstFragmentLength = firstFragment.length();
                currentFragmentLength = currentFragment.length();
                //
                for (int currentFragmentCounter = 1 - currentFragment.length(); currentFragmentCounter < firstFragment.length(); currentFragmentCounter++) {
                    if (currentFragmentCounter < 0) {
                        int fragmentLength = currentFragmentLength + currentFragmentCounter;
                        if (firstFragment.regionMatches(0, currentFragment, -currentFragmentCounter, fragmentLength)) {
                            if (fragmentLength > max) {
                                index = currentFragmentCounter;
                                max = fragmentLength;
                                match = fragmentListCounter;
                            }
                        }
                    } else {
                        int frgamentLength = currentFragmentCounter + currentFragmentLength <= firstFragmentLength ? currentFragmentLength : firstFragmentLength - currentFragmentCounter;
                        if (firstFragment.regionMatches(currentFragmentCounter, currentFragment, 0, frgamentLength)) {
                            if (frgamentLength > max) {
                                index = currentFragmentCounter;
                                max = frgamentLength;
                                match = fragmentListCounter;
                            }
                        }
                    }
                }
            }

            if (index < 0) {
                firstFragment = fragmentList.get(match).substring(0, -index) + firstFragment;
            } else if (index > firstFragmentLength - fragmentList.get(match).length()) {
                firstFragment = firstFragment + fragmentList.get(match).substring(firstFragmentLength - index);
            }
            fragmentList.remove(match);
        }
        
        return firstFragment;
        
    }

	/**
	 * Sort the list in order from longest to smallest
	 * 
	 * @param fragmentList
	 */
	private void sortTheCollection(List<String> fragmentList) {
		Collections.sort(fragmentList, new Comparator<String>() {

			@Override
			public int compare(String str1, String str2) {
				return str2.length() - str1.length();
			}
		});
	}

}
