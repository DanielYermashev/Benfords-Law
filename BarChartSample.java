/**
 * 
 */

import java.util.Scanner; // import scanner class
import java.io.BufferedReader; // import buffer reader class (because scanner likes to skip lines)
import java.io.FileReader; // import class to read files
import java.io.*;
import java.io.IOException;

 class BenfordsLaw {
    public static void main(String[] args) throws IOException{

        // int[] lead = new int[1621];

        String filePath = "/Users/daiphylee/11 ComSci Java Lessons/A. Assignments/Benford's Law/Benfords-Law/sales.csv";


        for (int i = 0; i < 10; i ++){
            System.out.println(readFile(filePath, i));
        }

    }
    public static double[] readFile(String filePath, int i) throws IOException{
        FileReader fr = new FileReader(filePath);
        // initialize bufferedreader 
        BufferedReader br = new BufferedReader(fr);

        int[] tally = {0,0,0,0,0,0,0,0,0,0};
        int count = 0;

        for (String s = br.readLine(); s != null; s = br.readLine()){
            String ld = Character.toString(s.charAt(4));
            char[] digits = ld.toCharArray();
            for(char digit : digits){
                if(Character.isDigit(digit)){
                    count++;
                    tally[Integer.parseInt(Character.toString(digit))]++;
                    break;
                }
            }
        }
        br.close();

        double[] avg = {0,0,0,0,0,0,0,0,0,0};

        avg[i] = tally[i] == 0?0.0: ( ((double)tally[i]) / count ) * 100;
        // double average = tally[index] == 0 ? 0.0 : (((double)tally[index]) / count) * 100;
        // System.out.println("[" + index + "][" + tally[index] + "][" + count + "][" + Math.round(avg[index] * 100.0) / 100.0 + "]");
        avg[i] = Math.round(avg[i] * 100.0) / 100.0;
        
        return avg;

    }
}
