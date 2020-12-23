package dynamicproxy;

import dynamicproxy.cached.CacheProxy;
import dynamicproxy.service.HardWorkService;
import dynamicproxy.service.Service;

public class Main {
    public static void main(String[] args) {
        Service proxyService = CacheProxy.cache(new HardWorkService(), "src/main/resources");
        proxyService.testMethod();
        proxyService.doHardWork();
        System.out.println(proxyService.doHardWork().toString());
        proxyService.doEasyWork();
        proxyService.getUser();// exception
    }
}
