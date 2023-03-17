package rpg_game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileData {
	void save() throws IOException {
		File folder = new File("saveGames");
		
		if (!folder.exists()) {
		    folder.mkdir(); 
		}
		saveFile(folder);
	}
	
	void saveFile(File folder) throws IOException {
		FileWriter fw = null;
		String fileName = Player.playerUnit.getName();
		
		File file = new File(folder, fileName);
		
		fw = new FileWriter(file);
		ArrayList<Unit> temp = Player.getUnitList();
		String gameData = "";
		gameData += Player.playerUnit.getName() + "/" + Player.playerUnit.getLevel() + "/" 
		+ Player.playerUnit.getMaxHp() + "/" + Player.playerUnit.getAtt() + "/" +Player.playerUnit.getDef() + "/" 
		+ Player.playerUnit.getExp() + "/" +Player.playerUnit.isParty();
		gameData += "\r\n";
		if (Player.playerUnit.getWeapon() == null) {
			gameData += Player.playerUnit.getWeapon();
		} else {
			Item userItem = Player.playerUnit.getWeapon();
			gameData += userItem.getKind() + "," + userItem.getName() +","
					+ userItem.getPower() + "," + userItem.getPrice();
		}
		gameData += "/";
		if (Player.playerUnit.getArmor() == null) {
			gameData += Player.playerUnit.getWeapon();
		} else {
			Item userItem = Player.playerUnit.getArmor();
			gameData += userItem.getKind() + "," + userItem.getName() +","
					+ userItem.getPower() + "," + userItem.getPrice();
		}
		gameData += "/";
		if (Player.playerUnit.getRing() == null) {
			gameData += Player.playerUnit.getRing();
		} else {
			Item userItem = Player.playerUnit.getRing();
			gameData += userItem.getKind() + "," + userItem.getName() +","
					+ userItem.getPower() + "," + userItem.getPrice();
		}
		gameData += "\r\n";
		gameData += Player.getMoney();
		gameData += "\r\n";
		gameData += temp.size();
		gameData += "\r\n";
		for (int i = 0; i < temp.size(); i++) {
			gameData += temp.get(i).getName();
			gameData += "/";
			gameData += temp.get(i).getLevel();
			gameData += "/";
			gameData += temp.get(i).getMaxHp();
			gameData += "/";
			gameData += temp.get(i).getAtt();
			gameData += "/";
			gameData += temp.get(i).getDef();
			gameData += "/";
			gameData += temp.get(i).getExp();
			gameData += "/";
			gameData += temp.get(i).isParty();
			gameData += "\r\n";
			if (temp.get(i).getWeapon() == null) {
				gameData += temp.get(i).getWeapon();
			} else {
				Item item = temp.get(i).getWeapon();
				String weaponData = "";
				weaponData += item.getKind();
				weaponData += ",";
				weaponData += item.getName();
				weaponData += ",";
				weaponData += item.getPower();
				weaponData += ",";
				weaponData += item.getPrice();
				gameData += weaponData;

			}
			gameData += "/";
			if (temp.get(i).getArmor() == null) {
				gameData += temp.get(i).getArmor();
			} else {
				Item item = temp.get(i).getArmor();
				String weaponData = "";
				weaponData += item.getKind();
				weaponData += ",";
				weaponData += item.getName();
				weaponData += ",";
				weaponData += item.getPower();
				weaponData += ",";
				weaponData += item.getPrice();
				gameData += weaponData;

			}
			gameData += "/";
			if (temp.get(i).getRing() == null) {
				gameData += temp.get(i).getRing();
			} else {
				Item item = temp.get(i).getRing();
				String weaponData = "";
				weaponData += item.getKind();
				weaponData += ",";
				weaponData += item.getName();
				weaponData += ",";
				weaponData += item.getPower();
				weaponData += ",";
				weaponData += item.getPrice();
				gameData += weaponData;
			}
			gameData += "\r\n";
		}
		gameData += Player.getItemSize();
		gameData += "\r\n";
		for (int i = 0; i < Player.getItemSize(); i++) {
			Item item = Player.getItemList().get(i);

			gameData += item.getKind();
			gameData += "/";
			gameData += item.getName();
			gameData += "/";
			gameData += item.getPower();
			gameData += "/";
			gameData += item.getPrice();
			gameData += "\r\n";
		}
		System.out.println(gameData);
		fw.write(gameData, 0, gameData.length());
		fw.close();

	}

	void loadData(String fileName, Player player) throws IOException {
		File file = null;
		FileReader reader = null;
		BufferedReader br = null;
		file = new File(fileName);
		if (file.exists()) {
			reader = new FileReader(fileName);
			br = new BufferedReader(reader);
			// 플레이어
			String playerData = br.readLine();
			String[] playerArr = playerData.split("/");
			String playerName = playerArr[0];
			int playerLevel = Integer.parseInt(playerArr[1]);
			int playerMaxhp = Integer.parseInt(playerArr[2]);
			int playerAtt = Integer.parseInt(playerArr[3]);
			int playerDef = Integer.parseInt(playerArr[4]);
			int playerExp = Integer.parseInt(playerArr[5]);
			boolean playerParty = Boolean.parseBoolean(playerArr[6]);
			player.playerUnit.setUnit(playerName, playerLevel, playerMaxhp, playerAtt, playerDef, playerExp, playerParty);
			String userItemData = br.readLine();
			String userItemArr[] = userItemData.split("/");
			if (userItemArr[0].equals("null")) {
				player.playerUnit.setWeapon(null);
			} else {
				String[] weapon = userItemArr[0].split(",");
				int itemKind = Integer.parseInt(weapon[0]);
				String itemName = weapon[1];
				int itemPower = Integer.parseInt(weapon[2]);
				int itemPrice = Integer.parseInt(weapon[3]);
				Item item = new Item();
				item.setItem(itemKind, itemName, itemPower, itemPrice);
				player.playerUnit.setWeapon(item);
			}
			if (userItemArr[1].equals("null")) {
				player.playerUnit.setArmor(null);
			} else {
				String[] armor = userItemArr[1].split(",");
				int itemKind = Integer.parseInt(armor[0]);
				String itemName = armor[1];
				int itemPower = Integer.parseInt(armor[2]);
				int itemPrice = Integer.parseInt(armor[3]);
				Item item = new Item();
				item.setItem(itemKind, itemName, itemPower, itemPrice);
				player.playerUnit.setArmor(item);
			}
			if (userItemArr[2].equals("null")) {
				player.playerUnit.setRing(null);
			} else {
				String[] ring = userItemArr[2].split(",");
				int itemKind = Integer.parseInt(ring[0]);
				String itemName = ring[1];
				int itemPower = Integer.parseInt(ring[2]);
				int itemPrice = Integer.parseInt(ring[3]);
				Item item = new Item();
				item.setItem(itemKind, itemName, itemPower, itemPrice);
				player.playerUnit.setRing(item);
			}
			String money = br.readLine();
			player.setMoney(Integer.parseInt(money));
			System.out.println(Player.getMoney());
			// 길드
			String guildSize = br.readLine();
			int size = Integer.parseInt(guildSize);
			player.guild.clearUnitList();
			System.out.println(size);
			for (int i = 0; i < size; i++) {
				String unitData = br.readLine();
				String[] unitArr = unitData.split("/");
				String name = unitArr[0];
				int level = Integer.parseInt(unitArr[1]);
				int maxhp = Integer.parseInt(unitArr[2]);
				int att = Integer.parseInt(unitArr[3]);
				int def = Integer.parseInt(unitArr[4]);
				int exp = Integer.parseInt(unitArr[5]);
				boolean party = Boolean.parseBoolean(unitArr[6]);
				Unit temp = new Unit(name, level, maxhp, att, def, exp, party);
				player.guild.addUnit(temp);
				// ==================== item =======================
				String itemData = br.readLine();
				String itemArr[] = itemData.split("/");
				if (itemArr[0].equals("null")) {
					player.setGuildUnitWeapon(i, null);
				} else {
					String[] weapon = itemArr[0].split(",");
					int itemKind = Integer.parseInt(weapon[0]);
					String itemName = weapon[1];
					int itemPower = Integer.parseInt(weapon[2]);
					int itemPrice = Integer.parseInt(weapon[3]);
					Item item = new Item();
					item.setItem(itemKind, itemName, itemPower, itemPrice);
					player.setGuildUnitWeapon(i, item);
				}
				if (itemArr[1].equals("null")) {
					player.setGuildUnitArmor(i, null);
				} else {
					String[] armor = itemArr[1].split(",");
					int itemKind = Integer.parseInt(armor[0]);
					String itemName = armor[1];
					int itemPower = Integer.parseInt(armor[2]);
					int itemPrice = Integer.parseInt(armor[3]);
					Item item = new Item();
					item.setItem(itemKind, itemName, itemPower, itemPrice);
					player.setGuildUnitArmor(i, item);
				}
				if (itemArr[2].equals("null")) {
					player.setGuildUnitRing(i, null);
				} else {
					String[] ring = itemArr[2].split(",");
					int itemKind = Integer.parseInt(ring[0]);
					String itemName = ring[1];
					int itemPower = Integer.parseInt(ring[2]);
					int itemPrice = Integer.parseInt(ring[3]);
					Item item = new Item();
					item.setItem(itemKind, itemName, itemPower, itemPrice);
					player.setGuildUnitRing(i, item);
				}
			}
			// ===================== item ============================
			String invenSize = br.readLine();
			System.out.println(invenSize);
			int inSize = Integer.parseInt(invenSize);

			player.inven.clearItemList();
			for (int i = 0; i < inSize; i++) {
				String invenDate = br.readLine();
				String[] invenArr = invenDate.split("/");
				int itemKind = Integer.parseInt(invenArr[0]);
				String itemName = invenArr[1];
				int itemPower = Integer.parseInt(invenArr[2]);
				int itemPrice = Integer.parseInt(invenArr[3]);
				Item item = new Item();
				item.setItem(itemKind, itemName, itemPower, itemPrice);
				player.inven.addItem(item);
			}
		}
	}
}