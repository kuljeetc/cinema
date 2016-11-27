/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import javax.swing.JLabel;

/**
 *
 * @author Kuljeet
 */
class Banner extends Thread 
{
    JLabel label;
    BannerDatabase bannerDb;
    
    Banner(JLabel label, CinemaProperties cinemaProp) 
    {
        this.label = label;
        this.bannerDb = new BannerDatabase(cinemaProp);
    }

    @Override
    public void run() 
    {
        int n = 1;
        while(true)
        {
            String banner = bannerDb.getBanner(n);
            String formatterBanner = "<html><img src=\"" + banner + "\" height=\"180\" width=\"1000\" /></html>";
            label.setText(formatterBanner);
            
            try 
            {
                Thread.sleep(5000);
            } 
            catch (InterruptedException e) {}
                
            n = n < bannerDb.size() ? ++n : 1;
        }
    }
}
