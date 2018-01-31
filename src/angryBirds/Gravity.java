package angryBirds;

import java.io.Serializable;

public class Gravity implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private double gravity;
    
    // attribut pour indiquer si la gravité a été inversée 
    private boolean inverted = false;

    public double getGravity() {
        return gravity;
    }

    public void affect(Bird bird){
        bird.setVelocityY(bird.getVelocityY() + gravity* bird.getWeight());
    }
    
    public void affect(Character c){
        //
    }
    
    public void inverse(){
        inverted = !inverted;
        setGravity(getGravity()*-1);
    }
    
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public Gravity(double gravity) {
        super();
        this.gravity = gravity;
    }

    public boolean isInverted() {
        return inverted;
    }

    public void setInverted(boolean inverted) {
        this.inverted = inverted;
    }
    

}
