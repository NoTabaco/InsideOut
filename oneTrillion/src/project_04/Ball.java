package project_04;

/** �÷��̾ ������ �÷����ϰ� �Ǵ� Ball�� ����(��ġ,ȸ�� ��)�ϱ� ���� Ŭ����
 * 
 * @author Jimin Kim
 * @version 0.4
 * 
 * */
public class Ball implements Runnable {
	/** Ball�� �׸��� ���� x��ǥ, y��ǥ, radius, ���� �߽� x��ǥ Ox, ���� �߽� y��ǥ Oy ������ */
	private int x, y, r , Ox , Oy;
	/** Ball�� ȸ����Ű�� ���� ������ų ���� */
	private double size;
	/** Ball�� ȸ����Ű�� ���� Thread ��ü */
	private Thread thread;
	/** Ball�� �׸��� �Ǹ� leftX, topY���� �׸��� ������ �׿� ���� �Ÿ��� �����ϱ� ���� ���� */
    private int adjustmentDistance ;
    
    /** Ball�� �߽���ǥ ���� �����ϱ� ���� ������ */
	Ball() {
		// �����带 ����� ��ü�� �־��ش�.
		setThread(new Thread(this));
		// Ball�� �߽���ǥ ��, ó�� ������ǥ x, y
		x = 640;
		y = 85;
		// ���� �� ���� ������ 265 + ball�� ������ 13 + ���� ��¦ �������Ƿ� �׿� ���� ������ 2�� �־���. 
		r = 280;     
		 // ���� �� ���� �߽� x��ǥ = 375 + 265 = 640 , ���� �� ���� �������� 265
		Ox = 640;  
		 // ���� �� ���� �߽� y��ǥ = 100 + 265 = 640 , ���� �� ���� �������� 265
		Oy = 365;  
		 // Ball�� �߽���ǥ���� Ball�� ������ ��ŭ�� 13�� x��ǥ, y��ǥ�� ���� �־��ָ� �׸����� �ϴ� Ball�� �׸� �� �ְԵȴ�.
		adjustmentDistance = 13;
	}
	
    /** Thread�� �����Ű�� run�Լ��� ����
     *  ����, run�Լ� ����  while���� ���� Ball�� ȸ���ϰ��� �ϴ� ���� ��� ȸ���ϰ� �ȴ�. */
	public void run() {
		// ���� ���� �� ���� ������� �ϹǷ� ��� �ݺ�
		while (true) {
			try {
				// Ball�� ���� �� ���� �߽����� ���ƾ� �ϹǷ� �� �߽ɰ��� ���� �������� ���� ���� Ox,Oy�� �����־�� �Ѵ�. 
				// 3�ø� �������� 0�� �̹Ƿ� , ������ ���� ����� �� 90���� ���־ 12�ø� �������� 0�� ������� �Ѵ�.
				x = Ox + (int) (r * Math.cos(  (size - 90)/ 180 * Math.PI )  );
				y = Oy + (int) (r * Math.sin(  (size - 90)/ 180 * Math.PI )  );
				// �ﰢ�Լ��� ���� ���� ���� size�� ������Ű�鼭 Ball�� ���� ���� �� ���� ȸ����Ų��.
				size += 1;
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
		return x - adjustmentDistance ;
	}

	/** Ball�� x��ǥ�� �����ϴ� setX �Լ� 
	 * 
	 * @param int x
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
	 * @param int y
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
	 * @param Thread thread
	 * */
	public void setThread(Thread thread) {
		this.thread = thread;
	}

}
