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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Kuljeet
 */
public class MovieSchedule
{
    private Map<String, Map<Integer, ArrayList<String>>> 
                        movieTimeSchedule;
    private String movieScheduleFile = null;
    private JFrame frame = null;
    
    public MovieSchedule(String movieScheduleFile, JFrame frame)
    {
        this.movieTimeSchedule = new TreeMap<>();
        if(movieScheduleFile == null || movieScheduleFile.isEmpty())
        {
            return;
        }
        this.movieScheduleFile = movieScheduleFile;
        this.frame = frame;
        Refresh();
    }
    
    public void Refresh()
    {
        BufferedReader br = null;
        try 
        {
            movieTimeSchedule.clear();
            String line = "";
            InputStream is = getClass().getResourceAsStream(movieScheduleFile);
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = br.readLine()) != null) 
            {
                String[] vars = line.split(",");
                if(vars.length > 2)
                {
                    JOptionPane.showMessageDialog(frame, 
                                                  "Input Movie Schedule list csv file in not correctly formated\n"+
                                                  "\"" + line + "\"",
                                                  "Error", 
                                                  JOptionPane.ERROR_MESSAGE);
                }
                try
                {
                    if(!movieTimeSchedule.containsKey(vars[0]))
                    {
                        movieTimeSchedule.put(vars[0], vars.length == 2 ? ParseTime(vars[1]) : null);
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
    
    private Map<Integer, ArrayList<String>> ParseTime(String timing)
    {
        Map<Integer, ArrayList<String>> schedule = new TreeMap<Integer, ArrayList<String>>();
        
        if(timing == null || timing.isEmpty() )
            return null;
        
        String[] days = timing.split("\\|");
        for(int i = 0; i < days.length; ++i)
        {
            try
            {
                String[] dayTime = days[i].split("=");
                if(dayTime.length != 2)
                {
                    IllegalArgumentException iae = new IllegalArgumentException(
                                                    "Movie timings are in correct format : Day & Time Should seperate by '='");
                    throw iae;
                }

                int day = Integer.parseInt(dayTime[0]);
                if( schedule.containsKey(day) )
                {
                    IllegalArgumentException iae = new IllegalArgumentException(
                                                    String.format("Movie timings are in correct format : %d day has duplicate values", day));
                    throw iae;
                }

                String[] times = dayTime[1].split(";");
                ArrayList<String> vTimes = new ArrayList<>(Arrays.asList(times));
                schedule.put(day, vTimes);
            }
            catch(IllegalArgumentException e)
            {
                //No Action
            }
        }
        return schedule;
    }
    
    public ArrayList<String> getTimes(String movieId, int d)
    {
        if(movieTimeSchedule.containsKey(movieId))
        {
            Map<Integer, ArrayList<String>> schedule = movieTimeSchedule.get(movieId);
            if(schedule != null && schedule.containsKey(d))
            {
                return schedule.get(d);
            }
        }
        return null;
    }
}
