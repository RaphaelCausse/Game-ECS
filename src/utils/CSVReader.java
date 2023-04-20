package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CSVReader
{
	/*----------------------------------------*/

	/**
	 * Lecture d'un fichier 'cvs' pour en extraire les donnees sour forme de matrix de dimension deux.
	 * @return data Matrice dimension deux d'entier
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
    }
    
    /*----------------------------------------*/
}