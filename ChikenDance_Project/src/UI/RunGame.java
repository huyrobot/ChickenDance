/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import javax.swing.JFrame;

/**
 *
 * @author TuanLE
 */
public class RunGame extends JFrame {

    public static final int chieuNgangManHinh = 600;
    public static final int chieuDocManHinh = 400;
    private ManHinhGame manHinhGame;

    public RunGame() {
        super("Chicken Dance"); //tên game
        setSize(chieuNgangManHinh, 275);
        setLocation(450, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        manHinhGame = new ManHinhGame();
        addKeyListener(manHinhGame);
        add(manHinhGame);
    }

    public void startGame() {
        setVisible(true);
        manHinhGame.startGame();
    }

    public static void main(String args[]) {
        RunGame run = new RunGame();
        run.startGame();
    }
}
