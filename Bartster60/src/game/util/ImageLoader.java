package game.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
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
	
	public static BufferedImage loadSub(String path, int x, int y, int w, int h) {
		return load(path).getSubimage(x, y, w, h);
	}
	
	public static BufferedImage[] loadAnimationSheet(String path, int amountFrames) {
		return loadAnimationSheet(path, amountFrames, 16, 16);
	}
	
	public static BufferedImage[] loadAnimationSheet(String path, int amountFrames, int pixWidth, int pixHeight) {
		BufferedImage[] tempImgArray = new BufferedImage[amountFrames];
		for (int i=0; i<tempImgArray.length; i++) {
			tempImgArray[i]=loadSub(path, 0, i*pixHeight, pixWidth, pixHeight);
		}
		return tempImgArray;
	}
	
	public static BufferedImage rotateImage(BufferedImage img, int degrees) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(degrees), img.getWidth()/2, img.getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		return img = op.filter(img, null);
	}
	
	public static BufferedImage flipImageX(BufferedImage img) {
		AffineTransform transform = AffineTransform.getScaleInstance(-1, 1);
		transform.translate(-img.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return img = op.filter(img, null);
	}
	
	public static BufferedImage[] rotateAnimationSheet(BufferedImage[] sheet, int degrees) {
		for (int i = 0; i<sheet.length; i++) {
		BufferedImage img = sheet[i];
		sheet[i] = rotateImage(img, degrees);
		}
		return sheet;
	}
	
	public static BufferedImage[] flipAnimationSheetX(BufferedImage[] sheet) {
		for (int i = 0; i<sheet.length; i++) {
		BufferedImage img = sheet[i];
		sheet[i] = flipImageX(img);
		}
		return sheet;
	}
}
