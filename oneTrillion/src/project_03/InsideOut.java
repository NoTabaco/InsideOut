package project_03;

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

/**
 * ������ �������� ��Ʈ���� ���ִ� Ŭ�����̴�.
 * 
 * @author Yun
 *
 */
public class InsideOut extends JFrame {

	// MainScreenPanel ��ü�̴�.
	private MainScreenPanel mainScreenPanel;
	// MusicSelectScreenPanel ��ü�̴�.
	private GameSelectScreenPanel gameSelectScreenPanel;
	// GameScreenPanel ��ü�̴�.
	private GameScreenPanel gameScreenPanel;
	// �ʿ��� ������ ����ϴ� �κ��� contentpane �̴�.
	private Container contentpane;

	// MenuBar�̹���
	private JLabel menubar;

	/**
	 * ���� ���α׷������� ���콺�� X�� Y��ǥ�� ������ �ִ� �ʵ带 �����. �޴��ٸ� �ű�� ���ؼ� �ʿ��ϴ�.
	 */
	private int mouseX, mouseY;

	// Menubar Exit�̹���
	private ImageIcon menubarImageBasic = new ImageIcon(
			getClass().getClassLoader().getResource("images/manubarExitButtonImage.png"));
	private ImageIcon menubarImageEntered = new ImageIcon(
			getClass().getClassLoader().getResource("images/manubarExitButtonImageEntered.png"));
	
	// Menubar Button
	private JButton menubarExitButton = new JButton(menubarImageBasic);

	public InsideOut() {
		// �����̸� ����
		setTitle("Inside Out");
		// ������ ���� ������ ����, �ݵ�� �ʿ��� �Լ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// getContentPane ���� contentpane ������ ���´�.
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
		mainScreenPanel = new MainScreenPanel(this);
		// �г��� �߰����ش�.
		contentpane.add(mainScreenPanel);
		// MainPanel�� ������ ����
		mainScreenPanel.getThread().start();
		// ȭ�� ��� ���� �⺻���� false �̹Ƿ� ���� ������Ѵ�.�ǵ��� �Ǹ������� ���ش�.
		setVisible(true);
	}
	
	// Start ��ư�� ������ ���� ����Ǵ� �ǳ��� �����Ű�� MusicSelectScreenȭ������ �̵�
	// ���� GameScreenȭ�鿡�� Back��ư�� ������ MusicSelectScreenȭ�����ε� �̵�
	public void changeGameSelectScreen() {
		// ���� ����ǰ� �ִ� ��� �ǳ��� �����Ѵ�.
		contentpane.removeAll();
		// ���Ӱ� ���� ��ũ�� �ǳ� ��ü�� ���� �����ڸ� ����
		gameSelectScreenPanel = new GameSelectScreenPanel(this);
		// �г��� �߰����ش�.
		contentpane.add(gameSelectScreenPanel);
		// GamePanel�� ������ ����
		gameSelectScreenPanel.getThread().start();
		// ȭ�� ��� ���� �⺻���� false �̹Ƿ� ���� ������Ѵ�.
		setVisible(true);
	}
    
	// MusicSelectPanel���� ���� ���ý� ����Ǵ� �ǳ��� �����Ű�� GameScreenȭ������ �̵�
	public void changeGameScreen() {
		// ���� ����ǰ� �ִ� ��� �ǳ��� �����Ѵ�.
		contentpane.removeAll();
		// ���Ӱ� ���� ��ũ�� �ǳ� ��ü�� ���� �����ڸ� ����
		gameScreenPanel = new GameScreenPanel(this);
		// �г��� �߰����ش�.
		contentpane.add(gameScreenPanel);
		// GamePanel�� ������ ����
		gameScreenPanel.getThread().start();
		// ȭ�� ��� ���� �⺻���� false �̹Ƿ� ���� ������Ѵ�.
		setVisible(true);
	}

	// MusicSelectPanel���� ����Ǵ� �ǳ��� �����Ű�� MainScreenȭ������ �̵�
	public void changeMainScreen() {
		// ���� ����ǰ� �ִ� ��� �ǳ��� �����Ѵ�.
		contentpane.removeAll();
		// ���Ӱ� ���� ��ũ�� �ǳ� ��ü�� ���� �����ڸ� ����
		mainScreenPanel = new MainScreenPanel(this);
		// �г��� �߰����ش�.
		contentpane.add(mainScreenPanel);
		// GamePanel�� ������ ����
		mainScreenPanel.getThread().start();
		// ȭ�� ��� ���� �⺻���� false �̹Ƿ� ���� ������Ѵ�.
		setVisible(true);
	}

	public JLabel getMenubar() {
		return menubar;
	}

	public void setMenubar(JLabel menubar) {
		this.menubar = menubar;
	}

	public JButton getMenubarExitButton() {
		return menubarExitButton;
	}

	public void setMenubarExitButton(JButton menubarExitButton) {
		this.menubarExitButton = menubarExitButton;
	}
	
	

}
