package angryBirds;


// équivalent au trou noir, tue tout oiseau qui rentre en collision avec lui
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

    @Override
    public void outOfBoundaries() {
        // TODO Auto-generated method stub
        
    }

   
}
