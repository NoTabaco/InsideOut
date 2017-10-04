package project_03;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class GameSelectScreenPanel extends JPanel implements Runnable {

	// ��� �̹����� ���� �� �ִ� ��ü
	private Image gameSelectBackGround;
	private Image musicSelectBackGround;

	// �ڷΰ��� ��ư �̹����� ���� �� �ִ� ��ü
	private ImageIcon backButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/backButtonImage.png"));
	// ���� ��ư �̹����� ���� �� �ִ� ��ü
	private ImageIcon leftButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/leftButtonImage.png"));
	// ������ ��ư �̹����� ���� �� �ִ� ��ü
	private ImageIcon rightButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/rightButtonImage.png"));

	// ���콺�� ��ư�� �������� ���� �̹���
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/backButtonEnteredImage.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/leftButtonEnteredImage.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/rightButtonEnteredImage.png"));

	// JButton ����
	private JButton backButton = new JButton(backButtonImage);
	private JButton leftButton = new JButton(leftButtonImage);
	private JButton rightButton = new JButton(rightButtonImage);

	// ������ ��ü ����
	private Thread thread;

	// fadeIn�� fadeOut �� ���� ����
	private float fadeValue;
	private boolean isFadeOut;

	// MainScreen ��� ���� ����
	private boolean isMainScreen;

	ArrayList<Track> trackList = new ArrayList<Track>();

	// �ڽſ��� �´� �ǳڷ� �����ؾ� �ϹǷ� insideout��ü ������ ���� ����
	private InsideOut insideOut;

	GameSelectScreenPanel(InsideOut insideOut) {
		// �������� �Ű������� �޾� �����Ѵ�.
		this.insideOut = insideOut;
		// fadeOut���� false�� �ʱ�ȭ ���ѹ���
		isFadeOut = false;
		// isMainScreen�� ���� false�� �ʱ�ȭ �����ش�.
		isMainScreen = false;
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

		// Main Ŭ������ ��ġ�� ������� �ؼ� Resource�� �� �װ��� �̹������� ������ ���Խ����ش�.
		gameSelectBackGround = new ImageIcon(
				getClass().getClassLoader().getResource("images/gameSelectScreenImage_2.png")).getImage();
		musicSelectBackGround = new ImageIcon(
				getClass().getClassLoader().getResource("images/sunburstGameselectImage_2.png")).getImage();
		// �޴��� exitButton ����
		buttonSet(insideOut.getMenubarExitButton(), 1200, 0, 64, 28);
		// �޴��� ����
		add(insideOut.getMenubar());
		// leftButton�� ��ġ ����
		buttonSet(leftButton, 100, 310, 120, 120); // 73, 98 (���� ũ��)

		/**
		 * leftButton�� ���콺 �̺�Ʈ�� ó�����ش�.
		 */

		leftButton.addMouseListener(new MouseAdapter() {
			/**
			 * ���콺�� ������ ���� ������ �̺�Ʈ ó��
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ������ �̹����� Entered �̹����� ����
				leftButton.setIcon(leftButtonEnteredImage);
				// Ŀ�� �̹����� HAND_CURSOR�� �����ؼ� ���� �˾ƺ��� �����Ѵ�.
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� �������� ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				leftButton.setIcon(leftButtonImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� leftbutton ������ �������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				// ���� ��ư �̺�Ʈ ó��
			}
		});

		// rightButton�� ��ġ ����
		buttonSet(rightButton, 1050, 310, 120, 120); // 73 98 (���� ũ��)
		/**
		 * rightButton�� ���콺 �̺�Ʈ�� ó�����ش�.
		 */

		rightButton.addMouseListener(new MouseAdapter() {
			/**
			 * ���콺�� ������ ���� ������ �̺�Ʈ ó��
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ������ �̹����� Entered �̹����� ����
				rightButton.setIcon(rightButtonEnteredImage);
				// Ŀ�� �̹����� HAND_CURSOR�� �����ؼ� ���� �˾ƺ��� �����Ѵ�.
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� �������� ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				rightButton.setIcon(rightButtonImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� leftbutton ������ �������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				// ������ ��ư �̺�Ʈ ó��
			}
		});

		// backButton�� ��ġ ����
		buttonSet(backButton, 80, 60, 228, 57);
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
				isMainScreen = true;
			}
		});

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
				Thread.sleep(100);
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
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// graphics�� 2D�� ����
		Graphics2D g2 = (Graphics2D) g;
		// ������ �����ϱ� ���� �κ� fadeValue �� 1.0�̸� ������ 100%, 0.1�̸� �������� 10% �̴�.
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeValue));
		g2.drawImage(musicSelectBackGround, 0, 0, null);
		g2.drawImage(gameSelectBackGround, 0, 0, null);
	}

	// run �Լ����� while���� ���� ��� ȭ���� �׷������ν� ���� ȭ������ �Ѿ �� �ְ� ���ش�.
	@Override
	public void run() {
		fadeIn();
		while (true) {
			repaint();
			try {
				if (isFadeOut && isMainScreen) {
					fadeOut();
					insideOut.changeMainScreen();
					return;
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

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

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
}
