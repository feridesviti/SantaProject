package utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.exceptions.CsvException;

public class User {
    private String name;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public  List<User> getUserList(List<String[]> csvList) throws IOException, CsvException {
        List<User> userList = new ArrayList<>();
        for (String[] item : csvList){
            User user = new User();
            user.name = item[0].trim();
            user.email = item[1].trim();
            userList.add(user);
        }
        return userList;
    }

    public User getUserByName(List<User> userList, String name){
        for (User user : userList){
            if(user.name.equals(name)){
                return  user;
            }
        }
        return null;
    }

}
