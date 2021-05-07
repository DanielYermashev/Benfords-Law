/**
 * 
 */

import java.util.Scanner; // import scanner class
import java.io.BufferedReader; // import buffer reader class (because scanner likes to skip lines)
import java.io.FileReader; // import class to read files
import java.io.*;

// import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileReader;
import java.io.IOException;

// import java.util.List;
// import java.util.Vector;

 class BenfordsLawT {
     public static void main(String[] args) throws IOException{

        // int[] lead = new int[1621];

        String filePath = "/Users/daiphylee/11 ComSci Java Lessons/A. Assignments/Benford's Law/Benfords-Law/sales.csv";

        FileReader fr = new FileReader(filePath);
        // initialize bufferedreader 
        BufferedReader br = new BufferedReader(fr);

        int[] tally = new int[]{0,0,0,0,0,0,0,0,0,0};
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

        for(int index = 0; index < 10; index++) {
            double average = tally[index] == 0 ? 0.0 : (((double)tally[index]) / count) * 100;
            System.out.println("[" + index + "][" + tally[index] + "][" + count + "][" + Math.round(average * 100.0) / 100.0 + "]");
        }  



        // try{
        //     br = new BufferedReader(fr);
        //     String line;
        //     while((line = br.readLine()) != null ){
        //         char ld = line.charAt(4);
        //         int leadingDigit = Character.getNumericValue(ld);
        //         // System.out.println(leadingDigit);
        //         for (int i = 0; i < 1621; i++){
        //             lead[i] = leadingDigit;
        //             System.out.println(lead[i]);
        //         }
        //     }
    
        // }
        // catch (IOException e) {
        //     e.printStackTrace();
        // } 
        // finally {                
        //     try {
        //     // if the line reads as blank
        //     if (br != null)br.close(); // reader closes            
        //     }
        //     catch (IOException ex) {
        //         ex.printStackTrace();
        //     }
        // }

     }
    //  public static int leadingDigit(int x){
    //      while(x >= 10){
    //          x = x/10;
    //      }
    //      return x;
    //  }
    //  public static void BenfordsLaw(int[] count, int ld){
    //      int n = 0;
        
    //      while (!StdIn.isEmpty()){
    //          int x = StdIn.readInt();
    //          int digit = ld;
    //          count[digit]++;
    //          n++;
    //      }
    //      for(int i = 1; i < 10; i++){
    //          StdOut.printf("%d: %6.lf%%\n", i, 100.0 * count[i] / n);
    //      }
    //  }

    //  public static void fileReader(String filePath){
         
    //     BufferedReader br = null;
    //     try {
    //         String sCurrentLine;
    //         br = new BufferedReader(new FileReader(filePath));//file name with path
    //         // while there is content on the line
    //         while ((sCurrentLine = br.readLine()) != null) {

    //             System.out.println(sCurrentLine);
    //             // String[] strArr = sCurrentLine.split("\\,");
    //             // for(String str:strArr){
    //             //         System.out.println(str);
    //             //         }
    //             // }

    //         } 
    //         catch (IOException e) {
    //             e.printStackTrace();
    //         } finally {
    //             try {
    //                 // if the line reads as blank
    //                 if (br != null)br.close(); // reader closes
    //             } catch (IOException ex) {
    //                 ex.printStackTrace();
    //             }
    //     }

    //  }
 }