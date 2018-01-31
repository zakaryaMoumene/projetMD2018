package angryBirds;

public class Observer {

    private Window w;
    
    public Observer(Window w) {
        super();
        this.w = w;
    }

    public void refreshWindow(){
        w.repaint();
    }
}
