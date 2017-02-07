package com.github.czarijb.interfaces.impl;

import com.github.czarijb.hibernate.utils.DAOFactory;
import com.github.czarijb.interfaces.AssetsDAO;
import com.github.czarijb.objects.Assets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by aleksandr on 04.02.17.
 */
public class AssetsDAOImpl implements AssetsDAO {

    private ObservableList<Assets> assetsList = FXCollections.observableArrayList();
    private Session session;

    @Override
    public void addAssets(Assets assets) {
        assetsList.add(assets);
        try {
            session.beginTransaction();
            session.save(assets);
            session.getTransaction().commit();
        }catch (Exception e){
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
        try {
            session.beginTransaction();
            session.delete(assets);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public ObservableList<Assets> getAssetsList() {
        return assetsList;
    }

    public void fillAssetsTestData(){
        /*assetsList.add(new Assets("Квартира", 1, 1400000));
        assetsList.add(new Assets("Акции ВТБ", 100, 31));
        assetsList.add(new Assets("База отдыха", 1, 100000));*/
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Assets.class);
            this.assetsList.addAll(criteria.list());
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
