/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Kuljeet
 */
public class MovieScheduleFormatter
{
    private MovieSchedule movieSchedule = null;
    private MovieImageDatabase movieImageDB = null;
            
    public MovieScheduleFormatter(MovieSchedule movieSchedule, MovieImageDatabase movieImageDB)
    {
        if(movieSchedule == null)
        {
            IllegalArgumentException iae = new IllegalArgumentException("movieSchedule is Null");
            throw iae;
        }
        this.movieSchedule = movieSchedule;
        this.movieImageDB = movieImageDB;
    }
    
    public String format(Movie m)
    {
        String spaces = new String(new char[2]).replace("\0", "&nbsp;");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        
        String shows = "<html>";
        shows += "<table style=\"width: 750px; height: 480px;\" margin: 0px; cellpadding=\"0\"; cellspacing=\"2\"; border=\"0\">";

        shows += "<tr><td style=\"text-align:left; font-size:20px; height: 40px; width: 360px; color:blue; margin-left: 0.4cm\">"
              +m.Name()
              +"</td>";
              
        shows += "<td style=\"text-align:left; font-size:20px; height: 40px; width: 130px; color:blue;\">" 
              +m.Genre()
              +"</td>";
        
        shows += "<td style=\"text-align:left; font-size:20px; height: 40px; width: 100px; color:blue;\">" 
              +m.Duration()
              +"</i></td>";
                
        shows += "<td align=\"center\" >";        
        shows += getRatingImg(m);
        shows += "</td></tr>";
        
        shows += "</table>";
        
        
        shows += "<table style=\"width: 750px; height: 480px;\" cellpadding=\"0\"; cellspacing=\"2\";  border=\"0\">";
        shows += "<tr><td style=\"height: 40px; width: 100px; margin-left: 0.4cm\">Today</td>";
        shows += timeInfo(day, m);
        shows += imageInfo(m);
        shows += "</tr>";

        calendar.add(Calendar.DAY_OF_WEEK, 1);
        day = calendar.get(Calendar.DAY_OF_WEEK);
        shows += "<tr><td style=\"height: 40px; margin-left: 0.4cm\">Tomorrow</td>";
        shows += timeInfo(day, m);
        shows += "</tr>";
         
        DateFormat df = new SimpleDateFormat("EEEE");
        for(int i = 0; i < 5; ++i)
        {
            calendar.add(Calendar.DAY_OF_WEEK, 1);
            day = calendar.get(Calendar.DAY_OF_WEEK);
            shows += "<tr><td style=\"height: 40px; margin-left: 0.4cm\">";
            shows += df.format(calendar.getTime());
            shows += "</td>";
            shows += timeInfo(day, m);
            shows += "</tr>";
        }

        shows += "</table></html>";
        return shows;
    }
    
    private String timeInfo(int day, Movie m)
    {
        String shows = "<td style=\"width: 400px;\">";
        ArrayList<String> times = movieSchedule.getTimes(m.Id(), day);
        if(times == null || times.isEmpty())
        {
            shows += "No Show Scheduled";
        }
        else
        {
            for (String time : times) 
            {
                shows += String.format("%s; ", time);
            }
        }
        shows += "</td>";
        return shows;
    }
    
    private String imageInfo(Movie m)
    {
        String imageUri = movieImageDB.getImageUri(m.Id());
        String shows = "<td rowspan=\"7\"; align=\"center\"; style=\"width: 250px;\" >";
        shows += "<img width=\"250\" height=\"350\" src=\"" + imageUri + "\" />";
        shows += "</td>";
        return shows;
    }
    
    private String getRatingImg(Movie m)
    {
        String path = null;
      
        switch(m.Rating().toLowerCase())
        {
            case "u":       path = "cinema/res/u.png";      break;
            case "pg":      path = "cinema/res/pg.png";      break;
            case "12":      path = "cinema/res/12.png";      break;
            case "12a":     path = "cinema/res/12a.png";      break;
            case "15":      path = "cinema/res/15.png";      break;
            case "18":      path = "cinema/res/18.png";      break;
        }

        String s = getClass().getClassLoader().getResource(path).toString();
        return "<img src=\"" + s + "\" />";
    }   
}
