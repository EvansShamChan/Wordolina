package wordolina.dao;

import wordolina.service.DatabaseService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcDao {

    private Connection connection;
    private PreparedStatement preparedStatement;

    private final String GET_WORD = "SELECT * FROM words WHERE id = ?;";
    private final String GET_TRANSLATE = "SELECT translate FROM words WHERE id = ?;";
    private final String GET_TRANSLATE_WITH_NAME = "SELECT translate FROM words WHERE name = ?;";
    private final String CHECK_WORD = "SELECT translate FROM words WHERE name = ?;";

    public String getWord(){
        String result = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/wordolina", "root", "root");
            preparedStatement = connection.prepareStatement(GET_WORD);
            String id = String.valueOf(DatabaseService.getValidId());
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                result = resultSet.getString("name") + "=" + resultSet.getString("translate");
            }
        } catch (Exception e) {
            result = "exception";
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public String getTranslate(){
        String translate = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/wordolina", "root", "root");
            preparedStatement = connection.prepareStatement(GET_TRANSLATE);
            preparedStatement.setString(1, String.valueOf(DatabaseService.getValidId()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                translate = resultSet.getString("translate");
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
        return translate;
    }

    public List<String> getTranslate(String name){
        List<String> name_translate = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/wordolina", "root", "root");
            preparedStatement = connection.prepareStatement(GET_TRANSLATE_WITH_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                name_translate.add(name);
                name_translate.add(resultSet.getString("translate"));
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
        return name_translate;
    }

    public boolean checkWord(String word, String translate){
        String trueTranslate = null;
        boolean result = false;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/wordolina", "root", "root");
            preparedStatement = connection.prepareStatement(CHECK_WORD);
            preparedStatement.setString(1, word);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                trueTranslate = resultSet.getString("translate");
            }
            if (trueTranslate.equals(translate)){
                result = true;
            }
        } catch (SQLException e) {
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
