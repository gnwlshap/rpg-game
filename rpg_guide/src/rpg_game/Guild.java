package rpg_game;

import java.util.ArrayList;

public class Guild {
	private final int PARTY_SIZE = 4;
	private ArrayList<Unit> unitList = new ArrayList<>();
	private ArrayList<Unit> partyList = new ArrayList<>();

	public void setGuild() {
		Unit temp = new Unit("호랑이", 1, 100, 10, 5, 0);
		unitList.add(temp);
		temp = new Unit("강아지", 1, 80, 7, 3, 0);
		unitList.add(temp);
		temp = new Unit("사슴", 1, 50, 3, 1, 0);
		unitList.add(temp);
		temp = new Unit("두더지", 1, 70, 5, 2, 0);
		unitList.add(temp);
		temp = new Unit("돼지", 1, 200, 4, 8, 0);
		unitList.add(temp);
		temp = new Unit("사자", 1, 120, 11, 7, 0);
		unitList.add(temp);
		for (int i = 0; i < 4; i++) {
			unitList.get(i).setParty(true);
		}
		for (int i = 0; i < unitList.size(); i++) {
			if (unitList.get(i).isParty()) {
				partyList.add(unitList.get(i));
			}
		}
	}
	public ArrayList<Unit> getUnitList() {
		return (ArrayList<Unit>) unitList.clone();
	}
	
	public void addUnit(Unit unit) {
		unitList.add(unit);
	}
	
	public void setUnitWeapon(int index, Item item) {
		unitList.get(index).setWeapon(item);
	}
	public void setUnitArmor(int index, Item item) {
		unitList.get(index).setArmor(item);
	}
	public void setUnitRing(int index, Item item) {
		unitList.get(index).setRing(item);
	}
	
	public void clearUnitList() {
		unitList.clear();
	}

	public Unit getGuildUnit(int num) {
		return unitList.get(num);
	}

	public void printAllUnitStaus() {
		System.out.println("======================================");
		System.out.println("[골드 : " + Player.money + "]");
		System.out.println("============= [길드원] =================");
		for (int i = 0; i < unitList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print(" [이름 : " + unitList.get(i).getName() + "]");
			System.out.print(" [레벨 : " + unitList.get(i).getLevel() + "]");
			System.out.print(" [체력 : " + unitList.get(i).getHp());
			System.out.println(" / " + unitList.get(i).getMaxHp() + "]");
			System.out.print("[공격력 : " + unitList.get(i).getAtt() + "]");
			System.out.print(" [방어력 : " + unitList.get(i).getDef() + "]");
			System.out.println(" [파티중 : " + unitList.get(i).isParty() + "]");
			System.out.println("");
		}
		System.out.println("=================================");
	}

	public void printUnitStatus(int num) {
		unitList.get(num).printStatus();
	}

	public void printUnitItem(int num) {
		unitList.get(num).printEquippedItem();
	}

	private void buyUnit() {
		if (Player.money < 5000) {
			System.out.println("보유 금액이 부족합니다.");
			return;
		}
		String[] n1 = { "박", "이", "김", "최", "안", "양", "정" };
		String[] n2 = { "명", "정", "재", "원", "우", "의", "철" };
		String[] n3 = { "수", "후", "환", "준", "진", "지", "원" };

		String name = n1[MainGame.ran.nextInt(n1.length)];
		name += n2[MainGame.ran.nextInt(n1.length)];
		name += n3[MainGame.ran.nextInt(n1.length)];
		int ran = MainGame.ran.nextInt(8) + 2;
		int hp = ran * 11;
		int att = ran + 1;
		int def = ran / 2 + 1;
		Unit temp = new Unit(name, 1, hp, att, def, 0);

		System.out.println("=====================================");
		System.out.print("[이름 : " + name + "]");
		System.out.print(" [레벨 : " + 1 + "]");
		System.out.print(" [체력 : " + hp);
		System.out.println(" / " + hp + "]");
		System.out.print("[공격력 : " + att + "]");
		System.out.println(" [방어력 : " + def + "]");
		System.out.println("길드원을 추가합니다.");
		System.out.println("=====================================");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		unitList.add(temp);
		Player.money -= 5000;
	}

