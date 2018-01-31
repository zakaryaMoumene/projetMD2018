package angryBirds;

// est utilisé pour mettre à jour l'affichage après qu'un traitement ait été fait dans le jeu
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
