package project_04;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

/**
 * mp3������ ��������ִ� Ŭ������ JavaZoom���� �����ϴ� �ܺζ��̺귯���� ����ߴ�.
 * 
 * @author Yun
 */

public class Music extends Thread {
	// Player Ŭ������ Javazoom ���� �����ϴ� Ŭ������ �ϳ��̴�
	private Player player;
	
	// �ѹ��� ����� �Ǵ��� ������ ����� �Ǵ��� �����ϴ� ��
	private boolean isLoop;

	// ���� ����� ���õ� �ʵ尪��
	private BufferedInputStream bis;
	private InputStream is;
	private String name;

	// �����ڸ� ���ؼ� ���� �̸��� �ݺ��������� �޴´�
	public Music(String name, boolean isLoop) {

		try {
			// name �ʱ�ȭ
			this.name = name;
			// isLoop�� �ʱ�ȭ
			this.isLoop = isLoop;
			
			// fis�� ����ϸ� Jar������ �̿��ϰ� �ɶ� ������ �߻��ϹǷ� InputStream�� ����Ѵ�.
			is = getClass().getClassLoader().getResourceAsStream("music/" + name);
			// InputStream�� bis���ۿ� ��Ƽ� ������ �ְ� �Ѵ�
			bis = new BufferedInputStream(is);
			// player�� �� ���۸� ������ �ְ� ���ش�.
			player = new Player(bis);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// ���� ������ ������ ����� �̴��� �˷��ش� 10�ʱ��� ����� �Ǿ��ٸ� 10000�̶�� ���� ����
	// �� 0.001�� �������� �˷��ش�
	public int getTime() {
		if (player == null)
			return 0;
		return player.getPosition();
	}

	// ������ �������� ���� �� �� �ִ�.
	public void close() {
		isLoop = false;
		player.close();
		// �����带 �������·� �����.
		this.interrupt();
	}

	// Thread�� ����ϸ� ������ �־�� �ϴ� �޼ҵ��̴�.
	// ���� �����Ų��.
	@Override
	public void run() {
		try {
			// isLoop�� true�̸� ���� ��������ȴ�.
			do {
				player.play();
				is = getClass().getClassLoader().getResourceAsStream("music/" + name);
				// fis�� ���ۿ� ��Ƽ� ������ �ְ� �Ѵ�
				bis = new BufferedInputStream(is);
				// player�� �� ���۸� ������ �ְ� ���ش�.
				player = new Player(bis);
			} while (isLoop);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
