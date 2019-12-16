package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.Data;


@Data
public class Santa {
    private User santa;
    private List<User> exceptionsList;
    private User santaTo;

    public void addToExceptionsList(User user){
        if (exceptionsList == null){
            exceptionsList = new ArrayList<>();
        }
        exceptionsList.add(user);
    }


    public void toString(Santa santa){
        String exceptPerson1, exceptPerson2;
        try {
            exceptPerson1 = santa.getExceptionsList().get(0).getName();
        }catch (NullPointerException n){
            exceptPerson1 = "";
        }

        try {
            exceptPerson2 = santa.getExceptionsList().get(1).getName();
        }catch (NullPointerException n){
            exceptPerson2 = "";
        }
        //System.out.println("Santa : " + santa.santa.getName() + " will make gifts to " + santa.santaTo.getName() + " definitely will not give to " +
        // exceptPerson1 + " " + " and " + exceptPerson2);
        System.out.println("Santa : " + santa.santa.getName() + " will make gift");
    }


}
