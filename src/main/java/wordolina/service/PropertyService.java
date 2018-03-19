package wordolina.service;

import java.io.*;

public class PropertyService {

    public static String getScore(){
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("src/main/webapp/WEB-INF/property/score.properties")));
            String score = bufferedReader.readLine();
            score = score.substring(11, 14);
            return score;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addPositiveNumber(){
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("src/main/webapp/WEB-INF/property/score.properties")));
            String score = bufferedReader.readLine();
            score = score.substring(11, 14);
            String[] positive_negative = score.split(":");
            int pos = Integer.parseInt(positive_negative[0]);
            pos++;
            String res = "test.score=" + pos + ":" + positive_negative[1];
            clearFile();
            FileWriter outputStream = new FileWriter("src/main/webapp/WEB-INF/property/score.properties");
            outputStream.write(res);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addNegativeNumber(){
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(new FileInputStream("src/main/webapp/WEB-INF/property/score.properties")));
            String score = bufferedReader.readLine();
            score = score.substring(11, 14);
            String[] positive_negative = score.split(":");
            int neg = Integer.parseInt(positive_negative[1]);
            neg++;
            String res = "test.score=" + positive_negative[0] + ":" + neg;
            clearFile();
            FileWriter outputStream = new FileWriter("src/main/webapp/WEB-INF/property/score.properties");
            outputStream.write(res);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nullifyScore(){
        try {
            String res = "test.score=0:0";
            clearFile();
            FileWriter outputStream = new FileWriter("src/main/webapp/WEB-INF/property/score.properties");
            outputStream.write(res);
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearFile(){
        try {
            FileWriter outputStream = new FileWriter("src/main/webapp/WEB-INF/property/score.properties");
            outputStream.write("");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
