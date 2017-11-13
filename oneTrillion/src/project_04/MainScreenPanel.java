package project_04;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/** ������ MainScreen ��, ���� ȭ�鿡 ���� ������ ����ִ� Ŭ����
 *  
 *  @author Jimin Kim
 *  @version 0.4
 *
 */

public class MainScreenPanel extends JPanel implements Runnable {

	
	/** ���� ȭ���� ��� �̹����� ��� ��ü */
	private Image introBackground;
	/** ���� ȭ���� ��� �̹���(��)�� ��� ��ü */
	private Image introBackgroundCircle;

	/** ���� ȭ���� exitButton �̹����� ��� ��ü */
	private ImageIcon exitButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/exitButton.png"));
	/** ���� ȭ���� helpButton �̹����� ��� ��ü */
	private ImageIcon helpButtonImage = new ImageIcon(getClass().getClassLoader().getResource("images/helpButton.png"));
	/** ���� ȭ���� startButton �̹����� ��� ��ü */
	private ImageIcon startButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/startButton.png"));

	/** ���콺�� ��ư�� �ö� ���� ���� ���� exitButton �̹����� ��� ��ü */
	private ImageIcon exitEnteredButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/exitButtonEntered.png"));
	/** ���콺�� ��ư�� �ö� ���� ���� ���� helpButton �̹����� ��� ��ü */
	private ImageIcon helpEnteredButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/helpButtonEntered.png"));
	/** ���콺�� ��ư�� �ö� ���� ���� ���� startButton �̹����� ��� ��ü */
	private ImageIcon startEnteredButtonImage = new ImageIcon(
			getClass().getClassLoader().getResource("images/startButtonEntered.png"));

	
	/** Exit JButton ��ü ���� */
	private JButton exitButton = new JButton(exitButtonImage);
	/** Help JButton ��ü ���� */
	private JButton helpButton = new JButton(helpButtonImage);
	/** Start JButton ��ü ���� */
	private JButton startButton = new JButton(startButtonImage);
	
	/** ���� ȭ���� �����ϸ� ����Ǵ� ������ ���� �� �ִ� ��ü */
	private Music introMusic;
	
	/** ������ ȭ�� ��ȯ � ���� ���� �Ǿ��� �� �� ���� �����ϱ� ���� ���� */
	private int stopPoint;

	/** fadeIn�� ��� ������ ���� ���� */
	private float fadeValue;
	/** fadeOut�� ��� ������ ���� ���� */
	private boolean isFadeOut;

	/** GameSelectScreen ��ȯ�� ������ boolean���� */
	private boolean isGameSelectScreen ;
	/** HelpScreen ��ȯ�� ������ boolean���� */
	private boolean isHelpScreen ;

	/** ȭ�� ��ȯ�� ����Ǵ� Music ���� ��� �����ϱ� ���� Thread ��ü*/
	private Thread thread;

	/** �������� �Ű������� �ѱ�� ���� InsideOut ��ü */
	private InsideOut insideOut;
	
	/** ����ȭ���� MainScreen�� ���� ��� �� ������ ��� �ִ� ������
	 * 
	 * @param insideOut
	 * @param stopPoint 
	 * */
	public MainScreenPanel(InsideOut insideOut, int stopPoint) 	{
		// �������� �Ű������� �޾� �����Ѵ�.
		this.insideOut = insideOut;
		// fadeOut�� ���� false�� �ʱ�ȭ �����ش�.
		isFadeOut = false;

		//stopPoint�� �ʱ�ȭ �����ش�
		this.stopPoint = stopPoint;
		
		// Music�� �Ű������� mp3���� �̸��� ���������� ��Ÿ�� ����Ʈ�� �־��ش�. ����ȭ�鿡�� ��Ʈ�ι����� ���� �ݺ� ������ �����԰� ���ÿ� ������ ���� ���
		introMusic = new Music("introMusic.mp3", true, stopPoint);
		introMusic.start();
		
		// isGameSelectScreen�� ���� false�� �ʱ�ȭ �����ش�.
		isGameSelectScreen = false;
		// isHelpScreen�� ���� false�� �ʱ�ȭ �����ش�.
		isHelpScreen = false;
		// �����̳��� ũ�Ⱑ ����ɶ� ������Ʈ���� ũ��� ��ġ�� �ڵ������� ����Ǵµ� �װ� �����Ѵ�
		setLayout(null);
		// ����â ũ�� ����
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// ���� ���ȭ�� ���� �������� ����
		setBackground(Color.BLACK);
		// ȭ�� ��� ���� �⺻���� false �̹Ƿ� ���� ������Ѵ�.
		setVisible(true);

		// Main Ŭ������ ��ġ�� ������� �ؼ� Resource�� �� �װ��� �̹������� ������ ���Խ����ش�.
		// ����̹��� , introBackground => Background�� ����
		introBackground = new ImageIcon(getClass().getClassLoader().getResource("images/MainBackGround.png"))
				.getImage();
		introBackgroundCircle = new ImageIcon(
				getClass().getClassLoader().getResource("images/MainBackGroundCircle.gif")).getImage();
		

		// ��ư���� �̸� �����س��� buttonSet �޼ҵ带 ���� �߰�
		buttonSet(startButton, 110, 450, 228, 57);
		buttonSet(helpButton, 110, 515, 183, 55);
		buttonSet(exitButton, 110, 575, 148, 53);
		

		// �޴��� exitButton ����
		buttonSet(insideOut.getMenubarExitButton(), 1200, 0, 64, 28);
		// �޴��� ����
		add(insideOut.getMenubar());

		// �����带 ����� ��������ش�.
		setThread(new Thread(this));

		// startButton�� ���콺 �̺�Ʈ�� ó�����ش�.
		startButton.addMouseListener(new MouseAdapter() {

			// ���콺�� ������ ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseEntered(MouseEvent e) {
				// ������ �̹����� Entered �̹����� ����
				startButton.setIcon(startEnteredButtonImage);
				// Ŀ�� �̹����� HAND_CURSOR�� �����ؼ� ���� �˾ƺ��� �����Ѵ�.
				startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� �������� ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				startButton.setIcon(startButtonImage);
				startButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� startButton ������ �������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				isFadeOut = true;
				isGameSelectScreen = true;

			}
		});

