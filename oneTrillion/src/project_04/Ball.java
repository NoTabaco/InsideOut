package project_04;

/** �÷��̾ ������ �÷����ϰ� �Ǵ� Ball�� ����(��ġ,ȸ�� ��)�ϱ� ���� Ŭ����
 * 
 * @author Jimin Kim
 * @version 0.4
 * 
 */
public class Ball implements Runnable {

	/** Ball�� �׸��� ���� x��ǥ, y��ǥ, radius, ���� �߽� x��ǥ Ox, ���� �߽� y��ǥ Oy ������ */
	private int x, y, radius, circleX, circleY;
	/** Ball�� ȸ����Ű�� ���� ������ų ���� */
	private double size;
	/** Ball�� radian�� ���� ȸ���ϱ� ������ �׿� ���� ���� ������ ����*/
	private double radian;
	/** Ball�� ȸ����Ű�� ���� Thread ��ü */	
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
		
        // Ball�� �߽���ǥ���� Ball�� ������ ��ŭ�� 13�� x��ǥ, y��ǥ�� ���� �־��ָ� �׸����� �ϴ� Ball�� �׸� �� �ְԵȴ�.
		adjustmentDistance = 13;
		// ���� �� ���� ������ 265 + ball�� ������ 13 + ���� ��¦ �������Ƿ� �׿� ���� ������ 2�� �־���.
		radius = 280;
		// ���� �߽� x��ǥ = 375 + 265 = 640 , ���� �� ���� �������� 265
		circleX = 640;
		// ���� �߽� y��ǥ = 100 + 265 = 640 , ���� �� ���� �������� 265
		circleY = 365;
		// size�� ���� �ʱ�ȭ 3�ø� �������� 0�� �̹Ƿ� , ������ ���� ����� �� 90���� ���־ 12�ø� �������� 0�� ������� �Ѵ�.
		size = -90;

	}

	/** Thread�� �����Ű�� run�Լ��� ����
     *  ����, run�Լ� ����  while���� ���� Ball�� ȸ���ϰ��� �ϴ� ���� ��� ȸ���ϰ� �ȴ�. */
	public void run() {
		// ���� ���� �� ���� ������� �ϹǷ� ��� �ݺ�
		while (true) {
			try {
				// �ﰢ�Լ��� ���� ���� ���� size�� ������Ű�鼭 Ball�� ���� ���� �� ���� ȸ����Ų��.
				// �� �߽ɰ� + �������̹Ƿ� �߽���ǥ ���� ���ؾ� �Ѵ�.
				// ��� �����ų x��ǥ, y��ǥ
				x = circleX + (int) (radius * Math.cos(radian));
				y = circleY + (int) (radius * Math.sin(radian));
				
				// �ﰢ�Լ��� ���� ���� ���� size�� ������Ű�鼭 Ball�� ���� ���� �� ���� ȸ����Ų��.
				size += 1;
				// radian �� ���
				radian = size / 180 * Math.PI;
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}


	/** Ball�� x��ǥ�� ������ getX �Լ� 
	 * 
	 * @return x - adjustmentDistance
	 * */
	public int getX() {
		// Ball�� �׸� �� 11�� ����(leftX)�� �������� �׷��ֹǷ� �������� 13�� ���־ X��ǥ�� ���´�.
		return x - adjustmentDistance;
	}

	/** Ball�� x��ǥ�� �����ϴ� setX �Լ� 
	 * 
	 * @param x
	 * */
	public void setX(int x) {
		this.x = x;
	}

	/** Ball�� y��ǥ�� ������ getY �Լ� 
	 * 
	 * @return y - adjustmentDistance
	 * */
	public int getY() {
		// Ball�� �׸� �� 11�� ������ ����(topY)���� �׷��ֹǷ� �������� 13�� ���־ Y��ǥ�� ���´�.
		return y - adjustmentDistance;
	}

	/** Ball�� y��ǥ�� �����ϴ� setY �Լ�
	 * 
	 * @param y
	 * */
	public void setY(int y) {
		this.y = y;
	}

	/** Ball�� Thread�� ������ �Լ�
	 * 
	 * @return thread
	 * */	
	public Thread getThread() {
		return thread;
	}

	/** Ball�� Thread�� �����ϴ� �Լ�
	 * 
	 * @param thread
	 * */
	public void setThread(Thread thread) {
		this.thread = thread;
	}
    
	/** Ball�� Radius�� ������ �Լ�
	 * 
	 * @return radius
	 * */	
	public int getRadius() {
		return radius;
	}
	
	/** Ball�� CircleX(���� �߽� x��ǥ)�� ������ �Լ�
	 * 
	 * @return circleX
	 * */
	public int getCircleX() {
		return circleX;
	}
    
	/** Ball�� CircleY(���� �߽� y��ǥ)�� ������ �Լ�
	 * 
	 * @return circleY
	 * */
	public int getCircleY() {
		return circleY;
	}
    
	/** Ball�� Radian�� ������ �Լ�
	 * 
	 * @return radian
	 * */
	public double getRadian() {
		return radian;
	}
    
	/** Ball�� Size�� ������ �Լ�
	 * 
	 * @return size
	 * */
	public double getSize() {
		return size;
	}

}