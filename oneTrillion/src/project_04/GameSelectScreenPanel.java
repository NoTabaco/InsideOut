package project_04;

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

/** �÷��� �� ���� ���� ȭ�鿡 ���� ������ ����ִ� Ŭ����
 *  
 *  @author Jimin Kim
 *  @version 0.4
 *
 */

public class GameSelectScreenPanel extends JPanel implements Runnable {

	/** �� ���� ȭ���� ��� �̹����� ��� ��ü */
	private Image gameSelectBackGround;
	/** �� ���� ȭ���� ���� ���� �� ���� �̹����� ��� ��ü */
	private Image selectedImage; 
	/** �� ���� ȭ���� ���� ���� �� Ÿ��Ʋ �̹����� ��� ��ü */
	private Image selectedTitleImage;
	/** Ÿ��Ʋ �̹����� ��ġ�� �����ϱ� ���� X��ǥ ���� */
	private int selectedDrawX;
	/** Ÿ��Ʋ �̹����� ��ġ�� �����ϱ� ���� Y��ǥ ���� */
	private int selectedDrawY;

	/** backButton �̹����� ��� ��ü */
	private ImageIcon backButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/backButtonImage_2.png"));
	/** leftButton �̹����� ��� ��ü */
	private ImageIcon leftButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/leftButtonImage_2.png"));
	/** rightButton �̹����� ��� ��ü */
	private ImageIcon rightButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/rightButtonImage_2.png"));
	/** normalButton �̹����� ��� ��ü */
	private ImageIcon normalButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/normalButtonImage_2.png"));
	/** challengeButton �̹����� ��� ��ü */
	private ImageIcon challengeButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/challengeButtonImage_2.png"));
	/** practiceButton �̹����� ��� ��ü */
	private ImageIcon practiceButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/practiceButtonImage_2.png"));

	/** ���콺�� backButton�� �ö� ���� ���� �̹����� ��� ��ü */
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/backButtonEnteredImage_2.png"));
	/** ���콺�� leftButton�� �ö� ���� ���� �̹����� ��� ��ü */
	private ImageIcon leftButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/leftButtonEnteredImage_2.png"));
	/** ���콺�� rightButton�� �ö� ���� ���� �̹����� ��� ��ü */
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/rightButtonEnteredImage_2.png"));
	/** ���콺�� normalButton�� �ö� ���� ���� �̹����� ��� ��ü */
	private ImageIcon normalButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/normalButtonEnteredImage_2.png"));
	/** ���콺�� challengeButton�� �ö� ���� ���� �̹����� ��� ��ü */
	private ImageIcon challengeButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/challengeButtonEnteredImage_2.png"));
	/** ���콺�� practiceButton�� �ö� ���� ���� �̹����� ��� ��ü */
	private ImageIcon practiceButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/practiceButtonEnteredImage_2.png"));

	/** back JButton ��ü ���� */
	private JButton backButton = new JButton(backButtonImage);
	/** left JButton ��ü ���� */
	private JButton leftButton = new JButton(leftButtonImage);
	/** right JButton ��ü ���� */
	private JButton rightButton = new JButton(rightButtonImage);
	/** normal JButton ��ü ���� */
	private JButton normalButton = new JButton(normalButtonImage);
	/** challenge JButton ��ü ���� */
	private JButton challengeButton = new JButton(challengeButtonImage);
	/** practice JButton ��ü ���� */
	private JButton practiceButton = new JButton(practiceButtonImage);

	/** ȭ�� ��ȯ�� ����Ǵ� Music�� ��� ���� Thread ��ü*/
	private Thread thread;

	/** fadeIn�� ��� ������ ���� ���� */
	private float fadeValue;
	/** fadeOut�� ��� ������ ���� ���� */
	private boolean isFadeOut;

	/** MainScreen ��ȯ�� ������ boolean���� */
	private boolean isMainScreen;
	/** NormalGameScreen ��ȯ�� ������ boolean���� */
	private boolean isNormalGameScreen;
	/** ChallengeGameScreen ��ȯ�� ������ boolean���� */
	private boolean isChallengeGameScreen;
	/** PracticeGameScreen ��ȯ�� ������ boolean���� */
	private boolean isPracticeGameScreen;
    
	/** �ϳ��� � ���� ������ ������� ArrayList ��ü */
	private ArrayList<Track> trackList = new ArrayList<Track>();

	/** ���� ���õ� ���� �������ֱ� ���� ���� */
	private int nowSelected ;
	/** �� ���� ȭ�鿡�� ���� ������ ������ ��� ���� ��ü */
	private Music selectedMusic;

	/** �������� �Ű������� �ѱ�� ���� InsideOut ��ü */
	private InsideOut insideOut;
	
	/** �� ����ȭ���� GameSelectScreen�� ���� ���� ��� �� ������ ��� �ִ� ������
	 * 
	 * @param InsideOut insideOut
	 */
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
		
