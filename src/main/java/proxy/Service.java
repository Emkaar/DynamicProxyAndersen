package proxy;

import cached.Cached;
import model.User;

import java.util.ArrayList;

public interface Service {
    @Cached
    ArrayList<String> doHardWork();
    @Cached(saveName = "hahaah")
    String doEasyWork();
    void testMethod();
    @Cached
    User getUser();
}
