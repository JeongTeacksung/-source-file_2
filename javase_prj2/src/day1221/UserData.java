package day1221;

import java.io.Serializable;

/**
 * �����͸� ������ �ִ� Ŭ������ ����ȭ��� Ŭ����
 * @author Owner
 */
public class UserData implements Serializable{
	private static final long serialVersionUID = 1433112021624942950L;
	
	private int age;
	private /*transient*/ double weight;
	private /*transient*/ String name;
	
	//transient : ����ȭ ���� Ű����(���� JVM�ܺη� ������ ���� �ʴ´�.)
	
	public UserData() {
	}//UserData

	public UserData(int age, double weight, String name) {
		this.age = age;
		this.weight = weight;
		this.name = name;
	}//UserData

	@Override
	public String toString() {
		return "UserData [age=" + age + ", weight=" + weight + ", name=" + name + "]";
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}//class