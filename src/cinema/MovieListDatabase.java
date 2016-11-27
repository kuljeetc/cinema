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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;
/**
 *
 * @author Kuljeet
 */
public final class MovieListDatabase implements ListModel
{
    private final String movieListSource;
    private ArrayList<Movie> movieDatabase = new ArrayList<>();
    private ArrayList<Movie> filteredMovieDatabase = null;
    private ListDataListener listDataListener = null;
    private JFrame frame = null;
    
    public MovieListDatabase(String source, JFrame frame)
    {
        movieListSource = source;
        this.frame = frame;
        Refresh();
    }
    
    public void Refresh()
    {
        BufferedReader br = null;
        try 
        {
            movieDatabase = new ArrayList<Movie>();
            String line = "";
            InputStream is = getClass().getResourceAsStream(movieListSource);
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((line = br.readLine()) != null) 
            {
                String[] vars = line.split(",");
                if(vars.length != 5)
                {
                    JOptionPane.showMessageDialog(frame, 
                                                  "Input Movie list csv file in not correctly formated\n"+
                                                  "\"" + line + "\"",
                                                  "Error", 
                                                  JOptionPane.ERROR_MESSAGE);
                }
                try
                {
                    if(isDuplicateItem(vars[0]))
                    {
                        JOptionPane.showMessageDialog(frame, 
                                                  "Duplication item " + vars[0] + " found in list" +
                                                  "\n\nSkipping this item\n\n",
                                                  "Warning", 
                                                  JOptionPane.WARNING_MESSAGE);
                        continue;
                    }
                    movieDatabase.add(new Movie(vars[0], vars[1], vars[2], vars[3], vars[4]));
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
        if(movieDatabase.isEmpty())
        {
            JOptionPane.showMessageDialog(frame, "No movies find in source file, program will exit", "Warning", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        filteredMovieDatabase = new ArrayList<Movie>(movieDatabase);
        Notify();
    }

    public void filterMovies(String filter)
    {
        if(filter == null || filter.isEmpty())
        {
            resetFilter();
        }
        else
        {
            filteredMovieDatabase = new ArrayList<>();
            for(int i = 0; i < movieDatabase.size(); ++i)
            {
                if(movieDatabase.get(i).Name().toLowerCase().contains(filter.toLowerCase()) 
                   || movieDatabase.get(i).Genre().toLowerCase().contains(filter.toLowerCase())
                   || movieDatabase.get(i).Rating().toLowerCase().contains(filter.toLowerCase()) )
                {
                    filteredMovieDatabase.add(movieDatabase.get(i));
                }
            }
            Notify();
        }
    }
    
    public Movie getMovie(int idx, JFrame frame)
    {
        if(idx < 2)
        {
            RuntimeException e = new RuntimeException("Invalid movie selection in the list");
            JOptionPane.showMessageDialog(frame, "RuntimeException\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw e;
        }
        
        return filteredMovieDatabase.get(idx-2);
    }
    
    private boolean isDuplicateItem(String id)
    {
        for(int i = 0; i < movieDatabase.size(); ++i)
        {
            if( movieDatabase.get(i).Id().equalsIgnoreCase(id) )
                return true;
        }
        return false;
    }
    
    @Override
    public int getSize()
    {
        return filteredMovieDatabase.size() + 2; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getElementAt(int index)
    {
        switch (index)
        {
            case 0:
                return Movie.getHeader();
            case 1:
                return Movie.getSeperator();
            default:
                return filteredMovieDatabase.get(index-2).getFormattedString(index-1);
        }
    }

    @Override
    public void addListDataListener(ListDataListener l)
    {
        listDataListener = l;
    }

    @Override
    public void removeListDataListener(ListDataListener l)
    {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    void resetFilter()
    {
        if(filteredMovieDatabase.size() != movieDatabase.size())
        {
            filteredMovieDatabase = new ArrayList<Movie>(movieDatabase);
            Notify();
        }
    }
    
    private void Notify()
    {
        if(listDataListener != null)
        {
            listDataListener.contentsChanged(null);
        }
    }
    
}