		// ���� ���õǾ��� ���� �ε���, ArrayList �ε����� 0������ �����ϹǷ� ó�� �־��� ���� �ε����� 0���� �����ϵ��� �Ͽ���.
		nowSelected = 0;

		// trackList�� ���� ���ϴ� ��� ȭ���� ���� 
		// ���� Ʈ��
		trackList.add(new Track("SunburstTitleImage.png", "sunburstGameselectImage_2.png", 
				"Tobu & Itro - Sunburst_Highlight.mp3", "Tobu & Itro - Sunburst.mp3", 420 , 180));
		// 1�� Ʈ��
		trackList.add(new Track("BadNewsTitleImage.png", "BadNewsImage.png",
				"BadNewsHighLight.mp3", "Lock N Bounce - Bad News.mp3", 375 , 180));
		// 2�� Ʈ�� 
		trackList.add(new Track("HeartBeatTitleImage.png", "HeartBeatImage.png",
				"HeartBeatHighLight.mp3", "Krale - Heartbeat,mp3" , 375 , 170));

		// Main Ŭ������ ��ġ�� ������� �ؼ� Resource�� �� �װ��� �̹������� ������ ���Խ����ش�.
		gameSelectBackGround = new ImageIcon(
				getClass().getClassLoader().getResource("images/gameSelectScreenImage_2.png")).getImage();


