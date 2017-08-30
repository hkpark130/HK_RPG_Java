package User;

import Item.Sword;
import Quest.Quest;
import Skill.*;

import java.util.*;

import Item.Clothes;
import Item.Item;
import rpg.App;

public class User {
	public int lev = 1;
	public int str = 10;
	public int dex = 0;
	public int vit = 10;
	public int wiz = 5;
	public int exp = 0;
	public int hp = 100;
	public int mp = 50;
	public int maxExp = 70;
	public int maxHp = 100;	
	public int maxMp = 50;	
	public int gold = 1000;
	public int point = 5;	
	public Quest quest = Quest.whoAmI("Quest1");
	public ArrayList<Item> inven = new ArrayList<Item>(12);;	//인벤토리!!
	public Item atkItem = Item.whoAmI("Sword");		//장착중인 아이템
	public Item defItem = Item.whoAmI("Clothes");	
	public static User player = new Warrior();
	public Hashtable<String,Skill> skills = new Hashtable<String,Skill>();
	public String stack = "";

	
	public void userSkill(int lev){		//스킬 초기화	로드
		for(int i = 0; i<Skill.skillCount ; i++){	
			
			if(lev >= Skill.whoAmI("Skill"+(i+1)).minLev){
				User.player.skills.put("Skill"+(i+1),Skill.whoAmI("Skill"+(i+1)));
				User.player.skills.get("Skill"+(i+1)).setUp("Skill"+(i+1));
			}
		}
		
	}
	
	public void whoAmI(int lev, int str, int dex, int vit, int wiz, int exp, int hp, int mp, int maxExp, int maxHp, int maxMp, int gold, int point, int goal, int atkGrade, int defGrade, String quest, String atkItem, String defItem) {	
		this.lev = lev;
		this.maxExp = lev*lev*7;
		this.str = str;
		this.dex = dex;
		this.vit = vit;

		this.exp = exp;
		this.hp = hp;
		this.mp = mp;
		this.maxExp = maxExp;
		this.maxHp = maxHp;

		this.gold = gold;
		this.point = point;
		this.quest = Quest.whoAmI(quest);
		this.quest.goal = goal;
		this.atkItem = Item.whoAmI(atkItem);
		this.atkItem.grade = atkGrade;
		Item.itemSetUp(this.atkItem);
		this.defItem = Item.whoAmI(defItem);
		this.defItem.grade = defGrade;
		Item.itemSetUp(this.defItem);
		userSkill(lev);
	}
	
}
