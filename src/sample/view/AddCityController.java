package sample.view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.entity.City;
import sample.entity.dao.CityDAO;

import java.util.HashSet;

/**
 * Created by abrasha on 12/19/15.
 */
public class AddCityController {

    private Stage parent;

    @FXML
    private TextField tfCityName;
    private ObservableList<City> items;



    @FXML
    public void buttonOkClicked(){

        String cityName = tfCityName.getText();

        if (!cityName.isEmpty()){

            City added = new City();
            added.setName(cityName);
            added.setRestaurants(new HashSet<>());

            // save city
            CityDAO.getInstance().save(added);

            items.add(added);

            parent.close();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No nameprovided.");
            alert.setHeaderText("Cannot add city.");
            alert.showAndWait();

        }

    }



    @FXML
    public void buttonCancelClicked(){
        parent.close();
    }



    public void setParent(Stage parent){
        this.parent = parent;
    }



    public void setItems(ObservableList<City> items){
        this.items = items;
    }
}
