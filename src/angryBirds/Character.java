package angryBirds;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

// chaque personnage a une position et une image et un attribut acitvated qui indique si le personnage doit être pris en compte ou pas (notamment lors des collisions)
public abstract class Character implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private double positionX, positionY;
    private transient Image design;
    private String designUrl;
    private Integer sizeX = 40;
    private Integer sizeY = 40;
    private boolean activated = true;

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Image getDesign() {
        return design;
    }

    // peut prendre des gif annimés 
    public void setDesign(String imageUrl) {
        try {
            if (imageUrl.endsWith("gif"))
                design = new ImageIcon("images/" + imageUrl).getImage();
            else
                design = (ImageIO.read(new File("images/" + imageUrl))).getScaledInstance(sizeX,
                        sizeY, Image.SCALE_SMOOTH);
            designUrl = imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public abstract Integer collisionWith(Character character);
    
    public abstract void outOfBoundaries();

    public Integer getSizeX() {
        return sizeX;
    }

    public void setSizeX(Integer sizeX) {
        this.sizeX = sizeX;
    }

    public Integer getSizeY() {
        return sizeY;
    }

    public void setSizeY(Integer sizeY) {
        this.sizeY = sizeY;
    }

    public String getDesignUrl() {
        return designUrl;
    }

    public void setDesignUrl(String designUrl) {
        this.designUrl = designUrl;
    }

}
