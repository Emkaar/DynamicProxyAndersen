package proxy;

import cached.Cached;
import model.User;

import java.util.ArrayList;
import java.util.Arrays;

public class HardWorkService implements Service{
    @Cached(saveName = "lala")
    @Override
    public ArrayList<String> doHardWork() {
        ArrayList<String> resultList = new ArrayList<>();
        resultList.add("lala");
        resultList.add("lalala");
        return resultList;
    }

    @Override
    public String doEasyWork() {
        return null;
    }

    @Override
    public void testMethod() {
        System.out.println("Kokos");
    }

    @Override
    public User getUser() {
        return new User("Ivan", 22);
    }
}