		// �޴��� exitButton ����
		buttonSet(insideOut.getMenubarExitButton(), 1200, 0, 64, 28);
		// �޴��� �߰�
		add(insideOut.getMenubar());
		// leftButton�� ��ġ ����
		buttonSet(leftButton, 100, 310, 120, 120); // 73, 98 (���� ũ��)
		// ������ ���� �����ְ� ����ش�. �ε����� nowSelected���� ���� �� ������ ������ 
		selectTrack(nowSelected);


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
				selectLeft();
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
				selectRight();
			}
		});

		// normalButton�� ��ġ ���� x��ǥ,y��ǥ,ũ�� (���� x ����)
		buttonSet(normalButton, 390, 360, 213, 40); //
		/**
		 * normalButton�� ���콺 �̺�Ʈ�� ó�����ش�.
		 */

		normalButton.addMouseListener(new MouseAdapter() {
			/**
			 * ���콺�� ������ ���� ������ �̺�Ʈ ó��
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ������ �̹����� Entered �̹����� ����
				normalButton.setIcon(normalButtonEnteredImage);
				// Ŀ�� �̹����� HAND_CURSOR�� �����ؼ� ���� �˾ƺ��� �����Ѵ�.
				normalButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� �������� ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				normalButton.setIcon(normalButtonImage);
				normalButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� practiceButton ������ �������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				// �븻 ��ư �̺�Ʈ ó��
				isFadeOut = true;
				isNormalGameScreen = true;
			}
		});

		// challengeButton�� ��ġ ���� x��ǥ,y��ǥ,ũ�� (���� x ����)
		buttonSet(challengeButton, 680, 360, 234, 38); //
		/**
		 * challengeButton�� ���콺 �̺�Ʈ�� ó�����ش�.
		 */

		challengeButton.addMouseListener(new MouseAdapter() {
			/**
			 * ���콺�� ������ ���� ������ �̺�Ʈ ó��
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ������ �̹����� Entered �̹����� ����
				challengeButton.setIcon(challengeButtonEnteredImage);
				// Ŀ�� �̹����� HAND_CURSOR�� �����ؼ� ���� �˾ƺ��� �����Ѵ�.
				challengeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� �������� ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				challengeButton.setIcon(challengeButtonImage);
				challengeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� practiceButton ������ �������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				// ç���� ��ư �̺�Ʈ ó��
				isFadeOut = true;
				isChallengeGameScreen = true;
			}
		});

		// practiceButton�� ��ġ ���� x��ǥ,y��ǥ,ũ�� (���� x ����)
		buttonSet(practiceButton, 540, 580, 213, 40); //
		/**
		 * practiceButton�� ���콺 �̺�Ʈ�� ó�����ش�.
		 */

		practiceButton.addMouseListener(new MouseAdapter() {
			/**
			 * ���콺�� ������ ���� ������ �̺�Ʈ ó��
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ������ �̹����� Entered �̹����� ����
				practiceButton.setIcon(practiceButtonEnteredImage);
				// Ŀ�� �̹����� HAND_CURSOR�� �����ؼ� ���� �˾ƺ��� �����Ѵ�.
				practiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� �������� ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				practiceButton.setIcon(practiceButtonImage);
				practiceButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� practiceButton ������ �������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				// ���� ��ư �̺�Ʈ ó��
				isFadeOut = true;
				isPracticeGameScreen = true;
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

	/** fadeIn ȿ���� �����ϱ� ���� �Լ�
	 * 
	 * temp�� ����� ������ fadeIn���� 1.0�� �Ѿ�� ������ �߻��Ѵ�.
	 * ������ float���� Ư���� 0.1�� 10�� ������Ű�� 1.0�� �ƴ϶� 1.000001�� �Ǳ� ������ ������ �߻��Ѵ�.
	 * ���� temp�� ������Ű�� fadeIn�� ���Խ�Ű�� ����� ����Ѵ�. ���⼭ temp�� 1���� Ŀ���� temp�� 1�� �����ϰ� ���Խ����ش�.
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
	
	/** fadeOut ȿ���� �����ϱ� ���� �Լ�
	 * 
	 * ����������, float������ Ư���� 0���Ϸ� �������� �Ǹ� 0�� �ƴ� ���� ������ ������ 0���� �۾����� 0���� �����Ѵ�.
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
    
    /** GameSelectScreen�� � ���� �̹����� �׷��ְų� ������ ������ �ִ� paint�Լ� 
     * 
     * @param g
     * */
   	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// graphics�� 2D�� ����
		Graphics2D g2 = (Graphics2D) g;
		// ������ �����ϱ� ���� �κ� fadeValue �� 1.0�̸� ������ 100%, 0.1�̸� �������� 10% �̴�.
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeValue));
		// ���� ���õ� ���� �̹����� �׷���
		g2.drawImage(selectedImage, 0, 0, null);
		// titleImage�� ��ġ�� ��� �ٸ��Ƿ� ��쿡 get�޼ҵ带 ���� ó���� ���־���.
		g2.drawImage(selectedTitleImage, selectedDrawX, selectedDrawY, null);
		// ���� ������� ��Ÿ���� ���� ���� �׷���
		g2.drawImage(gameSelectBackGround, 0, 0, null);
	}

	  /** �� ���� ȭ��(GameSelectScreen)�� Thread�� ���� �� �� ����Ǵ� �Լ� */
	@Override
	public void run() {
		fadeIn();
		while (true) {
			repaint();
			try {
				if (isFadeOut && (isMainScreen)) {
					fadeOut();
					insideOut.changeMainScreen(0);
					// �������� ���ư��� �ϱ� ������ ���� �����ϰ� �ִ� ������ �����Ѵ�.
					selectedMusic.close();
					return;

				} else if (isFadeOut && (isNormalGameScreen || isChallengeGameScreen || isPracticeGameScreen)) {
					fadeOut();
					insideOut.changeGameScreen();
					// ���� ȭ������  ��ȯ�ؾ� �ϱ� ������ ���� �����ϰ� �ִ� ������ �����Ѵ�.
					selectedMusic.close();
					return;
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	  /** �ڽ��� �÷��� �ϰ� ���� ���� ������ �� �ֵ��� �����ϱ� ���� �Լ� 
	   * 
	   * @param nowSelected
	   * */
	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		// �뷡 ���ý��� TitleImage ����
		selectedTitleImage = new ImageIcon(
				getClass().getClassLoader().getResource("images/" + trackList.get(nowSelected).getTitleImage())).getImage();
		// �뷡 ���ý��� StartImage ����
		selectedImage = new ImageIcon(
				getClass().getClassLoader().getResource("images/" + trackList.get(nowSelected).getStartImage())).getImage();
		// ���� ���� �� ���� Ÿ��Ʋ �̹����� X��ǥ�� ������ �Լ� 
		selectedDrawX = trackList.get(nowSelected).getDrawX();
		// ���� ���� �� ���� Ÿ��Ʋ �̹����� Y��ǥ�� ������ �Լ� 
		selectedDrawY = trackList.get(nowSelected).getDrawY();
		// Music ��ü�� ���� �������ν� �����ϰ��� �ϴ� ���� ���� �ݺ� ��Ų��.
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true, 0);
		selectedMusic.start(); // ���� ���
	}

	/** �� ����ȭ�鿡�� ���� ��ư�� ������ ���� �̺�Ʈ�� ó���ϴ� �Լ� */
	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1; // ù ��°��� ���� ��ư�� ������ ���� ������ ���� ���õǾ�� �ϱ� ����
		else
			nowSelected--; // �� ���� ���� 1�� ����
		selectTrack(nowSelected);
	}

	/** �� ����ȭ�鿡�� ������ ��ư�� ������ ���� �̺�Ʈ�� ó���ϴ� �Լ� */
	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0; // ���ʰ� �ݴ�
		else
			nowSelected++; // ���������� �� ���� ���� 1�� ����
		selectTrack(nowSelected);
	}

	/** JButton�� ��ġ�� �ٸ� ��ҵ��� �����ϱ� ���� �Լ�
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
    /** �� ���� ȭ���� Thread�� ������ �Լ�
     * 
     * @return thread
     */
	public Thread getThread() {
		return thread;
	}
    
	/** �� ���� ȭ���� Thread�� �����ϴ� �Լ� 
	 * 
	 * @param thread
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}
}