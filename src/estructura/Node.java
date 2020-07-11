/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructura;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Tania
 * @param <E>
 */
public class Node <E> extends StackPane{
    private E data;
    private int fe;
    private Node<E> left;
    private Node<E> right;
    private Text texto;
    private Circle contenido;
    
    public Node(E data) {
        this.data = data;
        this.fe=0;
        left=right=null;
    }
    private void CicleNode(){
        texto= new Text(String.valueOf(data));
        texto.setFont(Font.font(20));
        contenido.setRadius(20);
        contenido.setFill(Color.BLUE);
        super.getChildren().addAll(contenido,texto);
        super.setAlignment(Pos.CENTER);               
    }
    
    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public int getFe() {
        return fe;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }

    public Text getTexto() {
        return texto;
    }

    public void setTexto(Text texto) {
        this.texto = texto;
    }

    public Circle getContenido() {
        return contenido;
    }

    public void setContenido(Circle contenido) {
        this.contenido = contenido;
    }


    
}
