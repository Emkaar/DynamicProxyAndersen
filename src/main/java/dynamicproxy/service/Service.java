package dynamicproxy.service;

import dynamicproxy.cached.Cached;
import dynamicproxy.model.User;

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
