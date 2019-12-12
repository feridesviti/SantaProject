package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class SantaBill {
    private List<String[]> csvList;
    private List<User> userList;
    private List<User> victimList;


    public List<Santa> setSantaList(List<String[]> csvList, List<User> userList) {
        this.csvList = csvList;
        this.userList = userList;
        this.victimList = new ArrayList<>(userList);
        List<Santa> santaList = getPartSantaList();
        for (Santa santa : santaList) {
            List<User> potentialVictims= getPotentialVictimsList(santa);
            santa.setSantaTo(getRandomVictim(potentialVictims));
            victimList.remove(santa.getSantaTo());
            santa.toString(santa);
        }
        return santaList;

    }

    private List<User> getPotentialVictimsList(Santa santa) {
         List<User> potentialVictims  = new ArrayList<>(victimList);
        removeSantaFromOunList(potentialVictims, santa.getSanta());
        potentialVictims = removeExceptionList(santa, potentialVictims);
        return potentialVictims;
    }

    private List<User> removeSantaFromOunList(List<User> potentialVictims, User santaAsUser) {
        if (potentialVictims.contains(santaAsUser)) {
            potentialVictims.remove(santaAsUser);
        }
        return potentialVictims;
    }

    private List<User> removeExceptionList(Santa santa, List<User> potentialVictims) {
        List<User> exceptionsList = santa.getExceptionsList();
        for (User user : exceptionsList){
            potentialVictims.remove(user);
        }
        return potentialVictims;
    }

    private User getRandomVictim(List<User> potentialVictims){
        Random rand = new Random();
        System.out.println("potentialVictims " + potentialVictims.size() +" =========================");
        int randInt = rand.nextInt(potentialVictims.size());
        return potentialVictims.get(randInt);
    }

    private List<Santa> getPartSantaList() {
        User user = new User();
        List<Santa> preSantaList = new ArrayList<>();
        for (String[] item : csvList) {
            Santa santaInfo = new Santa();
            santaInfo.setSanta(user.getUserByName(userList, item[0].trim()));
            santaInfo.addToExceptionsList(user.getUserByName(userList, item[2].trim()));
            santaInfo.addToExceptionsList(user.getUserByName(userList, item[3].trim()));
            preSantaList.add(santaInfo);
        }
        return preSantaList;
    }


}
