package angryBirds;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Window extends Panel implements MouseListener, MouseMotionListener {

    /**
     * 
     */
    static Integer resolutionX = 800;
    static Integer resolutionY = 600;

    public Integer getResolutionX() {
        return resolutionX;
    }

    public void setResolutionX(Integer resolutionX) {
        Window.resolutionX = resolutionX;
    }

    public Integer getResolutionY() {
        return resolutionY;
    }

    public void setResolutionY(Integer resolutionY) {
        Window.resolutionY = resolutionY;
    }

    private static final long serialVersionUID = 1L;
    private Player mouse;
    private Game game;
    private Image buffer;
    private Observer observer;
    LevelFactory levelFactory;

    Window() {
        observer = new Observer(this);
        levelFactory = new LevelFactory();
        mouse = new Player();
        addMouseListener(this);
        addMouseMotionListener(this);
        createGame(1);
    }
    
    private void createGame(int level){
        game = levelFactory.buildRandomLevel(observer);
        game.start();
    }

    // évite les scintillements
    public void update(Graphics g) {
        paint(g);
    }

    // dessine le contenu de l'écran dans un buffer puis copie le buffer à
    // l'écran
    public void paint(Graphics g2) {
        if (buffer == null)
            buffer = createImage(resolutionX, resolutionY);
        Graphics2D g = (Graphics2D) buffer.getGraphics();

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        ((Graphics2D) g2).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        ((Graphics2D) g2).setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        // fond
        g.setColor(Color.WHITE);
        // g.fillRect(0, 0, getWidth(), getHeight());
        try {
            g.drawImage(ImageIO.read(new File("images/background.png")),0 ,0
                    , resolutionX,resolutionY, null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        g.setColor(Color.RED);
        if (game.isSelecting() && !game.getBirds().isEmpty())
            g.drawLine((int) game.getBirds().peek().getPositionX(),
                    (int) game.getBirds().peek().getPositionY(), mouse.getMouseX(),
                    mouse.getMouseY()); // montre l'angle et

        for (Character character : game.getCharacters())
            g.drawImage(character.getDesign(), (int) character.getPositionX() - 20,
                    (int) character.getPositionY() - 20, character.getSizeX(), character.getSizeY(),
                    null);

        // messages
        g.setColor(Color.BLACK);
        g.drawString(game.getMessage(), 300, 100);
        g.drawString("score: " + game.getScore(), 20, 20);

        // affichage à l'écran sans scintillement
        g2.drawImage(buffer, 0, 0, null);
    }

    // taille de la fenêtre
    public Dimension getPreferredSize() {
        return new Dimension(resolutionX, resolutionY);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        boolean gameOver = game.shootBird(mouse.getMouseX(), mouse.getMouseY());
        repaint();
        if(gameOver)
            createGame(2);
    }

    public void mouseDragged(MouseEvent e) {
        mouseMoved(e);
    }

    public void mouseMoved(MouseEvent e) {
        mouse.setMouseX(e.getX());
        mouse.setMouseY(e.getY());
        repaint();
    }
}
