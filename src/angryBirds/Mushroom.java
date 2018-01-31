package angryBirds;


// agrandit et alourdit l'oiseau qui le mange
public class Mushroom extends Character {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    double mass;

    public Mushroom(double mass, String imageUrl) {
        setDesign(imageUrl);
        setSizeX(40);
        setSizeY(40);
        this.mass = mass;
    }

    public Mushroom() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Integer collisionWith(Character character) {
        if(character instanceof Bird){
            setActivated(false);
            setDesign("transparent.png");
        }
        return null;
    }

    @Override
    public String toString() {
        return "Mushroom [mass=" + mass + "]";
    }

    @Override
    public void outOfBoundaries() {
        // TODO Auto-generated method stub
        
    }

    
}
