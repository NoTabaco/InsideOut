package project_03;

//playable ĳ���͸� �����ϱ� ���� �޼ҵ�
public class Ball implements Runnable {
	// x��ǥ , y��ǥ , radius , �߽���ǥ ��  x , y
	private int x, y, r , Ox , Oy;
	// ������ų ũ��
	private double size;
	// Thread ��ü
	private Thread thread;


	Ball() {
		// �����带 ����� ��ü�� �־��ش�.
		setThread(new Thread(this));
		// �������� x, y
		x = 630;
		y = 73;
		r = 280;     
		 // ���� �������� �������� �� ( x��ǥ ) 905 - 265 - 15 (x��ǥ(3��) - ������ - ������ ����) 625 , ���� 3�ú��� �׸��� �׸�
		Ox = 625;  
		// ���� �������� �ö� ( y��ǥ ) 630 - 265 - 10 (y��ǥ(6��) - ������ - ������ ����) 355 , 12�ú��� �׸��� �׸�
		Oy = 355;  
	}

	public void run() {
		// ���� ���� �� ���� ������� �ϹǷ� ��� �ݺ�
		while (true) {
			try {
				// �� �߽ɰ� + �������̹Ƿ� �߽���ǥ ����  ���ؾ� �Ѵ�. 
				// 3�ø� �������� 0�� �̹Ƿ� , ������ ���� ����� �� 90���� ���־ 12�ø� �������� 0�� ������� �Ѵ�.
				x = Ox + (int) (r * Math.cos(  (size - 90)/ 180 * Math.PI ) + 3 );
				y = Oy + (int) (r * Math.sin(  (size - 90)/ 180 * Math.PI )  );
				// ��� �����ų x��ǥ, y��ǥ 
				size += 0.5;
				Thread.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// x�� ���� ������ �Լ�
	public int getX() {
		return x;
	}

	// x�� ���� �����ϴ� �Լ�
	public void setX(int x) {
		this.x = x;
	}

	// y�� ���� ������ �Լ�
	public int getY() {
		return y;
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
