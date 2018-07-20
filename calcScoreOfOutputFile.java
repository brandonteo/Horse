import java.io.*;
import java.lang.Integer;
import java.util.*;


//This program will calculate the total score of the .out file with respect to the 600 .in files
public class calcScoreOfOutputFile {
    public static void main(String[] args) throws IOException {
    	//This block initializes a reader to read the .out file
        int[] sizes = {500,500,500,38,499,248,500,500,500,50,500,500,4,4,4,170,222,499,41,200,500,100,10,5,8,8,10,500,500,500,500,500,499,100,250,500,500,500,388,5,6,7,448,428,16,500,500,500,5,11,300,6,25,6,12,16,18,50,100,500,4,100,500,500,500,500,500,500,500,500,500,127,10,10,10,500,500,500,10,500,250,500,500,500,500,500,12,377,217,435,500,500,500,500,500,500,500,500,500,4,5,5,500,500,16,500,500,17,250,482,123,8,16,30,100,100,100,4,100,500,500,500,500,35,150,450,500,500,500,500,500,500,500,500,500,4,4,6,500,200,300,20,500,500,26,337,90,15,127,500,500,500,500,80,42,28,500,500,500,210,452,56,20,36,10,475,500,500,328,482,340,60,88,158,500,500,500,497,443,254,500,500,500,31,340,422,33,51,469,457,489,500,15,15,8,500,500,500,500,500,500,500,500,500,500,500,500,3,5,7,13,15,500,4,100,500,500,500,500,500,500,500,500,500,500,6,9,484,500,500,500,4,100,500,100,150,200,21,21,500,200,200,200,500,100,300,497,463,336,101,500,143,68,19,15,14,8,20,5,100,500,6,500,500,500,500,500,387,433,463,500,500,500,5,4,7,100,497,500,4,100,500,7,9,50,500,500,500,6,6,10,100,250,500,100,100,500,500,500,500,10,150,499,8,8,9,500,500,500,8,500,500,256,256,256,100,60,100,5,100,500,500,500,500,20,375,500,12,6,7,484,484,500,500,500,500,500,500,500,81,41,85,500,500,500,10,500,500,56,378,434,500,500,500,500,500,500,69,154,99,40,500,50,17,100,500,500,500,500,7,5,500,100,250,500,100,250,500,500,500,500,100,50,75,7,6,15,10,5,5,147,499,40,500,500,500,500,500,500,100,100,500,8,11,15,500,500,500,485,208,280,10,10,4,500,500,498,500,500,500,10,27,6,268,66,305,500,500,500,500,500,500,500,500,500,25,500,500,10,31,20,500,500,500,15,135,200,500,500,500,15,12,500,20,100,500,214,355,148,9,499,499,500,500,500,300,400,500,5,100,500,500,500,500,10,100,500,500,500,500,100,300,200,420,420,420,500,500,500,250,100,450,10,50,200,500,500,500,17,16,500,6,89,15,10,10,15,500,499,498,10,100,50,100,497,497,20,10,500,500,500,500,500,500,500,500,500,500,500,500,500,49,44,431,500,500,500,500,500,500,500,500,500,6,5,7,5,10,100,500,500,500,500,500,500,500,500,500,200,100,500,11,12,14,10,22,100,500,500,500,500,256,10,500,500,500,100,500,80,500,500,500,500,500,500,250,375,500,500,500,500,100,400,25,500,500,500,4,3,4};
        int n = sizes.length;
        BufferedReader r = new BufferedReader(new FileReader(args[0] + ".out"));


        //This block initializes a reader for the .in files with their respective number of horses
        //Gets the folder name
        String dir1 = "";
        dir1 = args[1];
        //Creates an array with 1 - 600 in it
        int[] arr = new int[600];
        for (int i = 1; i < 601; i++) {
            arr[i-1] = i;
        }

        //This variable keeps track of the total score to return
        int totalScore = 0;


        //Loop through each .in file and match it with the correct segment in the .out file
        for(int i = 0; i < n; i++) {

        	//This block readies the reader to read in the current .in file
        	String fn = dir1 + "/" + arr[i] + ".in";
        	BufferedReader rdr = new BufferedReader(new FileReader(fn));
            String sos = rdr.readLine();
            int numHorses = Integer.parseInt(sos);

            //We can fill everything in the .in file into matrix
            int matrix[][] = new int[numHorses][numHorses];
            for(int j = 0; j < numHorses; j++) {
                sos = rdr.readLine();
                String[] stwo = sos.split(" ");
                for(int k = 0; k < numHorses; k++){
                    matrix[j][k] = Integer.parseInt(stwo[k]);
                }
            }

            //Create a performance index catalog
            int pindex[] = new int[numHorses];
            for(int p = 0; p < numHorses; p++) {
            	pindex[p] = matrix[p][p];
            }


            //This block reads the corresponding line in the .out file and counts the score
            String s = r.readLine();
            String[] s2 = s.split("; "); //divides into paths
            for(int j = 0; j < s2.length; j++) { //loops through each path
            	int pathScore = 0;
            	int pathCount = 0;
                String[] s3 = s2[j].split(" "); //divide into nodes
                for(int k = 0; k < s3.length; k++) { //for each horse/node
                    int x = Integer.parseInt(s3[k]); //x is the horseID
                    pathCount++;
                    pathScore = pathScore + pindex[x];
                }
                totalScore = totalScore + pathScore;
            }
            //System.out.println((i+1) + "->score till now: " + totalScore); //Uncomment to debug
            
        }
        //System.out.println("the output file is: " + args[0]); //Uncomment to debug
        System.out.println("The total score for this output file is: " + totalScore);
    
    }

}