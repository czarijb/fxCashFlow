package com.github.czarijb.interfaces;

import com.github.czarijb.objects.Assets;

public interface AssetsDAO {

    public void addAssets (Assets assets);
    public void updateAssets(Assets assets);
    public void deleteAssets(Assets assets);
}
