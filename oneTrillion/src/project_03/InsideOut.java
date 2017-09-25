package project_03;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JFrame;

/**
 * ������ �������� ��Ʈ���� ���ִ� Ŭ�����̴�.
 * 
 * @author Yun
 *
 */
public class InsideOut extends JFrame {

	// MainScreenPanel ��ü�̴�.
	private MainScreenPanel mainScreenPanel;
	// GameScreenPanel ��ü�̴�.
	private GameScreenPanel gameScreenPanel;
	// �ʿ��� ������ ����ϴ� �κ��� contentpane �̴�.
	private Container contentpane;

	public InsideOut() {
		// �����̸� ����
		setTitle("Inside Out");
		// ������ ���� ������ ����, �ݵ�� �ʿ��� �Լ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// getContentPane ���� contentpane ������ ���´�.
		contentpane = getContentPane();
		// ����â ũ�� ����
		setSize(Main.SCREENT_WIDTH, Main.SCREENT_HEIGHT);
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
		// ���� �г� ����
		mainScreenPanel = new MainScreenPanel(this);
		// �г��� �߰����ش�.
		contentpane.add(mainScreenPanel);
		// MainPanel�� ������ ����
		mainScreenPanel.getThread().start();
		// ȭ�� ��� ���� �⺻���� false �̹Ƿ� ���� ������Ѵ�.
		setVisible(true);
	}

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

}
