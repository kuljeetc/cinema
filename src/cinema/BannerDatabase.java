/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinema;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kuljeet
 */
class BannerDatabase
{
    List<String> banners = new ArrayList<>();
    
    BannerDatabase(CinemaProperties cinemaProp)
    {
        banners.add("cinema/res/banner-bs2.jpg");
        banners.add("cinema/res/banner-trolls.jpg");
        banners.add("cinema/res/banner-axmas.jpg");
        banners.add("cinema/res/banner-drs.jpg");
        banners.add("cinema/res/banner-moana.jpg");
    }

    int size()
    {
        return banners.size();
    }

    String getBanner(int n)
    {
        return getClass().getClassLoader().getResource(banners.get(n-1)).toString();
    }
}
