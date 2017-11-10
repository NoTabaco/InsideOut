package project_04;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 * mp3������ ��������ִ� Ŭ������ JavaZoom���� �����ϴ� �ܺζ��̺귯���� ����ߴ�.
 * 
 * @author Yun
 */

public class Music extends Thread {
	// Player Ŭ������ Javazoom ���� �����ϴ� Ŭ������ �ϳ��̴�
	private AdvancedPlayer player;
	
	// playerEvent�� ���� �뷡�� ���� ������ ������ �����ü� �ִ�.
	private PlaybackEvent playerEvent;
	
	// �ѹ��� ����� �Ǵ��� ������ ����� �Ǵ��� �����ϴ� ��
	private boolean isLoop;
	
	// �뷡�� �����Ǿ������� Frame ���� �����ϱ� ���� ����
	private int pausedOnFrame = 0;

	// ���� ����� ���õ� �ʵ尪��
	private BufferedInputStream bis;
	private InputStream is;
	private String name;

	// �����ڸ� ���ؼ� ���� �̸��� �ݺ��������� startPoint�� �޴´�.
	public Music(String name, boolean isLoop, int startPoint) {

		try {
			// name �ʱ�ȭ
			this.name = name;
			// isLoop�� �ʱ�ȭ
			this.isLoop = isLoop;
			// startPoint �ʱ�ȭ
			pausedOnFrame = startPoint;
			// fis�� ����ϸ� Jar������ �̿��ϰ� �ɶ� ������ �߻��ϹǷ� InputStream�� ����Ѵ�.
			is = getClass().getClassLoader().getResourceAsStream("music/" + name);
			// InputStream�� bis���ۿ� ��Ƽ� ������ �ְ� �Ѵ�
			bis = new BufferedInputStream(is);
			// player�� �� ���۸� ������ �ְ� ���ش�.
			player = new AdvancedPlayer(bis);
			
			player.setPlayBackListener(new PlaybackListener() {
			    @Override
			    public void playbackFinished(PlaybackEvent event) {
			    	// pausedOnFrame�� �뷡�� ���۵ǰ� ������ Frame���� �������� ������ �������� �߰������ �Ѵ�.
			        pausedOnFrame += event.getFrame();
			    }
			});

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
				/** pausedOnFrame�� �̺�Ʈ�� ���ؼ� �����ö��� 1�� �϶� 1000 ������
				 * player.play �Ű������� �������� 1�ʰ� 100�̹Ƿ� /10�� ������Ѵ�. 
				 */
				player.play(pausedOnFrame/10, Integer.MAX_VALUE);
				is = getClass().getClassLoader().getResourceAsStream("music/" + name);
				// fis�� ���ۿ� ��Ƽ� ������ �ְ� �Ѵ�
				bis = new BufferedInputStream(is);
				// player�� �� ���۸� ������ �ְ� ���ش�.
				player = new AdvancedPlayer(bis);
			} while (isLoop);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public AdvancedPlayer getPlayer() {
		return player;
	}


	public int getPausedOnFrame() {
		return pausedOnFrame;
	}


}
