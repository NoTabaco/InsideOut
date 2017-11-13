package project_04;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ������ �����ϴ� �ǳ��̴�.
 * 
 * @author SungHo Yun
 * @version 0.4
 *
 */
public class GameScreenPanel extends JPanel implements Runnable {

	/** �ڷΰ��� ��ư �̹����� ���� �� �ִ� ��ü */
	private ImageIcon backButtonImage;
	/** ���� ���� ��ư �̹����� ���� �� �ִ� ��ü */
	private ImageIcon gamePlayButtonImage;
	/** ���콺�� Back ��ư�� �������� ���� �̹��� */
	private ImageIcon backButtonEnteredImage;
	/** ���� ���� ��ư�� �������� ���� �̹��� */
	private ImageIcon gamePlayButtonEnteredImage;

	/** JButton���� backButton ���� */
	private JButton backButton;
	/** JButton���� gamePlayButton ���� */
	private JButton gamePlayButton;

	/** Thread ��ü */
	private Thread thread;

	/** Ball ��ġ ��� ���� ��ü */
	private Ball ball;

	/** ������ ���������� ���� ��� ���� ��ü*/
	private Circle circle;
	/** ��ֹ� ������ ���� ��ü */
	ArrayList<Obstacle> obstacles;

	/** fadeIn�� fadeOut �� ���� ���� fade Value�� ���� ������ �����ȴ�. */
	private float fadeValue;
	/** fadeIn�� fadeOut �� ���� ���� isFadeOut�� FadeIn�� �Ұ��� FadeOut�� �Ѱ��� �����ȴ�.. */
	private boolean isFadeOut;

	/** Screen ��� ���� ���� */
	private boolean isGameSelectScreen;

	/**
	 * ȭ����� ���� ��ü Frame�� InsideOut�� ������ �־�� insideOut�� �ִ� �г� ���� �޼ҵ带 ����Ҽ� �ִ�.
	 */
	private InsideOut insideOut;

	/**
	 * GameScreenPanel�� �����ڷ� �ʵ尪���� �ʱ�ȭ �����ְ�. insideOut�� �Ű������� �޾� ȭ����� �ϰ�
	 * 
	 * @param insideOut
	 */
	public GameScreenPanel(InsideOut insideOut) {
		// �������� �Ű������� �޾� �����Ѵ�.
		this.insideOut = insideOut;
		// fadeOut���� false�� �ʱ�ȭ ���ѹ���
		isFadeOut = false;
		// isGameSelectScreen�� ���� false�� �ʱ�ȭ �����ش�.
		isGameSelectScreen = false;
		// �����带 ����� ��������ش�.
		setThread(new Thread(this));
		// Image�� �ʱ�ȭ
		backButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/backButtonImage_2.png"));
		gamePlayButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/gamePlayButton.png"));
		backButtonEnteredImage = new ImageIcon(
				getClass().getClassLoader().getResource("images/backButtonEnteredImage_2.png"));
		gamePlayButtonEnteredImage = new ImageIcon(
				getClass().getClassLoader().getResource("images/gamePlayButtonEntered.png"));

		// ��ư�� ����
		backButton = new JButton(backButtonImage);
		gamePlayButton = new JButton(gamePlayButtonImage);

		// ��ֹ� ����
		obstacles = new ArrayList<Obstacle>();
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
		buttonSet(insideOut.getMenubarExitButton(), 1200, 0, 64, 28);
		// �޴��� ����
		add(insideOut.getMenubar());
		// x,y ��ǥ�� �ޱ� ���� ��ü ����
		ball = new Ball();
		// ���� ���� ��ü ����
		circle = new Circle(375,100,530,530,8,Color.WHITE);

		// test
		for (int i = 0; i < 36; i++) {
			obstacles.add(new Obstacle(ball.getRadius(), ball.getCircleX(), ball.getCircleY(), 10 * i));
			System.out.println(10 * i);
		}

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

	/**
	 * 	/**
	 * ��ư ���� �޼ҵ� ��� ��ư���� �������� �ֱ� ���ϵ��� �޼ҵ�� �������.
	 * JButton�� ��ġ��ǥ�� ũ�⸦ �������ָ� �ڵ����� �־��ش�.
	 * @param button
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
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
	/**
	 * fadeOut ȿ���� �ֱ����� �޼ҵ� temp�� ����� ������ fadeOut���� 0�� �Ѿ�� ������ �߻��ϱ� ������ float���� Ư����
	 * 0.1�� 10�� ���ҽ�Ű�� 1.0�� �ƴ϶� -0.000001�� �Ǽ� ������ �߻��Ѵ�. ���� temp�� ���ҽ�Ű�� fadeOut�� ���Խ�Ű��
	 * ����� ����Ѵ�. ���⼭ temp�� 0���� �۾����� temp�� 0�� �����ϰ� ���Խ����ش�.
	 */
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

	/**
	 * GameScreen�� ���õ� �̹����� �׷��ְ�
	 * ���� ���������� ���� �׷��ش�.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// graphics�� 2D�� ����
		Graphics2D g2 = (Graphics2D) g;
		// ������ �����ϱ� ���� �κ� fadeValue �� 1.0�̸� ������ 100%, 0.1�̸� �������� 10% �̴�.
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeValue));
		// ��Ƽ�ٸ���� , ���� ������ �ʰ� ���
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for (int i = 0; i < 36; i++) {
			g2.drawImage(obstacles.get(i).getObstacleImage(), obstacles.get(i).getX(), obstacles.get(i).getY(), null);
		}
		// ������� ����
		g2.setColor(circle.getColor());
		// �β� ����
		g2.setStroke(circle.getStroke());
		// ���� ����ִ� �� , x��ǥ, y��ǥ, width, height
		g2.drawOval(circle.getX(), circle.getY(), circle.getWidth(), circle.getHeight());
		// ���� ���� �� �� , ballŬ�������� ��� ���� ��ǥ�� ����ǹǷ� get�޼ҵ� �̿� , �츮�� ������ ��ü
		g2.fillOval(ball.getX(), ball.getY(), 26, 26);
	}

	/**
	 * �����带 ���� ���������� ȭ����ȯ�� �Ѵ�.
	 */
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
    /** ���� ȭ���� Thread�� ������ �Լ�
     * 
     * @return thread
     */
	public Thread getThread() {
		return thread;
	}
	/** ���� ȭ���� Thread�� �����ϴ� �Լ� 
	 * 
	 * @param Thread thread
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}

}
