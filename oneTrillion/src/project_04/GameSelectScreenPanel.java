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


public class GameSelectScreenPanel extends JPanel implements Runnable {

	// 배경 ?��미�?�? ?��?�� ?�� ?��?�� 객체
	private Image gameSelectBackGround;
	private Image musicSelectBackGround;
	private Image selectedImage; // ?��?��?���? ?��?�� 갔을 ?��?�� ?��미�?

	// ?��로�?�? 버튼 ?��미�?�? ?��?�� ?�� ?��?�� 객체
	private ImageIcon backButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/backButtonImage_2.png"));
	// ?���? 버튼 ?��미�?�? ?��?�� ?�� ?��?�� 객체
	private ImageIcon leftButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/leftButtonImage_2.png"));
	// ?��른쪽 버튼 ?��미�?�? ?��?�� ?�� ?��?�� 객체
	private ImageIcon rightButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/rightButtonImage_2.png"));
	// ?���? 버튼 ?��미�?�? ?��?�� ?�� ?��?�� 객체
	private ImageIcon normalButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/normalButtonImage_2.png"));
	// 챌린�? 버튼 ?��미�?�? ?��?�� ?�� ?��?�� 객체
	private ImageIcon challengeButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/challengeButtonImage_2.png"));
	// ?��?�� 버튼 ?��미�?�? ?��?�� ?�� ?��?�� 객체
	private ImageIcon practiceButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/practiceButtonImage_2.png"));

