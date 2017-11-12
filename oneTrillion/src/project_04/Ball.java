package project_04;

/**
 * �÷��̾ ������ �÷����ϰ� �Ǵ� Ball�� ����(��ġ,ȸ�� ��)�ϱ� ���� Ŭ����
 * 
 * @author Jimin Kim
 * @version 0.4
 * 
 */
public class Ball implements Runnable {

	// x��ǥ , y��ǥ , radius , �߽���ǥ �� x , y
	private int x, y, radius, circleX, circleY;
	// ������ų ũ��
	private double size;
	// Radian ��
	private double radian;
	// Thread ��ü

	private Thread thread;
	/** Ball�� �׸��� �Ǹ� leftX, topY���� �׸��� ������ �׿� ���� �Ÿ��� �����ϱ� ���� ���� */
	private int adjustmentDistance;

	/** Ball�� �߽���ǥ ���� �����ϱ� ���� ������ */
	Ball() {
		// �����带 ����� ��ü�� �־��ش�.
		setThread(new Thread(this));
		// Ball�� �߽���ǥ ��, ó�� ������ǥ x, y
		x = 640;
		y = 85;
		// ���� �� ���� ������ 265 + ball�� ������ 13 + ���� ��¦ �������Ƿ� �׿� ���� ������ 2�� �־���.

		// Ball�� �߽���ǥ���� Ball�� ������ ��ŭ�� 13�� x��ǥ, y��ǥ�� ���� �־��ָ� �׸����� �ϴ� Ball�� �׸� �� �ְԵȴ�.
		adjustmentDistance = 13;

		radius = 280;
		// ���� �߽� x��ǥ = 375 + 265 = 640 , ���� �� ���� �������� 265
		circleX = 640;
		// ���� �߽� y��ǥ = 100 + 265 = 640 , ���� �� ���� �������� 265
		circleY = 365;
		// size�� ���� �ʱ�ȭ 3�ø� �������� 0�� �̹Ƿ� , ������ ���� ����� �� 90���� ���־ 12�ø� �������� 0�� ������� �Ѵ�.
		size = -90;
		// radian �� �ʱ�ȭ

	}

	/**
	 * Thread�� �����Ű�� run�Լ��� ���� ����, run�Լ� ���� while���� ���� Ball�� ȸ���ϰ��� �ϴ� ���� ��� ȸ���ϰ�
	 * �ȴ�.
	 */
	public void run() {
		// ���� ���� �� ���� ������� �ϹǷ� ��� �ݺ�
		while (true) {
			try {
				// �ﰢ�Լ��� ���� ���� ���� size�� ������Ű�鼭 Ball�� ���� ���� �� ���� ȸ����Ų��.
				// �� �߽ɰ� + �������̹Ƿ� �߽���ǥ ���� ���ؾ� �Ѵ�.
				x = circleX + (int) (radius * Math.cos(radian));
				y = circleY + (int) (radius * Math.sin(radian));
				// ��� �����ų x��ǥ, y��ǥ

				size += 1;
				// radian �� ���
				radian = size / 180 * Math.PI;
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	/**
	 * Ball�� x��ǥ�� ������ getX �Լ�
	 * 
	 * @return x - adjustmentDistance
	 */
	public int getX() {
		// Ball�� �׸� �� 11�� ����(leftX)�� �������� �׷��ֹǷ� �������� 13�� ���־ X��ǥ�� ���´�.
		return x - adjustmentDistance;
	}

	/**
	 * Ball�� x��ǥ�� �����ϴ� setX �Լ�
	 * 
	 * @param int
	 *            x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Ball�� y��ǥ�� ������ getY �Լ�
	 * 
	 * @return y - adjustmentDistance
	 */

	// y�� ���� ������ �Լ�

	public int getY() {
		// Ball�� �׸� �� 11�� ������ ����(topY)���� �׷��ֹǷ� �������� 13�� ���־ Y��ǥ�� ���´�.
		return y - adjustmentDistance;
	}

	/**
	 * Ball�� y��ǥ�� �����ϴ� setY �Լ�
	 * 
	 * @param int
	 *            y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Ball�� Thread�� ������ �Լ�
	 * 
	 * @return thread
	 */

	public Thread getThread() {
		return thread;
	}

	/**
	 * Ball�� Thread�� �����ϴ� �Լ�
	 * 
	 * @param Thread
	 *            thread
	 */
	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public int getRadius() {
		return radius;
	}

	public int getCircleX() {
		return circleX;
	}

	public int getCircleY() {
		return circleY;
	}

	public double getRadian() {
		return radian;
	}

	public double getSize() {
		return size;
	}

}