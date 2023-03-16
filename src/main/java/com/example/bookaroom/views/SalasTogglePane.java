package com.example.bookaroom.views;

import com.example.bookaroom.campus.Predio;
import com.example.bookaroom.campus.Sala;
import com.example.bookaroom.views.widgets.SalaItem;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;

public class SalasTogglePane extends ScrollPane {
    static class SalaToggleButton extends RadioButton {
        public Sala sala;
        public SalaToggleButton(Sala sala) {
            this.sala = sala;
        }
    }

    HashMap<Sala, SalaItem> toggleItems = new HashMap<>();

    private final ToggleGroup salasToggleGroup;

    public SalasTogglePane(List<Predio> predios) {
        salasToggleGroup = new ToggleGroup();

        setPrefWidth(500);
        setMinHeight(400);

        HBox prediosHBox = new HBox();
        prediosHBox.setSpacing(30);

        predios.forEach(predio -> {
            Separator separator = new Separator();
            separator.setOrientation(Orientation.VERTICAL);
            prediosHBox.getChildren()
                    .addAll(salaListVBox(predio), separator);
        });

        setContent(prediosHBox);
    }


    public void filter(HashMap<Sala, Boolean> salasOcupacao) {
        toggleItems.forEach((sala, salaItem) -> {
            salaItem.setDisponibilidade(salasOcupacao.get(sala));
        });
    }

    private VBox salaListVBox(Predio predio) {
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
            toggleItems.put(sala, salaCard);
        });

        return vBox;
    }

    public SalaItem getSelected() {
        RadioButton selected = (RadioButton)salasToggleGroup.getSelectedToggle();
        if (selected == null) return null;

        return (SalaItem) selected.getParent();
    }

    public Sala getSalaSelecionada() {
        SalaToggleButton selected = (SalaToggleButton)salasToggleGroup.getSelectedToggle();
        if(selected == null) return null;

        return selected.sala;
    }
}
