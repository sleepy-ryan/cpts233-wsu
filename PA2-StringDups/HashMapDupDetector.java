import java.util.ArrayList;
import java.util.HashMap;

class HashMapDupDetector extends StringDupDetectorBase {

    public HashMapDupDetector() {
        // Constructor - should set your algorithms general metadata

        // Give your algorithm a name to identify it
        algorithm_name = "HashMap O(1) Hotrod!";

        // Describe the key feature(s) of your algorithm
        description = "Uses a whatawhat?";

        // Estimate your algorithm's complexity
        complexity = "I dunno!";
    }

    // The one function that does the duplicates detection for your algorithm.
    //  This function is called by the testing code
    //  It returns a list of duplicate strings found - NOTE: you CANNOT have duplicates in the results themselves!
    @Override
    public ArrayList<String> doDupDetection(ArrayList<String> strings) {
        // These two arrays are stored in the results when your algorithm is done
        // NOTE: The duplicates ArrayList *cannot* have duplicates within itself!
        ArrayList<String> duplicates = new ArrayList<>();   // Lists a set of strings that appear more than once

        //buffer layer that to help find the duplicate list
        HashMap<String, String> map = new HashMap<>();

        // PA CODE GOES HERE! -- MUST use the HashMap (map) for your solution, not Crandall's Brute Force searching!

        for(int i = 0; i<strings.size(); i++) {
            //To ensure that items in the map that have previously existed and the duplicate list do not include the same item
            //we add the new duplicated item to the duplicate list.
            if(map.containsKey(strings.get(i))&& !duplicates.contains(strings.get(i))) {
                duplicates.add(strings.get(i));
            }
            map.put(strings.get(i), null);

        }

        return duplicates;
    }
}