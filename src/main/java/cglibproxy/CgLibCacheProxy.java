package cglibproxy;

import dynamicproxy.cached.Cached;
import dynamicproxy.service.Service;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.io.*;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CgLibCacheProxy {
    private static HashMap<String, Object> jvmCache = new HashMap<>();

    public static Service cache(Service service, String path){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(service.getClass());
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                if (method.isAnnotationPresent(Cached.class)){
                    Cached cached = method.getAnnotation(Cached.class);
                    String cachedName;
                    Object result = null;
                    Class returnClass = method.getReturnType();
                    if(cached.saveName().equals("")){
                        cachedName = method.getName();
                    }else {
                        cachedName = cached.saveName();
                    }

                    if(cached.savePolicy().equals(Cached.SavePolicy.FILE)){
                        File cachedFilePath = new File(path, cachedName + ".ser");
                        if(cachedFilePath.exists()){
                            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cachedFilePath))) {
                                result = ois.readObject();
                            }catch (WriteAbortedException e){
                                System.out.println("The object you want to cache is not serializable, use cacheProxy with serializable object.");
                            }
                        }else {
                            result = proxy.invoke(service, args);
                            if(!new File(path).exists()){
                                new File(path).mkdir();
                            }
                            try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(cachedFilePath))) {
                                ois.writeObject(result);
                            }catch (NotSerializableException e){
                                System.out.println("The object you want to cache is not serializable, use cacheProxy with serializable object.");
                            }
                        }
                    }
                    else {
                        if(jvmCache.containsKey(cachedName)){
                            result = jvmCache.get(cachedName);
                        }else {
                            result = proxy.invoke(service, args);
                            jvmCache.put(cachedName, result);
                        }
                    }
                    try {
                        return returnClass.cast(result);
                    }catch (ClassCastException e){
                        System.out.println("The Cache contains wrong data type in this file / for this key.");
                        return proxy.invoke(service, args);
                    }

                } else {
                    return proxy.invokeSuper(obj, args);
                }
            }
        });
        Service proxy = (Service)enhancer.create();
        return proxy;
    }

    public static Service cache(Service service){
        return cache(service, "");
    }
}
