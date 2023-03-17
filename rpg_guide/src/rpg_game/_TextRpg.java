package rpg_game;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

class MainGame {
	static Scanner scan = new Scanner(System.in);
	static Random ran = new Random();
	private Player player;
	private FileData fileData;
	private Shop shop;
	
	public MainGame() {
		fileData = new FileData();
		shop = new Shop();
	}
	
	public void loadGame(String name, Player player) {
		try {
			player = new Player("");
			fileData.loadData(name, player);
		} catch (Exception e) {
			e.printStackTrace();
		}
		while (true) {
			System.out.println("=============== [메인메뉴] ================");
			System.out.println("[1.길드관리] [2.상점] [3.인벤토리]");
			System.out.println("[4.저장] [0.종료]");
			int sel = scan.nextInt();
			if (sel == 1) {
				player.guildMenu();
			} else if (sel == 2) {
				shop.shopMng();
			} else if (sel == 3) {
				player.inventoryMenu();
			} else if (sel == 4) {
				try {
					fileData.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(sel == 0) {
				System.out.println("게임을 종료 합니다.");
				break;
			}
		}
	}
		
	public void newGame(String name) {
		player = new Player(name);
		while (true) {
			System.out.println("=============== [메인메뉴] ================");
			System.out.println("[1.길드관리] [2.상점] [3.인벤토리]");
			System.out.println("[4.저장] [0.종료]");
			int sel = scan.nextInt();
			if (sel == 1) {
				player.guildMenu();
			} else if (sel == 2) {
				shop.shopMng();
			} else if (sel == 3) {
				player.inventoryMenu();
			} else if (sel == 4) {
				try {
					fileData.save();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if(sel == 0) {
				System.out.println("게임을 종료 합니다.");
				break;
			}
		}
	}
	public void startGame() {
		while(true) {
			System.out.println("=============== [시작메뉴] ================");
			System.out.println("[1.새 게임] [2.게임 로드]");
			int selStart = MainGame.scan.nextInt();
			
			if (selStart == 1) {
				System.out.print("플레이어 이름 : ");
				String name = MainGame.scan.next();
				newGame(name);
			}
			else if (selStart == 2) {
				File folder = new File("saveGames");
				if(folder.exists()) {
					String[] saveList = folder.list();
					if(saveList.length > 0) {
						for(int i=0; i<saveList.length; i++) {
							System.out.printf("%d. %s\n",i+1,saveList[i]);
						}
						System.out.println("로드할 파일 번호를 입력하세요");
						int selFile = MainGame.scan.nextInt();
						String fileName = saveList[selFile - 1];
						loadGame(fileName, player);
					}
					else {
						System.out.println("세이브 파일이 없습니다. 새 게임을 실행해 주세요.");
						continue;
					}
				}
				else {
					System.out.println("세이브 폴더가 없습니다. 새 게임을 실행해 주세요.");
					continue;
				}
			}
			else if(selStart == 0) {
				System.out.println("게임을 종료 합니다.");
				break;
			}
		}
	}
}


public class _TextRpg {
	public static void main(String[] args) {
		
		MainGame game = new MainGame();
		game.startGame();
	}
}