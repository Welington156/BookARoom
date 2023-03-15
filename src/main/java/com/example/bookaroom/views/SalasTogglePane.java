package com.example.bookaroom.views;

import com.example.bookaroom.campus.CampusControlador;
import com.example.bookaroom.campus.Predio;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.views.widgets.SalaItem;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class SalasTogglePane extends ScrollPane {
    static class SalaToggleButton extends RadioButton {
        public Sala sala;
        public SalaToggleButton(Sala sala) {
            this.sala = sala;
        }
    }
    private final HBox prediosHBox;

    private final ToggleGroup salasToggleGroup;

    public SalasTogglePane() {
        salasToggleGroup = new ToggleGroup();
        salasToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                System.out.println("Selected: " + ((SalaToggleButton) newValue).sala.getNumero());
            }
        });

        List<Predio> predios = CampusControlador.getPredios();

        setPrefWidth(500);
        setMinHeight(400);

        prediosHBox = new HBox();

        prediosHBox.setSpacing(30);

        predios.forEach(predio -> {
            Separator separator = new Separator();
            separator.setOrientation(Orientation.VERTICAL);
            prediosHBox.getChildren()
                    .addAll(salaList(predio), separator);
        });

        setContent(prediosHBox);
    }

    private VBox salaList(Predio predio) {
        VBox vBox = new VBox();

        Label title = new Label("Predio: " + predio.getNome());
        title.getStyleClass().add("property-name");
        title.setPadding(new Insets(0, 0, 0, 10));

        vBox.getChildren().add(title);

        predio.getSalas().forEach(sala -> {
            SalaToggleButton radioButton = new SalaToggleButton(sala);
            radioButton.setToggleGroup(salasToggleGroup);
            SalaItem salaCard = new SalaItem(sala, radioButton);
            vBox.getChildren().add(salaCard);
        });

        return vBox;
    }
}
