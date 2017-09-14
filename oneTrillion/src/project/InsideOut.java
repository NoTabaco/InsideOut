package project;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * ������ �������� ��Ʈ���� ���ִ� Ŭ�����̴�.
 * 
 * @author Yun
 *
 */
public class InsideOut extends JFrame {
	// ���� ���۸��� ���� ��ü ȭ���� �̹����� ��� �� �ν��Ͻ� (screenImage, screenGraphic) import �ʿ�
	private Image screenImage;
	private Graphics screenGraphic;

	// ��� �̹����� ���� �� �ִ� ��ü
	private Image introBackground;
	private Image introBackgroundCircle;

	// ��ư �̹����� ���� �� �ִ� ��ü
	private ImageIcon exitButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/exitButton.png"));
	private ImageIcon helpButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/helpButton.png"));
	private ImageIcon startButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/startButton.png"));

	// ��ư
	private JButton exitButton = new JButton(exitButtonImage);
	private JButton helpButton = new JButton(helpButtonImage);
	private JButton startButton = new JButton(startButtonImage);

	// ������ ���� �� �ִ� ��ü
	Music introMusic;

	// fadeIn�� fadeOut �� ���� ����
	private float fadeValue;

	public InsideOut() {
		// ����ȭ�鿡�� ��Ʈ�ι����� ���� �ݺ�
		// ������ �����԰� ���ÿ� ������ ���� ���
		introMusic = new Music("introMusic.mp3", true);
		introMusic.start();
		// �����̸� ����
		setTitle("Inside Out");
		// ����â ũ�� ����
		setSize(Main.SCREENT_WIDTH, Main.SCREENT_HEIGHT);
		// �޴��ٰ� ������ �ʰԲ� ����
		setUndecorated(true);
		// ����ڰ� ����â�� ���Ƿ� ���̰� �ϴ°� �Ұ���, true�� �ϸ� ����
		setResizable(false);
		// ����â�� �� �߾ӿ� ���
		setLocationRelativeTo(null);
		// ������ ���� ������ ����, �ݵ�� �ʿ��� �Լ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ȭ�� ��� ���� �⺻���� false �̹Ƿ� ���� ������Ѵ�.
		setVisible(true);
		// paintComponent�� �׸��� ������ ȸ������ ����
		setBackground(new Color(0, 0, 0, 0));
		// �����̳��� ũ�Ⱑ ����ɶ� ������Ʈ���� ũ��� ��ġ�� �ڵ������� ����Ǵµ� �װ� �����Ѵ�.
		setLayout(null);

		// Main Ŭ������ ��ġ�� ������� �ؼ� Resource�� �� �װ��� �̹������� ������ ���Խ����ش�.
		// ����̹���
		introBackground = new ImageIcon(getClass().getClassLoader().getResource("images/MainBackGround.png"))
				.getImage();
		introBackgroundCircle = new ImageIcon(
				getClass().getClassLoader().getResource("images/MainBackGroundCircle.gif")).getImage();
		buttonSet(startButton, 120, 450, 218, 38);
		buttonSet(helpButton, 95, 505, 218, 38);
		buttonSet(exitButton, 80, 565, 218, 38);

		// fadeIn �޼ҵ� ����
		fadeIn();
		

	}

	/**
	 * ��ư ���� �޼ҵ� ��� ��ư���� �������� �ֱ� �������Ƿ� �޼ҵ�� �������.
	 * 
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
	 * fadeIn ȿ���� �ֱ����� �޼ҵ�
	 * temp�� ����� ������ fadeIn���� 1.0�� �Ѿ�� ������ �߻��ϱ� ������
	 * float���� Ư���� 0.1�� 10�� ������Ű�� 1.0�� �ƴ϶� 1.000001�� �Ǽ� ������ �߻��Ѵ�.
	 * ���� temp�� ������Ű�� fadeIn�� ���Խ�Ű�� ����� ����Ѵ�.
	 * ���⼭ temp�� 1���� Ŀ���� temp�� 1�� �����ϰ� ���Խ����ش�.
	 */
	public void fadeIn() {
		float temp = 0;
		fadeValue = 0;
		while (fadeValue < 1) {
			temp += 0.1;
			if (temp > 1) {
				temp = 1.0f;
			}
			fadeValue = temp;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void fadeOut() {
		float temp = 1.0f;
		fadeValue = 1.0f;
		while (fadeValue > 0) {
			temp -= 0.1;
			if( temp < 0) {
				temp = 0;
			}
			fadeValue = temp;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * �ϳ��� ��ӵ� �� ���ǵǾ� �ִ� �޼ҵ��̴�. paint �޼ҵ�� JFrame�� ����� Ŭ�������� ���� ù��°�� ȭ���� �׷��ִ� �޼ҵ��̴�.
	 */
	public void paint(Graphics g) {
		// 1280 x 720 ��ŭ�� �̹����� ������ �־���
		screenImage = createImage(Main.SCREENT_WIDTH, Main.SCREENT_HEIGHT);
		// screenImage ���� �׷��Ȱ��� �� ������ ����
		screenGraphic = screenImage.getGraphics();
		/**
		 * screenDraw �Լ��� ���ؼ� �׷��� ���� screenGraphic�� �׸���. Graphics2D�� �����ؾ� �۾��� ������ �ʴ´�.
		 */
		screenDraw((Graphics2D) screenGraphic);
		// screenImage �� screenDraw���� �׸� �̹����� ȭ�鿡 ����ش�.
		g.drawImage(screenImage, 0, 0, null); // ȭ�鿡 ��ũ�� �̹����� �׷���
	}

	/**
	 * mainScreen�� 0,0�� �׷��ش�. g.drawImage�� �������� �׸��� �׸��� ����ϰ� �׻� �����ؾ��ϴ� �׸��� �׻� �����ؾ��ϴ�
	 * �׸��� paintComponents�� �̿��ؼ� �׸���. paintComponents�� �̿��ؼ� �׸���.
	 * 
	 * @param g
	 */
	public void screenDraw(Graphics2D g) {
		// ������ �����ϱ� ���� �κ� fadeValue �� 1.0�̸� ������ 100%, 0.1�̸� �������� 10% �̴�. 
		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeValue));
		g.drawImage(introBackgroundCircle, 275, 30, 1200, 676, null);
		g.drawImage(introBackground, 0, 0, null);

		paintComponents(g);

		// �ٽ� paint�޼ҵ带 ȣ���Ѵ�
		this.repaint(); // �ٽ� ����Ʈ �Լ��� �ҷ��� , ��ü �̹����� ��� �ݺ��ϸ� �׷���
	}
}
