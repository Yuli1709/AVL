/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructura;

import java.util.Comparator;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

/**
 *
 * @author Tania
 */
public class AVL <E> extends Pane{ //arbol binario de Buqueda
    //los elementos mayores estan a la derecha y los elementos menores estan a la izquierda
    
    private Node<E> root;
    private Comparator<E> f;
    private double radius = 15;
    private double vGap = 50;   
    
    
    public AVL(Comparator<E> f) {
        this.f=f;
        this.root=null;
    }
    
        public void mostrarArbol(){
        this.getChildren().clear();
        if(this.getRoot()!=null){
            showArbol(root, getWidth() / 2, vGap, getWidth() / 4, Color.MEDIUMPURPLE);
        }
    }
    private void showArbol(Node root, double x, double y, double hGap, Color color){
        if(root.getLeft() != null){
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            showArbol(root.getLeft(), x - hGap, y + vGap, hGap / 2,color);
        }

        if (root.getRight() != null){
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            showArbol(root.getRight(), x + hGap, y + vGap, hGap / 2, color);
        }
        Circle circle = new Circle(x, y, 20);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle, new Text(x - 4, y + 4, root.getData() + ""));
    
    }
    
    
    public Node getRoot() {
        return root;
    }
    public boolean isEmpty(){
        return root==null;
    }
 
    public int height(){
        return height(root);
    }
    
    private int height(Node<E> n){
        if(n==null) return 0;
        return 1+ Math.max(height(n.getLeft()),height(n.getRight()));
    }
    
    public int totalNodos(){
        return totalNodos(root);
    }
    
    private int totalNodos(Node<E> n){
        if (n==null) return 0;
        return 1 + totalNodos(n.getLeft())+ totalNodos(n.getRight());
    }
    
    public int contarHojas(){
        return contarHojas(root);
    }
    private int contarHojas(Node<E> n){
        if(n==null) return 0;
        else if(n.getLeft()==null&& n.getRight()==null){
            return 1;
        }return contarHojas(n.getLeft())+ contarHojas(n.getRight());
    }
    
    public boolean add(E element){
        if(element==null)return false;        
        this.root=add(element,root);        
        return true;
    }
    
    private Node<E> add(E element, Node<E> n){
        if(n==null){
            n= new Node<>(element);
        }else if(f.compare(element, n.getData())>0){
            n.setRight(add(element,n.getRight()));           
        }else if(f.compare(element, n.getData())<0){
            n.setLeft(add(element,n.getLeft()));
        }
        
        actualizar(n);
        return balance(n);
    }
    
    private void actualizar(Node<E> node){
        node.setFe(height(node.getRight())-height(node.getLeft()));
    }
    
    private Node<E> balance(Node node){
        if(node.getFe()== -2){
            if(node.getLeft().getFe() <=0)
                return leftLeftCase(node);
            else return leftRightCase(node);
            
        }else if(node.getFe()== +2){
            if(node.getRight().getFe() >=0)
                return rightRightCase(node);
            else return rightLeftCase(node);
        }
        
        return node;
    }
    
    private Node leftLeftCase(Node<E> node){
        return rightRotation(node);        
    }
    
    private Node leftRightCase(Node<E> node){
        node.setLeft(leftRotation(node.getLeft()));
        return  leftLeftCase(node);
        
    }
    private Node rightRightCase(Node<E> node){
        return leftRotation(node);
        
    }
    private Node rightLeftCase(Node<E> node){
        node.setRight(rightRotation(node.getRight()));
        return rightRightCase(node);
        
    }
    
    private Node leftRotation(Node<E> n){
        Node<E> padre= n.getRight();
         n.setRight(padre.getLeft());
        padre.setLeft(n);
        actualizar(n);
        actualizar(padre);
        return padre;        
    }
    
    private Node rightRotation(Node<E> n){
        Node<E> padre=n.getLeft();        
         n.setLeft(padre.getRight());
        padre.setRight(n);
        actualizar(n);
        actualizar(padre);
        return padre; 
    }            
    
        public E max(){
        return max(root);
    }
    private E max(Node<E> n){
        if(n==null)return null;
        else if(n.getRight()==null){
            return n.getData();
        }else
            return max(n.getRight());
    }
    
    public E min(){
        return min(root);
    }
    private E min(Node<E> n){
        if(n==null)return null;
        else if(n.getLeft()==null){
            return n.getData();
        }else
            return min(n.getLeft());
    }
    
    public boolean remove(E element){
        if(element==null|| isEmpty()) return false;
        root=remove(element,root);
        return true;
    }
    
    private Node<E> remove(E element, Node<E> n){
        if(n==null) return n;
        else if(f.compare(element, n.getData())>0){
            n.setRight(remove(element,n.getRight()));
        }else if(f.compare(element, n.getData())<0){
            n.setLeft(remove(element,n.getLeft()));
        }else{
            if(n.getLeft()==null){
                return n.getRight();
            }else if(n.getRight()==null){
                return n.getLeft();                
            }else{
                if(height(n.getLeft())>height(n.getRight())){
                    E newdata= findMax(n.getLeft());
                    n.setData(newdata);
                    n.setLeft(remove(newdata,n.getLeft()));
                }else{
                    E newdata= findMin(n.getRight());
                    n.setData(newdata);
                    n.setRight(remove(newdata,n.getRight()));  
                }
            }   
        }
        actualizar(n);
        return balance(n);
    }
    
    
    private E findMax(Node<E> n){
        while(n.getRight()!= null){
            n=n.getRight();
        }
        return n.getData();
    }
    
    private E findMin(Node<E> n){
        while(n.getLeft()!= null){
            n=n.getLeft();
        }
        return n.getData();
    }
    
    public boolean contains(E element){
        if(element==null|| isEmpty()) return false;
        return contains(element,root);
         
    }
    
    private boolean contains(E element, Node<E> n){
        if(n==null) return false;
        else if(f.compare(element,n.getData()) == 0)
            return true;
        else if(f.compare(element, n.getData())>0){
            contains(element,n.getRight());
        }else if(f.compare(element, n.getData())<0){
            contains(element,n.getLeft());
        }return true;
    }
    
    public void posOrde(){
        posOrden(root);
    }
    private void posOrden(Node<E> n){
        if(n!=null){
            posOrden(n.getLeft());
            posOrden(n.getRight());
            System.out.println(n.getData());
        }
    }
     
    public void preOrden(){
        preOrden(root);
    }
    private void preOrden(Node<E> n){
        if(n!=null){
            System.out.println(n.getData());
            preOrden(n.getLeft());
            preOrden(n.getRight());           
        }
    }
    
    public void enOrden(){
        enOrden(root);
    }
    private void enOrden(Node<E> n){
        if(n!=null){
            enOrden(n.getLeft());
            System.out.println(n.getData());            
            enOrden(n.getRight());            
        }
    }
    
    public AVL<E> mirror(){
        AVL<E> tree = new AVL<>(this.f);
        tree.root = mirror(this.root);
        return tree;
    }
    private Node<E> mirror(Node<E> n) {
        if(n==null)
            return n;        
        if (n.getData().equals(this.root.getData()) || (n.getRight() != null && n.getLeft() != null)) {
            return invert(n);
        }
        return new Node<>(n.getData());
    }
    
    private Node<E> invert(Node<E> n) {
        Node<E> r = new Node<>(n.getData());
        r.setRight(mirror(n.getLeft()));
        r.setLeft(mirror(n.getRight()));
        return r;
    }
    
    public int nivel(E element){
        if (element==null|| isEmpty()|| this.contains(element)) return -1;
        return nivel(element, root);
    }
    
    private int nivel(E element, Node<E> n){
        int num=height()-1;
        if(n==null) return -1;      
        else if(f.compare(element,n.getData()) == 0){
            return num;
        }else if(f.compare(element, n.getData())>0){
            return nivel(element,n.getRight())-1;            
        }else if(f.compare(element, n.getData())<0){           
            return nivel(element,n.getLeft())-1;
        }return -1;
    }
        
    @Override
    public boolean equals(Object obj) {
        if (this == null || obj == null) 
            return false;
        else if (!(this instanceof AVL) || !(obj instanceof AVL)){ 
            return false;
        }
        final AVL<E> other = (AVL<E>) obj;
        return equals(this.root,other.root);    
    }
    private boolean equals(Node n1, Node n2){
        if(n1 == null && n2 == null)
            return true;
        else if((n1 == null && n2 != null) || (n1 != null && n2 == null))
            return false;
        else if(!n1.getData().equals(n2.getData())) {
            return false;
        }

        return equals(n1.getLeft(), n2.getLeft()) && equals(n1.getRight(), n2.getRight());
    } 

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
