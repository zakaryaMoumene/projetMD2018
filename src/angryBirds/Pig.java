package angryBirds;

public class Pig extends Character  {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Pig(String imageUrl) {
        super();
        setDesign(imageUrl);
    }

    public Pig() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public void setActivated(boolean activated) {
        super.setActivated(activated);
        if (activated) 
            setDesign("pig.png");
    }

    @Override
    public Integer collisionWith(Character character) {
        if (character instanceof Bird) {
            setActivated(false);
            setDesign("pig_hit.png");
            return 1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Pig [isActivated()=" + isActivated() + "]";
    }

   
    
    
}
