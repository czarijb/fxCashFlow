package com.github.czarijb.interfaces.impl;

import com.github.czarijb.interfaces.LiabilitiesDAO;
import com.github.czarijb.objects.Liabilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Criteria;
import org.hibernate.Session;

/**
 * Created by aleksandr on 04.02.17.
 */
public class LiabilitiesDAOImpl implements LiabilitiesDAO {
    private ObservableList<Liabilities> liabilitiesList = FXCollections.observableArrayList();

    private Session session;

    @Override
    public void addLiabilities(Liabilities liabilities) {

        liabilitiesList.add(liabilities);
        try {
            session.beginTransaction();
            session.save(liabilities);
            session.getTransaction().commit();
        }catch (Exception e){
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
        try {
            session.beginTransaction();
            session.delete(liabilities);
            session.getTransaction().commit();
        }catch (Exception e){
            session.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public javafx.collections.ObservableList<Liabilities> getLiabilitiesList() {
        return liabilitiesList;
    }

    public void fillLiabilitiesTestData(){
        /*liabilitiesList.add(new Liabilities("Кредитка ВТБ", 70000));
        liabilitiesList.add(new Liabilities("Кредитка Тинькофф", 104000));*/
        try {
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Liabilities.class);
            this.liabilitiesList.addAll(criteria.list());
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
