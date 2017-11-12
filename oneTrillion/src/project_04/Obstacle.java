package project_04;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

/**
 * ��ֹ� ������ ���� Ŭ���� �̴�.
 * @author SungHo Yun
 * @version 0.4
 */
public class Obstacle{
	/** ��ֹ� �̹����� ���� ��ü  */
	private Image obstacleImage;
	/** ��ֹ� �� ������ġ */
	private int x, y;
	/** ���߽��� ��ġ�� ������ */
	private int circleX, circleY, circleRadius;
	/** Ball ��ü�� ������ ��  */
	private double radian;
	
	/** Thread ��ü */
	private Thread thread;
	
	/** ball ��ü */
	Ball ball;
	
	/**
	 * ���� �������� �� �߽��� ��ġ�� �޾ƿ��� ���Ȱ��� �޾ƿͼ� ��ֹ��� �����Ѵ�. 
	 * @param circleRadius
	 * @param circleX
	 * @param circleY
	 * @param radian
	 */
	public Obstacle(int circleRadius, int circleX, int circleY, double radian) {
		obstacleImage = new ImageIcon(getClass().getClassLoader().getResource("images/obstacleImage.png")).getImage();
		this.circleRadius = circleRadius;
		this.circleX = circleX;
		this.circleY = circleY;
		this.radian = radian;
		this.x =   obstacleImage.getWidth(null) /-2 + circleX + (int) (circleRadius * Math.cos(Math.toRadians(radian))  );
		this.y =   obstacleImage.getHeight(null) /-2 + circleY + (int) (circleRadius * Math.sin(Math.toRadians(radian) )  );
		rotateImage(radian+90);
	}
	
	/**
	 * ������ �޾ƿͼ� �ش� ���ȿ� �´� ������ �̹����� ȸ�������ش�.
	 * @param radian
	 */
	public void rotateImage(double radian) {
		ImageIcon icon = new ImageIcon(this.obstacleImage);
		BufferedImage blankCanvas = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(),BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D)blankCanvas.getGraphics();
		g2.rotate(Math.toRadians(radian), icon.getIconWidth()/2 , icon.getIconHeight()/2);
		g2.drawImage(this.obstacleImage, 0, 0, null);
		this.obstacleImage = blankCanvas;
	}
	

	/**
	 * ��ֹ��� ������ġ�� x�� ���� �޾ƿ´�. 
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * ��ֹ��� ������ġ�� y�� ���� �޾ƿ´�.
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * ��ֹ��� �̹����� �޾ƿ´�.
	 * @return obstacleImage
	 */
	public Image getObstacleImage() {
		return obstacleImage;
	}

	
}
