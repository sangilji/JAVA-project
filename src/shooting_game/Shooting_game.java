package shooting_game;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Shooting_game {

	JButton startButton;
	JButton exitButton;
	JButton restartButton;
	JLabel score;
	JLabel finishscore;
	JFrame Frame;
	CardLayout card = new CardLayout(0, 0); // 카드레이아웃을 사용해 패널화면을 변경시켜줌

	StartPanel startPanel;
	MainPanel mainPanel;
	FinishPanel finishPanel;
	JPanel cardPanel;
	Timer bgTimer;
	Timer atTimer;
	Timer dgTimer;
	Timer gameTimer;
	KeyListener charKeyListener;
	ArrayList<Character> character_attack = new ArrayList<>();
	ArrayList<Character> dragon_attack = new ArrayList<>();
	ArrayList<Character> reddragon_attack = new ArrayList<>();
	ArrayList<Character> delete_attack = new ArrayList<>();
	ArrayList<Character> dragon = new ArrayList<Character>();
	ArrayList<Character> red_dragon = new ArrayList<Character>();
	ArrayList<Character> heart_list = new ArrayList<Character>();

	int move_bg = 0;
	int time = 0;
	int i = 0;
	int mainC_health= 2;
	int startX = 350;
	int startY = 800;
	boolean up, down, right, left;
	boolean lose;
	boolean startp=false;
	int Score=0;
	int a;
	Character main_character;
	Character main_mujuk;
	ImageIcon startBg = new ImageIcon("src/shooting_game/초기화면배경.png");
	ImageIcon bg = new ImageIcon("src/shooting_game/background.png");
	ImageIcon character = new ImageIcon("src/shooting_game/hatchling_055.png");
	ImageIcon mujuk = new ImageIcon("src/shooting_game/무적.png");
	ImageIcon mu = new ImageIcon("src/shooting_game/무.png");
	ImageIcon attack = new ImageIcon("src/shooting_game/bullet_03_05.png");
	ImageIcon dAttack = new ImageIcon("src/shooting_game/파란용 공격.png");
	ImageIcon dragonImage = new ImageIcon("src/shooting_game/파란용.gif");
	ImageIcon dragondel = new ImageIcon("src/shooting_game/파란용 피격.png");
	ImageIcon reddAttack = new ImageIcon("src/shooting_game/화염용 공격.png");
	ImageIcon reddragonImage = new ImageIcon("src/shooting_game/화염용.gif");
	ImageIcon reddragondel = new ImageIcon("src/shooting_game/화염용 피격.png");
	ImageIcon heart = new ImageIcon("src/shooting_game/하트.png");
	ImageIcon heartDel = new ImageIcon("src/shooting_game/하트x.png");
	ImageIcon exitbg = new ImageIcon("src/shooting_game/패배사진.png");

	Image dragonatk_img = dragondel.getImage();
	Image dragon_img = dragonImage.getImage();
	Image reddragonatk_img = reddragondel.getImage();
	Image reddragon_img = reddragonImage.getImage();
	Image heartDel_imag = heartDel.getImage();

	public void go() {
		Frame = new JFrame();
		Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		startPanel = new StartPanel();
		mainPanel = new MainPanel();
		finishPanel = new FinishPanel();
		cardPanel = new JPanel();
		cardPanel.setLayout(card);

		startButton = new JButton(new ImageIcon("src/shooting_game/게임시작버튼.png"));
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.setFocusPainted(false);
		exitButton = new JButton(new ImageIcon("src/shooting_game/게임종료버튼.png"));
		exitButton.setContentAreaFilled(false);
		exitButton.setBorderPainted(false);
		exitButton.setFocusPainted(false);
		restartButton = new JButton(new ImageIcon("src/shooting_game/다시하기버튼.png"));
		restartButton.setContentAreaFilled(false);
		restartButton.setBorderPainted(false);
		restartButton.setFocusPainted(false);
		
		
		score = new JLabel("score: " +Score);
		score.setFont(new Font("고딕",Font.BOLD,50));
		finishscore = new JLabel();
		finishscore.setFont(new Font("고딕",Font.BOLD,80));
		mainPanel.setLayout(null);
		mainPanel.add(score);
		
		
		startPanel.setBackground(Color.white);
		Frame.setBackground(Color.white);
		startPanel.setPreferredSize(new Dimension(800, 1000));
		startPanel.add(startButton);
		finishPanel.add(exitButton);
		finishPanel.add(restartButton);
		finishPanel.add(finishscore);

		main_character = new Character(character, startX, startY, 64, 128);
		main_mujuk = new Character(mu, startX-55, startY-30, 180, 180);
		Frame.getContentPane().add(BorderLayout.CENTER, startPanel);
		Frame.getContentPane().setLayout(card);

		ButtonListener b = new ButtonListener();
		startButton.addActionListener(b);
		exitButton.addActionListener(b);
		restartButton.addActionListener(b);
		
		
		cardPanel.add("a1", startPanel);
		cardPanel.add("a2", mainPanel);
		cardPanel.add("a3", finishPanel);
		Frame.add(cardPanel);
		Frame.pack();
		Frame.setVisible(true);

		
		
		bgTimer = new Timer(10, new bgTime());
		atTimer = new Timer(700, new attackTime());
		dgTimer = new Timer(1000, new createDragon());
		gameTimer = new Timer(1, new GameTimer());
		// bgTimer.start();
		// atTimer.start();
		// dgTimer.start();
		// gameTimer.start();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Shooting_game game = new Shooting_game();
		game.go();

	}

	class StartPanel extends JPanel {
		public void paintComponent(Graphics g) {
			int w = this.getWidth();
			int h = this.getHeight();

			g.drawImage(startBg.getImage(), 0, 0, w, h, null);
			startButton.setBounds(450, 700, 300, 105);
			
		}

	}

	class MainPanel extends JPanel {
		public void paintComponent(Graphics g) {

			int w = this.getWidth();
			int h = this.getHeight();

			g.drawImage(bg.getImage(), 0, move_bg, w, h, null);
			g.drawImage(bg.getImage(), 0, move_bg - 1000, w, h, null);

			for (Character d : dragon) {
				d.draw(g);
			}
			for (Character d : red_dragon) {
				d.draw(g);
			}
			main_character.draw(g);
			main_mujuk.draw(g);
			
			for (Character a : character_attack) {
				a.draw(g);
			}
			for (Character da : dragon_attack) {
				da.drawdA(g);
			}	

			for (Character rda : reddragon_attack) {
				rda.drawdA(g);
			}
			score.setBounds(30,-170,500,400);
			score.setText("score: " +Score);
			for (Character hl : heart_list) {
				hl.draw(g);
			}
			repaint();

		}
	}
	class FinishPanel extends JPanel {
		public void paintComponent(Graphics g) {
			int w = this.getWidth();
			int h = this.getHeight();
			
			g.drawImage(exitbg.getImage(), 0, 0, w, h, null);
			finishscore.setBounds(280,550,500,400);
			finishscore.setText(""+Score);
			exitButton.setBounds(380, 900, 300, 105);
			restartButton.setBounds(130, 900, 300, 105);
		}
	}
	class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			if (button == startButton) {
				card.show(cardPanel, "a2");
				bgTimer.start();
				atTimer.start();
				dgTimer.start();
				gameTimer.start();
				mainPanel.addKeyListener(new character_move());
				mainPanel.addKeyListener(new attackTime());
				mainPanel.setFocusable(true);
				mainPanel.requestFocus();
				heart_list.add(new Character(heart, 30, 60, 35, 31));
				heart_list.add(new Character(heart, 70, 60, 35, 31));
				heart_list.add(new Character(heart, 110, 60, 35, 31));
			}
			if(button==exitButton)
				System.exit(1);
			if(button==restartButton) {
				card.show(cardPanel, "a2");
				dragon.removeAll(dragon);
				dragon_attack.removeAll(dragon_attack);
				red_dragon.removeAll(red_dragon);
				reddragon_attack.removeAll(reddragon_attack);
				main_character.pX=startX;
				main_character.pY=startY;
				main_mujuk.pX=startX-55;
				main_mujuk.pY=startY-30;
				Score=0;
				dgTimer.start();
				atTimer.start();
				gameTimer.start();
				
				mainPanel.setFocusable(true);
				mainPanel.requestFocus();
				heart_list.get(0).setImage(heart.getImage());
				heart_list.get(1).setImage(heart.getImage());
				heart_list.get(2).setImage(heart.getImage());
				mainC_health=2;
			}

		}
	}

	class bgTime implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
			if(lose==true) {
				
			}
		}

	}

	class createDragon implements ActionListener {

		int rand = (int) (Math.random() * 550) + 50;

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			time++;
			if (time % 3 == 0) {
				dragon.add(new Character(dragonImage, (int) (Math.random() * 510), -100, 85, 83, 5));

			}
			if (time % 7 == 0) {
				red_dragon.add(new Character(reddragonImage, (int) (Math.random() * 510), -100, 85, 83, 10));
			}

			for (Character b : dragon) {
				if (b.pY > 1000)
					delete_attack.add(b);

			}
			for (Character b : red_dragon) {
				if (b.pY > 1000)
					delete_attack.add(b);
			}

			for (Character d : delete_attack) {
				red_dragon.remove(d);
				dragon.remove(d);
			}
			for (Character Rd : red_dragon)
				if (Rd.pY > 0) {
					if (time % 4 == 0) {
						redDAtk();
					}
				}
			
		}

	}

	class GameTimer implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			move_bg++;
			if (move_bg == 1000)
				move_bg = 0;
			for (Character dA : dragon_attack) {
				dA.move();
				dA.move();
				dA.move();
			}
			for (Character rdA : reddragon_attack) {
				rdA.move();
				rdA.move();
				
			}
			for (Character a : character_attack) {
				a.pY -= 10;
				if (a.pY < -100)
					delete_attack.add(a);
			}

			for (Character d : delete_attack) {
				character_attack.remove(d);
			}
			for (Character d : dragon) {
				d.pY++;
			}
			for (Character d : red_dragon) {
				d.pY++;
			}

			dragon_del();
			reddragon_del();
			dragonAtk_del();
			reddragonAtk_del();
			character_move();
			if(startp==false) {
				heart_lose();
				if(lose==true) {
					lose();
					a=time;
					main_mujuk.setImage(mujuk.getImage());
					lose=false;
				}
			}
			
			wait_time();
			Frame.repaint();
		}

	}

	class attackTime implements KeyListener, ActionListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			switch (e.getKeyCode()) {
			case KeyEvent.VK_A:
				character_attack.add(new Character(attack, main_character.pX - 20, main_character.pY - 60, 110, 110));
				break;
			case KeyEvent.VK_S:

				break;
			}

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			for (Character b : dragon)
				if (b.pY > 0) {
					if (time % 4 == 0 || time % 4 == 1) {
						DAtk();
					}
				}
			
		}
		

	}

	class character_move implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_UP)
				up = true;

			if (key == KeyEvent.VK_DOWN)
				down = true;

			if (key == KeyEvent.VK_LEFT)
				left = true;

			if (key == KeyEvent.VK_RIGHT)
				right = true;

			mainPanel.setFocusable(true);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_UP)
				up = false;

			if (key == KeyEvent.VK_DOWN)
				down = false;

			if (key == KeyEvent.VK_LEFT)
				left = false;

			if (key == KeyEvent.VK_RIGHT)
				right = false;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void character_move() {
		if (up) {
			if (main_character.pY >= 0) {
				main_character.pY -= 8;
				main_mujuk.pY-=8;
			}
		} else if (down) {
			if (main_character.pY <= 1000 - 128) {
				main_character.pY += 8;
				main_mujuk.pY += 8;
			}
		} else if (left) {
			if (main_character.pX >= 0) {
				main_character.pX -= 8;
				main_mujuk.pX -= 8;
			}
		} else if (right) {
			if (main_character.pX <= 800 - 64) {
				main_character.pX += 8;
				main_mujuk.pX += 8;
			}
		}
	}
	public void wait_time() {
			if(a+3==time) {
				startp=false;
			main_mujuk.setImage(mu.getImage());
			}
	}
	public void lose() {
		heart_list.get(mainC_health).setImage(heartDel_imag);
		mainC_health--;
		main_character.pX=startX;
		main_character.pY=startY;
		main_mujuk.pX=startX-55;
		main_mujuk.pY=startY-30;
		
	}
	public void heart_lose() {
		int k=0;
		for(Character dAtk : dragon_attack) {
			if ((main_character.pY + main_character.height -18 > dAtk.y) &&  //위
					dAtk.x + dAtk.width > main_character.pX +10 && //왼쪽
					dAtk.x < main_character.pX + main_character.width -10 && //오른쪽
					dAtk.y + dAtk.height > main_character.pY +10) //아래
			{ 
				
				System.out.println(k);
				if(mainC_health!=-1) {
					delete_attack.add(dAtk);	
					
					lose=true;
					startp=true;
					
					
				}
				else if (mainC_health==-1){
					card.show(cardPanel, "a3");
					bgTimer.stop();
					atTimer.stop();
					dgTimer.stop();
					gameTimer.stop();
				}
			}
			
		}
		
		for(Character rdAtk : reddragon_attack) {
			if ((main_character.pY + main_character.height -18 > rdAtk.y) &&  //위
					rdAtk.x + rdAtk.width > main_character.pX +10 && //왼쪽
					rdAtk.x < main_character.pX + main_character.width -10 && //오른쪽
					rdAtk.y + rdAtk.height > main_character.pY +10) //아래
			{ 
				if(mainC_health!=-1) {
				delete_attack.add(rdAtk);	
				lose=true;
				startp=true;
				
				}
				else if (mainC_health==-1) {
					card.show(cardPanel, "a3");
					bgTimer.stop();
					atTimer.stop();
					dgTimer.stop();
					gameTimer.stop();
				}
			}
			
		}
		for(Character delA : delete_attack) {
			dragon_attack.remove(delA);
			reddragon_attack.remove(delA);
		}
	}
	
	public void dragon_del() {
		
		for (Character d : dragon) {
			for (Character a : character_attack) {
				if ((d.pY + d.height - 50 > a.pY) && a.pX + a.width > d.pX && a.pX < d.pX + d.width
						&& a.pY + a.height > d.pY) {
					i = time;
					d.attack();
					d.setImage(dragonatk_img);

					delete_attack.add(a);

				}
			}
			System.out.println(d.health);
			if (d.health == 0) {
				delete_attack.add(d);
				Score+=50;
			}
			if (i + 1 == time)
				d.setImage(dragon_img);
		}
		for (Character d : delete_attack) {
			dragon.remove(d);
			character_attack.remove(d);
		}
	}

	public void dragonAtk_del() {

		for (Character dA : dragon_attack) {
			if (dA.y > 1000 || dA.x > 800 || dA.y + dA.height < 0 || dA.x + dA.width < 0) {
				delete_attack.add(dA);

			}
		}
		for (Character delA : delete_attack) {
			dragon_attack.remove(delA);
		}

	}
	public void reddragonAtk_del() {

		for (Character rdA : reddragon_attack) {
			if (rdA.y > 1000 || rdA.x > 800 || rdA.y + rdA.height < 0 || rdA.x + rdA.width < 0) {
				delete_attack.add(rdA);

			}
		}
		for (Character delA : delete_attack) {
			dragon_attack.remove(delA);
		}

	}
	public void reddragon_del() {
		for (Character d : red_dragon) {
			for (Character a : character_attack) {
				if ((d.pY + d.height - 50 > a.pY) && a.pX + a.width > d.pX && a.pX < d.pX + d.width
						&& a.pY + a.height > d.pY) {
					d.attack();
					i = time;
					d.setImage(reddragonatk_img);
					delete_attack.add(a);
				
				}
			}

			if (d.health == 0) {
				delete_attack.add(d);
				Score+=100;
			}
			if (i + 1 == time)
				d.setImage(reddragon_img);
		}
		for (Character d : delete_attack) {

			red_dragon.remove(d);
			character_attack.remove(d);
		}

	}

	public void DAtk() {
		double atX;
		double atY;
		double under;
		Point p = new Point(main_character.pX + (main_character.width / 2),
				main_character.pY + (main_character.height / 2));
		for (Character d : dragon) {
			
			atX = (double) (p.x - d.pX - 40 / 2);
			atY = (double) (p.y - d.pY - 40 / 2);
			
			under = Math.sqrt((atX * atX) + (atY * atY));
			atX = atX / under;
			atY = atY / under;

			dragon_attack.add(
					new Character(dAttack, d.pX + d.width / 2 - 10, d.pY + d.height - 10, 40, 40,  atX,  atY));

		}
	}
	public void redDAtk() {
		double atX;
		double atY;
		double under;
		Point p = new Point(main_character.pX + (main_character.width / 2),
				main_character.pY + (main_character.height / 2));
		for (Character d : red_dragon) {
			
			atX = (double) (p.x - d.pX - 80 / 2);
			atY = (double) (p.y - d.pY - 80 / 2);
			
			under = Math.sqrt((atX * atX) + (atY * atY));
			atX = atX / under;
			atY = atY / under;

			reddragon_attack.add(
					new Character(reddAttack, d.pX + d.width / 2 - 10, d.pY + d.height - 10, 80, 80,  atX,  atY));
			System.out.println("생성");

		}
	}
}
