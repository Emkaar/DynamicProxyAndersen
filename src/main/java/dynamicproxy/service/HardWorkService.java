package dynamicproxy.service;

import dynamicproxy.cached.Cached;
import dynamicproxy.model.User;

import java.util.ArrayList;

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
