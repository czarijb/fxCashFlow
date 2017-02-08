package com.github.czarijb.controllers;

import com.github.czarijb.hibernate.utils.DAOFactory;
import com.github.czarijb.interfaces.impl.AssetsDAOImpl;
import com.github.czarijb.interfaces.impl.ExpensesDAOImpl;
import com.github.czarijb.interfaces.impl.IncomeDAOImpl;
import com.github.czarijb.interfaces.impl.LiabilitiesDAOImpl;
import com.github.czarijb.objects.Assets;
import com.github.czarijb.objects.Expenses;
import com.github.czarijb.objects.Income;
import com.github.czarijb.objects.Liabilities;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class EventDialogController {

    private static final Logger log = LoggerFactory.getLogger(EventDialogController.class);

    @FXML private RadioButton assetsBlock;
    @FXML private Label assetsNameLabel;
    @FXML private Label assetsVolumeLabel;
    @FXML private Label assetsPriceLabel;
    @FXML private TextField assetsNameTextField;
    @FXML private TextField assetsVolumeTextField;
    @FXML private TextField assetsPriceTextField;

    @FXML private RadioButton expensesBlock;
    @FXML private Label expensesNameLabel;
    @FXML private Label expensesPriceLabel;
    @FXML private TextField expensesNameTextField;
    @FXML private TextField expensesPriceTextField;

    @FXML private RadioButton incomeBlock;
    @FXML private Label incomeNameLabel;
    @FXML private Label incomePriceLabel;
    @FXML private TextField incomeNameTextField;
    @FXML private TextField incomePriceTextField;

    @FXML private RadioButton liabilitiesBlock;
    @FXML private Label liabilitiesNameLabel;
    @FXML private Label liabilitiesPriceLabel;
    @FXML private TextField liabilitiesNameTextField;
    @FXML private TextField liabilitiesPriceTextField;

    @FXML private Button createCategory;
    @FXML private Button updateCategory;
    @FXML private Button deleteCategory;

    private Assets assets;
    private Expenses expenses;
    private Income income;
    private Liabilities liabilities;



    public void initialize(){

    }

    @FXML
    public void actionClose(ActionEvent actionEvent){
        Node sourse = (Node) actionEvent.getSource();
        Stage stage = (Stage) sourse.getScene().getWindow();
        stage.hide();
    }

    public boolean checkingEmptyTextField(TextField textField) {
        boolean textFieldIsEmpty = true;
        if (textField.getText() == null)
            textFieldIsEmpty = false;

        return textFieldIsEmpty;
    }

    public boolean checkingIntInTextField(TextField textField) {
        Pattern p = Pattern.compile("^\\d+");
        Matcher m = p.matcher(textField.getText());
        return m.matches();
    }

    @FXML
    public void assetsBlockIsSelected() {

        if (assetsBlock.isSelected()) {
            log.debug("assets block is selecting for enter");
            assetsNameTextField.setEditable(true);
            assetsVolumeTextField.setEditable(true);
            assetsPriceTextField.setEditable(true);
            assetsNameTextField.setText(null);
            assetsVolumeTextField.setText(null);
            assetsPriceTextField.setText(null);
        } else {
            log.debug("assets block is unselecting for enter");
            assetsNameTextField.setEditable(false);
            assetsVolumeTextField.setEditable(false);
            assetsPriceTextField.setEditable(false);
            assetsNameTextField.setText("введите название актива");
            assetsVolumeTextField.setText("введите количество актива");
            assetsPriceTextField.setText("введите стоимость актива");
        }
    }

    @FXML
    public void expensesBlockIsSelected() {

        if (expensesBlock.isSelected()) {
            log.debug("expenses block is selecting for enter");
            expensesNameTextField.setEditable(true);
            expensesPriceTextField.setEditable(true);
            expensesNameTextField.setText(null);
            expensesPriceTextField.setText(null);
        } else {
            log.debug("expenses block is unselecting for enter");
            expensesNameTextField.setEditable(false);
            expensesPriceTextField.setEditable(false);
            expensesNameTextField.setText("введите название источника расходов");
            expensesPriceTextField.setText("введите стоимость источника расходов");
        }
    }

    @FXML
    public void incomeBlockIsSelected() {

        if (incomeBlock.isSelected()) {
            log.debug("income block is selecting for enter");
            incomeNameTextField.setEditable(true);
            incomePriceTextField.setEditable(true);
            incomeNameTextField.setText(null);
            incomePriceTextField.setText(null);
        } else {
            log.debug("income block is unselecting for enter");
            incomeNameTextField.setEditable(false);
            incomePriceTextField.setEditable(false);
            incomeNameTextField.setText("введите название источника дохода");
            incomePriceTextField.setText("введите стоимость источника дохода");
        }
    }

    @FXML
    public void liabilitiesBlockIsSelected() {

        if (liabilitiesBlock.isSelected()) {
            log.debug("liabilities block is selecting for enter");
            liabilitiesNameTextField.setEditable(true);
            liabilitiesPriceTextField.setEditable(true);
            liabilitiesNameTextField.setText(null);
            liabilitiesPriceTextField.setText(null);
        } else {
            log.debug("liabilities block is unselecting for enter");
            liabilitiesNameTextField.setEditable(false);
            liabilitiesPriceTextField.setEditable(false);
            liabilitiesNameTextField.setText("введите название пассива");
            liabilitiesPriceTextField.setText("введите стоимость пассива");
        }
    }

    public void setAssets(Assets assets) {

        if (assets == null) {
            return;
        }
        this.assets = assets;
        assetsBlock.setSelected(true);
        assetsNameTextField.setText(assets.getName());
        assetsVolumeTextField.setText(String.valueOf(assets.getVolume()));
        assetsPriceTextField.setText(String.valueOf(assets.getPrice()));
        assetsNameTextField.setEditable(true);
        assetsVolumeTextField.setEditable(true);
        assetsPriceTextField.setEditable(true);
    }

    public void setExpenses(Expenses expenses) {

        if (expenses == null) {
            return;
        }
        this.expenses = expenses;
        expensesBlock.setSelected(true);
        expensesNameTextField.setText(expenses.getName());
        expensesPriceTextField.setText(String.valueOf(expenses.getPrice()));
        expensesNameTextField.setEditable(true);
        expensesPriceTextField.setEditable(true);
    }

    public void setIncome(Income income) {

        if (income == null) {
            return;
        }
        this.income = income;
        incomeBlock.setSelected(true);
        incomeNameTextField.setText(income.getName());
        incomePriceTextField.setText(String.valueOf(income.getPrice()));
        incomeNameTextField.setEditable(true);
        incomePriceTextField.setEditable(true);
    }

    public void setLiabilities(Liabilities liabilities) {

        if (liabilities == null) {
            return;
        }
        this.liabilities = liabilities;
        liabilitiesBlock.setSelected(true);
        liabilitiesNameTextField.setText(liabilities.getName());
        liabilitiesPriceTextField.setText(String.valueOf(liabilities.getPrice()));
        liabilitiesNameTextField.setEditable(true);
        liabilitiesPriceTextField.setEditable(true);
    }

    private void createButtonForAssets() {
        if (assetsBlock.isSelected() && checkAssetsTextFieldNotEmpty() && checkIntInAssetsTextField()){
            AssetsDAOImpl assetsDAO = DAOFactory.getInstance().getAssetsDao();
            assets = new Assets(assetsNameTextField.getText(), Integer.parseInt(assetsVolumeTextField.getText()),
                    Integer.parseInt(assetsPriceTextField.getText()));
            assetsDAO.addAssets(assets);
            log.debug("create new assets");
        }
    }

    private void createButtonForExpenses() {
        if (expensesBlock.isSelected() && checkExpensesTextFieldNotEmpty() && checkIntInExpensesTextField()){
            ExpensesDAOImpl expensesDAO = DAOFactory.getInstance().getExpensesDao();
            expenses = new Expenses(expensesNameTextField.getText(), Integer.parseInt(expensesPriceTextField.getText()));
            expensesDAO.addExpenses(expenses);
            log.debug("create new expenses");
        }
    }

    private void createButtonForIncome() {
        if (incomeBlock.isSelected() && checkIncomeTextFieldNotEmpty() && checkIntInIncomeTextField()){
            IncomeDAOImpl incomeDAO = DAOFactory.getInstance().getIncomeDao();
            income = new Income(incomeNameTextField.getText(), Integer.parseInt(incomePriceTextField.getText()));
            incomeDAO.addIncome(income);
            log.debug("create new income");
        }
    }

    private void createButtonForLiabilities() {
        if (liabilitiesBlock.isSelected() && checkLiabilitiesTextFieldNotEmpty() && checkIntInLiabilitiesTextField()){
            LiabilitiesDAOImpl liabilitiesDAO = DAOFactory.getInstance().getLiabilitiesDao();
            liabilities = new Liabilities(liabilitiesNameTextField.getText(), Integer.parseInt(liabilitiesPriceTextField.getText()));
            liabilitiesDAO.addLiabilities(liabilities);
            log.debug("create new liabilities");
        }
    }

    private void updateButtonForAssets() {
        if (assetsBlock.isSelected() && checkAssetsTextFieldNotEmpty() && checkIntInAssetsTextField()){
            AssetsDAOImpl assetsDAO = DAOFactory.getInstance().getAssetsDao();
            for (Assets assets : assetsDAO.getAssetsList()){
                if (assets.getName() == assetsNameTextField.getText())
                    this.assets = assets;
            }
            assetsDAO.updateAssets(assets);
            log.debug("update assets");
        }
    }

    private void updateButtonForExpenses() {
        if (expensesBlock.isSelected() && checkExpensesTextFieldNotEmpty() && checkIntInExpensesTextField()){
            ExpensesDAOImpl expensesDAO = DAOFactory.getInstance().getExpensesDao();
            for (Expenses expenses : expensesDAO.getExpensesList()){
                if(expenses.getName() == expensesNameTextField.getText()){
                    this.expenses = expenses;
                }
            }
            expensesDAO.updateExpenses(expenses);
            log.debug("update expenses");
        }
    }

    private void updateButtonForIncome() {
        if (incomeBlock.isSelected() && checkIncomeTextFieldNotEmpty() && checkIntInIncomeTextField()){
            IncomeDAOImpl incomeDAO = DAOFactory.getInstance().getIncomeDao();
            for (Income income : incomeDAO.getIncomeList()){
                if(income.getName() == incomeNameTextField.getText()){
                    this.income = income;
                }
            }
            incomeDAO.updateIncome(income);
            log.debug("update income");
        }
    }

    private void updateButtonForLiabilities() {
        if (liabilitiesBlock.isSelected() && checkLiabilitiesTextFieldNotEmpty() && checkIntInLiabilitiesTextField()){
            LiabilitiesDAOImpl liabilitiesDAO = DAOFactory.getInstance().getLiabilitiesDao();
            for (Liabilities liabilities : liabilitiesDAO.getLiabilitiesList()){
                if(liabilities.getName() == liabilitiesNameTextField.getText()){
                    this.liabilities = liabilities;
                }
            }
            liabilitiesDAO.updateLiabilities(liabilities);
            log.debug("update liabilities");
        }
    }

    private void deleteButtonForAssets() {
        if (assetsBlock.isSelected() && checkAssetsTextFieldNotEmpty() && checkIntInAssetsTextField()){
            AssetsDAOImpl assetsDAO = DAOFactory.getInstance().getAssetsDao();
            for (Assets assets : assetsDAO.getAssetsList()){
                if (Objects.equals(assets.getName(), assetsNameTextField.getText()))
                    this.assets = assets;
            }
            assetsDAO.deleteAssets(assets);
            log.debug("delete assets");
        }
    }

    private void deleteButtonForExpenses() {
        if (expensesBlock.isSelected() && checkExpensesTextFieldNotEmpty() && checkIntInExpensesTextField()){
            ExpensesDAOImpl expensesDAO = DAOFactory.getInstance().getExpensesDao();
            for (Expenses expenses : expensesDAO.getExpensesList()){
                if(Objects.equals(expenses.getName(), expensesNameTextField.getText())){
                    this.expenses = expenses;
                }
            }
            expensesDAO.deleteExpenses(expenses);
            log.debug("delete expenses");
        }
    }

    private void deleteButtonForIncome() {
        if (incomeBlock.isSelected() && checkIncomeTextFieldNotEmpty() && checkIntInIncomeTextField()){
            IncomeDAOImpl incomeDAO = DAOFactory.getInstance().getIncomeDao();
            for (Income income : incomeDAO.getIncomeList()){
                if(Objects.equals(income.getName(), incomeNameTextField.getText())){
                    this.income = income;
                }
            }
            incomeDAO.deleteIncome(income);
            log.debug("delete income");
        }
    }

    private void deleteButtonForLiabilities() {
        if (liabilitiesBlock.isSelected() && checkLiabilitiesTextFieldNotEmpty() && checkIntInLiabilitiesTextField()){
            LiabilitiesDAOImpl liabilitiesDAO = DAOFactory.getInstance().getLiabilitiesDao();
            for (Liabilities liabilities : liabilitiesDAO.getLiabilitiesList()){
                if(Objects.equals(liabilities.getName(), liabilitiesNameTextField.getText())){
                    this.liabilities = liabilities;
                }
            }
            liabilitiesDAO.deleteLiabilities(liabilities);
            log.debug("delete liabilities");
        }
    }

    @FXML
    private void eventButtonAction(ActionEvent actionEvent){
        Object source = actionEvent.getSource();

        if(!(source instanceof Button)) return;

        Button clickedButton = (Button) source;

        Window parentWindow = ((Node) actionEvent.getSource()).getScene().getWindow();

        switch (clickedButton.getId()){
            case "createCategory" :
                createButtonForAssets();
                createButtonForExpenses();
                createButtonForIncome();
                createButtonForLiabilities();
                actionClose(actionEvent);
                break;

            case "updateCategory" :
                updateButtonForAssets();
                updateButtonForExpenses();
                updateButtonForIncome();
                updateButtonForLiabilities();
                actionClose(actionEvent);
                break;

            case "deleteCategory" :
                deleteButtonForAssets();
                deleteButtonForExpenses();
                deleteButtonForIncome();
                deleteButtonForLiabilities();
                actionClose(actionEvent);
                break;
        }
    }



    private final boolean checkAssetsTextFieldNotEmpty(){
        log.debug("check empty assets textfield ");
        boolean a = false;
        if (checkingEmptyTextField(assetsNameTextField) && checkingEmptyTextField(assetsVolumeTextField)
                && checkingEmptyTextField(assetsPriceTextField)) a = true;
        return a;
    }

    private final boolean checkExpensesTextFieldNotEmpty(){
        log.debug("check empty expenses textfield ");
        boolean a = false;
        if (checkingEmptyTextField(expensesNameTextField)
                && checkingEmptyTextField(expensesPriceTextField)) a = true;
        return a;
    }

    private final boolean checkIncomeTextFieldNotEmpty(){
        log.debug("check empty income textfield ");
        boolean a = false;
        if (checkingEmptyTextField(incomeNameTextField)
                && checkingEmptyTextField(incomePriceTextField)) a = true;
        return a;
    }

    private final boolean checkLiabilitiesTextFieldNotEmpty(){
        log.debug("check empty liabilities textfield ");
        boolean a = false;
        if (checkingEmptyTextField(liabilitiesNameTextField)
                && checkingEmptyTextField(liabilitiesPriceTextField)) a = true;
        return a;
    }

    private final boolean checkIntInAssetsTextField(){
        log.debug("check entering integer's type in assets integer's type textfield ");
        boolean a = false;
        if(checkingIntInTextField(assetsVolumeTextField) && checkingIntInTextField(assetsPriceTextField))
            a = true;
        return a;
    }

    private final boolean checkIntInExpensesTextField(){
        log.debug("check entering integer's type in expenses integer's type textfield ");
        boolean a = false;
        if(checkingIntInTextField(expensesPriceTextField))
            a = true;
        return a;
    }

    private final boolean checkIntInIncomeTextField(){
        log.debug("check entering integer's type in income integer's type textfield ");
        boolean a = false;
        if(checkingIntInTextField(incomePriceTextField))
            a = true;
        return a;
    }

    private final boolean checkIntInLiabilitiesTextField(){
        log.debug("check entering integer's type in liabilities integer's type textfield ");
        boolean a = false;
        if(checkingIntInTextField(liabilitiesPriceTextField))
            a = true;
        return a;
    }
}
