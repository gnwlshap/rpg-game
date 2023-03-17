package rpg_game;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> itemList = new ArrayList<>();

	public void inventoryMenu() {
		while (true) {
			System.out.println("============ [인벤메뉴] =============");
			System.out.println("[1.착용] [2.판매] [0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 0)
				break;
			if (sel == 1) {
				equipMenu();
			}
			if (sel == 2)
				sellMenu();
		}
	}
	
	public ArrayList<Item> getItemList() {
		return (ArrayList<Item>) itemList.clone();
	}
	
	public void clearItemList() {
		itemList.clear();
	}
	
	public void equipMenu() {
		System.out.println("[1.플레이어] [2.길드원] [0.뒤로가기]");
		int sel = MainGame.scan.nextInt();
		if (sel == 0)
			return;
		else if (sel == 1)
			equipPlayerMenu();
		else if (sel == 2)
			equipUnitMenu();
	}
	
	public void equipPlayerMenu() {
		while (true) {
			Player.playerUnit.printStatus();
			Player.playerUnit.printEquippedItem();
			printItemList();
			System.out.println("착용할 아이템 번호를 입력하세요 [0.뒤로가기]");
			int selEquip = MainGame.scan.nextInt();
			if (selEquip == 0 || selEquip > itemList.size())
				break;
			else if (itemList.get(selEquip - 1).getKind() == Item.WEAPON) {
				if (Player.playerUnit.getWeapon() != null) {
					itemList.add(Player.playerUnit.getWeapon());
				}
				Player.playerUnit.setWeapon(itemList.get(selEquip - 1));
				
			} else if (itemList.get(selEquip - 1).getKind() == Item.ARMOR) {
				if (Player.playerUnit.getArmor() != null) {
					itemList.add(Player.playerUnit.getArmor());
				}
				Player.playerUnit.setArmor(itemList.get(selEquip - 1));
				
			} else if (itemList.get(selEquip - 1).getKind() == Item.RING) {
				if (Player.playerUnit.getRing() != null) {
					itemList.add(Player.playerUnit.getRing());
				}
				Player.playerUnit.setRing(itemList.get(selEquip - 1));
			}
			itemList.remove(selEquip - 1);
		}
	}

	public void equipUnitMenu() {
		Player.guild.printAllUnitStaus();
		System.out.println("아이템 착용할 길드원을 선택하세요 [0.뒤로가기]");
		int selUnit = MainGame.scan.nextInt();
		if(selUnit == 0 || selUnit > Player.getGuildSize())
			return;
		while (true) {
			Player.guild.printUnitStatus(selUnit - 1);
			Player.guild.printUnitItem(selUnit - 1);
			printItemList();
			System.out.println("착용할 아이템 번호를 입력하세요 [0.뒤로가기]");
			int selEquip = MainGame.scan.nextInt();
			if (selEquip == 0 || selEquip > itemList.size())
				break;
			else if (itemList.get(selEquip - 1).getKind() == Item.WEAPON) {
				if (Player.getGuildUnit(selUnit - 1).getWeapon() != null) {
					itemList.add(Player.getGuildUnit(selUnit - 1).getWeapon());
				}
				Player.setGuildUnitWeapon(selUnit - 1, itemList.get(selEquip - 1));
			}
			else if (itemList.get(selEquip - 1).getKind() == Item.ARMOR) {
				if (Player.getGuildUnit(selUnit - 1).getArmor() != null) {
					itemList.add(Player.getGuildUnit(selUnit - 1).getArmor());
				}
				Player.setGuildUnitArmor(selUnit - 1, itemList.get(selEquip - 1));
			}
			else if (itemList.get(selEquip - 1).getKind() == Item.RING) {
				if (Player.getGuildUnit(selUnit - 1).getRing() != null) {
					itemList.add(Player.getGuildUnit(selUnit - 1).getRing());
				}
				Player.setGuildUnitRing(selUnit - 1, itemList.get(selEquip - 1));
			}
			itemList.remove(selEquip - 1);
		}
	}
	
	public void unequipPlayerMenu() {
		while (true) {
			Player.playerUnit.printStatus();
			Player.playerUnit.printEquippedItem();
			printItemList();
			System.out.println("해제할 아이템 번호를 입력하세요 [0.뒤로가기]");
			int selEquip = MainGame.scan.nextInt();
			if (selEquip == 0 || selEquip > 3)
				break;
			else if (selEquip - 1 == Item.WEAPON) {
				if (Player.playerUnit.getWeapon() != null) {
					itemList.add(Player.playerUnit.getWeapon());
					Player.playerUnit.setWeapon(null);
				}
				else
					System.out.println("착용중인 아이템이 없습니다.");
			} 
			else if (selEquip - 1 == Item.ARMOR) {
				if (Player.playerUnit.getArmor() != null) {
					itemList.add(Player.playerUnit.getArmor());
					Player.playerUnit.setArmor(null);
				}
				else
					System.out.println("착용중인 아이템이 없습니다.");
			}
			else if (selEquip - 1 == Item.RING) {
				if (Player.playerUnit.getRing() != null) {
					itemList.add(Player.playerUnit.getRing());
					Player.playerUnit.setRing(null);
				}
				else
					System.out.println("착용중인 아이템이 없습니다.");
			}
		}
	}
	
	public void unequipUnitMenu() {
		Player.guild.printAllUnitStaus();
		System.out.println("아이템 해제할 길드원을 선택하세요 [0.뒤로가기]");
		int selUnit = MainGame.scan.nextInt();
		if(selUnit == 0 || selUnit > Player.getGuildSize())
			return;
		while (true) {
			Player.guild.printUnitStatus(selUnit - 1);
			Player.guild.printUnitItem(selUnit - 1);
			System.out.println("해제할 부위 번호를 입력하세요 [0.뒤로가기]");
			int selEquip = MainGame.scan.nextInt();
			if (selEquip == 0 || selEquip > 3)
				break;
			else if (selEquip - 1 == Item.WEAPON) {
				if (Player.getGuildUnit(selUnit - 1).getWeapon() != null) {
					itemList.add(Player.getGuildUnit(selUnit - 1).getWeapon());
					Player.setGuildUnitWeapon(selUnit - 1, null);
				}
				else
					System.out.println("착용중인 아이템이 없습니다.");
			} 
			else if (selEquip - 1 == Item.ARMOR) {
				if (Player.getGuildUnit(selUnit - 1).getArmor() != null) {
					itemList.add(Player.getGuildUnit(selUnit - 1).getArmor());
					Player.setGuildUnitArmor(selUnit - 1, null);
				}
				else
					System.out.println("착용중인 아이템이 없습니다.");
			}
			else if (selEquip - 1 == Item.RING) {
				if (Player.getGuildUnit(selUnit - 1).getRing() != null) {
					itemList.add(Player.getGuildUnit(selUnit - 1).getRing());
					Player.setGuildUnitRing(selUnit - 1, null);
				}
				else
					System.out.println("착용중인 아이템이 없습니다.");
			}
		}
	}
	
	

	public void printItemList() {
		System.out.println("============ [아이템리스트] ==============");
		for (int i = 0; i < itemList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print("[이름 : " + itemList.get(i).getName() + "]");
			System.out.print("[능력 : " + itemList.get(i).getPower() + "]");
			System.out.print("[가격 : " + itemList.get(i).getPrice() + "]");
			System.out.println("");
		}
	}

	public void sellMenu() {
		while (true) {
			printItemList();
			System.out.println("[골드 : " + Player.money + "]");
			System.out.println("판매할 아이템 번호를 입력하세요. (50 % 세금) [0.뒤로가기]");
			int selSell = MainGame.scan.nextInt();
			if(selSell == 0 || selSell > itemList.size())
				break;
			System.out.println(itemList.get(selSell - 1).getName() + "을 판매합니다.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Player.money += (itemList.get(selSell - 1).getPrice() / 2);
			itemList.remove(selSell - 1);
		}
	}

	public void addItem(Item item) {
		itemList.add(item);
	}

}