package com.github.czarijb.interfaces;

import com.github.czarijb.objects.Assets;

import java.util.List;

/**
 * Created by aleksandr on 04.02.17.
 */
public interface AssetsDAO {

    public void addAssets (Assets assets);
    public void updateAssets(Assets assets);
    public void deleteAssets(Assets assets);
    /*public List<Assets> getAllAssetsFromDB();*/
}
