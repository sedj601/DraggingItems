/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draggingitems;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author blj0011
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private AnchorPane apMain;
    
    static double x,y;
    static boolean isTransitionPlaying = false;  
    
    ArrayList<Rectangle2D> viewportContainer = new ArrayList();
    final ArrayList<ImageView> deck = new ArrayList();
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {      
        String suit[] = {"spades", "diamonds", "clubs", "hearts"};
        String faceValue[] = {"ace", "2","3","4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};
    
        for(int i = 0; i < 4; i++)
        {
            for(int ii = 0; ii < 13; ii++)
            {
                //System.out.println(faceValue[ii] + "_of_" + suit[i]);
                deck.add(new ImageView(new Image("images/deck/" + faceValue[ii] + "_of_" + suit[i] + ".png")));              
            }         
        }
        
        //System.out.println(apMain.getPrefWidth());
        //System.out.println(apMain.getPrefWidth() / 2 + "  " + apMain.getPrefHeight() / 2);
        for(int i = 0; i < 52; i++)
        {
            Integer intI = i;
            
            
            deck.get(i).setLayoutX(apMain.getPrefWidth() / 2 - (31));//make this generic for any size card (31) needs to be change
            deck.get(i).setLayoutY(apMain.getPrefHeight() / 2 - (45));//make this generic for any size card (45) needs to be change
            deck.get(i).setFitHeight(90);
            deck.get(i).setFitWidth(62);            
            deck.get(i).setOnMouseEntered((MouseEvent event) -> {apMain.setCursor(Cursor.HAND);});          
            deck.get(i).setOnMouseEntered((MouseEvent event) -> {apMain.setCursor(Cursor.HAND);});           
            deck.get(i).setOnMouseEntered((MouseEvent event) -> {apMain.setCursor(Cursor.HAND);});
            deck.get(i).setOnMouseReleased((MouseEvent event) -> {apMain.setCursor(Cursor.HAND);});
            deck.get(i).setOnMouseExited((MouseEvent event) -> {apMain.setCursor(Cursor.DEFAULT);});
            deck.get(i).setOnMousePressed((MouseEvent event) -> {
                apMain.setCursor(Cursor.CLOSED_HAND);
                boxingX(deck.get(intI).getLayoutX() - event.getSceneX());
                boxingY(deck.get(intI).getLayoutY() - event.getSceneY());
                ((ImageView)(event.getSource())).toFront();   
            });         
            deck.get(i).setOnMouseDragged((MouseEvent event) -> {
                deck.get(intI).setLayoutX(event.getSceneX() + gettingX());
                deck.get(intI).setLayoutY(event.getSceneY() + gettingY());
            });
        }  
        apMain.getChildren().addAll(deck);  
        
    
        
        //Path path = new Path();
        //path.getElements().add(new MoveTo(50,50));
        //path.getElements().add(new MoveTo(100, 100));
        //path.getElements().add(new MoveTo(200, 200));
        //path.getElements().add(new CubicCurveTo(380, 0, 380, 120, 200, 120));
        //path.getElements().add(new CubicCurveTo(0, 120, 0, 240, 380, 240));
        
        int counter = 400;
        
        Timeline timeline = new Timeline();
        for(int i = 0; i < 20; i++)
        {  
            int a = i % 4;
            switch(a)
            {
                case 0:
                   timeline.getKeyFrames().addAll(
                    new KeyFrame(new Duration(i * counter),
                        new KeyValue(deck.get(i).translateXProperty(), 0),
                        new KeyValue(deck.get(i).translateYProperty(), 0)),
                    new KeyFrame(new Duration((i + 1) * counter),
                        new KeyValue(deck.get(i).translateXProperty(), 200),
                        new KeyValue(deck.get(i).translateYProperty(), 0))
                    ); 
                   break;
                case 1:
                   timeline.getKeyFrames().addAll(
                    new KeyFrame(new Duration(i * counter),
                        new KeyValue(deck.get(i).translateXProperty(), 0),
                        new KeyValue(deck.get(i).translateYProperty(), 0)),
                    new KeyFrame(new Duration((i + 1) * counter),
                        new KeyValue(deck.get(i).translateXProperty(), 0),
                        new KeyValue(deck.get(i).translateYProperty(), 150))
                    ); 
                   break;
                case 2:
                   timeline.getKeyFrames().addAll(
                    new KeyFrame(new Duration(i * counter),
                        new KeyValue(deck.get(i).translateXProperty(), 0),
                        new KeyValue(deck.get(i).translateYProperty(), 0)),
                    new KeyFrame(new Duration((i + 1) * counter),
                        new KeyValue(deck.get(i).translateXProperty(), -200),
                        new KeyValue(deck.get(i).translateYProperty(), 0))
                    ); 
                   break;
                case 3:
                   timeline.getKeyFrames().addAll(
                    new KeyFrame(new Duration(i * counter),
                        new KeyValue(deck.get(i).translateXProperty(), 0),
                        new KeyValue(deck.get(i).translateYProperty(), 0)),
                    new KeyFrame(new Duration((i + 1) * counter),
                        new KeyValue(deck.get(i).translateXProperty(), 0),
                        new KeyValue(deck.get(i).translateYProperty(), -150))
                    ); 
                   break;
            } 
        }        
       timeline.play();
       
       
       System.out.println(deck.size());
       
       
       
      // playAnimationAndWaitForFinish();
    }    
    
    
    static void boxingX(double a)
    {
        x = a;
    }
    
    static void boxingY(double a)
    {
        y = a;
    }
    static double gettingX()
    {
        return x;
    }
    static double gettingY()
    {
        return y;
    }
    
    
    static void boxingBoolean(boolean a)
    {
        isTransitionPlaying = a;  
    }
    
    static boolean gettingBoolean()
    {
        return isTransitionPlaying;  
    }
    
    private synchronized void playAnimationAndWaitForFinish(final Animation animation) {
    if (Platform.isFxApplicationThread()) {
        throw new IllegalThreadStateException("Cannot be executed on main JavaFX thread");
    }
    final Thread currentThread = Thread.currentThread();
    final EventHandler<ActionEvent> originalOnFinished = animation.getOnFinished();
    animation.setOnFinished(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            if (originalOnFinished != null) {
                originalOnFinished.handle(event);
            }
            synchronized (currentThread) {
                currentThread.notify();
            }
        }
    });
    Platform.runLater(new Runnable() {

        @Override
        public void run() {
            animation.play();
        }
    });
    synchronized (currentThread) {
        try {
            currentThread.wait();
        } catch (InterruptedException ex) {
            //somebody interrupted me, OK
        }
    }
}
}
