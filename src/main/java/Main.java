import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, InvocationTargetException, IllegalAccessException {
        PatchPont patchPont = new PatchPont();
        patchPont.findPath();

        int index = 0;
        for(String path: patchPont.fontPath) {
            index++;
            System.out.println(index +". " + path);
        }

        index = 0;
        for(String path: patchPont.compositeFontName) {
            index++;
            System.out.println(index +". " + path);
        }
    }
}
