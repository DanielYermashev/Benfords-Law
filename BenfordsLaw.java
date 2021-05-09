/**
 * 
 */

 //import classes
import java.util.Scanner;
import java.io.*;
import java.io.File;
import java.io.IOException;

 class BenfordsLaw {
     public static void main(String[] args) throws IOException{
          // initialize Scanner package
          Scanner reader = new Scanner(System.in);
          // initialize the array
          double[] percentArr = new double[10];
 
          String filePath;
          boolean existingFile = false;

        do{
            // prompt user to enter file path
            System.out.println("Enter the file path. (To find this open the file on VS code, Right click on the tab and click 'Copy Path')");
            System.out.println("Your path should look like : /Users/daiphylee/11 ComSci Java Lessons/A. Assignments/Benford's Law/Benfords-Law/sales.csv ");
            filePath = reader.nextLine();

            System.out.println();
            
            // read file method
            existingFile = readFile(filePath, percentArr);
        }while(existingFile == false);


        reader.close();
 
      }
      public static boolean readFile(String path, double[] percent) throws IOException{
 
          // initialize filereader and bufferedreader package
          FileReader fr = new FileReader(path);
          BufferedReader br = new BufferedReader(fr);
 
          try{ 
 
              // initialize the array and integer
              int[] tally = new int[10];  // array that counts the amount of each num in the file
              int count = 0;  // integer that counts amount of lines in the file
              String s;

              // for loop to read each line in the file until it reaches a blank line
              while ((s = br.readLine()) != null){
                  // ld is the leading digit of the number 
                  String ld = Character.toString(s.charAt(4));
                  // convert the leading digit from a string to a char arrat
                  char[] digits = ld.toCharArray();
                  // for each loop to go through all the leading digits and change only the integers into characters
                  for(char digit : digits){
                      if(Character.isDigit(digit)){
                          count++;    // counts the amount of lines
                          // counts and tallys the amount of each num
                          tally[Integer.parseInt(Character.toString(digit))]++;
                          // breaks the for loop when all of the int are converted into characters
                          break;
                      }
                  }
              }
              // calls on method to find the percent
              percentageArr(percent, tally, count);

              br.close();
 
          }
          // Program cannot find file
          catch (IOException e){ 
              System.out.println("An Error has occurred. File not Found.");
              return false;
          }
          return true;
 
      }
    public static void percentageArr(double[] percent, int[] tally, int count){
        // goes through each digits frequency
        for(int i = 1; i < 10; i++) {
            // finds the percentage of each leading digit number 
            percent[i] = tally[i] == 0?0.0: ( ((double)tally[i]) / count ) * 100;
            //rounds the percent to the hundredth decimal place
            percent[i] = Math.round(percent[i] * 100.0) / 100.0;
            System.out.println("[" + i + "][" + tally[i] + "][" + count + "][" + Math.round(percent[i] * 100.0) / 100.0 + "]");
            System.out.println(percent[i]);
        }  
        // let user know if there's fraud && calls on fraud method
        System.out.println("Is there Fraud present? " + fraudValidation(percent));
    }
    public static String fraudValidation(double[] arr){
        // if the frequency percentage of 1 isn't in range then return yes -> yes fraud
        if (arr[0] >= 32.0 && arr[0] <= 29.0){
            return "Yes";
        }
        // else return no -> no fraud
        else{
            return "No";
        }
    }
 }
