package cglibproxy;

import dynamicproxy.service.HardWorkService;
import dynamicproxy.service.Service;

public class CglibMain {
    public static void main(String[] args) {
        Service cglibProxy = CgLibCacheProxy.cache(new HardWorkService(), "src/momo");
        cglibProxy.doHardWork();
        System.out.println(cglibProxy.doEasyWork());
        cglibProxy.getUser();
    }
}
