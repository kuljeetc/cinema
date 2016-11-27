/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Kuljeet
 */
public class SeatDatabase
{
    private final Map<Integer, Integer> seatAvailability = new TreeMap<>();
    private final int cinemaCapacity = 50;
    
    public int getRemainingSeats(int hash)
    {
        if(!seatAvailability.containsKey(hash))
        {
            seatAvailability.put(hash, cinemaCapacity); 
        }
        return seatAvailability.get(hash);
    }
    
    public void allocateSeats(int hash, int count)
    {
        if(!seatAvailability.containsKey(hash))
        {
            RuntimeException re = new RuntimeException("Can't allocate seat from unknown show!!!");
            throw re;
        }
        if(seatAvailability.get(hash) < count)
        {
            RuntimeException re = new RuntimeException(String.format(
                    "Not enough seats available, Available = %d, Requested = %d", seatAvailability.get(hash), count));
            throw re;
        }
        seatAvailability.put(hash, seatAvailability.get(hash) - count);
    }
    
    public boolean isSeatAvailable(int hash, int count)
    {
        if(!seatAvailability.containsKey(hash))
        {
            RuntimeException re = new RuntimeException("Can't allocate seat from unknown show!!!");
            throw re;
        }
        return seatAvailability.get(hash) >= count;
    }
}
