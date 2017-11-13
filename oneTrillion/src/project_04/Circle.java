package project_04;

import java.awt.BasicStroke;
import java.awt.Color;

/**
 * ���� �÷��̸� ���� ���������� ���� ���� Ŭ�����̴�.
 * @author SungHo Yun
 * @version 0.4
 */
public class Circle {
	/** ���� ��ǥ ���� ũ�� �� ���� �����̴�. */
	private int x,y, width, height;
	/** ���� ���⿡ ���� �����̴�. */
	BasicStroke stroke;
	/** �������� ���� �����̴�. */
	private Color color;
	
	/**
	 * ���� ��ǥ�� ���� ũ�� �׸��� ����� ������ �����ڸ� ���ؼ� �ʱ�ȭ �����ش�.
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param stroke
	 * @param color
	 */
	public Circle(int x, int y, int width, int height, int stroke, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.stroke = new BasicStroke(stroke);
		this.color = color;
	}

	/**
	 * ���� ��ǥ�� x �� ���ϰ����� ��ȯ�Ѵ�.
	 * @return x
	 */
	public int getX() {
		return x;
	}

	/**
	 * ���� ��ǥ�� x�� �������ش�.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * ���� ��ǥ�� y�� ���ϰ����� ��ȯ�Ѵ�.
	 * @return y
	 */
	public int getY() {
		return y;
	}

	/**
	 * ���� ��ǥ�� y�� �������ش�.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * ���� ���α��̸� ���ϰ����� ��ȯ�Ѵ�.
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * ���� ���α��̸� �������ش�.
	 * @param width
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * ���� ���α��̸� ���ϰ����� ��ȯ�Ѵ�.
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * ���� ���α��̸� �������ش�.
	 * @param height
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * ���� ���Ⱚ�� ���ϰ����� ��ȯ�Ѵ�.
	 * @return stroke
	 */
	public BasicStroke getStroke() {
		return stroke;
	}

	/**
	 * ���� ���Ⱚ�� �������ش�.
	 * @param stroke
	 */
	public void setStroke(int stroke) {
		this.stroke = new BasicStroke(stroke);
	}

	/**
	 * ���� ������ ���ϰ����� ��ȯ�Ѵ�.
	 * @return color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * ���� ������ �������ش�.
	 * @param color
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
