package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean arriba,abajo,izquierda,derecha;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Codigo de la tecla
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_W:
                arriba = true;
                break;
            case KeyEvent.VK_A:
                izquierda = true;
                break;
            case KeyEvent.VK_S:
                abajo = true;
                break;
            case KeyEvent.VK_D:
                derecha = true;
                break;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_W:
                arriba = false;
                break;
            case KeyEvent.VK_A:
                izquierda = false;
                break;
            case KeyEvent.VK_S:
                abajo = false;
                break;
            case KeyEvent.VK_D:
                derecha = false;
                break;
        }
    }
}
