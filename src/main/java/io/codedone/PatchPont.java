package io.codedone;

import sun.font.PhysicalFont;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class PatchPont {

    public List<String> fontPath = new ArrayList<String>();
    public List<String> compositeFontName = new ArrayList<String>();

  public void findPath() throws ClassNotFoundException, IllegalAccessException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException {


      GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
      Font[] fonts = e.getAllFonts();
        for(Font font : fonts) {
            Object font2D;
            try {
                font2D = Class.forName("sun.font.FontUtilities").getDeclaredMethod("getFont2D", new Class[] {Font.class}).invoke(null, new Object[] {font});
            } catch (Throwable ignored) {
                font2D = Class.forName("sun.font.FontManager").getDeclaredMethod("getFont2D", new Class[] {Font.class}).invoke(null, new Object[] {font});
            }

            Field platNameField = null;
            String file = null;
            try {
                platNameField = Class.forName("sun.font.PhysicalFont").getDeclaredField("platName");
                platNameField.setAccessible(true);
                file = (String)platNameField.get(font2D);
                fontPath.add(file);
            } catch (Throwable th) {
                compositeFontName.add(font.getFontName());

                /*platNameField = Class.forName("sun.font.CompositeFont").getDeclaredField("components");
                platNameField.setAccessible(true);
                PhysicalFont[] physicalFonts = (PhysicalFont[]) platNameField.get(font2D);

                for(PhysicalFont physical: physicalFonts) {
                    platNameField = Class.forName("sun.font.PhysicalFont").getDeclaredField("platName");
                    platNameField.setAccessible(true);
                    file = (String)platNameField.get(physical);
                    System.out.println(file);
                }*/
            }
        }
    }
}
