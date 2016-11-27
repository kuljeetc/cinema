/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.text.DecimalFormat;
import java.util.UUID;

/**
 *
 * @author Kuljeet
 */
public class Transaction
{
    private final DecimalFormat df2 = new DecimalFormat("£#0.00");
    
    private final UUID txId;
    private final Movie movie;
    
    private final int adultCount;
    private final int childCount;
    private final int studentCount;
    private final int seniorCount;
    
    private final double adultPrice;
    private final double childPrice;
    private final double studentPrice;
    private final double seniorPrice;
    
    private final double tax;
    private final double totalPrice;
    
    public Transaction( Movie m, 
                        int ac, double ap, 
                        int cc, double cp,
                        int sc, double sp, 
                        int sec, double sep,
                        double tax, double tp)
    {
        txId = UUID.randomUUID();
        movie = m;
        
        adultCount = ac;
        adultPrice = ap;
        
        childCount = cc;
        childPrice = cp;
        
        studentCount = sc;
        studentPrice = sp;
        
        seniorCount = sec;
        seniorPrice = sep;
        
        this.tax = tax;
        totalPrice = tp;
    }
    
    public UUID getTxId() { return txId; }
    public Movie getMovie() {return movie;}
    
    public int getAdultCount() {return adultCount;}
    public int getChildCount() {return childCount;}
    public int getStudentCount() {return studentCount;}
    public int getSeniorCount() {return seniorCount;}
    
    public double getAdultPrice() {return adultPrice;}
    public double getChildPrice() {return childPrice;}
    public double getStudentPrice() {return studentPrice;}
    public double getSeniorPrice() {return seniorPrice;}
    
    public double getTax() {return tax;}
    public double getTotalPrice() {return totalPrice;}
    
    @Override
    public String toString() 
    { 
        return String.format("%s  %-20.20s %12s %12s %12s %12s %12s %12s", 
                        getTxId().toString(),
                        getMovie().Name().length() > 20 ? getMovie().Name().substring(0, 16) + "..." : getMovie().Name(),
                        getCountPrice(getAdultCount(), getAdultPrice()),
                        getCountPrice(getChildCount(), getChildPrice()),
                        getCountPrice(getStudentCount(), getStudentPrice()),
                        getCountPrice(getSeniorCount(), getSeniorPrice()),
                        priceFormat(getTax()),
                        priceFormat(getTotalPrice())
                     );
    }
    
    private String getCountPrice(int c, double p)
    {
        return String.format("%s (%s)", c, priceFormat(p));
    }
    
    private String priceFormat(double p)
    {
        return df2.format(p);
    }
}
