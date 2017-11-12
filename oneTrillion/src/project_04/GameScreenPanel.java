package project_04;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameScreenPanel extends JPanel implements Runnable {

	// �ڷΰ��� ��ư �̹����� ���� �� �ִ� ��ü
	private ImageIcon backButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/backButtonImage_2.png"));
	// ���� ���� ��ư �̹����� ���� �� �ִ� ��ü
	private ImageIcon gamePlayButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/gamePlayButton.png"));

	// ���콺�� ��ư�� �������� ���� �̹���
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/backButtonEnteredImage_2.png"));
	// ���� ���� ��ư�� �������� ���� �̹���
	private ImageIcon gamePlayButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/gamePlayButtonEntered.png"));

	// JButton ����
	private JButton backButton = new JButton(backButtonImage);
	private JButton gamePlayButton = new JButton(gamePlayButtonImage);
	
	// Thread ��ü
	private Thread thread;
	
	// Ball ��ġ ��� ���� ��ü
	private Ball ball;

	// fadeIn�� fadeOut �� ���� ����
	private float fadeValue;
	private boolean isFadeOut;

	// MainScreen ��� ���� ����
	private boolean isGameSelectScreen;
	
	// insideOut ��ü�� ������ �ڱ� �ڽ��� �ǳ��� ���� �� �ֵ��� �Ѵ�. 
	private InsideOut insideOut;

	public GameScreenPanel(InsideOut insideOut) {
		// �������� �Ű������� �޾� �����Ѵ�.
		this.insideOut = insideOut;
		// fadeOut���� false�� �ʱ�ȭ ���ѹ���
		isFadeOut = false;
		// isGameSelectScreen�� ���� false�� �ʱ�ȭ �����ش�.
		isGameSelectScreen = false;
		// �����带 ����� ��������ش�.
		setThread(new Thread(this));
		// �����̳��� ũ�Ⱑ ����ɶ� ������Ʈ���� ũ��� ��ġ�� �ڵ������� ����Ǵµ� �װ� �����Ѵ�
		setLayout(null);
		// ����â ũ�� ����
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// ������ ������ ��� �� �̹Ƿ� ���������� ����
		setBackground(Color.BLACK);
		// ȭ�� ��� ���� �⺻���� false �̹Ƿ� ���� ������Ѵ�.
		setVisible(true);
		// backButton�� ��ġ ����
		buttonSet(backButton, 80, 60, 228, 57);
		buttonSet(gamePlayButton, 600, 300, 125, 135);
	    
		
		// �޴��� exitButton ����
		buttonSet(insideOut.getMenubarExitButton(),1200,0,64,28);
		// �޴��� ����
		add(insideOut.getMenubar());
		// x,y ��ǥ�� �ޱ� ���� ��ü ����
		ball = new Ball();
		/**
		 * backButton�� ���콺 �̺�Ʈ�� ó�����ش�.
		 */

		backButton.addMouseListener(new MouseAdapter() {
			/**
			 * ���콺�� ������ ���� ������ �̺�Ʈ ó��
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ������ �̹����� Entered �̹����� ����
				backButton.setIcon(backButtonEnteredImage);
				// Ŀ�� �̹����� HAND_CURSOR�� �����ؼ� ���� �˾ƺ��� �����Ѵ�.
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� �������� ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				backButton.setIcon(backButtonImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� backButton ������ �������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				isFadeOut = true;
				isGameSelectScreen = true;
			}
		});

		/**
		 * gamePlayButton�� ���콺 �̺�Ʈ�� ó�����ش�.
		 */

		gamePlayButton.addMouseListener(new MouseAdapter() {
			/**
			 * ���콺�� ������ ���� ������ �̺�Ʈ ó��
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ������ �̹����� Entered �̹����� ����
				gamePlayButton.setIcon(gamePlayButtonEnteredImage);
				// Ŀ�� �̹����� HAND_CURSOR�� �����ؼ� ���� �˾ƺ��� �����Ѵ�.
				gamePlayButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� �������� ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				gamePlayButton.setIcon(gamePlayButtonImage);
				gamePlayButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� gamePlayButton ������ �������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				// ������ ����Ǵ� �̺�Ʈ
				// ���� �÷��� ��ư�� ������� �ϹǷ� false������ ������ ���� �Ⱥ��̰� ��
				gamePlayButton.setVisible(false);
				// �����带 ������� x��ǥ , y��ǥ ���� ����
				ball.getThread().start();
			}
		});

	}

	// ������ �� �����ϱ� ����Ƿ� �޼ҵ带 ���� �ս��� ��ư�� ��ġ�� ����
	public void buttonSet(JButton button, int x, int y, int width, int height) {
		button.setBounds(x, y, width, height);
		// ��ư �׵θ� ����
		button.setBorderPainted(false);
		// ������ ���� ����
		button.setContentAreaFilled(false);
		// �۾� �׵θ� ����
		button.setFocusPainted(false);
		// ��ư �߰�
		add(button);
	}

	/**
	 * fadeIn ȿ���� �ֱ����� �޼ҵ� temp�� ����� ������ fadeIn���� 1.0�� �Ѿ�� ������ �߻��ϱ� ������ float���� Ư����
	 * 0.1�� 10�� ������Ű�� 1.0�� �ƴ϶� 1.000001�� �Ǽ� ������ �߻��Ѵ�. ���� temp�� ������Ű�� fadeIn�� ���Խ�Ű��
	 * ����� ����Ѵ�. ���⼭ temp�� 1���� Ŀ���� temp�� 1�� �����ϰ� ���Խ����ش�.
	 */
	public void fadeIn() {
		try {
			float temp = 0;
			fadeValue = 0;
			while (fadeValue < 1) {
				temp += 0.1;
				if (temp > 1) {
					temp = 1.0f;
				}
				fadeValue = temp;
				repaint();
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void fadeOut() {
		try {
			float temp = 1.0f;
			fadeValue = 1.0f;
			while (fadeValue > 0) {
				temp -= 0.1;
				if (temp < 0) {
					temp = 0;
				}
				fadeValue = temp;
				repaint();
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ����Ʈ�� ���빰�� �׷��ִ� �޼ҵ�
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// graphics�� 2D�� ����
		Graphics2D g2 = (Graphics2D) g;
		// ������ �����ϱ� ���� �κ� fadeValue �� 1.0�̸� ������ 100%, 0.1�̸� �������� 10% �̴�.
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeValue));
		// ��Ƽ�ٸ���� , ���� ������ �ʰ� ���
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// ������� ����
		g2.setColor(Color.WHITE);
		// �β� ����
		g2.setStroke(new BasicStroke(8));
		// ���� ����ִ� �� , x��ǥ, y��ǥ, width, height 
		g2.drawOval(375, 100, 530, 530);
		// ���� ���� �� �� , ballŬ�������� ��� ���� ��ǥ�� ����ǹǷ� get�޼ҵ� �̿� , �츮�� ������ ��ü
		g2.fillOval(ball.getX(), ball.getY() , 26, 26);
	}

	// run �Լ����� while���� ���� ��� ȭ���� �׷������ν� ���� ȭ������ �Ѿ �� �ְ� ���ش�.
	@Override
	public void run() {
		fadeIn();
		while (true) {
			repaint();
			try {
				if (isFadeOut && isGameSelectScreen) {
					fadeOut();
					insideOut.changeGameSelectScreen();
					return;
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

}
