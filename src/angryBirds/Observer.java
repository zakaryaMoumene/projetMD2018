package angryBirds;

// est utilis� pour mettre � jour l'affichage apr�s qu'un traitement ait �t� fait dans le jeu
public class Observer {

    private Window w;

    public Observer(Window w) {
        super();
        this.w = w;
    }

    public void refreshWindow() {
        w.repaint();
    }
}
