package com.github.czarijb.interfaces.impl;

import com.github.czarijb.interfaces.LiabilitiesDAO;
import com.github.czarijb.objects.Liabilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LiabilitiesDAOImpl implements LiabilitiesDAO {
    private ObservableList<Liabilities> liabilitiesList = FXCollections.observableArrayList();
    private static final Logger log = LoggerFactory.getLogger(LiabilitiesDAOImpl.class);
    private Session session;

    @Override
    public void addLiabilities(Liabilities liabilities) {

        liabilitiesList.add(liabilities);
        log.debug("add new liabilities in liabilitiesList");
        try {
            log.debug("try to insert new liabilities in DB");
            session.beginTransaction();
            session.save(liabilities);
            session.getTransaction().commit();
            log.debug("insert new liabilities in DB");
        }catch (Exception e){
            log.debug("can't adding new liabilities in DB");
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void updateLiabilities(Liabilities liabilities) {
        try {
            session.beginTransaction();
            session.update(liabilities);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLiabilities(Liabilities liabilities) {

        liabilitiesList.remove(liabilities);
        log.debug("delete liabilities in liabilitiesList");
        try {
            log.debug("try to delete liabilities in DB");
            session.beginTransaction();
            session.delete(liabilities);
            session.getTransaction().commit();
            log.debug("delete liabilities in DB");
        }catch (Exception e){
            log.debug("can't deleting liabilities in DB");
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public javafx.collections.ObservableList<Liabilities> getLiabilitiesList() {
        return liabilitiesList;
    }

    public void fillLiabilitiesTestData(){

        try {
            log.debug("try get list of liabilities from DB");
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Liabilities.class);
            this.liabilitiesList.addAll(criteria.list());
            session.getTransaction().commit();
            log.debug("get all liabilities from DB");
        }catch (Exception e){
            log.debug("cant't get all liabilities from DB");
            e.printStackTrace();
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
