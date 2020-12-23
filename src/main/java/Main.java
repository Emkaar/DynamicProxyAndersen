import proxy.CacheProxy;
import proxy.HardWorkService;
import proxy.Service;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        HardWorkService hardWorkService = new HardWorkService();
        Service proxyService = (Service) Proxy.newProxyInstance(hardWorkService.getClass().getClassLoader(),
                hardWorkService.getClass().getInterfaces(), new CacheProxy(hardWorkService, "src/main/resources"));
        proxyService.testMethod();
        proxyService.doHardWork();
        System.out.println(proxyService.doHardWork().toString());
        proxyService.doEasyWork();
        proxyService.getUser();
    }
}
