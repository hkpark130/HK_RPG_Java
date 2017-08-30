package Monster;

import java.util.Random;

import Item.Item;

public class Monster19 extends Monster{
	
	public Monster19(){
		Random rand = new Random();
		super. name = "정예 주술사";
		super. skillName = "강타";
		super.skillRate = 20;
		super. lev = 26 + rand.nextInt(5);
		
		super. hp = 70000 + lev*2;
		super. maxHp = 70000 + lev*2;
		super. atk = 7500 + (int)((double)lev*2);
		super. skillDmg = atk*2;
		super. def = 4500;
		super. isBoss = false;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon19.png";
		super.dropItem = Item.whoAmI("atk6");
		super. pattern = "QQWER";
		super.dropRate = 0;
	}
}