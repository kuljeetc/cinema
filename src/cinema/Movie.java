/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

/**
 *
 * @author Kuljeet
 */
public final class Movie
{
    private String id = null;
    private String name = null;
    private String genre = null;
    private String rating = null;
    private String duration = null;
    
    Movie(String id, String name, String genre, String rating, String duration)
    {
        Id(id);
        Name(name);
        Genre(genre);
        Rating(rating);
        Duration(duration);
    }
    
    private void Id(String id)
    {
        if(id == null || id.isEmpty())
        {
            IllegalArgumentException e = new IllegalArgumentException("Movie id can't be null or blank");
            throw e;
        }
        this.id = id;        
    }
    
    private void Name(String name)
    {
        if(name == null || name.isEmpty())
        {
            IllegalArgumentException e = new IllegalArgumentException("Movie name can't be null or blank");
            throw e;
        }
        this.name = name;
    }

    private void Genre(String genre)
    {
        if(genre == null || genre.isEmpty())
        {
            IllegalArgumentException e = new IllegalArgumentException("Movie genre can't be null or blank");
            throw e;
        }
        this.genre = genre;
    }

    private void Rating(String rating)
    {
        if(rating == null || rating.isEmpty())
        {
            IllegalArgumentException e = new IllegalArgumentException("Movie rating can't be null or blank");
            throw e;
        }
        this.rating = rating;
    }

    private void Duration(String duration)
    {
        if(duration == null || duration.isEmpty())
        {
            IllegalArgumentException e = new IllegalArgumentException("Movie duration can't be null or blank");
            throw e;
        }
        this.duration = duration;
    }
    
    public String Id()
    {
        if(id == null || id.isEmpty())
        {
            RuntimeException e = new RuntimeException("Movie Id is not set");
            throw e;
        }
        return id;
    }    
    
    public String Name()
    {
        if(name == null || name.isEmpty())
        {
            RuntimeException e = new RuntimeException("Movie name is not set");
            throw e;
        }
        return name;
    }
    
    public String Genre()
    {
        if(genre == null || genre.isEmpty())
        {
            RuntimeException e = new RuntimeException("Movie genre is not set");
            throw e;
        }
        return genre;
    }
    
    public String Rating()
    {
        if(rating == null || rating.isEmpty())
        {
            RuntimeException e = new RuntimeException("Movie rating is not set");
            throw e;
        }
        return rating;  
    }
    
    public String Duration()
    {
        if(duration == null || duration.isEmpty())
        {
            RuntimeException e = new RuntimeException("Movie duration is not set");
            throw e;
        }
        return duration; 
    }
    
    public static String getHeader()
    {
        return String.format("%-5s%-60.60s%-25.25s%-16.16s%-16.16s", "No.", "Name", "Genre", "Rating", "Duration");
    }
    
    public static String getSeperator()
    {
        return new String(new char[121]).replace("\0", "=");
    }
    
    public String getFormattedString(int idx)
    {
        if(idx < 1)
            return String.format("%-65.65s%-35.35s%-20.20s%-12.12s", Name(), Genre(), Rating(), Duration());
        else
            return String.format("%-5d%-60.60s%-25.25s%-16.16s%-16.16s", idx, Name(), Genre(), Rating(), Duration());
    }

    public boolean isChildSafe()
    {
        switch(Rating().toUpperCase())
        {
            case "U":
            case "PG":      return true;

            default:        return false;
        }
    }
    
    public boolean isChildSafeWithAdult()
    {
        switch(Rating().toUpperCase())
        {
            case "U":
            case "PG":      
            case "12A":     return true;

            default:        return false;
        }
    }
}
