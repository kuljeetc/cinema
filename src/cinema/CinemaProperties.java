/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.io.InputStream;
/**
 *
 * @author Kuljeet
 */
public class CinemaProperties
{
    private static CinemaProperties cinemaProp;
    private Properties properties;
    
    public static CinemaProperties GetCinemaProperties() throws IOException
    {
        if(cinemaProp == null)
        {
            cinemaProp = new CinemaProperties();
            cinemaProp.Initialize("/cinema/res/config.properties");
        }
        return cinemaProp;
    }
    
    private void Initialize(String propFile) throws IOException
    {
        InputStream inputStream = null;
        try 
        {
            properties = new Properties();
            inputStream = getClass().getResourceAsStream(propFile);

            if (inputStream != null) {
                    properties.load(inputStream);
            } else {
                    throw new FileNotFoundException("property file '" + propFile + "' not found in the classpath");
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Exception: " + e);
            System.exit(0);
        }
        finally 
        {
            if(inputStream != null) 
                inputStream.close();
        }
    }
    
    public boolean VerifyUsername(String username)
    {
        if( properties.getProperty("Username") == null || username == null)
        {
            return false;
        }
        String u = properties.getProperty("Username");
        return properties.getProperty("Username").equals(username);
    }

    public boolean VerifyPassword(String password)
    {
        if( properties.getProperty("Password") == null || password == null)
        {
            return false;
        }
        String u = properties.getProperty("Password");
        return properties.getProperty("Password").equals(password);
    }
    
    public String getMovieList()
    {
        return "res/" + properties.getProperty("MovieList");
    }
    
    public String getMovieScheduleList()
    {
        return "res/" + properties.getProperty("MovieSchedule");
    }
    
    public String getMovieImageList()
    {
        return "res/" + properties.getProperty("MovieImageList");
    }

    double AdultPrice()
    {
        return Double.parseDouble(properties.getProperty("AdultPrice"));
    }

    double ChildPrice()
    {
        return Double.parseDouble(properties.getProperty("ChildPrice"));
    }

    double StudentPrice()
    {
        return Double.parseDouble(properties.getProperty("StudentPrice"));
    }

    double SeniorPrice()
    {
        return Double.parseDouble(properties.getProperty("SeniorPrice"));
    }
}
