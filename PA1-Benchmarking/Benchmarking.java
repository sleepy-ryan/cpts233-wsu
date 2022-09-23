import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Benchmarking {

    public static void main(String args[]) {

        try {

            //I could only figure out how to read and sort one file instead of two
            File file = new File("input1.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            ArrayList<Integer> time_insert = new ArrayList<Integer>();

            String str;

            //Goes through input1.txt and grabs each integer
            while((str = br.readLine()) != null)
                time_insert.add(Integer.parseInt(str));

            //Sort elements in list in ascending order
            Collections.sort(time_insert);
            //Grabs element from index 0 which will be the lowest number
            Integer time_min = time_insert.get(0);
            //Grabs element from the last index which will be the highest number
            Integer time_max = time_insert.get(time_insert.size() - 1);
            //Navigates to middle of list to find median
            Integer time_med = 0;
            if (time_insert.size() % 2 == 1)
                time_med = time_insert.get((time_insert.size() - 1) / 2);
            else
                time_med = (time_insert.get((time_insert.size() - 1) / 2) + time_insert.get((time_insert.size()) / 2)) / 2;

            //LinkedList to be populated with sorted values from arrayList
            LinkedList<Integer> linkedList = new LinkedList<>();
            for (int i = 0; i < time_insert.size(); i++) {
                linkedList.add(time_insert.get(i));
            }

            System.out.println(linkedList);

            System.out.println("Minimum: " + time_min);
            System.out.println("Maximum: " + time_max);
            System.out.println("Median: " + time_med);

            br.close();

        }

        //Exception output should filereading not execute
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}

