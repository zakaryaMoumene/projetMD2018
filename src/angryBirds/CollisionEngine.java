package angryBirds;

import java.util.ArrayList;
import java.util.List;

// gestion des collision 
public class CollisionEngine {

    private List<Character> characters;

    public CollisionEngine() {
        super();
        characters = new ArrayList<Character>();
    }

    public void addCharacter(Character c) {
        characters.add(c);
    }

    public Integer checkCollisions() {
        Integer type = 0;
        // parcours de tous les personnages
        for (int i = 0; i < characters.size() - 1; i++) {
            for (int j = i + 1; j < characters.size(); j++)
                // si les persos sont actifs et qu'il sont en collision , chacun
                // en est notifié
                if (characters.get(i).isActivated() && characters.get(j).isActivated()
                        && collision(characters.get(i), characters.get(j))) {
                    characters.get(i).collisionWith(characters.get(j));
                    characters.get(j).collisionWith(characters.get(i));
                    return 1;

                    // cas de la sortie du cadre de la fenêtre
                } else if (characters.get(i) instanceof Bird && characters.get(i).isActivated()
                        && (characters.get(i).getPositionX() < 20
                                || characters.get(i).getPositionX() > Window.resolutionX - 20
                                || characters.get(i).getPositionY() < 0
                                || characters.get(i).getPositionY() > Window.resolutionY - 90)) {
                    characters.get(i).outOfBoundaries();
                    return -1;
                }
                // cas de la sortie du cadre de la fenêtre
                else if (characters.get(j) instanceof Bird && characters.get(j).isActivated()
                        && (characters.get(j).getPositionX() < 20
                                || characters.get(j).getPositionX() > Window.resolutionX - 20
                                || characters.get(j).getPositionY() < 0
                                || characters.get(j).getPositionY() > Window.resolutionY - 90)) {
                    characters.get(j).outOfBoundaries();
                    return -1;
                }
        }
        return type;

    }

    // calcul si les deux objets sont en collision. (cette méthode prend en
    // compte la taille des images des persos)
    static boolean collision(Character c1, Character c2) {
        if ((c2.getPositionX() >= c1.getPositionX() + c1.getSizeX() - 10) // trop
                                                                          // à
                // droite
                || (c2.getPositionX() + c2.getSizeX() - 10 <= c1.getPositionX()) // trop
                // à
                // gauche
                || (c2.getPositionY() >= c1.getPositionY() + c1.getSizeY() - 10) // trop
                // en
                // bas
                || (c2.getPositionY() + c2.getSizeY() - 10 <= c1.getPositionY()))
            return false;
        return true;
    }

    public void clear() {
        characters.clear();
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

}
