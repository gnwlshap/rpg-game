package rpg_game;

import java.util.ArrayList;

public class Player {
	static int money;
	static Guild guild = new Guild();
	static Inventory inven = new Inventory();

	public Player() {
		money = 100000;
		guild.setGuild();
	}

	public void guildMenu() {
		guild.guildMenu();
	}

	public void inventoryMenu() {
		inven.inventoryMenu();
	}

	static public ArrayList<Unit> getUnitList() {
		
		return guild.getUnitList();
	}

	static public ArrayList<Item> getItemList() {
		return inven.getItemList();
	}

	static public Unit getGuildUnit(int num) {
		return guild.getGuildUnit(num);
	}

	static public int getGuildSize() {
		return guild.getUnitList().size();
	}
	
	static public int getItemSize() {
		return inven.getItemList().size();
	}
	
	public static int getMoney() {
		return money;
	}
	
	public void clearInven() {
		inven.
	}
	
	public static void setGuildUnitWeapon(int index, Item item) {
		guild.setUnitWeapon(index, item);
	}
	public static void setGuildUnitArmor(int index, Item item) {
		guild.setUnitArmor(index, item);
	}
	public static void setGuildUnitRing(int index, Item item) {
		guild.setUnitRing(index, item);
	}
	
	public static void setMoney(int money) {
		Player.money = money;
	}
}