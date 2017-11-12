package project_04;

/**
 * �ϳ��� � ���� ������ ��� Ŭ���� 
 * @author Jimin Kim
 * @version 0.4
 */
public class Track {
 
	/** Ÿ��Ʋ �̹������� �̸� */
	private String titleImage;
	/** ���� ���ý� �������� �̹��� ���� �̸� */
	private String startImage; 
	/** ���� ���ý� �����ϴ� ���� ���� �̸� */
	private String startMusic; 
	/** ���� ����� ���۵Ǵ� ���� ���� �̸� */
	private String gameMusic; 
	/** �� ������ ��µǴ� ��ġ�� ����Ǿ� �ִ� x��  */
	private int drawX;
	/** �� ������ ��µǴ� ��ġ�� ����Ǿ� �ִ� y��  */
	private int drawY;
	
	/**
	 * titleImage�� �̸�, StartImage�� �̸�, startMusic�� �̸� gameMusic�� �̸�, �������� ��½�ų drawX, drawY���� �����ڷ� �޾� �ʱ�ȭ�����ش�. 
	 * @param titleImage
	 * @param startImage
	 * @param startMusic
	 * @param gameMusic
	 * @param drawX
	 * @param drawY
	 */
	public Track(String titleImage, String startImage, String startMusic, String gameMusic, int drawX, int drawY) {
		super();
	    this.titleImage = titleImage;
		this.startImage = startImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.drawX = drawX;
		this.drawY = drawY;
	}
	
	/**
	 * titleImage�� �̸� ��ȯ�Ѵ�.
	 * @return titleImage
	 */
	public String getTitleImage() {
		return titleImage;
	}
	/**
	 * startImage�� �̸� ��ȯ�Ѵ�.
	 * @return startImage
	 */
	public String getStartImage() {
		return startImage;
	}
	/**
	 * startMusic�� �̸� ��ȯ�Ѵ�.
	 * @return startMusic
	 */
	public String getStartMusic() {
		return startMusic;
	}
	/**
	 * gameMusic�� �̸� ��ȯ�Ѵ�.
	 * @return gameMusic
	 */
	public String getGameMusic() {
		return gameMusic;
	}
	/**
	 * �� ������ ��µǴ� ��ġ�� ����Ǿ� �ִ� x�� �� ��ȯ�Ѵ�.
	 * @return drawX
	 */
	public int getDrawX() {
		return drawX;
	}
	/**
	 *  �� ������ ��µǴ� ��ġ�� ����Ǿ� �ִ� y���� ��ȯ�Ѵ�.
	 * @return drawY
	 */
	public int getDrawY() {
		return drawY;
	}
	
	
}
