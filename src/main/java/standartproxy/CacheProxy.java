package standartproxy;

import dynamicproxy.cached.CacheHandler;
import dynamicproxy.service.Service;

import java.lang.reflect.Proxy;

public class CacheProxy {
    public static Service cache(Service service, String path){
        Service resultCacheService = (Service) Proxy.newProxyInstance(service.getClass().getClassLoader(),
                service.getClass().getInterfaces(), new CacheHandler(service, path));
        return resultCacheService;
    }

    public static Service cache(Service service){
        return cache(service, "");
    }
}
