package dynamicproxy;

import dynamicproxy.cached.CacheHandler;
import dynamicproxy.service.HardWorkService;
import dynamicproxy.service.Service;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        HardWorkService hardWorkService = new HardWorkService();
        Service proxyService = (Service) Proxy.newProxyInstance(hardWorkService.getClass().getClassLoader(),
                hardWorkService.getClass().getInterfaces(), new CacheHandler(hardWorkService, "src/main/resources"));
        proxyService.testMethod();
        proxyService.doHardWork();
        System.out.println(proxyService.doHardWork().toString());
        proxyService.doEasyWork();
        proxyService.getUser();// exception
    }
}
