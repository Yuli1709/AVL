package vista;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import estructura.AVL;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Tania
 */
public class APP extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button agregar = new Button("Agregar");        
        Button delete = new Button("Eliminar");
        
        TextField numnA = new TextField();
        TextField numnE = new TextField();
               
        AVL<Integer> arbol = new AVL<>((Integer e1,Integer e2) -> e1-e2);
        Pane contenedor = new Pane();  
        HBox hb= new HBox();
        Label labTitulo= new Label("AVL TREE");
        Label contador= new Label("Numero de elementos: "); 
        Label num= new Label();
        contador.setLayoutX(900);
        num.setLayoutX(1200);        
        labTitulo.setLayoutX(100);
        contador.setStyle("-fx-font-weight:bolder; -fx-font-size:26");
        labTitulo.setStyle("-fx-font-weight:bolder; -fx-font-size:26");
        num.setStyle("-fx-font-weight:bolder; -fx-font-size:26");
        hb.getChildren().addAll(numnA,agregar,numnE,delete);
        hb.setLayoutX(400);
        hb.setLayoutY(5);
        hb.setSpacing(20);
        contenedor.setStyle("-fx-background-color:#FFFF66");  
        contenedor.getChildren().addAll(labTitulo,hb,contador,num);
        VBox vb = new VBox(5,contenedor,arbol);
        vb.setAlignment(Pos.TOP_CENTER);
        vb.setStyle("-fx-background-color:#B0E0E6");
        	
                
        
        
       
       // ab.add(5);
        agregar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    arbol.add(Integer.parseInt(numnA.getText()));
                }catch(Exception e){
                    mensajeAlerta("Ingrese el tipo de dato correcto");
                }
                num.setText(String.valueOf(arbol.totalNodos()) );
                arbol.mostrarArbol();             
                numnA.clear();
            }
        });
    
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    arbol.remove(Integer.parseInt(numnE.getText()));
                }catch(Exception e){
                    mensajeAlerta("Ingrese el tipo de dato correcto");
                }
                num.setText(String.valueOf(arbol.totalNodos()) );
                arbol.mostrarArbol();
                numnE.clear();
            }
        });
                        
        Scene scene = new Scene(vb,Constantes.MAX_WIDTH,Constantes.MAX_HEIGHT);
        scene.getStylesheets().add("vista/hojaStilo.css");
        primaryStage.setTitle("Arbol AVL");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void mensajeAlerta(String name){
        Alert alerta= new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Ventana de Alerta");
        alerta.setHeaderText(null);
        alerta.setContentText(name);
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}