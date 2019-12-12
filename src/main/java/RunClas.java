import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import com.opencsv.exceptions.CsvException;
import utils.CsvHelper;
import utils.EmailHelper;
import utils.Santa;
import utils.SantaBill;
import utils.User;

public class RunClas {
    private static String textTemplate = "<h1 style=\"text-align: center;\"><img src=\"https://encrypted-tbn0.gstatic" +
            ".com/images?q=tbn:ANd9GcSFm8fVaYWzBdRUG-eym-X3OzKByMQZD_a6zifPXZ8ffAJwvZIV_Q&amp;s\" alt=\"\" width=\"379\" height=\"133\" /></h1>\n" +
            "<h1 style=\"text-align: center;\">Secret Santa Gift Exchange</h1>\n" +
            "<h3 style=\"text-align: center;\">Hi SantaUser, You will be Secret Santa for <strong>User</strong></h3>\n" +
            "<p style=\"text-align: center;\"><strong>Gifts should cost 150-200UAN</strong></p>\n" +
            "<h4 style=\"text-align: center;\"><strong>Best wishes for a Merry Christmas and a Happy New Year!</strong></h4>\n" +
            "<p style=\"text-align: center;\"><strong><img src=\"https://encrypted-tbn0.gstatic" +
            ".com/images?q=tbn:ANd9GcQ3_ueuAIdqRzwKMaCfnrAKD5iRBH7UaKJcBEFxWPK4m-bggKmX6A&amp;s\" alt=\"\" width=\"451\" height=\"112\" /></strong></p>\n" +
            "<h3>&nbsp;</h3>";


    private static String path = "C:\\Users\\syuvkovetska\\Desktop\\autoTests\\mini-project\\src\\main\\resources\\users.csv";

    public static void main(String[] args) throws IOException, CsvException, MessagingException {

        User user = new User();
        SantaBill santaBill = new SantaBill();
        List<String[]> csvList = getItemFromCsv(path);
        List<User> userList = user.getUserList(csvList);
        List<Santa> happySantaList = santaBill.setSantaList(csvList, userList);
        System.out.println("@@@@@@@@");

        for (Santa happySanta : happySantaList){
            EmailHelper emailHelper = new EmailHelper();
            String personalTemplate = textTemplate.replace("SantaUser", happySanta.getSanta().getName());
            personalTemplate = personalTemplate.replace("User", happySanta.getSantaTo().getName());
           // emailHelper.sendEmail("svitlana.slobodyan894@gmail.com", "svitlanas1", "Secret Santa", personalTemplate, happySanta.getSanta().getEmail());
            System.out.println("Sended for " + happySanta.getSanta().getName());

        }


    }

    private static List<String[]> getItemFromCsv(String path) throws IOException, CsvException {
        CsvHelper csvHelper = new CsvHelper(path);
        int headIndex = 0;
        return csvHelper.csvReader();
    }

}
