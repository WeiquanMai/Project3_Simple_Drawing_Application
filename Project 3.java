/*
 * CSC-239 Project 2: Simple Drawing Application
 * Student: Weiquan Mai
 * Date: April 28, 2025
 * Description: Pressing arrow keys draws a line on the screen corresponding to
 * the direction of the arrow key. Pressing shift + arrow key moves the
 * position, but does not draw. Pressing DELETE key removes all lines on the screen.
 * 
 */

 // Imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Project3 extends Application{
    // Hold location of lastX and last Y coordinate
    private double lastX;
    private double lastY;

    @Override // // Override the start method in the Application class
    public void start (Stage primaryStage){
        // Create a pane
        Pane pane = new Pane();
        
        // Create a scene and place it on the stage
        Scene scene = new Scene(pane, 400, 400);
        primaryStage.setTitle("CSC-239 Project3");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set lastX and lastY coordinate to middle of pane;
        lastX = scene.getWidth()/2.0;
        lastY = scene.getHeight()/2.0;

        // Used to receive keyboard input
        pane.requestFocus();

        pane.setOnKeyPressed(e->{
            // Change newX and newY values based on arrowkey pressed
            double newX = lastX;
            double newY = lastY;

            switch (e.getCode()){
                case DOWN:
                    newY += 10;
                    break;
                case UP:
                    newY -= 10;
                    break;
                case LEFT:
                    newX -= 10;
                    break;
                case RIGHT:
                    newX += 10;
                    break;
                // If DELETE key is pressed, clear the board
                case DELETE:
                    pane.getChildren().clear();
                    break;
                default:
                    break; // Ignore other keys
            }
            // If shift key is pressed, simply move the coordinates
            if (e.isShiftDown()){
                lastX = newX;
                lastY = newY;
            // Else, draw add a line
            } else{
                addLine(pane,lastX,lastY,newX,newY);
                lastX = newX;
                lastY = newY; 
            }
        });
    }
    
    // Draw a line based on specified information and then put line into getChildren
    private void addLine(Pane pane, double startX, double startY, double endX, double endY){
        
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(Color.BLACK);
        pane.getChildren().add(line);
    }

    public static void main(String[] args){
        launch(args);
    }
}
