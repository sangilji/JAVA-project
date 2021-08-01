package shooting_game;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

// ImageIcon�� ��ǥ�� ��ġ�� �ο��ϰ��� ImageIcon Ŭ������ �����
public class PosImageIcon extends ImageIcon {
	ImageIcon img;
	int x;				// ImageIcon�� X��ǥ
	int y;				// ImageIcon�� y��ǥ
	double xx;
	double yy;
	int width;			// ImageIcon�� ����
	int height;			// ImageIcon�� ����
	
	
	public PosImageIcon(ImageIcon img,int x,int y, int width, int height) {
		super(img.getImage());
		this.img = img;
		this.x=x;
		this.xx = (double)x;
		this.y=y;
		this.yy=(double)y;
		this.width=width;
		this.height=height;
	
	}
	//�浹
	public boolean collide(Rectangle rc) {
		return rc.intersects((double)x,(double)y,(double)width,(double)height);
	}
	//���
	public Point center() {
		return new Point(x+width/2,y+height/2);

	}
	
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), x, y, width, height, null);
	}
}
