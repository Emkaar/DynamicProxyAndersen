package cached;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CachedAnalyzer {
    public void parse(Class<?> clazz){
        Method [] methods = clazz.getMethods();
        for(Method method : methods){
            if(method.isAnnotationPresent(Cached.class)){
                try {
                    method.invoke(null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
