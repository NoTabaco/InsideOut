package project_04;

//playable ĳ���͸� �����ϱ� ���� �޼ҵ�
public class Ball implements Runnable {
	// x��ǥ , y��ǥ , radius , �߽���ǥ ��  x , y
	private int x, y, r , Ox , Oy;
	// ������ų ũ��
	private double size;
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
		r = 280;     
		 // ���� �߽� x��ǥ = 375 + 265 = 640 , ���� �� ���� �������� 265
		Ox = 640;  
		 // ���� �߽� y��ǥ = 100 + 265 = 640 , ���� �� ���� �������� 265
		Oy = 365;  
	}

	public void run() {
		// ���� ���� �� ���� ������� �ϹǷ� ��� �ݺ�
		while (true) {
			try {
				// �� �߽ɰ� + �������̹Ƿ� �߽���ǥ ����  ���ؾ� �Ѵ�. 
				// 3�ø� �������� 0�� �̹Ƿ� , ������ ���� ����� �� 90���� ���־ 12�ø� �������� 0�� ������� �Ѵ�.
				x = Ox + (int) (r * Math.cos(  (size - 90)/ 180 * Math.PI )  );
				y = Oy + (int) (r * Math.sin(  (size - 90)/ 180 * Math.PI )  );
				// ��� �����ų x��ǥ, y��ǥ 
				size += 1;
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

	// x�� ���� �����ϴ� �Լ�
	public void setX(int x) {
		this.x = x;
	}

	// y�� ���� ������ �Լ�
	public int getY() {
		// Ball�� �׸� �� 11�� ������ �������� �׷��ֹǷ� �������� 13�� ���־ Y��ǥ�� ���´�. 
		return y - adjustmentDistance;
	}

	// y�� ���� �����ϴ� �Լ�
	public void setY(int y) {
		this.y = y;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

}
