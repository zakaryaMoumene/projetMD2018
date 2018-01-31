package angryBirds;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


// classe principale qui va permettre de lancer le jeu
public class AngryBirds {

    public static void main(String[] args) {
        Frame frame = new Frame("Oiseau pas content");
        final Window obj = new Window();
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });
        frame.add(obj);
        frame.pack();
        frame.setVisible(true);

    }

}
