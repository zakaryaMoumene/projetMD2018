package angryBirds;

public class Fire extends Character {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    Fire(String imageUrl) {
        setSizeX(60);
        setSizeY(90);
        setDesign(imageUrl);
    }

    public Fire() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Integer collisionWith(Character character) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
        return "Fire [isActivated()=" + isActivated() + "]";
    }

   
}
