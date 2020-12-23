package proxy;

import cached.Cached;

import java.util.ArrayList;

public class HardWorkService implements Service{
    @Cached
    @Override
    public ArrayList<String> doHardWork() {
        return null;
    }

    @Override
    public String doEasyWork() {
        return null;
    }
}
