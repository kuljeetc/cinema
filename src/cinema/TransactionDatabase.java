/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Kuljeet
 */
public class TransactionDatabase implements ListModel
{
    private final List<Transaction> transactionList = new ArrayList<>();

    void Add(Transaction tx)
    {
        transactionList.add(tx);
    }
    
    @Override
    public int getSize()
    {
        return transactionList.size() + 2;
    }

    @Override
    public Object getElementAt(int index)
    {
        if(index == 0)
            return String.format("%-37.37s %-20.20s %12s %12s %12s %12s %12s %12s", "Id", "Movie", "Adult", "Child", "Student", "Senior", "Tax", "Total");
        if(index == 1)
            return new String(new char[140]).replace("\0", "-");
        else
            return transactionList.get(index-2).toString();
    }

    @Override
    public void addListDataListener(ListDataListener l)
    {
        
    }

    @Override
    public void removeListDataListener(ListDataListener l)
    {
        
    }
    
}
