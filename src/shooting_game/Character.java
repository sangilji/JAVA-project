package shooting_game;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;

public class Character extends PosImageIcon{
	int pX;				// ImageIcon�� X��ǥ
	int pY;				// ImageIcon�� y��ǥ
	int width;			// ImageIcon�� ����
	int height;			// ImageIcon�� ����
	int health;
	int power;
	double moveX,moveY;
	int dragonTime=0;
	public Character(ImageIcon character, int x, int y, int width, int height) { //����ĳ���� ����
		super(character,x,y,width,height);
		
		pX=x;
		pY=y;
		this.width = width;
		this.height = height;
		this.power=1;
		
	}
	public Character(ImageIcon character, int x, int y, int width, int height,int health) { //�� ����
		super(character,x,y,width,height);
		pX=x;
		pY=y;
		this.width = width;
		this.height = height;
		this.health= health;
		
	}
	public Character(ImageIcon character, int x, int y,int width, int height, double moveX, double moveY){ //�� ���� ����
		super(character,x,y,width,height);
		this.moveX = moveX;
		this.moveY = moveY;
		pX=x;
		pY=y;
		this.width = width;
		this.height = height;
		this.health= health;
	}
	
	public void move() {
		xx+= moveX;
		yy+= moveY;
		x = (int)xx;
		y = (int)yy;
	}
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), pX, pY, width, height, null);
	}
	public void attack() {
		health--;
	}
	public boolean inside (Point p) {
		return (p.x>= this.pX && p.x <= this.pX+this.width) && (p.y>= this.pY && p.y <= this.pY+this.height);
	}
	public void drawdA(Graphics g) {
		g.drawImage(this.getImage(), (int)xx, (int)yy, width, height, null);
	}
}
