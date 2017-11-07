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

	// λ°°κ²½ ?΄λ―Έμ?λ₯? ?΄? ? ?? κ°μ²΄
	private Image gameSelectBackGround;
	private Image musicSelectBackGround;
	private Image selectedImage; // ???κ³? ?€?΄ κ°μ ?? ?΄λ―Έμ?

	// ?€λ‘κ?κΈ? λ²νΌ ?΄λ―Έμ?λ₯? ?΄? ? ?? κ°μ²΄
	private ImageIcon backButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/backButtonImage_2.png"));
	// ?Όμͺ? λ²νΌ ?΄λ―Έμ?λ₯? ?΄? ? ?? κ°μ²΄
	private ImageIcon leftButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/leftButtonImage_2.png"));
	// ?€λ₯Έμͺ½ λ²νΌ ?΄λ―Έμ?λ₯? ?΄? ? ?? κ°μ²΄
	private ImageIcon rightButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/rightButtonImage_2.png"));
	// ?Έλ§? λ²νΌ ?΄λ―Έμ?λ₯? ?΄? ? ?? κ°μ²΄
	private ImageIcon normalButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/normalButtonImage_2.png"));
	// μ±λ¦°μ§? λ²νΌ ?΄λ―Έμ?λ₯? ?΄? ? ?? κ°μ²΄
	private ImageIcon challengeButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/challengeButtonImage_2.png"));
	// ?°?΅ λ²νΌ ?΄λ―Έμ?λ₯? ?΄? ? ?? κ°μ²΄
	private ImageIcon practiceButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/practiceButtonImage_2.png"));

	// λ§μ°?€κ°? λ²νΌ? μ§μ?? ?? ?΄λ―Έμ?
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

	// JButton κ΅¬ν
	private JButton backButton = new JButton(backButtonImage);
	private JButton leftButton = new JButton(leftButtonImage);
	private JButton rightButton = new JButton(rightButtonImage);
	private JButton normalButton = new JButton(normalButtonImage);
	private JButton challengeButton = new JButton(challengeButtonImage);
	private JButton practiceButton = new JButton(practiceButtonImage);

	// ?°? ? κ°μ²΄ ? ?Έ
	private Thread thread;

	// fadeInκ³? fadeOut ? ?? λ³??
	private float fadeValue;
	private boolean isFadeOut;

	// MainScreen ? ?΄λ₯? ?? λ³??
	private boolean isMainScreen;
	// NormalGameScreen ? ?΄λ₯? ?? λ³??
	private boolean isNormalGameScreen;
	// ChallengeGameScreen ? ?΄λ₯? ?? λ³??
	private boolean isChallengeGameScreen;
	// PracticeGameScreen ? ?΄λ₯? ?? λ³??
	private boolean isPracticeGameScreen;

	ArrayList<Track> trackList = new ArrayList<Track>();

	// μ²? λ²μ§Έ κ³‘μ ?λ―?, ?Έ?±?€λ‘? ?? , ArrayList? ?Έ?±?€ 0λΆ??° ??
	private int nowSelected = 0;
	private Music selectedMusic;

	// ?? ?κ²? λ§λ ??¬λ‘? ? ?΄?΄?Ό ?λ―?λ‘? insideoutκ°μ²΄ ? ?Έ? ?΅?΄ ? ?΄
	private InsideOut insideOut;

	GameSelectScreenPanel(InsideOut insideOut) {
		// ?? ?? λ§€κ°λ³??λ‘? λ°μ ? ?΄??€.
		this.insideOut = insideOut;
		// fadeOutκ°μ falseλ‘? μ΄κΈ°? ?μΌλ¬Έ?€
		isFadeOut = false;
		// isMainScreen? κ°μ falseλ‘? μ΄κΈ°? ?μΌμ??€.
		isMainScreen = false;
		// ?°? ?λ₯? λ§λ€κ³? ?€??μΌμ??€.
		setThread(new Thread(this));
		// μ»¨ν?΄?? ?¬κΈ°κ? λ³?κ²½λ ? μ»΄ν¬??Έ?€? ?¬κΈ°μ? ?μΉκ? ??? ?Όλ‘? λ³?κ²½λ??° κ·Έκ±Έ ?΄? ??€
		setLayout(null);
		// κ²μμ°? ?¬κΈ? ?€? 
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// κ²?? ? λ°ν? ?°? ? ?΄λ―?λ‘? κ²?? ??Όλ‘? ?€? 
		setBackground(Color.BLACK);
		// ?λ©? μΆλ ₯ ?€?  κΈ°λ³Έκ°μ? false ?΄λ―?λ‘? ?€?  ?΄μ€μΌ??€.
		setVisible(true);
         
		// trackListλ₯? ?΅?΄ ??? κ³‘κ³Ό ?λ©΄μ κ΅¬ν ?°?Ό?, ?΄?° ??Όλ‘? κ΅¬ν?΄ κ°??₯??€. κΈ°λ³Έ? ?Όλ‘? 4κ°? κ΅¬μ±?΄λ΄?
		trackList.add(new Track("sunburstGameselectImage_2.png", "sunburstGameselectImage_2.png", "Tobu & Itro - Sunburst_Highlight.mp3",
				"Tobu & Itro - Sunburst.mp3"));
		trackList.add(new Track("Metalika Start image.jpg", "Metalika Start image.jpg", "Master of puppets.mp3",
				"Master of puppets.mp3"));
		trackList.add(new Track("Defending champion Start image.png", "Defending champions Start Image.mp3",
				"Defending Champions.mp3", "Defending Champions.mp3"));
		trackList.add(new Track("Dasboot Start image.png", "Dasboot Start Image.png", "Dasboot.mp3", "Dasboot.mp3"));

		// Main ?΄??€? ?μΉλ?? κΈ°λ°?Όλ‘? ?΄? Resourceλ₯? ?»?΄? κ·Έκ²? ?΄λ―Έμ?κ°μ λ³??? ????μΌμ??€.
		gameSelectBackGround = new ImageIcon(
				getClass().getClassLoader().getResource("images/gameSelectScreenImage_2.png")).getImage();
		musicSelectBackGround = new ImageIcon(
				getClass().getClassLoader().getResource("images/sunburstGameselectImage_2.png")).getImage();

		// λ©λ΄λ°? exitButton ?€? 
		buttonSet(insideOut.getMenubarExitButton(), 1200, 0, 64, 28);
		// λ©λ΄λ°? ?€? 
		add(insideOut.getMenubar());
		// leftButton? ?μΉ? ?€? 
		buttonSet(leftButton, 100, 310, 120, 120); // 73, 98 (?? ?¬κΈ?)
        // ? ??  κ³‘μ λ³΄μ¬μ£Όκ³  ?€? €μ€??€. ?Έ?±?€?Έ nowSelectedκ°μ ?°?Ό κ³? λ³?κ²½μ΄ κ°??₯?¨ 
		selectTrack(nowSelected);
		
		
		/**
		 * leftButton? λ§μ°?€ ?΄λ²€νΈλ₯? μ²λ¦¬?΄μ€??€.
		 */

		leftButton.addMouseListener(new MouseAdapter() {
			/**
			 * λ§μ°?€κ°? ??΄μ½? ?? ??? ?΄λ²€νΈ μ²λ¦¬
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? Entered ?΄λ―Έμ?λ‘? λ³?κ²?
				leftButton.setIcon(leftButtonEnteredImage);
				// μ»€μ ?΄λ―Έμ?? HAND_CURSORλ‘? λ³?κ²½ν΄? μ’?? ??λ³΄κΈ° ?½κ²ν?€.
				leftButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// λ§μ°?€κ°? ??΄μ½μ λ²μ΄ ?¬?? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mouseExited(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? κΈ°λ³Έ?΄λ―Έμ‘Έ λ³?κ²?
				leftButton.setIcon(leftButtonImage);
				leftButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// λ§μ°?€κ°? leftbutton ??΄μ½? ????? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mousePressed(MouseEvent e) {
				// ?Όμͺ? λ²νΌ ?΄λ²€νΈ μ²λ¦¬
				selectLeft();
			}
		});

		// rightButton? ?μΉ? ?€? 
		buttonSet(rightButton, 1050, 310, 120, 120); // 73 98 (?? ?¬κΈ?)
		/**
		 * rightButton? λ§μ°?€ ?΄λ²€νΈλ₯? μ²λ¦¬?΄μ€??€.
		 */

		rightButton.addMouseListener(new MouseAdapter() {
			/**
			 * λ§μ°?€κ°? ??΄μ½? ?? ??? ?΄λ²€νΈ μ²λ¦¬
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? Entered ?΄λ―Έμ?λ‘? λ³?κ²?
				rightButton.setIcon(rightButtonEnteredImage);
				// μ»€μ ?΄λ―Έμ?? HAND_CURSORλ‘? λ³?κ²½ν΄? μ’?? ??λ³΄κΈ° ?½κ²ν?€.
				rightButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// λ§μ°?€κ°? ??΄μ½μ λ²μ΄ ?¬?? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mouseExited(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? κΈ°λ³Έ?΄λ―Έμ‘Έ λ³?κ²?
				rightButton.setIcon(rightButtonImage);
				rightButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// λ§μ°?€κ°? leftbutton ??΄μ½? ????? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mousePressed(MouseEvent e) {
				// ?€λ₯Έμͺ½ λ²νΌ ?΄λ²€νΈ μ²λ¦¬
				  selectRight();
			}
		});

		// normalButton? ?μΉ? ?€?  xμ’ν,yμ’ν,?¬κΈ? (κ°?λ‘? x ?Έλ‘?)
		buttonSet(normalButton, 390, 360, 213, 40); //
		/**
		 * normalButton? λ§μ°?€ ?΄λ²€νΈλ₯? μ²λ¦¬?΄μ€??€.
		 */

		normalButton.addMouseListener(new MouseAdapter() {
			/**
			 * λ§μ°?€κ°? ??΄μ½? ?? ??? ?΄λ²€νΈ μ²λ¦¬
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? Entered ?΄λ―Έμ?λ‘? λ³?κ²?
				normalButton.setIcon(normalButtonEnteredImage);
				// μ»€μ ?΄λ―Έμ?? HAND_CURSORλ‘? λ³?κ²½ν΄? μ’?? ??λ³΄κΈ° ?½κ²ν?€.
				normalButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// λ§μ°?€κ°? ??΄μ½μ λ²μ΄ ?¬?? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mouseExited(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? κΈ°λ³Έ?΄λ―Έμ‘Έ λ³?κ²?
				normalButton.setIcon(normalButtonImage);
				normalButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// λ§μ°?€κ°? practiceButton ??΄μ½? ????? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mousePressed(MouseEvent e) {
				// ?Έλ§? λ²νΌ ?΄λ²€νΈ μ²λ¦¬
				isFadeOut = true;
				isNormalGameScreen = true;
			}
		});

		// challengeButton? ?μΉ? ?€?  xμ’ν,yμ’ν,?¬κΈ? (κ°?λ‘? x ?Έλ‘?)
		buttonSet(challengeButton, 680, 360, 234, 38); //
		/**
		 * challengeButton? λ§μ°?€ ?΄λ²€νΈλ₯? μ²λ¦¬?΄μ€??€.
		 */

		challengeButton.addMouseListener(new MouseAdapter() {
			/**
			 * λ§μ°?€κ°? ??΄μ½? ?? ??? ?΄λ²€νΈ μ²λ¦¬
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? Entered ?΄λ―Έμ?λ‘? λ³?κ²?
				challengeButton.setIcon(challengeButtonEnteredImage);
				// μ»€μ ?΄λ―Έμ?? HAND_CURSORλ‘? λ³?κ²½ν΄? μ’?? ??λ³΄κΈ° ?½κ²ν?€.
				challengeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// λ§μ°?€κ°? ??΄μ½μ λ²μ΄ ?¬?? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mouseExited(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? κΈ°λ³Έ?΄λ―Έμ‘Έ λ³?κ²?
				challengeButton.setIcon(challengeButtonImage);
				challengeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// λ§μ°?€κ°? practiceButton ??΄μ½? ????? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mousePressed(MouseEvent e) {
				// μ±λ¦°μ§? λ²νΌ ?΄λ²€νΈ μ²λ¦¬
				isFadeOut = true;
				isChallengeGameScreen = true;
			}
		});

		// practiceButton? ?μΉ? ?€?  xμ’ν,yμ’ν,?¬κΈ? (κ°?λ‘? x ?Έλ‘?)
		buttonSet(practiceButton, 540, 580, 213, 40); //
		/**
		 * practiceButton? λ§μ°?€ ?΄λ²€νΈλ₯? μ²λ¦¬?΄μ€??€.
		 */

		practiceButton.addMouseListener(new MouseAdapter() {
			/**
			 * λ§μ°?€κ°? ??΄μ½? ?? ??? ?΄λ²€νΈ μ²λ¦¬
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? Entered ?΄λ―Έμ?λ‘? λ³?κ²?
				practiceButton.setIcon(practiceButtonEnteredImage);
				// μ»€μ ?΄λ―Έμ?? HAND_CURSORλ‘? λ³?κ²½ν΄? μ’?? ??λ³΄κΈ° ?½κ²ν?€.
				practiceButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// λ§μ°?€κ°? ??΄μ½μ λ²μ΄ ?¬?? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mouseExited(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? κΈ°λ³Έ?΄λ―Έμ‘Έ λ³?κ²?
				practiceButton.setIcon(practiceButtonImage);
				practiceButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// λ§μ°?€κ°? practiceButton ??΄μ½? ????? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mousePressed(MouseEvent e) {
				// ?°?΅ λ²νΌ ?΄λ²€νΈ μ²λ¦¬
				isFadeOut = true;
				isPracticeGameScreen = true;
			}
		});

		// backButton? ?μΉ? ?€? 
		buttonSet(backButton, 80, 60, 228, 57);
		/**
		 * backButton? λ§μ°?€ ?΄λ²€νΈλ₯? μ²λ¦¬?΄μ€??€.
		 */

		backButton.addMouseListener(new MouseAdapter() {
			/**
			 * λ§μ°?€κ°? ??΄μ½? ?? ??? ?΄λ²€νΈ μ²λ¦¬
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? Entered ?΄λ―Έμ?λ‘? λ³?κ²?
				backButton.setIcon(backButtonEnteredImage);
				// μ»€μ ?΄λ―Έμ?? HAND_CURSORλ‘? λ³?κ²½ν΄? μ’?? ??λ³΄κΈ° ?½κ²ν?€.
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// λ§μ°?€κ°? ??΄μ½μ λ²μ΄ ?¬?? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mouseExited(MouseEvent e) {
				// ??΄μ½? ?΄λ―Έμ?λ₯? κΈ°λ³Έ?΄λ―Έμ‘Έ λ³?κ²?
				backButton.setIcon(backButtonImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// λ§μ°?€κ°? backButton ??΄μ½? ????? ?΄λ²€νΈ μ²λ¦¬
			@Override
			public void mousePressed(MouseEvent e) {
				isFadeOut = true;
				isMainScreen = true;
			}
		});

	}

	/**
	 * fadeIn ?¨κ³Όλ?? μ£ΌκΈ°?? λ©μ? tempλ₯? ?¬?©? ?΄? ? fadeInκ°μ΄ 1.0? ??΄κ°?λ©? ??¬κ°? λ°μ?κΈ? ?λ¬Έμ float?°?° ?Ή?±?
	 * 0.1?© 10λ²? μ¦κ???€λ©? 1.0?΄ ???Ό 1.000001?΄ ?? ??¬κ°? λ°μ??€. ?°?Ό? tempλ₯? μ¦κ???€κ³? fadeIn? ?????€?
	 * λ°©μ? ?¬?©??€. ?¬κΈ°μ tempκ°? 1λ³΄λ€ μ»€μ?λ©? tempλ₯? 1λ‘? ?€? ?κ³? ????μΌμ??€.
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
		// graphicsλ₯? 2Dλ‘? λ³?κ²?
		Graphics2D g2 = (Graphics2D) g;
		// ?¬λͺλλ₯? μ‘°μ ?κΈ? ?? λΆ?λΆ? fadeValue κ°? 1.0?΄λ©? λΆν¬λͺλ 100%, 0.1?΄λ©? λΆν¬λͺλκ°? 10% ?΄?€.
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeValue));
		g2.drawImage(musicSelectBackGround, 0, 0, null);
		g2.drawImage(gameSelectBackGround, 0, 0, null);
		g2.drawImage(selectedImage, 0, 0, null);
	}

	// run ?¨??? whileλ¬Έμ ?΅?΄ κ³μ ?λ©΄μ κ·Έλ €μ€μΌλ‘μ¨ ?€? ?λ©΄μΌλ‘? ??΄κ°? ? ?κ²? ?΄μ€??€.
	@Override
	public void run() {
		fadeIn();
		while (true) {
			repaint();
			try {
				if (isFadeOut && (isMainScreen)) {
                	fadeOut();
              		insideOut.changeMainScreen(0);
              		// λ©μΈ?Όλ‘? ??κ°??Ό ?κΈ? ?λ¬Έμ ??¬ ?€??κ³? ?? ??? μ’λ£??€.
					selectedMusic.close();
					return;

				} else if (isFadeOut && (isNormalGameScreen || isChallengeGameScreen || isPracticeGameScreen)) {
					fadeOut();
	            	insideOut.changeGameScreen();
	    			// κ²μ ?λ©΄μΌλ‘?  ? ??΄?Ό ?κΈ? ?λ¬Έμ ??¬ ?€??κ³? ?? ??? μ’λ£??€.
					selectedMusic.close();
					return;
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

    // ?Έ?λ₯? ? ??κΈ? ??΄ λ§λ  λ©μ? 
	public void selectTrack(int nowSelected) {
		if (selectedMusic != null)
			selectedMusic.close();
		// ?Έ? ? ??? image κ΅¬ν
		selectedImage = new ImageIcon(
				getClass().getClassLoader().getResource("images/" + trackList.get(nowSelected).getStartImage())).getImage();
		// Music κ°μ²΄λ₯? ?λ‘? λ§λ¦?Όλ‘μ¨ ?€??κ³ μ ?? κ³‘μ λ¬΄ν λ°λ³΅ ??¨?€.
		selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true,0);
		selectedMusic.start(); // λ¬΄ν ?¬?
	}

	// ?Όμͺ? λ²νΌ? ???? ?? ?΄λ²€νΈ μ²λ¦¬
	public void selectLeft() {
		if (nowSelected == 0)
			nowSelected = trackList.size() - 1; // μ²? λ²μ§Έκ³‘μ? ?Όμͺ? λ²νΌ? ?λ₯΄λ©΄ κ°??₯ ?€λ₯Έμͺ½ κ³‘μ΄ ? ???΄?Ό ?κΈ? ?λ¬?
		else
			nowSelected--; // κ·? ?Έ? κ²½μ°? 1? λΉΌμ€
		selectTrack(nowSelected);
	}

	// ?€λ₯Έμͺ½ λ²νΌ? ???? ?? ?΄λ²€νΈ μ²λ¦¬
	public void selectRight() {
		if (nowSelected == trackList.size() - 1)
			nowSelected = 0; // ?Όμͺ½κ³Ό λ°λ?
		else
			nowSelected++; // λ§μ°¬κ°?μ§?λ‘? κ·? ?Έ? κ²½μ°? 1? ??¨
		selectTrack(nowSelected);
	}

	// ?Ό?Ό?΄ ?€ ?€? ?κΈ? ??λ―?λ‘? λ©μ?λ₯? ?΅?΄ ??½κ²? λ²νΌ? ?μΉλ?? ?€? 
	public void buttonSet(JButton button, int x, int y, int width, int height) {
		button.setBounds(x, y, width, height);
		// λ²νΌ ??λ¦? ? κ±?
		button.setBorderPainted(false);
		// ?λ₯΄λ ?? ? κ±?
		button.setContentAreaFilled(false);
		// κΈ??¨ ??λ¦? ? κ±?
		button.setFocusPainted(false);
		// λ²νΌ μΆκ?
		add(button);
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}
}