package project_04;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/** ������ ��� �����ؾ� �ϴ��� �˷��ִ� Ŭ����
 *  
 *  @author SungHo Yun
 *  @version 0.4
 *
 */
public class HelpScreenPanel extends JPanel implements Runnable {

	/** HelpScreen�� ��� �̹����� ���� �� �ִ� ��ü  ����̹����� ��� �÷����ؾ� �ϴ��� �����ִ� ���������̴�.*/
	private Image helpScreenBackGround;

	/** Back ��ư �̹����� ���� �� �ִ� ��ü */
	private ImageIcon backButtonImage;

	/** ���콺�� Back ��ư�� ���������� �̹��� */
	private ImageIcon backButtonEnteredImage;

	/** JButton�� ���� backButton ���� */
	private JButton backButton;

	/** ������ ���� �� �ִ� ��ü , introMusic�� ���� ���� �ð��� �޾Ƽ� ��� ��������־�� �ϹǷ� ��ü�� �����. */
	private Music introMusic;

	/** ������ ���� �Ǿ������� frame�� ������ �ִ� ��ü */
	private int stopPoint;
	
	/** fadeIn�� fadeOut �� ���� ����  fade Value�� ���� ������ �����ȴ�.*/
	private float fadeValue;
	/** fadeIn�� fadeOut �� ���� ����  isFadeOut�� FadeIn�� �Ұ��� FadeOut�� �Ѱ��� �����ȴ�..*/
	private boolean isFadeOut;

	/** boolean���� ���� � ȭ������ ��ȯ�� �� �������� ���� �� ������ ���� true�� �Ǹ� ȭ���� ��ȯ�Ѵ�. */
	private boolean isMainScreen ;
	
	/** Thread ��ü */
	private Thread thread;

	/** ȭ����� ���� ��ü 
	 * Frame�� InsideOut�� ������ �־�� insideOut�� �ִ� �г� ���� �޼ҵ带 ����Ҽ� �ִ�. */
	private InsideOut insideOut;

	/** HelpScreenPanel�� �����ڷ� �ʵ尪���� �ʱ�ȭ �����ְ�.
	 * insideOut�� �Ű������� �޾� ȭ����� �ϰ�
	 * stopPoint�� �Ű������� �޾� �� �������� �뷡�� ���۽�Ų��.
	 * 
	 * @param insideOut
	 * @param stopPoint
	 */
	HelpScreenPanel(InsideOut insideOut, int stopPoint) {
		// �������� �Ű������� �޾� �����Ѵ�.
		this.insideOut = insideOut;
		// introMusic �������� �������ش�
		this.stopPoint = stopPoint;
		// backButton �̹����� �ʱ�ȭ �����ش�.
		backButtonImage = new ImageIcon(
				getClass().getClassLoader().getResource("images/backButtonImage_2.png"));
		backButtonEnteredImage = new ImageIcon(
				getClass().getClassLoader().getResource("images/backButtonEnteredImage_2.png"));
		backButton = new JButton(backButtonImage);
		
		introMusic = new Music("introMusic.mp3", true, stopPoint);
		introMusic.start();
		// MainScreen�� ���� false�� �ʱ�ȭ �����ش�.
		isMainScreen = false;
		// fadeOut�� ���� false�� �ʱ�ȭ �����ش�.
		isFadeOut = false;
        // �����̳��� ũ�Ⱑ ����ɶ� ������Ʈ���� ũ��� ��ġ�� �ڵ������� ����Ǵµ� �װ� �����Ѵ�
		setLayout(null);
		// ����â ũ�� ����
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setBounds(0, 0, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// ���� ���ȭ�� ���� �������� ����
		setBackground(Color.BLACK);
		// ȭ�� ��� ���� �⺻���� false �̹Ƿ� ���� ������Ѵ�.
		setVisible(true);

        // helpScreen ȭ�� ���� 
		helpScreenBackGround = new ImageIcon(getClass().getClassLoader().getResource("images/helpScreen.png")).getImage();
		
		// �޴��� exitButton ����
		buttonSet(insideOut.getMenubarExitButton(), 1200, 0, 64, 28);
		// �޴��� ����
		add(insideOut.getMenubar());
		
		// �����带 ����� ��������ش�.
		setThread(new Thread(this));

		// backButton�� ��ġ ����
		
		buttonSet(backButton, 20, 60, 228, 57);
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

			/**
			 * ���콺�� �����ܿ� ��������� �̺�Ʈ ó��
			 */
			@Override
			public void mouseExited(MouseEvent e) {
				// ������ �̹����� �⺻�̹��� ����
				backButton.setIcon(backButtonImage);
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			/**
			 *  ���콺�� backButton ������ �������� �̺�Ʈ ó��
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				isFadeOut = true;
				isMainScreen = true;
			}
		});

	}
	

	/** ��ư ���� �޼ҵ� ��� ��ư���� �������� �ֱ� ���ϵ��� �޼ҵ�� �������.
	 * JButton�� ��ġ��ǥ�� ũ�⸦ �������ָ� �ڵ����� �־��ش�.
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

	/** helpScreenBackGround �̹����� �׷��ش�. */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// graphics�� 2D�� ����
		Graphics2D g2 = (Graphics2D) g;
			// ������ �����ϱ� ���� �κ� fadeValue �� 1.0�̸� ������ 100%, 0.1�̸� �������� 10% �̴�.
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, fadeValue));
			g2.drawImage(helpScreenBackGround, 0, 0, null);
		}
    
	/** �����带 ���� ���������� ȭ����ȯ�� �Ѵ�. */
	@Override
	public void run() {
		// fadeIn ȿ���� �־��ش�.
		fadeIn();
    while (true) {
			try {
				if (isFadeOut && isMainScreen) {
					fadeOut();
					// ���� ������ �޾Ƽ� ����
					introMusic.getPlayer().stop();
					stopPoint = introMusic.getPausedOnFrame();
					introMusic.close();
					insideOut.changeMainScreen(stopPoint);
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