	private void sellUnit() {
		printAllUnitStaus();
		System.out.println("판매할 용병을 입력하세요 [0.뒤로가기]");
		int sel = MainGame.scan.nextInt();
		if(sel == 0 || sel > unitList.size()) {
			return;
		}
		if (unitList.get(sel - 1).isParty()) {
			System.out.println("파티중인 멤버는 삭제할수 없습니다.");
		} else {
			System.out.println("=================================");
			System.out.print("[이름 : " + unitList.get(sel - 1).getName() + "]");
			System.out.println("길드원을 삭제합니다.");
			System.out.println("=================================");
			unitList.remove(sel - 1);
			Player.money += 2500;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void printParty() {
		System.out.println("================ [파티원] ===============");
		for (int i = 0; i < partyList.size(); i++) {
			System.out.print("[" + (i + 1) + "번]");
			System.out.print(" [이름 : " + partyList.get(i).getName() + "]");
			System.out.print(" [레벨 : " + partyList.get(i).getLevel() + "]");
			System.out.print(" [체력 : " + partyList.get(i).getHp());
			System.out.println(" / " + partyList.get(i).getMaxHp() + "]");
			System.out.print("[공격력 : " + partyList.get(i).getAtt() + "]");
			System.out.print(" [방어력 : " + partyList.get(i).getDef() + "]");
			System.out.println("");
		}
		System.out.println("=====================================");
	}

	public void inviteParty() {
		if (partyList.size() >= PARTY_SIZE) {
			System.out.println("더 이상 초대 할 수 없습니다.");
			return;
		}
		printAllUnitStaus();
		System.out.println("초대할 번호를 입력하세요 [0.뒤로가기]");
		int guildNum = MainGame.scan.nextInt();
		if(guildNum == 0 || guildNum > unitList.size())
			return;
		if(unitList.get(guildNum - 1).isParty()) {
			System.out.println("파티가 아닌 길드원 선택하세요.");
			return;
		}

		System.out.println("====================================");
		System.out.print("[이름 : " + unitList.get(guildNum - 1).getName() + "]");
		System.out.println("을(를) 파티에 초대합니다. ");
		System.out.println("====================================");

		unitList.get(guildNum - 1).setParty(true);
		partyList.add(unitList.get(guildNum - 1));
		
		partySort();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void kickOutParty() {
		printParty();
		System.out.println("강퇴할 번호를 입력하세요 [0.뒤로가기]");
		int partyNum = MainGame.scan.nextInt();
		if(partyNum == 0 || partyNum > partyList.size())
			return;

		System.out.println("====================================");
		System.out.print("[이름 : " + partyList.get(partyNum - 1).getName() + "]");
		System.out.print("을(를) 파티에서 추방합니다.");
		System.out.println("====================================");
		
		partyList.get(partyNum - 1).setParty(false);
		partyList.remove(partyNum - 1);

		partySort();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void partySort() {
		for(int i=0; i<partyList.size(); i++) {
			if(!unitList.get(i).isParty()) {
				unitList.add(unitList.get(i));
				unitList.remove(i);
				i --;
			}
		}
	}

	public void guildMenu() {
		while (true) {
			System.out.println("=============== [길드관리] ================");
			System.out.println("[1.유닛목록] [2.유닛구입] [3.유닛판매]\n" + "[4.파티초대] [5.파티강퇴] [0.뒤로가기]");
			int sel = MainGame.scan.nextInt();
			if (sel == 1) {
				printAllUnitStaus();
			} else if (sel == 2) {
				buyUnit();
			} else if (sel == 3) {
				sellUnit();
			} else if (sel == 4) {
				inviteParty();
			} else if (sel == 5) {
				kickOutParty();
			}
			else if (sel == 0) {
				break;
			}
		}
	}

}