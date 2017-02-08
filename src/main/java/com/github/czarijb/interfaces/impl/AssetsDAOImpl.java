package com.github.czarijb.interfaces.impl;

import com.github.czarijb.interfaces.AssetsDAO;
import com.github.czarijb.objects.Assets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AssetsDAOImpl implements AssetsDAO {

    private ObservableList<Assets> assetsList = FXCollections.observableArrayList();
    private static final Logger log = LoggerFactory.getLogger(AssetsDAOImpl.class);
    private Session session;

    @Override
    public void addAssets(Assets assets) {
        assetsList.add(assets);
        log.debug("add new assets in assetsList");
        try {
            log.debug("try to insert new assets in DB");
            session.beginTransaction();
            session.save(assets);
            session.getTransaction().commit();
            log.debug("insert new assets in DB");
        }catch (Exception e){
            log.debug("can't adding new assets in DB");
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateAssets(Assets assets) {
        try {
            session.beginTransaction();
            session.update(assets);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAssets(Assets assets) {

        assetsList.remove(assets);
        log.debug("delete assets in assetsList");
        try {
            log.debug("try to delete assets in DB");
            session.beginTransaction();
            session.delete(assets);
            session.getTransaction().commit();
            log.debug("delete assets in DB");
        }catch (Exception e){
            log.debug("can't deleting assets in DB");
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public ObservableList<Assets> getAssetsList() {
        return assetsList;
    }

    public void fillAssetsTestData(){

        try {
            log.debug("try get list of assets from DB");
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Assets.class);
            this.assetsList.addAll(criteria.list());
            session.getTransaction().commit();
            log.debug("get all assets from DB");
        }catch (Exception e){
            log.debug("cant't get all assets from DB");
            e.printStackTrace();
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