	// 마우?���? 버튼?�� 진입?��?�� ?��?�� ?��미�?
	private ImageIcon backButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/backButtonEnteredImage_2.png"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/leftButtonEnteredImage_2.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/rightButtonEnteredImage_2.png"));
	private ImageIcon normalButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/normalButtonEnteredImage_2.png"));
	private ImageIcon challengeButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/challengeButtonEnteredImage_2.png"));
	private ImageIcon practiceButtonEnteredImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/practiceButtonEnteredImage_2.png"));

	// JButton 구현
	private JButton backButton = new JButton(backButtonImage);
	private JButton leftButton = new JButton(leftButtonImage);
	private JButton rightButton = new JButton(rightButtonImage);
	private JButton normalButton = new JButton(normalButtonImage);
	private JButton challengeButton = new JButton(challengeButtonImage);
	private JButton practiceButton = new JButton(practiceButtonImage);

	// ?��?��?�� 객체 ?��?��
	private Thread thread;

	// fadeIn�? fadeOut ?�� ?��?�� �??��
	private float fadeValue;
	private boolean isFadeOut;

	// MainScreen ?��?���? ?��?�� �??��
	private boolean isMainScreen;
	// NormalGameScreen ?��?���? ?��?�� �??��
	private boolean isNormalGameScreen;
	// ChallengeGameScreen ?��?���? ?��?�� �??��
	private boolean isChallengeGameScreen;
	// PracticeGameScreen ?��?���? ?��?�� �??��
	private boolean isPracticeGameScreen;

	ArrayList<Track> trackList = new ArrayList<Track>();

	// �? 번째 곡을 ?���?, ?��?��?���? ?��?�� , ArrayList?�� ?��?��?�� 0�??�� ?��?��
	private int nowSelected = 0;
	private Music selectedMusic;

	// ?��?��?���? 맞는 ?��?���? ?��?��?��?�� ?���?�? insideout객체 ?��?��?�� ?��?�� ?��?��
	private InsideOut insideOut;

	GameSelectScreenPanel(InsideOut insideOut) {
		// ?��?��?��?�� 매개�??���? 받아 ?��?��?��?��.
		this.insideOut = insideOut;
		// fadeOut값을 false�? 초기?�� ?��켜문?��
		isFadeOut = false;
		// isMainScreen?�� 값을 false�? 초기?�� ?��켜�??��.
		isMainScreen = false;
		// ?��?��?���? 만들�? ?��?��?��켜�??��.
		setThread(new Thread(this));
		// 컨테?��?��?�� ?��기�? �?경될?�� 컴포?��?��?��?�� ?��기�? ?��치�? ?��?��?��?���? �?경되?��?�� 그걸 ?��?��?��?��
		setLayout(null);
		// 게임�? ?���? ?��?��
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// �??��?�� 바탕?�� ?��?�� ?�� ?���?�? �??��?��?���? ?��?��
		setBackground(Color.BLACK);
		// ?���? 출력 ?��?�� 기본값�? false ?���?�? ?��?�� ?��줘야?��?��.
		setVisible(true);
         
		// trackList�? ?��?�� ?��?��?�� 곡과 ?��면을 구현 ?��?��?��, ?��?�� ?��?���? 구현?�� �??��?��?��. 기본?��?���? 4�? 구성?���?
		trackList.add(new Track("sunburstGameselectImage_2.png", "sunburstGameselectImage_2.png", "Tobu & Itro - Sunburst_Highlight.mp3",
				"Tobu & Itro - Sunburst.mp3"));
		trackList.add(new Track("Metalika Start image.jpg", "Metalika Start image.jpg", "Master of puppets.mp3",
				"Master of puppets.mp3"));
		trackList.add(new Track("Defending champion Start image.png", "Defending champions Start Image.mp3",
				"Defending Champions.mp3", "Defending Champions.mp3"));
		trackList.add(new Track("Dasboot Start image.png", "Dasboot Start Image.png", "Dasboot.mp3", "Dasboot.mp3"));

		// Main ?��?��?��?�� ?��치�?? 기반?���? ?��?�� Resource�? ?��?��?�� 그것?�� ?��미�?값을 �??��?�� ???��?��켜�??��.
		gameSelectBackGround = new ImageIcon(
				getClass().getClassLoader().getResource("images/gameSelectScreenImage_2.png")).getImage();
		musicSelectBackGround = new ImageIcon(
				getClass().getClassLoader().getResource("images/sunburstGameselectImage_2.png")).getImage();

		// 메뉴�? exitButton ?��?��
		buttonSet(insideOut.getMenubarExitButton(), 1200, 0, 64, 28);
		// 메뉴�? ?��?��
		add(insideOut.getMenubar());
		// leftButton?�� ?���? ?��?��
		buttonSet(leftButton, 100, 310, 120, 120); // 73, 98 (?��?�� ?���?)
        // ?��?��?�� 곡을 보여주고 ?��?���??��. ?��?��?��?�� nowSelected값에 ?��?�� �? �?경이 �??��?�� 
		selectTrack(nowSelected);
		
		
		/**
		 * leftButton?�� 마우?�� ?��벤트�? 처리?���??��.
		 */

		leftButton.addMouseListener(new MouseAdapter() {
			/**
			 * 마우?���? ?��?���? ?��?�� ?��?��?�� ?��벤트 처리
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ?��?���? ?��미�?�? Entered ?��미�?�? �?�?
				leftButton.setIcon(leftButtonEnteredImage);
				// 커서 ?��미�??�� HAND_CURSOR�? �?경해?�� �??�� ?��?��보기 ?��게한?��.
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우?���? ?��?��콘을 벗어 ?��?��?�� ?��벤트 처리
			@Override
			public void mouseExited(MouseEvent e) {
				// ?��?���? ?��미�?�? 기본?��미졸 �?�?
				leftButton.setIcon(leftButtonImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 마우?���? leftbutton ?��?���? ?��???��?�� ?��벤트 처리
			@Override
			public void mousePressed(MouseEvent e) {
				// ?���? 버튼 ?��벤트 처리
				selectLeft();
			}
		});

		// rightButton?�� ?���? ?��?��
		buttonSet(rightButton, 1050, 310, 120, 120); // 73 98 (?��?�� ?���?)
		/**
		 * rightButton?�� 마우?�� ?��벤트�? 처리?���??��.
		 */

		rightButton.addMouseListener(new MouseAdapter() {
			/**
			 * 마우?���? ?��?���? ?��?�� ?��?��?�� ?��벤트 처리
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ?��?���? ?��미�?�? Entered ?��미�?�? �?�?
				rightButton.setIcon(rightButtonEnteredImage);
				// 커서 ?��미�??�� HAND_CURSOR�? �?경해?�� �??�� ?��?��보기 ?��게한?��.
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우?���? ?��?��콘을 벗어 ?��?��?�� ?��벤트 처리
			@Override
			public void mouseExited(MouseEvent e) {
				// ?��?���? ?��미�?�? 기본?��미졸 �?�?
				rightButton.setIcon(rightButtonImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 마우?���? leftbutton ?��?���? ?��???��?�� ?��벤트 처리
			@Override
			public void mousePressed(MouseEvent e) {
				// ?��른쪽 버튼 ?��벤트 처리
				  selectRight();
			}
		});

		// normalButton?�� ?���? ?��?�� x좌표,y좌표,?���? (�?�? x ?���?)
		buttonSet(normalButton, 390, 360, 213, 40); //
		/**
		 * normalButton?�� 마우?�� ?��벤트�? 처리?���??��.
		 */

		normalButton.addMouseListener(new MouseAdapter() {
			/**
			 * 마우?���? ?��?���? ?��?�� ?��?��?�� ?��벤트 처리
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ?��?���? ?��미�?�? Entered ?��미�?�? �?�?
				normalButton.setIcon(normalButtonEnteredImage);
				// 커서 ?��미�??�� HAND_CURSOR�? �?경해?�� �??�� ?��?��보기 ?��게한?��.
				normalButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우?���? ?��?��콘을 벗어 ?��?��?�� ?��벤트 처리
			@Override
			public void mouseExited(MouseEvent e) {
				// ?��?���? ?��미�?�? 기본?��미졸 �?�?
				normalButton.setIcon(normalButtonImage);
				normalButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 마우?���? practiceButton ?��?���? ?��???��?�� ?��벤트 처리
			@Override
			public void mousePressed(MouseEvent e) {
				// ?���? 버튼 ?��벤트 처리
				isFadeOut = true;
				isNormalGameScreen = true;
			}
		});

		// challengeButton?�� ?���? ?��?�� x좌표,y좌표,?���? (�?�? x ?���?)
		buttonSet(challengeButton, 680, 360, 234, 38); //
		/**
		 * challengeButton?�� 마우?�� ?��벤트�? 처리?���??��.
		 */

		challengeButton.addMouseListener(new MouseAdapter() {
			/**
			 * 마우?���? ?��?���? ?��?�� ?��?��?�� ?��벤트 처리
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ?��?���? ?��미�?�? Entered ?��미�?�? �?�?
				challengeButton.setIcon(challengeButtonEnteredImage);
				// 커서 ?��미�??�� HAND_CURSOR�? �?경해?�� �??�� ?��?��보기 ?��게한?��.
				challengeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우?���? ?��?��콘을 벗어 ?��?��?�� ?��벤트 처리
			@Override
			public void mouseExited(MouseEvent e) {
				// ?��?���? ?��미�?�? 기본?��미졸 �?�?
				challengeButton.setIcon(challengeButtonImage);
				challengeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 마우?���? practiceButton ?��?���? ?��???��?�� ?��벤트 처리
			@Override
			public void mousePressed(MouseEvent e) {
				// 챌린�? 버튼 ?��벤트 처리
				isFadeOut = true;
				isChallengeGameScreen = true;
			}
		});

		// practiceButton?�� ?���? ?��?�� x좌표,y좌표,?���? (�?�? x ?���?)
		buttonSet(practiceButton, 540, 580, 213, 40); //
		/**
		 * practiceButton?�� 마우?�� ?��벤트�? 처리?���??��.
		 */

		practiceButton.addMouseListener(new MouseAdapter() {
			/**
			 * 마우?���? ?��?���? ?��?�� ?��?��?�� ?��벤트 처리
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ?��?���? ?��미�?�? Entered ?��미�?�? �?�?
				practiceButton.setIcon(practiceButtonEnteredImage);
				// 커서 ?��미�??�� HAND_CURSOR�? �?경해?�� �??�� ?��?��보기 ?��게한?��.
				practiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우?���? ?��?��콘을 벗어 ?��?��?�� ?��벤트 처리
			@Override
			public void mouseExited(MouseEvent e) {
				// ?��?���? ?��미�?�? 기본?��미졸 �?�?
				practiceButton.setIcon(practiceButtonImage);
				practiceButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 마우?���? practiceButton ?��?���? ?��???��?�� ?��벤트 처리
			@Override
			public void mousePressed(MouseEvent e) {
				// ?��?�� 버튼 ?��벤트 처리
				isFadeOut = true;
				isPracticeGameScreen = true;
			}
		});

		// backButton?�� ?���? ?��?��
		buttonSet(backButton, 80, 60, 228, 57);
		/**
		 * backButton?�� 마우?�� ?��벤트�? 처리?���??��.
		 */

		backButton.addMouseListener(new MouseAdapter() {
			/**
			 * 마우?���? ?��?���? ?��?�� ?��?��?�� ?��벤트 처리
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ?��?���? ?��미�?�? Entered ?��미�?�? �?�?
				backButton.setIcon(backButtonEnteredImage);
				// 커서 ?��미�??�� HAND_CURSOR�? �?경해?�� �??�� ?��?��보기 ?��게한?��.
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// 마우?���? ?��?��콘을 벗어 ?��?��?�� ?��벤트 처리
			@Override
			public void mouseExited(MouseEvent e) {
				// ?��?���? ?��미�?�? 기본?��미졸 �?�?
				backButton.setIcon(backButtonImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// 마우?���? backButton ?��?���? ?��???��?�� ?��벤트 처리
			@Override
			public void mousePressed(MouseEvent e) {
				isFadeOut = true;
				isMainScreen = true;
			}
		});

	}

	/**
	 * fadeIn ?��과�?? 주기?��?�� 메소?�� temp�? ?��?��?�� ?��?��?�� fadeIn값이 1.0?�� ?��?���?�? ?��?���? 발생?���? ?��문에 float?��?�� ?��?��?��
	 * 0.1?�� 10�? 증�??��?���? 1.0?�� ?��?��?�� 1.000001?�� ?��?�� ?��?���? 발생?��?��. ?��?��?�� temp�? 증�??��?���? fadeIn?�� ???��?��?��?��
	 * 방식?�� ?��?��?��?��. ?��기서 temp�? 1보다 커�?�? temp�? 1�? ?��?��?���? ???��?��켜�??��.
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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// graphics�? 2D�? �?�?
		Graphics2D g2 = (Graphics2D) g;
		// ?��명도�? 조절?���? ?��?�� �?�? fadeValue �? 1.0?���? 불투명도 100%, 0.1?���? 불투명도�? 10% ?��?��.
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeValue));
		g2.drawImage(musicSelectBackGround, 0, 0, null);
		g2.drawImage(gameSelectBackGround, 0, 0, null);
		g2.drawImage(selectedImage, 0, 0, null);
	}

	// run ?��?��?��?�� while문을 ?��?�� 계속 ?��면을 그려줌으로써 ?��?�� ?��면으�? ?��?���? ?�� ?���? ?���??��.
	@Override
	public void run() {
		fadeIn();
		while (true) {
			repaint();
			try {
				if (isFadeOut && (isMainScreen)) {
                	fadeOut();
              		insideOut.changeMainScreen(0);
              		// 메인?���? ?��?���??�� ?���? ?��문에 ?��?�� ?��?��?���? ?��?�� ?��?��?�� 종료?��?��.
					selectedMusic.close();
					return;

				} else if (isFadeOut && (isNormalGameScreen || isChallengeGameScreen || isPracticeGameScreen)) {
					fadeOut();
	            	insideOut.changeGameScreen();
	    			// 게임 ?��면으�?  ?��?��?��?�� ?���? ?��문에 ?��?�� ?��?��?���? ?��?�� ?��?��?�� 종료?��?��.
					selectedMusic.close();
					return;
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

    // ?��?���? ?��?��?���? ?��?�� 만든 메소?�� 
	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		// ?��?�� ?��?��?��?�� image 구현
		selectedImage = new ImageIcon(
				getClass().getClassLoader().getResource("images/" + trackList.get(nowSelected).getStartImage())).getImage();
		// Music 객체�? ?���? 만듦?��로써 ?��?��?��고자 ?��?�� 곡을 무한 반복 ?��?��?��.
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true,0);
		selectedMusic.start(); // 무한 ?��?��
	}

	// ?���? 버튼?�� ?��???�� ?��?�� ?��벤트 처리
	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1; // �? 번째곡에?�� ?���? 버튼?�� ?��르면 �??�� ?��른쪽 곡이 ?��?��?��?��?�� ?���? ?���?
		else
			nowSelected--; // �? ?��?�� 경우?�� 1?�� 빼줌
		selectTrack(nowSelected);
	}

	// ?��른쪽 버튼?�� ?��???�� ?��?�� ?��벤트 처리
	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0; // ?��쪽과 반�?
		else
			nowSelected++; // 마찬�?�?�? �? ?��?�� 경우?�� 1?�� ?��?��
		selectTrack(nowSelected);
	}

	// ?��?��?�� ?�� ?��?��?���? ?��?���?�? 메소?���? ?��?�� ?��?���? 버튼?�� ?��치�?? ?��?��
	public void buttonSet(JButton button, int x, int y, int width, int height) {
		button.setBounds(x, y, width, height);
		// 버튼 ?��?���? ?���?
		button.setBorderPainted(false);
		// ?��르는 ?��?�� ?���?
		button.setContentAreaFilled(false);
		// �??�� ?��?���? ?���?
		button.setFocusPainted(false);
		// 버튼 추�?
		add(button);
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
}