package project_04;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/** ������ �������� ��Ʈ���� ���ִ� Ŭ����
 *  
 *  @author Jimin Kim
 *  @version 0.4
 *
 */
public class InsideOut extends JFrame {
	
	/** MainScreen(���� ȭ��)�� �����ϱ� ���� mainScreenPanel ��ü */
	private MainScreenPanel mainScreenPanel;
	/** HelpScreen(���� ȭ��)�� �����ϱ� ���� helpScreenPanel ��ü */
	private HelpScreenPanel helpScreenPanel;
	/** GameSelectScreen(�� ���� ȭ��)�� �����ϱ� ���� gameSelectScreenPanel ��ü */
	private GameSelectScreenPanel gameSelectScreenPanel;
	/** GameScreen(�÷��̾ �÷��� �ϰ� �Ǵ� ȭ��)�� �����ϱ� ���� gameScreenPanel ��ü */
	private GameScreenPanel gameScreenPanel;
	/** Panel�� ������ Frame�� �߰��Ͽ� Panel�� ������ ǥ���� �� �ֵ��� ����� �ִ� ��ü  */
	private Container contentpane;

	/** ���� ���α׷������� ���콺�� X�� Y��ǥ�� ������ �ִ� �ʵ带 �����. �޴��ٸ� �ű�� ���ؼ� �ʿ��� ���� */
	private int mouseX, mouseY;

	/** MenuBar �̹��� */
	private JLabel menubar;
	
	/** MenuBar�� Exit��ư �⺻ �̹��� */
	private ImageIcon menubarImageBasic = new ImageIcon(
			getClass().getClassLoader().getResource("images/manubarExitButtonImage.png"));
	/** MenuBar�� ���콺�� ��ư�� �ö��� ���� Exit��ư �̹��� */
	private ImageIcon menubarImageEntered = new ImageIcon(
			getClass().getClassLoader().getResource("images/manubarExitButtonImageEntered.png"));
	
	/** MenuBar�� Exit��ư ��ü ���� */
	private JButton menubarExitButton = new JButton(menubarImageBasic);

