package com.wedasoft.javafxspringbootmavenapp.controller;

import com.wedasoft.javafxspringbootmavenapp.feginClient.StackOverFlowUsersBackendClient;
import com.wedasoft.javafxspringbootmavenapp.model.ExportRequest;
import com.wedasoft.javafxspringbootmavenapp.model.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class stackOverFlowController {

    private final StackOverFlowUsersBackendClient stackOverFlowUsersBackendClient;
    List<User> usersList;
    List<User> dataTable;


    public void initialize() {

        addBookmarkShowButtons();

        fxDisplayNameColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDisplay_name()));

        fxUserIdColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getUser_id()));

        fxReputation.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getReputation()));

        fxLastAccessDate.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLast_access_date()));


        usersList = stackOverFlowUsersBackendClient.getUsers();
        dataTable = usersList;


        fxTable.getItems().setAll(dataTable);

    }

    

    @FXML
    private TableColumn<User, Void> fxAction;
    @FXML
    private javafx.scene.control.TableColumn<User, String> fxDisplayNameColumn;
    @FXML
    private javafx.scene.control.TableColumn<User, Long> fxLastAccessDate;
    @FXML
    private javafx.scene.control.TableColumn<User, Long> fxReputation;
    @FXML
    private TableView<User> fxTable;
    @FXML
    private javafx.scene.control.TableColumn<User, Long> fxUserIdColumn;
    @FXML
    private CheckBox fxCheckBoxBookmarkedUsers;
    @FXML
    private Text fxAccountIdText;
    @FXML
    private Text fxDisplayNameText;
    @FXML
    private Text fxLocationText;
    @FXML
    private Text fxReputationText;
    @FXML
    private Text fxUserAgeText;
    @FXML
    private Text fxUserIdText;
    @FXML
    private Text fxUserTypeText;
    @FXML
    private Text fxViewCountText;
    @FXML
    private Text fxQuestionCountText;
    @FXML
    private Text fxAnswerCountText;
    @FXML
    private ImageView fxProfileImageView;
   








    public void export(){
        ExportRequest exportRequest = new ExportRequest(dataTable , "Normal");
        
        for (User user : dataTable) {
            long timeElapsedInSeconds = getUserAgeInTimeElapsedInSeconds(user);
            user.setUserAge(timeElapsedInSeconds);
        }
        
        stackOverFlowUsersBackendClient.exportStackOverFlowUsersFile(exportRequest);
    }
    public void exportAscending(){
        ExportRequest exportRequest = new ExportRequest(dataTable , "Ascending");
        for (User user : dataTable) {
            long timeElapsedInSeconds = getUserAgeInTimeElapsedInSeconds(user);
            user.setUserAge(timeElapsedInSeconds);
        }
        stackOverFlowUsersBackendClient.exportStackOverFlowUsersFile(exportRequest);
    }
    public void exportDescending(){
        ExportRequest exportRequest = new ExportRequest(dataTable , "Descending");
        for (User user : dataTable) {
            long timeElapsedInSeconds = getUserAgeInTimeElapsedInSeconds(user);
            user.setUserAge(timeElapsedInSeconds);
        }
        stackOverFlowUsersBackendClient.exportStackOverFlowUsersFile(exportRequest);

    }
    public void bookmarkedUser(User user){
        Long userId = user.getUser_id();
        stackOverFlowUsersBackendClient.saveBookmarkUser(userId);
    }
    public void unBookmarkedUser(User user){
        Long userId = user.getUser_id();
        stackOverFlowUsersBackendClient.deleteBookmarkUser(userId);
    }
    public void showBookmarkedUsers(){



        if(fxCheckBoxBookmarkedUsers.isSelected()){

            List<Long>  bookmarkedUsersIds =  stackOverFlowUsersBackendClient.getBookmarkedUsersIds();
            List<User>  bookmarkedUsersList = new ArrayList<>();

            for (Long bookmarkedUserId : bookmarkedUsersIds) {

                for (User user : usersList) {
                    Long userId = user.getUser_id();

                    if (bookmarkedUserId.equals(userId)) {
                        bookmarkedUsersList.add(user);
                        break;
                    }
                }
            }
            
            dataTable = bookmarkedUsersList;

            addUnmarkShowButtons();
            fxTable.getItems().setAll(dataTable);

        }else {
            addBookmarkShowButtons();
            dataTable = usersList;
            fxTable.getItems().setAll(dataTable);
        }








    }
    private void addUnmarkShowButtons() {
        fxAction.setCellFactory(col -> new TableCell<User, Void>() {

            private final Button button;
            private final Button button2;

            {
                button = new Button("Unmark");
                button.setOnAction(evt -> {
                    User item = getTableRow().getItem();
                    unBookmarkedUser(item);
                    getTableView().getItems().remove(item);
                });

                button2 = new Button("Show");
                button2.setOnAction(evt -> {
                    User item = getTableRow().getItem();
                    showUserInformation(item);

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(button, button2);
                    setGraphic(hBox);
                }
            }
        });
    }
    private void addBookmarkShowButtons() {
        fxAction.setCellFactory(col -> new TableCell<User, Void>() {

            private final Button button;
            private final Button button2;

            {
                button = new Button("Bookmark");
                button.setOnAction(evt -> {
                    User item = getTableRow().getItem();
                    bookmarkedUser(item);
                    getTableView().getItems().remove(item);
                });

                button2 = new Button("Show");
                button2.setOnAction(evt -> {
                    User item = getTableRow().getItem();
                    showUserInformation(item);

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox hBox = new HBox(button, button2);
                    setGraphic(hBox);
                }
            }
        });
    }
    private void showUserInformation(User user){

        String userAge = getUserAgeInHumanReformatted(user);
        fxUserIdText.setText(String.valueOf(user.getUser_id()));
        fxAccountIdText.setText(String.valueOf(user.getAccount_id()));
        fxDisplayNameText.setText(String.valueOf(user.getDisplay_name()));
        fxUserAgeText.setText(userAge);
        fxReputationText.setText(String.valueOf(user.getReputation()));
        fxLocationText.setText(String.valueOf(user.getLocation()));
        fxUserTypeText.setText(String.valueOf(user.getUserType()));
        fxViewCountText.setText(String.valueOf(user.getVIEW_COUNT()));
        fxQuestionCountText.setText(String.valueOf(user.getQUESTION_COUNT()));
        fxAnswerCountText.setText(String.valueOf(user.getANSWER_COUNT()));
        Image profileImage = new Image(user.getPROFILE_IMAGE());
        fxProfileImageView.setImage(profileImage);

    }

    private static String getUserAgeInHumanReformatted(User user) {
        long timeElapsedInSeconds = getUserAgeInTimeElapsedInSeconds(user);
        return (timeElapsedInSeconds / 31556926) + " years and " + (timeElapsedInSeconds % 31556926) / 2629743 + " months ago";
    }

    private static long getUserAgeInTimeElapsedInSeconds(User user) {
        long currentTimestamp =  System.currentTimeMillis()/1000;
        long userTimestamp = user.getUserAge();
        return currentTimestamp - userTimestamp;
    }


}
