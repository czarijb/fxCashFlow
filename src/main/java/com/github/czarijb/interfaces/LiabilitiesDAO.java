package com.github.czarijb.interfaces;

import com.github.czarijb.objects.Liabilities;

/**
 * Created by aleksandr on 04.02.17.
 */
public interface LiabilitiesDAO {

    public void addLiabilities (Liabilities liabilities);
    public void updateLiabilities (Liabilities liabilities);
    public void deleteLiabilities (Liabilities liabilities);
    /*public List<Expenses> getAllAssetsFromDB();*/
}
