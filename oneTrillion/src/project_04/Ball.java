package project_04;

//playable ĳ���͸� �����ϱ� ���� �޼ҵ�
public class Ball implements Runnable {
	// x��ǥ , y��ǥ , radius , �߽���ǥ ��  x , y
	private int x, y, radius , circleX , circleY;
	// ������ų ũ��
	private double size;
	// Radian ��
	private double radian;
	// Thread ��ü
	private Thread thread;
	// �Ÿ� ���� ����
    private int adjustmentDistance = 13; 

	Ball() {
		// �����带 ����� ��ü�� �־��ش�.
		setThread(new Thread(this));
		// �������� x, y 
		x = 640;
		y = 85;
		// ���� �� ���� ������ 265 + ball�� ������ 13 + ���� ��¦ �������Ƿ� �׿� ���� ������ 2�� �־���. 
		radius = 280;     
		 // ���� �߽� x��ǥ = 375 + 265 = 640 , ���� �� ���� �������� 265
		circleX = 640;  
		 // ���� �߽� y��ǥ = 100 + 265 = 640 , ���� �� ���� �������� 265
		circleY = 365;  
		// size�� ���� �ʱ�ȭ  3�ø� �������� 0�� �̹Ƿ� , ������ ���� ����� �� 90���� ���־ 12�ø� �������� 0�� ������� �Ѵ�.
		size = -90;
		// radian �� �ʱ�ȭ
		radian = size / 180 * Math.PI;
	}

	public void run() {
		// ���� ���� �� ���� ������� �ϹǷ� ��� �ݺ�
		while (true) {
			try {
				// �� �߽ɰ� + �������̹Ƿ� �߽���ǥ ����  ���ؾ� �Ѵ�. 
				x = circleX + (int) (radius * Math.cos(radian)  );
				y = circleY + (int) (radius * Math.sin(radian )  );
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

	// x�� ���� ������ �Լ�
	public int getX() {
		// Ball�� �׸� �� 11�� ������ �������� �׷��ֹǷ� �������� 13�� ���־ X��ǥ�� ���´�. 
		return x - adjustmentDistance ;
	}

	// y�� ���� ������ �Լ�
	public int getY() {
		// Ball�� �׸� �� 11�� ������ �������� �׷��ֹǷ� �������� 13�� ���־ Y��ǥ�� ���´�. 
		return y - adjustmentDistance;
	}

	public Thread getThread() {
		return thread;
	}

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
