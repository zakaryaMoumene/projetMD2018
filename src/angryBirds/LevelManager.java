package angryBirds;

// class qui permet d'initialiser la partie 
public class LevelManager {

    // placer l'oiseau qui sera tiré
    public void nextBird(Game currentLevel) {

        currentLevel.setGameOver(false);
        currentLevel.setSelecting(true);

        currentLevel.getBirds().peek().setPositionX(Window.resolutionX * .18);
        currentLevel.getBirds().peek().setPositionY(Window.resolutionY * .7);
        currentLevel.getBirds().peek().setActivated(true);

        currentLevel.getCollision().addCharacter(currentLevel.getBirds().peek());

    }

    // initialiser les oiseaux ainsi que les éléments à recharger à chaque
    // recommencement de partie
    public void startLevel(Game currentLevel) {

        currentLevel.setScore(0);

        Bird bird;
        for (int i = 0; i < currentLevel.getNbBirds(); i++) {
            bird = new Bird("bird4.png");
            bird.setPositionX(
                    Window.resolutionX / 8 - i * Window.resolutionY / (Window.resolutionX / 10));
            bird.setPositionY(Window.resolutionY - Window.resolutionX / 10);
            bird.setVelocityX(0);
            bird.setVelocityY(0);
            bird.setWeight(1.001);
            currentLevel.getBirds().add(bird);
        }

        for (int i = 0; i < currentLevel.getNbPigs(); i++) {
            currentLevel.getPigs().get(i).setActivated(true);
        }

        if (currentLevel.getGravity().isInverted())
            currentLevel.getGravity().inverse();

        currentLevel.getCollision().clear();
        for (Character c : currentLevel.getCharacters()) {
            if (c.isActivated())
                currentLevel.getCollision().addCharacter(c);
            else if (c instanceof GravityInverser) {
                c.setActivated(true);
                currentLevel.getCollision().addCharacter(c);
            }

        }

        currentLevel.getCharacters().addAll(currentLevel.getBirds());
    }

}
