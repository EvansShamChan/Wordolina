package wordolina.service;

import wordolina.dao.JdbcDao;

import java.sql.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class DatabaseService {

    private static Connection connection;
    private static Statement statement;

    private static String GET_WORDS = "select * from words;";

    public static int getValidId(){
        return ThreadLocalRandom.current().nextInt(21, getDatabaseSize());
    }

    public static int getDatabaseSize(){
        int number = 0;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/wordolina", "root", "root");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_WORDS);
            while(resultSet.next()){
                number++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return number;
    }

    public static List<String> getRandomTranslates(String trueTranslate){
        List<String> translates = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            translates.add(new JdbcDao().getTranslate());
        }
        translates.add(trueTranslate);
        translates = mixStringValues(translates);
        return translates;
    }

    public static List<String> mixStringValues(List<String> baseList){
        for(int i = 0; i < 100; i++){
            int id = new Random().nextInt(baseList.size());
            int newId = new Random().nextInt(baseList.size());
            String value = baseList.get(id);
            baseList.remove(id);
            baseList.add(newId, value);
        }
        return baseList;
    }

    public static List<String> removeDublicates(List<String> dublicatedList){
        List<String> resultList = new ArrayList<>();
        Set<String> stringSet = new HashSet<>();
        List<String> validList = addSpaceWhereItNeeds(dublicatedList);
        stringSet.addAll(dublicatedList);
        while (true){
            if(stringSet.size() == 4){
                resultList.addAll(stringSet);
                return resultList;
            } else {
                int neededTranslates = 4 - stringSet.size();
                for (; neededTranslates != 0; neededTranslates--){
                    stringSet.add(new JdbcDao().getTranslate());
                }
            }
        }
    }

    public static List<String> addSpaceWhereItNeeds(List<String> list){
        String removeElement = null;
        for (String s :list) {
            if((String.valueOf(s.charAt(0))).equals(" ")){
                continue;
            } else {
                removeElement = s;
            }
        }
        if(removeElement == null){
            return list;
        } else {
            list.remove(removeElement);
            list.add(" " + removeElement);
        }
        return list;
    }
}
