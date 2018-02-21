package game.util;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage load(String path) {
		try {
			if (ImageLoader.class.getResourceAsStream(path) != null)
				return ImageIO.read(ImageLoader.class.getResourceAsStream(path));
			else System.out.println("'" + path + "' is missing!");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
}
