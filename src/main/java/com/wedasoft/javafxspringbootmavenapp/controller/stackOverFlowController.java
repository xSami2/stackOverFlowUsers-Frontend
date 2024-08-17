package com.wedasoft.javafxspringbootmavenapp.controller;

import com.wedasoft.javafxspringbootmavenapp.feginClient.StackOverFlowUsersBackendClient;
import com.wedasoft.javafxspringbootmavenapp.model.ExportRequest;
import com.wedasoft.javafxspringbootmavenapp.model.User;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequiredArgsConstructor
public class stackOverFlowController {

    private final StackOverFlowUsersBackendClient stackOverFlowUsersBackendClient;
    User[] usersList = new User[30];
    List<User> dataTable;




    public void initialize() {

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
        fxDisplayNameColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getDisplayName()));

        fxUserIdColumn.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getUserId()));

        fxReputation.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getReputation()));

        fxLastAccessDate.setCellValueFactory(cellData ->
                new SimpleObjectProperty<>(cellData.getValue().getLast_access_date()));


        usersList = stackOverFlowUsersBackendClient.getUsers();
        dataTable = List.of(usersList);

        fxTable.getItems().setAll(dataTable);

    }


    @FXML
    private TableColumn<User, Void> fxAction;

    @FXML
    private AnchorPane fxAncorPane;

    @FXML
    private MenuItem fxAscending;

    @FXML
    private MenuItem fxDescending;

    @FXML
    private javafx.scene.control.TableColumn<User, String> fxDisplayNameColumn;

    @FXML
    private SplitMenuButton fxExport;

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
    private Label fxAccountIdLabel;

    @FXML
    private Text fxAccountIdText;

    @FXML
    private Label fxDisplayNameLabel;

    @FXML
    private Text fxDisplayNameText;

    @FXML
    private Label fxLocationLabel;

    @FXML
    private Text fxLocationText;

    @FXML
    private Label fxReputationLabel;

    @FXML
    private Text fxReputationText;

    @FXML
    private Label fxUserAgeLabel;

    @FXML
    private Text fxUserAgeText;

    @FXML
    private Label fxUserIdLabel;

    @FXML
    private Text fxUserIdText;

    @FXML
    private GridPane fxUserInfoGridPane;

    @FXML
    private Label fxUserTypeLabel;

    @FXML
    private Text fxUserTypeText;

    @FXML
    private Label fxViewCountLabel;

    @FXML
    private Text fxViewCountText;

    @FXML
    private Label FXQuestionCountLabel;
    @FXML
    private Text fxQuestionCountText;

    @FXML
    private Label fxAnswerCountLabel;

    @FXML
    private Text fxAnswerCountText;

    @FXML
    private Label fxProfileImageLabel;

    @FXML
    private Text fxProfileImageText;
    @FXML
    private ImageView fxProfileImageView;








    public void export(){
        ExportRequest exportRequest = new ExportRequest(dataTable , "Normal");
        stackOverFlowUsersBackendClient.exportStackOverFlowUsersFile(exportRequest);
    }

    public void exportAscending(){
        ExportRequest exportRequest = new ExportRequest(dataTable , "Ascending");
        stackOverFlowUsersBackendClient.exportStackOverFlowUsersFile(exportRequest);
    }

    public void exportDescending(){
        ExportRequest exportRequest = new ExportRequest(dataTable , "Descending");
        stackOverFlowUsersBackendClient.exportStackOverFlowUsersFile(exportRequest);

    }

    public void bookmarkedUser(User user){
        System.out.println(user.getUserId());
        Long userId = user.getUserId();
        stackOverFlowUsersBackendClient.saveBookmarkUser(userId);
    }

    public void unBookmarkedUser(User user){
        System.out.println(user.getUserId());
        Long userId = user.getUserId();
        stackOverFlowUsersBackendClient.deleteBookmarkUser(userId);
    }

    public void showBookmarkedUsers(){



        if(fxCheckBoxBookmarkedUsers.isSelected()){

            List<Long>  bookmarkedUsersIds =  stackOverFlowUsersBackendClient.getBookmarkedUsersIds();
            List<User>  bookmarkedUsersList = new ArrayList<>();

            for (Long bookmarkedUserId : bookmarkedUsersIds) {

                for (User user : usersList) {
                    Long userId = user.getUserId();

                    if (bookmarkedUserId.equals(userId)) {
                        bookmarkedUsersList.add(user);
                        break;
                    }
                }
            }
            dataTable = bookmarkedUsersList;

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
            fxTable.getItems().setAll(dataTable);

        }else {
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

            dataTable = List.of(usersList);
            fxTable.getItems().setAll(dataTable);
        }








    }

    public void showUserInformation(User user){


        long currentTimestamp =  System.currentTimeMillis()/1000;
        long userTimestamp = user.getCreation_date();
        long timeElapsedInSeconds = currentTimestamp - userTimestamp;
        String userAge = (timeElapsedInSeconds / 31556926) + " years and " + (timeElapsedInSeconds % 31556926) / 2629743 + " months ago";


        System.out.println(user);
        fxUserIdText.setText(String.valueOf(user.getUserId()));
        fxAccountIdText.setText(String.valueOf(user.getAccount_id()));
        fxDisplayNameText.setText(String.valueOf(user.getDisplay_name()));
        fxUserAgeText.setText(userAge);
        fxReputationText.setText(String.valueOf(user.getReputation()));
        fxLocationText.setText(String.valueOf(user.getLocation()));
        fxUserTypeText.setText(String.valueOf(user.getUser_type()));
        fxViewCountText.setText(String.valueOf(user.getView_count()));
        fxQuestionCountText.setText(String.valueOf(user.getQuestion_count()));
        fxAnswerCountText.setText(String.valueOf(user.getAnswer_count()));
        System.out.println(user.getProfile_image());
        Image profileImage = new Image(user.getProfile_image());
        fxProfileImageView.setImage(profileImage);

    }


}
