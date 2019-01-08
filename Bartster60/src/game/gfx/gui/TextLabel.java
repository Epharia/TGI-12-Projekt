package game.gfx.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

	public class TextLabel extends GUIObjBase { 
	
	private String strText;
	private Font font;
	private int fontSize;
	private boolean centered;
	
	public TextLabel(int x, int y, int width, int height, String strText, int fontSize) {
		this(x, y, width, height, strText, new Font("font", Font.PLAIN, fontSize), fontSize);
	}
	
	public TextLabel(int x, int y, int width, int height, String strText, Font font, int fontSize) {
		super(x, y, width, height);
		this.strText=strText;
		this.fontSize=fontSize;
		this.font = font;
	}

	@Override
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(Color.WHITE);;
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
}
