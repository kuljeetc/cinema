/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Kuljeet
 */
public class Purchase
{
    private Movie movie;
    
    private double adultPrice;
    private double childPrice;
    private double studentPrice;
    private double seniorPrice;
    
    private int adultCount;
    private int childCount;
    private int studentCount;
    private int seniorCount;
    
    private String time;
    private String date;
    
    Purchase(Movie m, CinemaProperties cinemaProp)
    {
        movie = m;
        adultPrice = cinemaProp.AdultPrice();
        childPrice = cinemaProp.ChildPrice();
        studentPrice = cinemaProp.StudentPrice();
        seniorPrice = cinemaProp.SeniorPrice();
    }
    
    void setAdultCount(String count)
    {
        adultCount = Integer.parseInt(count);
    }
    
    void setChildCount(String count)
    {
        childCount = Integer.parseInt(count);
    }
        
    void setStudentCount(String count)
    {
        studentCount = Integer.parseInt(count);
    }    
        
    void setSeniorCount(String count)
    {
        seniorCount = Integer.parseInt(count);
    }
    
    int getAdultCount()
    {
        return adultCount;
    }
    
    int getChildCount()
    {
        return childCount;
    }
        
    int getStudentCount()
    {
        return studentCount;
    }    
        
    int getSeniorCount()
    {
        return seniorCount;
    }
    
    int getTotalCount()
    {
        return getAdultCount() + getChildCount() + getStudentCount() + getSeniorCount();
    }
    
    double getTotalAdultPrice()
    {
       return adultCount * adultPrice; 
    }

    double getTotalChildPrice()
    {
       return childCount * childPrice; 
    }
    
    double getTotalStudentPrice()
    {
       return studentCount * studentPrice; 
    }
        
    double getTotalSeniorPrice()
    {
       return seniorCount * seniorPrice; 
    }
    
    double getTotalPrice()
    {
        return getTotalAdultPrice() + getTotalChildPrice() + getTotalStudentPrice() + getTotalSeniorPrice();
    }
    
    double getTax()
    {
        return getTotalPrice()/6;
    }

    void setTime(String time)
    {
        this.time = time;
    }
    
    String getTime()
    {
        return time;
    }
    
    void setDate(String date)
    {
        this.date = date;
    }
    
    String getDate()
    {
        return date;
    }
    
    String Commit(File file, JFrame frame, TransactionDatabase txDb)
    {
        Transaction transaction = new Transaction(
                                            movie, 
                                            getAdultCount(), getTotalAdultPrice(),
                                            getChildCount(), getTotalChildPrice(),
                                            getStudentCount(), getTotalStudentPrice(),
                                            getSeniorCount(), getTotalSeniorPrice(),
                                            getTax(), getTotalPrice());
        
        String txId = transaction.getTxId().toString();
        
        if(txId == null || txId.isEmpty())
        {
            return null;
        }
        
        txDb.Add(transaction);
        
        if(file != null && file.getAbsolutePath() != null && !file.getAbsolutePath().isEmpty())
        {
            Print(file.getAbsolutePath(), frame, txId);
        }
       
        return txId;
    }
    
    void Print(String file, JFrame frame, String txId)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy hh:mm:ss");
        Calendar calendar = Calendar.getInstance();

        List<String> lines = new ArrayList<>();
        
        lines.add(String.format("Transaction Id: %-60.60s%-40.40s", txId, sdf.format(calendar.getTime())));
        lines.add(new String(new char[96]).replace("\0", "="));
        lines.add("\n");
        
        lines.add(String.format("Movie: %-32.32s Genre: %-14.14s Rating: %-7.7s Duration: %-9.9s",
                                movie.Name(), movie.Genre(), movie.Rating(), movie.Duration()));
        
        lines.add("\n");
        
        lines.add(String.format("Date: %-33.33s Time: %-32.32s", getDate(), getTime()));
        
        lines.add("\n");
        
        lines.add(new String(new char[96]).replace("\0", "-"));
        
        if(getAdultCount() > 0)
            lines.add(String.format("\tAdult:    %24s%24s%24s", getAdultCount(), formatPrice(adultPrice), formatPrice(getTotalAdultPrice())));
        
        if(getChildCount() > 0)
            lines.add(String.format("\tChild:    %24s%24s%24s", getChildCount(), formatPrice(childPrice), formatPrice(getTotalChildPrice())));
        
        if(getStudentCount() > 0)
            lines.add(String.format("\tStudent:  %24s%24s%24s", getStudentCount(), formatPrice(studentPrice), formatPrice(getTotalStudentPrice())));
        
        if(getSeniorCount() > 0)
            lines.add(String.format("\tSenior:   %24s%24s%24s", getSeniorCount(), formatPrice(seniorPrice), formatPrice(getTotalSeniorPrice())));
        
        lines.add("\n");
        
        lines.add(new String(new char[96]).replace("\0", "-"));
        
        lines.add(String.format("\tVAT @ 20%% (included in price):    %48s", formatPrice(getTax())));
        lines.add("\n");
        lines.add(String.format("\tTotal:    %24s%48s", getTotalCount(), formatPrice(getTotalPrice())));
        
        lines.add("\n");
        lines.add(new String(new char[96]).replace("\0", "-"));
        lines.add(String.format("%56s", "Enjoy Your Movie"));
        lines.add(new String(new char[96]).replace("\0", "-"));
        lines.add("\n");
        
        Path path = Paths.get(file);
        try
        {
            Files.write(path, formatMessage(lines).getBytes());
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(frame, "Failed to print the file \n\n"+
                                              "\"" + file + "\"\n\n" +
                                              e.getMessage(),
                                              "Error", 
                                              JOptionPane.ERROR_MESSAGE);       
        }
    }
    
    private String formatPrice(double price)
    {
        DecimalFormat df2 = new DecimalFormat("£#0.00");
        return df2.format(price);
    }
    
    private String formatMessage(List<String> lines)
    {
        String msg = "";
        for(int i = 0; i < lines.size(); ++i)
            msg += lines.get(i);
        return msg;
    }
}
