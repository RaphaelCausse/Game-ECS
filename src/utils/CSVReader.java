package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CSVReader
{
	/*----------------------------------------*/

	/**
	 * Lecture d'un fichier '.csv' pour en extraire les donnees sour forme de matrice.
	 * @return filename
	 */
    public static int[][] readCSV(String filename)
    {
        try
        {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            int numRows = 0;
            int numCols = 0;

            // Determine the number of rows and columns in the CSV file
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                numRows++;
                String[] values = line.split(",");
                numCols = values.length;
            }
            scanner.close();

            // Initialize the 2D int array to store the CSV data
            int[][] data = new int[numRows][numCols];

            // Read the CSV data into the 2D array
            scanner = new Scanner(file);
            int row = 0;
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                for (int col = 0; col < numCols; col++) {
                    data[row][col] = Integer.parseInt(values[col]);
                }
                row++;
            }
            scanner.close();
            return data;
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Le fichier specifie n'a pas ete trouve : " + e.getMessage());
            return null;
        }
//    	try {
//            // Obtain the absolute path of the "res" folder
//            File file = new File(CSVReader.class.getResource(filename).toExternalForm());
//            System.out.println(file.getAbsolutePath());
//            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//
//            int numRows = 0;
//            int numCols = 0;
//
//            // Determine the number of rows and columns in the CSV file
//            String line;
//            while ((line = reader.readLine()) != null) {
//                numRows++;
//                String[] values = line.split(",");
//                numCols = values.length;
//            }
//            reader.close();
//
//            // Initialize the 2D int array to store the CSV data
//            int[][] data = new int[numRows][numCols];
//
//            // Read the CSV data into the 2D array
//            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//            int row = 0;
//            while ((line = reader.readLine()) != null) {
//                String[] values = line.split(",");
//                for (int col = 0; col < numCols; col++) {
//                    data[row][col] = Integer.parseInt(values[col]);
//                }
//                row++;
//            }
//            reader.close();
//            return data;
//        } catch (IOException e) {
//            System.err.println("Une erreur s'est produite lors de la lecture du fichier : " + e.getMessage());
//            return null;
//        }
    }
    
    /*----------------------------------------*/
}