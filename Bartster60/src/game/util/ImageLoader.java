package game.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/* 
 * |====================================================|
 * |this Class is used to load Images and transform them|
 * |====================================================|
 */

public class ImageLoader {
	
	//loading an Image at the given path
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
	
	//loading an Image inside an Image(PNG) ==> used for Sheets f.i. storing several textures
	public static BufferedImage loadSub(String path, int x, int y, int w, int h) {
		if (ImageLoader.class.getResourceAsStream(path) != null)
			return load(path).getSubimage(x, y, w, h);
		else System.out.println("'" + path + "' is missing!");
		return null;
	}
	
	//loading an AnimationSheet at the given path with the given amount of frames
	public static BufferedImage[] loadAnimationSheet(String path, int amountFrames) {
		return loadAnimationSheet(path, amountFrames, 16, 16);
	}
	
	//loading an AnimationSheet at the given path with the given amount of frames and a different boundary
	public static BufferedImage[] loadAnimationSheet(String path, int amountFrames, int pixWidth, int pixHeight) {
		BufferedImage[] tempImgArray = new BufferedImage[amountFrames];
		for (int i=0; i<tempImgArray.length; i++) {
			tempImgArray[i]=loadSub(path, 0, i*pixHeight, pixWidth, pixHeight);
		}
		return tempImgArray;
	}
	
	//rotating an image by X°
	public static BufferedImage rotateImage(BufferedImage img, int degrees) {
		AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(degrees), img.getWidth()/2, img.getHeight()/2);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
		return img = op.filter(img, null);
	}
	
	//flips an Image
	public static BufferedImage flipImageX(BufferedImage img) {
		AffineTransform transform = AffineTransform.getScaleInstance(-1, 1);
		transform.translate(-img.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return img = op.filter(img, null);
	}
	
	//rotating every frame of an AnimationSheet by X°
	public static BufferedImage[] rotateAnimationSheet(BufferedImage[] sheet, int degrees) {
		for (int i = 0; i<sheet.length; i++) {
		BufferedImage img = sheet[i];
		sheet[i] = rotateImage(img, degrees);
		}
		return sheet;
	}
	
	//flips an AnimationSheet
	public static BufferedImage[] flipAnimationSheetX(BufferedImage[] sheet) {
		for (int i = 0; i<sheet.length; i++) {
		BufferedImage img = sheet[i];
		sheet[i] = flipImageX(img);
		}
		return sheet;
	}
}
