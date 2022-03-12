/**
 * Game object classes and dependencies implementation.
 */
package game.features;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

/**
 * Provides an interface for creating methods of related or dependent game objects without specifying their concrete type as agent, threat, laser and others.
 */
public abstract class Object {
    private int fitHeight,fitWidth,layoutX,layoutY;
    private boolean visible;
    private BufferedImage image; //  Comprised of a color and a raster of image data.

    public Object(int fitHeight, int fitWidth, int layoutX, int layoutY, boolean visible, String imagePath) {
        this.fitHeight = fitHeight; // Height
        this.fitWidth = fitWidth; // Width
        this.layoutX = layoutX; // X Position
        this.layoutY = layoutY; // Y Position
        this.visible = visible; // Visibility
        try {
            URL url = this.getClass().getResource(imagePath);
            image = ImageIO.read(url);
        }
        catch(IOException e) {e.printStackTrace();
        }
    }

    public int getFitHeight() {
        return fitHeight;
    }

    public void setFitHeight(int fitHeight) {
        this.fitHeight = fitHeight;
    }

    public int getFitWidth() {
        return fitWidth;
    }

    public void setFitWidth(int fitWidth) {
        this.fitWidth = fitWidth;
    }

    public int getLayoutX() {
        return layoutX;
    }

    public void setLayoutX(int layoutX) {
        this.layoutX = layoutX;
    }

    public int getLayoutY() {
        return layoutY;
    }

    public void setLayoutY(int layoutY) {
        this.layoutY = layoutY;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setImageResource(String imageResource) {
        try {
            URL url = this.getClass().getResource(imageResource);
            image = ImageIO.read(url);
        }
        catch(IOException e) {e.printStackTrace();
        }
    }
}