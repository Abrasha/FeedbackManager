package com.aabramov.view;

import com.aabramov.entity.Feedback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Andrii Abramov on 12/18/15.
 */
public class FeedbackOverviewController implements Initializable {
    
    private Feedback feedback;
    private Stage owner;
    
    
    @FXML
    private Label lblTitle;
    @FXML
    private Label lblBody;
    @FXML
    private Label lblAuthor;
    @FXML
    private Label lblDate;
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    
    private void updateFeedback() {
        lblTitle.setText(feedback.getTitle());
        lblBody.setText(feedback.getBody());
        lblDate.setText(feedback.getDate().toString());
        lblAuthor.setText(feedback.getAuthor());
    }
    
    public Feedback getFeedback() {
        return feedback;
    }
    
    public void setFeedback(Feedback feedback) {
        this.feedback = feedback;
        updateFeedback();
    }
    
    public void setStage(Stage owner) {
        this.owner = owner;
    }
}