		/**
		 * helpButton�� ���콺 �̺�Ʈ�� ó�����ش�.
		 */
		helpButton.addMouseListener(new MouseAdapter() {
			/**
			 * ���콺�� ������ ���� ������ �̺�Ʈ ó��
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ������ �̹����� Entered �̹����� ����
				helpButton.setIcon(helpEnteredButtonImage);
				// Ŀ�� �̹����� HAND_CURSOR�� �����ؼ� ���� �˾ƺ��� �����Ѵ�.
				helpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� �������� ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				helpButton.setIcon(helpButtonImage);
				helpButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� helpButton ������ �������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				// ���� ȭ�� ���� �̺�Ʈ
				isFadeOut = true;
				isHelpScreen = true;
			}
		});

		/**
		 * exitButton�� ���콺 �̺�Ʈ�� ó�����ش�.
		 */
		exitButton.addMouseListener(new MouseAdapter() {
			/**
			 * ���콺�� ������ ���� ������ �̺�Ʈ ó��
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				// ������ �̹����� Entered �̹����� ����
				exitButton.setIcon(exitEnteredButtonImage);
				// Ŀ�� �̹����� HAND_CURSOR�� �����ؼ� ���� �˾ƺ��� �����Ѵ�.
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			// ���콺�� �������� ���� ������ �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				exitButton.setIcon(exitButtonImage);
				exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// ���콺�� exitButton ������ �������� �̺�Ʈ ó�� => ����
			@Override
			public void mousePressed(MouseEvent e) {
				// �������� �̺�Ʈ
				System.exit(0);
			}
		});
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
	
    /** MainScreen�� ��� �̹����� �׷��ְų� ������ ������ �ִ� paint�Լ� 
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
			g2.drawImage(introBackgroundCircle, 275, 30, 1200, 676, null);
			g2.drawImage(introBackground, 0, 0, null);
		}
	
    /** ����ȭ��(MainScreenPanel)�� Thread�� ���� �� �� ����Ǵ� �Լ� */
	@Override
	public void run() {
		// fadeIn ȿ���� �־��ش�.
		fadeIn();
    while (true) {
			try {
				if (isFadeOut && isGameSelectScreen) {
					fadeOut();
					// ȭ���� �Ѿ���Ƿ� introMusic�� �����Ų��. ����, fadeOut()�� �� ���� music ���ᰡ ���� ����ϴٰ� �����ǹǷ�
					// ��ġ�� fadeOut()�������� ����
					// ����, ����� �ڵ��� ���� �������� run�Լ����� ������״�.
					introMusic.close();
					insideOut.changeGameSelectScreen();
					return;
				} 
				else if(isFadeOut && isHelpScreen) {
					fadeOut();
					// ���� ������ �޾Ƽ� ����
					introMusic.getPlayer().stop();
					stopPoint = introMusic.getPausedOnFrame();
					introMusic.close();
					insideOut.changeHelpScreen(stopPoint);
					return;
				}
				repaint();
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
	 * @param thread
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}

}
