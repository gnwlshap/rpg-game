package rpg_game;

public class Item {
	static final int WEAPON = 1;
	static final int ARMOR = 2;
	static final int RING = 3;
	private int kind;
	private String name;
	private int power;
	private int price;

	public void setItem(int k, String n, int p, int pr) {
		kind = k;
		name = n;
		power = p;
		price = pr;
	}
	
	public int getKind() {
		return kind;
	}
	public String getName() {
		return name;
	}
	public int getPower() {
		return power;
	}
	public int getPrice() {
		return price;
	}

	public void setKind(int kind) {
		this.kind = kind;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}