package project_04;

//�ϳ��� � ���� ������ ��� Ŭ���� 
public class Track {
 
	private String titleImage; // ���� �κ� �̹���
	private String startImage; // ���� ���� â ǥ�� �̹���
	private String startMusic; // ���� ���� â ����
	private String gameMusic; // �ش� ���� �������� ���� ���� 
	
	private int drawX;
	private int drawY;
	
	public String getTitleImage() {
		return titleImage;
	}
	public void setTitleImage(String titleImage) {
		this.titleImage = titleImage;
	}
	
	public String getStartImage() {
		return startImage;
	}
	public void setStartImage(String startImage) {
		this.startImage = startImage;
	}
	public String getStartMusic() {
		return startMusic;
	}
	public void setStartMusic(String startMusic) {
		this.startMusic = startMusic;
	}
	public String getGameMusic() {
		return gameMusic;
	}
	public void setGameMusic(String gameMusic) {
		this.gameMusic = gameMusic;
	}
	
	public int getDrawX() {
		return drawX;
	}
	
	public int getDrawY() {
		return drawY;
	}
	
	public Track(String titleImage, String startImage, String startMusic, String gameMusic, int drawX, int drawY) {
		super();
	    this.titleImage = titleImage;
		this.startImage = startImage;
		this.startMusic = startMusic;
		this.gameMusic = gameMusic;
		this.drawX = drawX;
		this.drawY = drawY;
	}
	
	
	
	
}
