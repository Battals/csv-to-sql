package Projekt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    private File csvFile; //csv - contains data
    private File sqlFile1; //ddl - contains columns
    private File sqlFile2; //dml - contains csv data
    private String tableName; //name of the table
    private String columns; //the columns
    private ArrayList<String> csvData; //contains data from the csv, line to line

    public FileHandler(String tableName, String csvFile, String sql1File, String sql2File){
        this.tableName = tableName;
        this.csvFile = new File("data/" + csvFile + ".csv");
        this.sqlFile1 = new File("data/" + sql1File + ".sql");
        this.sqlFile2 = new File("data/" + sql2File + ".sql");
        readCsv();
    }

    //FileReader
    public void readCsv(){
        ArrayList<String> csvData = new ArrayList<>();
        try {
            Scanner sc = new Scanner(csvFile);
            columns = sc.nextLine();
            while (sc.hasNextLine()) {
                csvData.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.csvData = csvData;
    }

    //FileWriter
    public void writeSQL(){
        try {
            FileWriter fw1 = new FileWriter(sqlFile1);
            FileWriter fw2 = new FileWriter(sqlFile2);
            fw1.write(writeSQL1());
            fw2.write(writeSQL2());
            fw1.close();
            fw2.close();
            System.out.println("written to sql 1");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Services
    public String writeSQL1(){
        String[] data = columns.split(";");
        String tables = "CREATE TABLE " + tableName + " (";
        for (int i = 0; i < data.length; i++) {
            tables += "\n" + data[i] + " varchar(255)";
        }
        tables += "\n)";
        return tables;
    } //Writes which columns is in the table
    public String writeSQL2(){
        String data = "";
        for (int i = 0; i < csvData.size(); i++) {
            ArrayList<String> tempMovie = stringToMovie(csvData.get(i)).getData();
            //Insert and Values
            String line1 = "INSERT INTO " + tableName + " (";
            String line2 = "VALUES (";
            //Give lines data

            for (int j = 0; j < tempMovie.size(); j++) {
                //column - data
                String[] tempLine = tempMovie.get(j).split(";");
                line1 += tempLine[0];
                if(tempLine[1].contains("'")){
                    String[] temp = tempLine[1].split("'");
                    line2 += "'";
                    for (int l = 0; l < temp.length; l++) {
                        line2 += temp[l];
                    }
                    line2 += "'";
                } else {
                    line2 += "'" + tempLine[1] + "'";
                }

                if(j < tempMovie.size()-1){
                    line1 += ", ";
                    line2 += ", ";
                } else {
                    line1 += ")";
                    line2 += ")";
                }
            }
            data += line1 + "\n" + line2 + "\n\n";
        }
        System.out.println("returning data: " + data);
        return data;
    } //Writes the csv data into the tables

    //Everything above this should be able to adjust to any type of csv data
    //Services(Services matching the data)
    public Movie stringToMovie(String movie){
        String[] result = movie.split(";");
        return new Movie(result[0], result[1], result[2], result[3], result[4], result[5]);
    }

    //main
    public static void main(String[] args) {
        String tableName = "imdb"; //Name of the table
        String csvFileName = "imdb-data"; //csv filename
        String sql1FileName = "ddl"; //sql columns file
        String sql2FileName = "dml"; //sql data file
        FileHandler fh = new FileHandler(tableName, csvFileName, sql1FileName, sql2FileName);
        fh.writeSQL();

        /*Instructions:
        */

    }

}
