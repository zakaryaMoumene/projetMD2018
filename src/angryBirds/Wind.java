package angryBirds;

import java.io.Serializable;


// pousse vers la droite l'oiseau qui s'en approche
public class Wind extends Character implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private double pushStrength;

    public Wind(double pushStrength, String imageUrl) {
        setDesign(imageUrl);
        setSizeX(90);
        setSizeY(60);
        this.pushStrength = pushStrength;
    }

    public Wind() {
        // TODO Auto-generated constructor stub
    }

    public double getPullStrength() {
        return pushStrength;
    }

    public void setPullStrength(double pullStrength) {
        this.pushStrength = pullStrength;
    }

    @Override
    public Integer collisionWith(Character character) {
        if (character instanceof Bird) {
            ((Bird) character).setVelocityX(((Bird) character).getVelocityX() + getPullStrength());
            return 2;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Wind [pushStrength=" + pushStrength + "]";
    }

    @Override
    public void outOfBoundaries() {
        // TODO Auto-generated method stub
        
    }
    
    

}
