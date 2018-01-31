package angryBirds;


public class Bird extends Character {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private double velocityX, velocityY;
    
    // le poid est propre à chaque oiseau
    private double weight;

    // état de l'oiseau selon sa vitesse
    private Integer state = 0;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Bird(String imageUrl) {
        setDesign(imageUrl);
    }

    public Bird() {
        // TODO Auto-generated constructor stub
    }

    public double getVelocityX() {
        return velocityX;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    // changer l'apparence de l'oiseau selon sa vélocité 
    public void updateBirdAppearance() {

        if (state != -1) {

            if (state != 0 && velocityY < 2 && velocityY > -1) {
                setDesign("bird_speed0.png");
                state = 0;

            } else if (state != 1 && velocityY < 4 && velocityY > 2) {
                setDesign("bird_speed1.png");
                state = 1;
            }

            else if (state != 2 && velocityY < 6 && velocityY > 4) {
                setDesign("bird_speed2.png");
                state = 2;

            } else if (state != 3 && velocityY < 7 && velocityY > 6) {
                setDesign("bird_speed3.png");
                state = 3;
            }

            else if (state != 4 && velocityY < 8 && velocityY > 7) {
                setDesign("bird_speed4.png");
                state = 4;
            }

            else if (state != 5 && velocityY > 8) {
                setDesign("bird_speed5.png");
                state = 5;
            }
        }
    }

    
    // gérer les collision avec les autres objets du jeu
    @Override
    public Integer collisionWith(Character character) {
        if (character instanceof Pig) {
            setDesign("bird_success.png");
            setActivated(false);
            return 1;
        }
        if (character instanceof Wind) {
            return 2;
        }
        if (character instanceof Fire) {
            setDesign("bird_roasted.png");
            setActivated(false);
            state = -1;
        }
        if (character instanceof Mushroom) {
            setSizeX(getSizeX() + 25);
            setSizeY(getSizeY() + 25);
            setWeight(getWeight() + 0.3);
            setVelocityX(getVelocityX() - 2);
        }
        if (character instanceof GravityInverser) {
            if (velocityY > 0) {
                velocityY = velocityY * -.99;
            } else {
                velocityY = velocityY * 1.1;
            }
        }
        return 0;
    }
    
    public void outOfBoundaries(){
        setDesign("bird_fail.png");
        setActivated(false);
    }

    @Override
    public String toString() {
        return "Bird [isActivated()=" + isActivated() + "]";
    }

}
