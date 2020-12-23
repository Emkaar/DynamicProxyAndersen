package cached;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cached {
    public enum SavePolicy{FILE, HEAP};
    String saveName() default "";
    SavePolicy savePolicy() default SavePolicy.FILE;
}
