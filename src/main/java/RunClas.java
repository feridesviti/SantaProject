import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;

import com.opencsv.exceptions.CsvException;
import utils.CsvHelper;
import utils.EmailHelper;
import utils.FileRider;
import utils.Santa;
import utils.SantaBill;
import utils.User;

public class RunClas {

    private static String path = "C:\\Users\\syuvkovetska\\Desktop\\autoTests\\mini-project\\src\\main\\resources\\users.csv";
    private static String htmlPath = "C:\\Users\\syuvkovetska\\Desktop\\autoTests\\SantaProject\\src\\main\\resources\\secretSantaTemplate";

    public static void main(String[] args) throws IOException, CsvException, MessagingException {
        String textTemplate = FileRider.getFile(htmlPath);

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
            emailHelper.sendEmail("svitlana.slobodyan894@gmail.com", "svitlanas1", "Secret Santa", personalTemplate, "feridesviti@gmail.com");
            System.out.println("Sended for " + happySanta.getSanta().getName());

        }


    }

    private static List<String[]> getItemFromCsv(String path) throws IOException, CsvException {
        CsvHelper csvHelper = new CsvHelper(path);
        int headIndex = 0;
        return csvHelper.csvReader();
    }

}
