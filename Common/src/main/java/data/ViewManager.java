/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ViewManager
{
    private static Map<String, View> views = new ConcurrentHashMap<>();

    public static Collection<View> views()
    {
        return views.values();
    }

    public static void createView(String path, boolean repeatImage)
    {
        View v = null;
        try
        {
            v = new View(new File(path).getCanonicalPath().replace("\\", "/"), repeatImage);
            views.put(path, v);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    public static View getView(String path)
    {
        return views.get(path);
    }

    public static void removeView(String path)
    {
        views.remove(path);
    }
}
