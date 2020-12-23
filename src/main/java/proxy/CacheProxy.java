package proxy;

import cached.Cached;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;

public class CacheProxy implements InvocationHandler {
    private Service service;
    private File path = new File("");
    private HashMap<String, Object> jvmCache = new HashMap<>();

    public CacheProxy(Service service) {
        this.service = service;
    }

    public CacheProxy(Service service, File path) {
        this.service = service;
        this.path = path;
    }

    public CacheProxy(Service service, String path) {
        this.service = service;
        this.path = new File(path);
    }



    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
                    result = method.invoke(service, args);
                    if(!path.exists()){
                        path.mkdir();
                    }
                    try(ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(cachedFilePath))) {
                        ois.writeObject(result);
                    }catch (NotSerializableException e){

                    }
                }
            }
            else {
                if(jvmCache.containsKey(cachedName)){
                    return jvmCache.get(cachedName);
                }else {
                    result = method.invoke(service, args);
                    jvmCache.put(cachedName, result);
                }
            }
            try {
                return returnClass.cast(result);
            }catch (ClassCastException e){
                System.out.println("The Cache contains wrong data type in this file / for this key.");
                return method.invoke(service, args);
            }
        }
        else {
            return method.invoke(service, args);
        }
    }
}
