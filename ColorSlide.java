import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ColorSlide extends Application {

    private BorderPane pane = new BorderPane();
    private GridPane gridPane = new GridPane();
    private Slider redSlider = new Slider();
    private Slider greenSlider = new Slider();
    private Slider blueSlider = new Slider();
    private int red = 50, green = 150, blue = 225, radius = 100;
    private Slider radiusSlider = new Slider();
    private Button pulseButton = new Button("Pulse");
    private Button stopPulseButton = new Button("Stop Pulse");
    private boolean growing = true;

    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage){
        
        gridPane.setHgap(7);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(new Label("Red"), 0, 0);
        gridPane.add(redSlider, 1, 0);
        gridPane.add(new Label("Green"), 0, 1);
        gridPane.add(greenSlider, 1, 1);
        gridPane.add(new Label("Blue"), 0, 2);
        gridPane.add(blueSlider, 1, 2);
        gridPane.add(new Label("Size"), 0, 3);
        gridPane.add(radiusSlider, 1, 3);
        gridPane.add(pulseButton, 0, 4);
        gridPane.add(stopPulseButton, 1, 4);


        Circle circle = new Circle(500, 400, radius);
        circle.setFill(Color.rgb(red, green, blue));

        pane.setPrefSize(1000, 1000);
        pane.getChildren().addAll(circle);
        pane.setBottom(gridPane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();

        redSlider.setMax(255);
        greenSlider.setMax(255);
        blueSlider.setMax(255);
        radiusSlider.setMax(1000);

        redSlider.setValue(red);
        greenSlider.setValue(green);
        blueSlider.setValue(blue);
        radiusSlider.setValue(radius);

        redSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                red = (int)redSlider.getValue();
                circle.setFill(Color.rgb(red, green, blue));
            }
        });

        greenSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                green = (int)greenSlider.getValue();
                circle.setFill(Color.rgb(red, green, blue));
            }
        });

        blueSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                blue = (int)blueSlider.getValue();
                circle.setFill(Color.rgb(red, green, blue));
            }
        });

        radiusSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                radius = (int)radiusSlider.getValue();
                circle.setRadius(radius);
            }
        });



        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), e -> {

            if(radius <= 100)
                growing = true;

            if(radius >= 300)
                growing = false;

            if(growing)
                radius++;

            else
                radius--;

            circle.setRadius(radius);
            radiusSlider.adjustValue(radius);
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

        pulseButton.setOnAction(event -> {
            timeline.play();
        });

        stopPulseButton.setOnAction(event -> {
            timeline.stop();
        });



    }


}
