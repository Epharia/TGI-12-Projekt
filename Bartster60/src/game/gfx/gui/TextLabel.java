package game.gfx.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import game.util.math.Area2D;

	public class TextLabel extends GUIObjBase { 
	
	private String strText;
	private Font font;
	private int fontSize;
	private Color c = Color.WHITE;
	private boolean centered;
	
	public TextLabel(int x, int y, int width, int height, String strText, int fontSize) {
		this(x, y, width, height, strText, Color.WHITE, new Font("font", Font.PLAIN, fontSize), fontSize);
	}
	
	public TextLabel(int x, int y, int width, int height, String strText, Color c, int fontSize) {
		this(x, y, width, height, strText, c, new Font("font", Font.PLAIN, fontSize), fontSize);
	}
	
	public TextLabel(int x, int y, int width, int height, String strText, Color c, Font font, int fontSize) {
		super(x, y, width, height);
		this.strText=strText;
		this.fontSize=fontSize;
		this.font = font;
		this.c = c;
	}

	@Override
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(c);;
		FontMetrics fm = g.getFontMetrics(font);
		if (centered) 
			g.drawString(strText, (int) (bounds.getX()+(bounds.getWidth()-fm.stringWidth(strText))/2), (int) (bounds.getY()+(bounds.getHeight()-fm.getHeight())/2 +fm.getAscent()));
		else 
			g.drawString(strText, (int) (bounds.getX()), (int) (bounds.getY()+(bounds.getHeight()-fm.getHeight())/2 +fm.getAscent()));
	}

	@Override
	public void tick() {
	}

	@Override
	public void onClick() {
	}
	
	//SETTER
	public void setText(String strText) {
		this.strText=strText;
	}
	
	public void setFont(Font font) {
		this.font=font;
	}
	
	public void deriveFont(int fontStyle) {
		this.font = font.deriveFont(fontStyle);
	}
	
	public void setCentered(boolean centered) {
		this.centered=centered;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	public void setPos(int x, int y) {
		this.bounds.setX(x);
		this.bounds.setY(y);
	}
	
	public void setBounds(int x, int y, int width, int height) {
		this.bounds.setX(x);
		this.bounds.setY(y);
		this.bounds.setWidth(width);
		this.bounds.setHeight(height);
	}
	
	//GETTER
	public String getText() {
		return this.strText;
	}
	
	public int getFontSize() {
		return fontSize;
	}
	
	public Font getFont() {
		return font;
	}
	
	public boolean isCentered() {
		return this.centered;
	}

	public Area2D getBounds() {
		return bounds;
	}
}
