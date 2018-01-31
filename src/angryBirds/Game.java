package angryBirds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Game implements Runnable, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 
     */
    private List<Character> characters = new ArrayList<Character>();
    private Stack<Bird> birds = new Stack<Bird>();
    private Stack<Bird> deadBirds = new Stack<Bird>();
    private List<Pig> pigs;
    private Integer nbBirds;
    private Integer nbPigs;
    private Gravity gravity;
    private transient String message;

    private transient int score; // nombre de fois où le joueur a gagné
    private transient boolean gameOver; // vrai lorsque le joueur a touché un
                                        // bord ou le
    private transient boolean selecting;

    private transient  Observer  observer;
    private transient CollisionEngine collision;
    private transient LevelManager manager = new LevelManager();

    // constructeur
    Game(Observer observer) {
        this.observer = observer;
    }

    public void start() {
        manager.startLevel(this);
        initArena();
        new Thread(this).start();
    }

    void initArena() {
        manager.nextBird(this);
        message = "Choisissez l'angle et la vitesse.";
    }

    // fin de partie
    void stop() {
        deadBirds.push(birds.pop());
        deadBirds.peek().setVelocityY(deadBirds.peek().getVelocityY() * -0.6);
        deadBirds.peek().setVelocityX(deadBirds.peek().getVelocityX() * 0.6);

        if (birds.isEmpty() || hasWon())
            gameOver = true;
        else
            initArena();

    }

    boolean hasWon() {
        for (Pig p : pigs)
            if (p.isActivated())
                return false;
        return true;
    }

    public boolean shootBird(int x, int y) {
        if (isGameOver() && hasWon()) {
            message = "Bsahtek";
            return true;
        } else if (isGameOver()) {
            manager.startLevel(this);
            initArena();
        } else if (isSelecting()) {
            birds.peek().setVelocityX((birds.peek().getPositionX() - x) / 20.0);
            birds.peek().setVelocityY((birds.peek().getPositionY() - y) / 20.0);
            setMessage("L'oiseau prend son envol");
            setSelecting(false);
        }
        return false;
    }

    // boucle qui calcule la position de l'oiseau en vol, effectue l'affichage
    // et teste les conditions de victoire
    public void run() {
        while (true) {
            // un pas de simulation toutes les 10ms
            try {
                Thread.currentThread();
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }

            for (Bird b : deadBirds) {
                b.setPositionX(b.getPositionX() + b.getVelocityX());
                b.setPositionY(b.getPositionY() + b.getVelocityY());
                gravity.affect(b);
            }
            observer.refreshWindow();

            if (!gameOver && !selecting) {

                // moteur physique
                birds.peek()
                        .setPositionX(birds.peek().getPositionX() + birds.peek().getVelocityX());
                birds.peek()
                        .setPositionY(birds.peek().getPositionY() + birds.peek().getVelocityY());

                gravity.affect(birds.peek());

                birds.peek().updateBirdAppearance();

                int type = collision.checkCollisions();

                if (type == -1) {
                    message = "Perdu : cliquez pour recommencer.";
                    birds.peek().setDesign("bird_fail.png");
                    stop();

                } else if (!birds.peek().isActivated()) {
                    score++;
                    stop();
                }
                observer.refreshWindow();
                // redessine
            }
        }
    }

    public Stack<Bird> getBirds() {
        return birds;
    }

    public void setBirds(Stack<Bird> birds) {
        this.birds = birds;
    }

    public Integer getNbBirds() {
        return nbBirds;
    }

    public void setNbBirds(Integer nbBirds) {
        this.nbBirds = nbBirds;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<Pig> getPigs() {
        return pigs;
    }

    public void setPigs(List<Pig> pigs) {
        this.pigs = pigs;
    }

    public Gravity getGravity() {
        return gravity;
    }

    public void setGravity(Gravity gravity) {
        this.gravity = gravity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CollisionEngine getCollision() {
        return collision;
    }

    public void setCollision(CollisionEngine collision) {
        this.collision = collision;
    }

    public LevelManager getManager() {
        return manager;
    }

    public void setManager(LevelManager manager) {
        this.manager = manager;
    }

    public Integer getNbPigs() {
        return nbPigs;
    }

    public void setNbPigs(Integer nbPigs) {
        this.nbPigs = nbPigs;
    }

    public Observer getObserver() {
        return observer;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isSelecting() {
        return selecting;
    }

    public void setSelecting(boolean selecting) {
        this.selecting = selecting;
    }

    public Stack<Bird> getDeadBirds() {
        return deadBirds;
    }

    public void setDeadBirds(Stack<Bird> deadBirds) {
        this.deadBirds = deadBirds;
    }

    @Override
    public String toString() {
        return "Game [characters=" + characters + ", birds=" + birds + ", deadBirds=" + deadBirds
                + ", pigs=" + pigs + ", nbBirds=" + nbBirds + ", nbPigs=" + nbPigs + ", gravity="
                + gravity + ", observer=" + observer + ", collision=" + collision + ", manager="
                + manager + "]";
    }

   
    
    

}
