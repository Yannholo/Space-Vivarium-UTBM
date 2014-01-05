package spaceVivarium.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImagesUtils {

    private static Map<URL, BufferedImage> images = new HashMap<URL, BufferedImage>();
    private static Map<InputStream, BufferedImage> imagesIS = new HashMap<InputStream, BufferedImage>();

    public static BufferedImage getImage(URL chemin) {
        BufferedImage img = images.get(chemin);
        if (img == null) {
            try {
                img = ImageIO.read(chemin);
                images.put(chemin, img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return img;
    }

    public static BufferedImage getImage(InputStream inputStream) {
        BufferedImage img = imagesIS.get(inputStream);
        if (img == null) {
            try {
                img = ImageIO.read(inputStream);
                imagesIS.put(inputStream, img);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return img;
    }
}
