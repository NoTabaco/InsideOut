package project_04;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/** mp3������ ��������ִ� Ŭ������ JavaZoom���� �����ϴ� �ܺζ��̺귯���� ����ߴ�.
 * 
 * @author SungHo Yun
 * @version 0.4
 * 
 */

public class Music extends Thread {
	/** AdvancedPlayer Ŭ������ Javazoom ���� �����Ͽ� �����ϴ� ���̺귯���� �ִ� Ŭ������ �ϳ��̴� 
	 *  �������� Player Ŭ������ ���ؼ� ������ ������ PlayerŬ�����δ� Ư�� �������������� �뷡 ������ �Ұ����ϹǷ� 
	 *  AdvancedPlayer�� ���� ���� �ߴ�.
	 *  BufferedInputStream�� �Ű������� �޾� �ش� ���ۿ� �ִ� ������ �����Ų��.
	 */
	private AdvancedPlayer player;
	
	/** playerEvent�� ���� �뷡�� ���� ������ ������ �����ü� �ִ�. */
	private PlaybackEvent playerEvent;
	
	/** �ѹ��� ����� �Ǵ��� ������ ����� �Ǵ��� �����ϴ� �� 
	 *  isLoop�� ���� True�̸� �ݺ���� False�̸� �ѹ��� ���۵ȴ�.
	 * */
	private boolean isLoop;
	
	/** �뷡�� �����Ǿ������� Frame ���� �����ϱ� ���� ���� */
	private int pausedOnFrame = 0;

	/** InputStream�� ���� ���Ϸκ��� ���� �޾ƿ´� */
	private InputStream is;
	/** InputStream���� �޾ƿ� ���� ���ۿ� ��´�. */
	private BufferedInputStream bis;
	
	/** �뷡�� ������ �����ϴ� �ʵ尪�̴�. */
	private String name;

	/** �����ڸ� ���� ���� ����� �ݺ����� ������ġ�� �޴´�.
	 * 
	 * @param name
	 * @param isLoop
	 * @param startPoint
	 */
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

	/** ������ �����Ű�� �޼ҵ��̴�. */
	public void close() {
		isLoop = false;
		player.close();
		// �����带 �������·� �����.
		this.interrupt();
	}

	/** ���� �����Ų��. */
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

	/** AdvancedPlyaer�� �������༭ �ٸ� Ŭ�������� stop�޼ҵ带 ���� �뷡�� ����� �ְԲ� �Ѵ�. 
	 * 
	 * @return player
	 */
	public AdvancedPlayer getPlayer() {
		return player;
	}

	/** pausedOnFrame�� �����Ͽ� ������ ������ Frame�� ������ �ְ� �Ѵ�.
	 * 
	 * @return pausedOnFrame
	 */
	public int getPausedOnFrame() {
		return pausedOnFrame;
	}


}
