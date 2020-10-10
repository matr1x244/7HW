import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {

    static Method beforeMethod = null;
    static Method afterMethod = null;

    public static void main(String[] args) throws Exception {
        Class cl = StartTest.class;
        Object testObj = cl.newInstance();
        Method[] methods = cl.getDeclaredMethods();
        ArrayList<Method> al = new ArrayList<>();

        for (Method a : cl.getDeclaredMethods()) {
            if (a.isAnnotationPresent(Test.class)) {
                al.add(a);
            }

            //////////////

            if (a.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null) {
                    beforeMethod = a;
                } else  {
                    throw new RuntimeException("Больше одного BeforeSuite");
                }

            }
            if (a.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null) {
                    afterMethod = a;
                } else {
                   throw new RuntimeException("Больше одного AfterSuite");
                }
            }

            ////////////
            al.sort(new Comparator<Method>() {
                @Override
                public int compare(Method a1, Method a2) {
                    return a1.getAnnotation(Test.class).priority() - a2.getAnnotation(Test.class).priority();
                }
            });

        }
        if (beforeMethod != null) beforeMethod.invoke(testObj, null);
        for (Method a : al) a.invoke(testObj, null);
        if (afterMethod != null) afterMethod.invoke(testObj, null);
    }
}