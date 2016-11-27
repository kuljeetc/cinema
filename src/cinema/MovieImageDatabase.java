/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Kuljeet
 */
public final class MovieImageDatabase
{
    private String movieImageFile = null;
    private Map<String, String> movieImages = new TreeMap<>();
    private JFrame frame = null;
    
    MovieImageDatabase(String movieImageFile, JFrame frame)
    {
        if(movieImageFile == null || movieImageFile.isEmpty())
        {
            return;
        }
        this.movieImageFile = movieImageFile;
        this.frame = frame;
        Refresh();
    }
    
    public void Refresh()
    {
        BufferedReader br = null;
        try 
        {
            movieImages.clear();
            String line = "";
            InputStream is = getClass().getResourceAsStream(movieImageFile);
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = br.readLine()) != null) 
            {
                String[] vars = line.split(",");
                if(vars.length > 2)
                {
                    JOptionPane.showMessageDialog(frame, 
                                                  "Input Movie Image list csv file in not correctly formated\n"+
                                                  "\"" + line + "\"",
                                                  "Error", 
                                                  JOptionPane.ERROR_MESSAGE);
                }
                try
                {
                    if(!movieImages.containsKey(vars[0]))
                    {
                        movieImages.put(vars[0], vars.length == 2 ? vars[1] : null );
                    }
                    else
                    {
                        //Display Error
                    }
                }
                catch(IllegalArgumentException e)
                {
                    JOptionPane.showMessageDialog(frame, 
                                                  e.getMessage() + "\n\n" + line + "\n\nSkipping this item\n",
                                                  "Error", 
                                                  JOptionPane.ERROR_MESSAGE);
                }
            }
        } 
        catch (FileNotFoundException e) 
        {
            JOptionPane.showMessageDialog(frame, "FileNotFoundException\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(frame, "IOException\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        finally 
        {
            if (br != null) 
            {
                try 
                {
                    br.close();
                }
                catch (IOException e) 
                {
                }
            }
        }
    }
    
    public String getImageUri(String movieId)
    {
        if(movieImages.containsKey(movieId))
        {
            String imgUri = movieImages.get(movieId);
            if(imgUri != null && !imgUri.isEmpty())
            {
                if(imgUri.toLowerCase().startsWith("http"))
                    return imgUri;
                else
                {
                    URL path = getClass().getClassLoader().getResource(("cinema/res/" + imgUri));
                    if(path != null)
                        return path.toString();
                }
            }
        }
        return getClass().getClassLoader().getResource("cinema/res/noimage.jpg").toString();
    }
    
}
