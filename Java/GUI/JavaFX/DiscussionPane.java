/**
 * @author Joe Baskin
 * @version 2.0
 */

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class DiscussionPane extends Pane {
    private TextField tfWeight; // Male and Female
    private TextField tfNaval; // Male and Female
    private TextField tfWrist; // Female only
    private TextField tfHip; // Female only
    private TextField tfForearm; // Female only
    private TextField tfOutput;
    private String gender;
    private VBox maleBox;
    private VBox femaleBox;

    public DiscussionPane() {
        setPrefSize(400, 500);

        // Fill the graph background color.
        BackgroundFill bgf = new BackgroundFill(Color.valueOf("fdfdfd"), null,
                getInsets());
        Background bg = new Background(bgf);
        setBackground(bg);

        Label lblGender = new Label("Gender: ");
        ComboBox<String> cbGender = new ComboBox<>();
        cbGender.getItems().addAll("Male", "Female");
        cbGender.setValue("Male");

        HBox topBox = new HBox(10, lblGender, cbGender);
        topBox.setLayoutX(120);
        topBox.setLayoutY(5);
        topBox.setAlignment(Pos.CENTER);
        getChildren().addAll(topBox);

        maleBox = new VBox(5);
        femaleBox = new VBox(5);
        getChildren().add(maleBox);
        updateUI("Male");

        cbGender.setOnAction(e -> {
            gender = cbGender.getValue();
            updateUI(gender);
        });

        VBox bottomBox = new VBox(5);
        bottomBox.setAlignment(Pos.CENTER);

        Label lblOutput = new Label("Calculated Body Fat: ");
        tfOutput = new TextField();
        tfOutput.setEditable(false);
        tfOutput.setPrefSize(300, 25);

        HBox btnBox = new HBox(3);
        Button calculateBtn = new Button("Calculate");
        Button clearBtn = new Button("Clear");
        Button exitBtn = new Button("Exit");

        btnBox.getChildren().addAll(calculateBtn, clearBtn, exitBtn);
        btnBox.setAlignment(Pos.CENTER);

        bottomBox.getChildren().addAll(lblOutput, tfOutput, btnBox);

        bottomBox.setLayoutX(50);
        bottomBox.setLayoutY(400);

        getChildren().addAll(bottomBox);

        calculateBtn.setOnMouseClicked(this::calculate);
        clearBtn.setOnMouseClicked(this::clear);
        exitBtn.setOnMouseClicked(this::exit);
    }

    private void updateUI(String uiGender) {
        getChildren().removeAll(maleBox, femaleBox);

        Label lblWeight = new Label("Please enter your weight (in pounds): [e.g. 201.4]:");
        tfWeight = new TextField();
        Label lblNaval = new Label("Please enter your waist measurement at the naval (in inches) [e.g. 37.6]:");
        tfNaval = new TextField();

        if (uiGender.equals("Male")) {
            femaleBox.getChildren().removeAll();
            maleBox.getChildren().clear();

            maleBox.getChildren().addAll(lblWeight, tfWeight, lblNaval, tfNaval);
            maleBox.setLayoutX(5);
            maleBox.setLayoutY(50);
            getChildren().addAll(maleBox);

            gender = "Male";
        } else if (uiGender.equals("Female")) {
            maleBox.getChildren().removeAll();
            femaleBox.getChildren().clear();

            Label lblWrist = new Label("Please enter your Wrist measurement (in inches) [e.g. 8.6]: ");
            Label lblHip = new Label("Please enter your hip measurement (in inches) [e.g. 22.5]: ");
            Label lblForearm = new Label("Please enter your forearm measurement (in inches) [e.g. 5.83]: ");
            tfWrist = new TextField();
            tfHip = new TextField();
            tfForearm = new TextField();

            femaleBox.getChildren().addAll(lblWeight, tfWeight, lblNaval, tfNaval, lblWrist, tfWrist,
                    lblHip, tfHip, lblForearm, tfForearm);
            femaleBox.setLayoutX(5);
            femaleBox.setLayoutY(50);
            getChildren().addAll(femaleBox);

            gender = "Female";
        }
    }

    private void calculate(MouseEvent e) throws NumberFormatException {
        if (gender == null) {
            tfOutput.setText("Error: Gender is null!");
        }

        double weight = Double.parseDouble(tfWeight.getText());
        double naval = Double.parseDouble(tfNaval.getText());
        if (gender.equals("Male")) {
            try {
                double factor1 = (weight * 1.082) + 94.42;
                double factor2 = naval * 4.15;
                double leanBodyMass = factor1 - factor2;
                double bodyFatWeight = weight - leanBodyMass;
                double bodyFatPercent = (bodyFatWeight * 100) / weight;

                tfOutput.setText((int)(bodyFatPercent * 100) / 100.0 + "%");
            } catch (NumberFormatException nfe) {
                tfOutput.setText("Error: One or more fields are empty!");
            }
        } else if (gender.equals("Female")) {
            double wrist = Double.parseDouble(tfWrist.getText());
            double hips = Double.parseDouble(tfHip.getText());
            double forearm = Double.parseDouble(tfForearm.getText());

            double factor1 = (weight * 0.732) + 8.987;
            double factor2 = wrist / 3.140;
            double factor3 = naval * 0.157;
            double factor4 = hips * 0.249;
            double factor5 = forearm * 0.434;
            double leanBodyMass = factor1 + factor2 - factor3 - factor4 + factor5;
            double bodyFatWeight = weight - leanBodyMass;
            double bodyFatPercent = (bodyFatWeight * 100) / weight;

            tfOutput.setText((int)(bodyFatPercent * 100) / 100.0 + "%");
        }
    }

    private void clear(MouseEvent e) throws NumberFormatException {
        tfWeight.setText("");
        tfNaval.setText("");

        if (gender.equals("Female")) {
            tfWrist.setText("");
            tfHip.setText("");
            tfForearm.setText("");
        }

        tfOutput.setText("");
    }

    private void exit(MouseEvent e) {
        System.exit(0);
    }
}
