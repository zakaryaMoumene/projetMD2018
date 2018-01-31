package angryBirds;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

// class qui permet de créer des niveau de jeu
public class LevelFactory {

    // méthode de génération semi-aléatoire
    public Game buildRandomLevel(Observer observer) {

        // besoin des deux builders
        CharacterBuilder cBuilder = new CharacterBuilder();
        ArenaComponentBuilder aBuilder = new ArenaComponentBuilder();

        Game level = new Game(observer);

        
        level.setNbPigs((int) (Math.random()*5) +1);
        level.setNbBirds((int)(level.getNbPigs()+Math.random()*3));

        level.setGravity(aBuilder.createGravity(0.1));
        level.setCollision(aBuilder.createCollisionEngine());

        // placement des cochons
        level.setPigs(new ArrayList<Pig>());
        for (int i = 0; i < level.getNbPigs(); i++) {
            Pig p = (Pig) cBuilder.buildCharacter("pig", "pig.png");
            p.setPositionX(Math.random() * (Window.resolutionX / (3 * level.getNbPigs()))
                    + i * 2 * Window.resolutionX / (3 * level.getNbPigs())
                    + Window.resolutionX / 3); // position
            p.setPositionY(Window.resolutionY - Window.resolutionX / 10);
            level.getPigs().add(p);
        }

        level.getCharacters().addAll(level.getPigs());

        // création des persos secondaires
        Wind w = ((Wind) cBuilder.buildCharacter("wind", .2, "wind.gif"));
        Fire f = ((Fire) cBuilder.buildCharacter("fire", "fire.gif"));
        Mushroom m = ((Mushroom) cBuilder.buildCharacter("mushroom", 2.0, "mushroom.png"));
        GravityInverser g = ((GravityInverser) cBuilder.buildCharacter("gInverser",
                level.getGravity(), "gInverser.png"));

        // placement des persos secodnaires
        w.setPositionX(Math.random() * (Window.resolutionX * .1) + Window.resolutionX * .2);
        w.setPositionY(Math.random() * (Window.resolutionY * .1) + Window.resolutionY * .5);

        g.setPositionX(Math.random() * (Window.resolutionX * .1) + Window.resolutionX * .4);
        g.setPositionY(Math.random() * (Window.resolutionY * .1) + Window.resolutionY * .3);

        f.setPositionX(Math.random() * (Window.resolutionX * .1) + Window.resolutionX * .5);
        f.setPositionY(Math.random() * (Window.resolutionY * .1) + Window.resolutionY * .5);

        m.setPositionX(Math.random() * (Window.resolutionX * .1) + Window.resolutionX * .6);
        m.setPositionY(Math.random() * (Window.resolutionY * .1) + Window.resolutionY * .3);

        // g.setPositionX(450);
        // g.setPositionY(300);
        //
        // f.setPositionX(500);
        // f.setPositionY(400);
        //
        // m.setPositionX(400);
        // m.setPositionY(250);

        // ajouter le tout dans le jeu
        if (Math.random() > 0.2)
            level.getCharacters().add(w);
        if (Math.random() > 0.5)
            level.getCharacters().add(f);
        if (Math.random() > 0.2)
            level.getCharacters().add(m);
        if (Math.random() > 0.6)
            level.getCharacters().add(g);

        return level;

    }

    // cette méthode lit un niveau qui a été sérialisé au préalable
    public Game readLevel(String fileName, Observer observer) {

        Game level = new Game(observer);

        try {
            FileInputStream fis = new FileInputStream("Level1.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            level = (Game) ois.readObject();
            System.out.println(level);
            ois.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        level.setObserver(observer);
        level.setCollision(new CollisionEngine());
        level.setManager(new LevelManager());

        for (Character c : level.getCharacters())
            c.setDesign(c.getDesignUrl());

        return level;
    }

    // cette méthode sérialise un niveau
    public void exportLevel(Game level, String levelName) {
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("Level1.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(level);
            oos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