	/** Main�Լ����� ���� ����� ó�� ���� ȭ��(MainScreen)�� ����ϱ� ���� ������ */
	public InsideOut() {
		// �����̸� ����
		setTitle("Inside Out");
		// ������ ���� ������ ����, �ݵ�� �ʿ��� �Լ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// getContentPane ���� contentpane(Panel) ������ ���´�.
		contentpane = getContentPane();
		// ����â ũ�� ����
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// ���ȭ���� �������� �������ش�.
		setBackground(Color.BLACK);
		// ����â�� �� �߾ӿ� ���
		setLocationRelativeTo(null);
		// �����̳��� ũ�Ⱑ ����ɶ� ������Ʈ���� ũ��� ��ġ�� �ڵ������� ����Ǵµ� �װ� �����Ѵ�.
		setLayout(null);
		// �޴��ٰ� ������ �ʰԲ� ����
		setUndecorated(true);
		// ����ڰ� ����â�� ���Ƿ� ���̰� �ϴ°� �Ұ���, true�� �ϸ� ����
		setResizable(false);

		// �޴��� �̹����� �߰������ش�.
		menubar = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("images/menubarImage.png")));
		// �޴����� ��ġ�� �������ش�.
		menubar.setBounds(0, 0, 1280, 28);
		// �޴��ٸ� �߰������ش�.
		add(menubar);
		// �޴��� �̺�Ʈ���� ó�����ش�.
		// menuBar�� ���콺�� ���ؼ� �����Ҽ� �ֵ��� �̺�Ʈó���� ���ش�.
		menubar.addMouseListener(new MouseAdapter() {
			// ���콺�� �Է������� ������Ʈ���� ���콺�� x��ǥ�� y��ǥ�� �����´�
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		// menuBar�� �巡�� ������ �̺�Ʈ ó���� ���ش�.
		menubar.addMouseMotionListener(new MouseMotionAdapter() {
			// ���콺�� �Է������� ��ũ��(�����)���� ���콺�� x��ǥ�� y��ǥ�� �����´�
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				// ��ũ������ ���콺�� ��ǥ�� ������Ʈ���� ���콺�� ��ǥ�� ���� ����â�� ��ġ�̴�.
				setLocation(x - mouseX, y - mouseY);
			}
		});
		
		// �޴��ٿ� �ִ� menubarExitButton �̺�Ʈ ó��
		menubarExitButton.addMouseListener(new MouseAdapter() {
			// ���콺�� ��ư�� �������� �̺�Ʈ ó��
			@Override
			public void mouseEntered(MouseEvent e) {
				// Entered�̹����� ���� �����ش�.
				menubarExitButton.setIcon(menubarImageEntered);
				// Ŀ���� ����� �ٲ��ش�
				menubarExitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				// ȿ���� ���
			}

			// ���콺�� ��ư�� �������� �̺�Ʈ ó��
			@Override
			public void mouseExited(MouseEvent e) {
				menubarExitButton.setIcon(menubarImageBasic);
				// Ŀ���� ����� �ٲ��ش�
				menubarExitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

			}

			// ��ư�� Ŭ�������� �̺�Ʈ ó��
			@Override
			public void mousePressed(MouseEvent e) {
				// ȿ���� ���
				// ���α׷��� �ٷ� ������ �ʰ� 1������ �ִٰ� �����Ա� ����
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				System.exit(0);
			}
			
		
		});

		// ���� �г� ����
		mainScreenPanel = new MainScreenPanel(this, 0);
		// Panel�� �߰����ش�.
		contentpane.add(mainScreenPanel);
		// MainPanel�� Thread ����
		mainScreenPanel.getThread().start();
		// ȭ�� ��� ���� �⺻���� false �̹Ƿ� ���� ������Ѵ�. �ǵ��� �Ǹ������� ���ش�.
		setVisible(true);
	}
	
	/** GameSelectScreen(�� ���� ȭ��)���� �̵��ϱ� ���� �Լ� */
	public void changeGameSelectScreen() {
		// ���� ����ǰ� �ִ� ��� �ǳ��� �����Ѵ�.
		contentpane.removeAll();
		// ���Ӱ� GameSelectScreen �ǳ� ��ü�� ���� �����ڸ� ����
		gameSelectScreenPanel = new GameSelectScreenPanel(this);
		// Panel�� �߰����ش�.
		contentpane.add(gameSelectScreenPanel);
		// gameSelectScreenPanel�� Thread ����
		gameSelectScreenPanel.getThread().start();
		// ȭ�� ��� ���� �⺻���� false�̹Ƿ� true�� ���� ������Ѵ�.
		setVisible(true);
	}
	
	/** HelpScreen(���� ȭ��)���� �̵��ϱ� ���� �Լ� 
	 * 
	 * @param introMusicStartPoint 
	 * */
	public void changeHelpScreen(int introMusicStartPoint) {
		// ���� ����ǰ� �ִ� ��� �ǳ��� �����Ѵ�.
		contentpane.removeAll();
		// ���Ӱ� HelpScreen �ǳ� ��ü�� ���� �����ڸ� ����
		helpScreenPanel = new HelpScreenPanel(this, introMusicStartPoint);
		// Panel�� �߰����ش�.
		contentpane.add(helpScreenPanel);
		// helpScreenPanel�� Thread ����
		helpScreenPanel.getThread().start();
		// ȭ�� ��� ���� �⺻���� false�̹Ƿ� true�� ���� ������Ѵ�.
		setVisible(true);
	}
    
	/** GameScreen(�÷��̾ �÷��� �ϰ� �Ǵ� ȭ��)���� �̵��ϱ� ���� �Լ� */
	public void changeGameScreen() {
		// ���� ����ǰ� �ִ� ��� �ǳ��� �����Ѵ�.
		contentpane.removeAll();
		// ���Ӱ� GameScreen �ǳ� ��ü�� ���� �����ڸ� ����
		gameScreenPanel = new GameScreenPanel(this);
		// Panel�� �߰����ش�.
		contentpane.add(gameScreenPanel);
		// gameScreenPanel�� Thread ����
		gameScreenPanel.getThread().start();
		// ȭ�� ��� ���� �⺻���� false�̹Ƿ� true�� ���� ������Ѵ�.
		setVisible(true);
	}

	/** MainScreen(���� ȭ��)���� �̵��ϱ� ���� �Լ� 
	 * 
	 * @param introMusicStartPoint 
	 * */
	public void changeMainScreen(int introMusicStartPoint) {
		// ���� ����ǰ� �ִ� ��� �ǳ��� �����Ѵ�.
		contentpane.removeAll();
		// ���Ӱ� MainScreen �ǳ� ��ü�� ���� �����ڸ� ����
		mainScreenPanel = new MainScreenPanel(this, introMusicStartPoint);
		// Panel�� �߰����ش�.
		contentpane.add(mainScreenPanel);
		// mainScreenPanel�� Thread ����
		mainScreenPanel.getThread().start();
		// ȭ�� ��� ���� �⺻���� false�̹Ƿ� true�� ���� ������Ѵ�.
		setVisible(true);
	}
	
    /** MenuBar�̹����� ������ ���� getMenubar�Լ� 
     * 
     * @return menubar
     * */
	public JLabel getMenubar() {
		return menubar;
	}
    
    /** MenuBar�̹����� �����ϱ� ���� setMenubar�Լ� 
     * 
     * @param menubar
     * */
	public void setMenubar(JLabel menubar) {
		this.menubar = menubar;
	}
    
	/** MenuBar�� Exit�̹��� ��ư�� ������ ���� getMenubarExitButton�Լ� 
	 * 
	 * @return menubarExitButton
	 * */
	public JButton getMenubarExitButton() {
		return menubarExitButton;
	}
    
	/** MenuBar�� Exit�̹��� ��ư�� �����ϱ� ���� setMenubarExitButton�Լ� 
	 * 
	 * @param menubarExitButton
	 * */
	public void setMenubarExitButton(JButton menubarExitButton) {
		this.menubarExitButton = menubarExitButton;
	}
	
	
}
