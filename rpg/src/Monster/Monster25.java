package Monster;

import java.util.Random;

import Item.Item;

public class Monster25 extends Monster{
	
	public Monster25(){
		Random rand = new Random();
		super. name = "벨제뷔트";
		super. skillName = "강타";
		super. lev = 26 + rand.nextInt(5);
		
		super. hp = 5000000 + lev*2;
		super. maxHp = 5000000 + lev*2;
		super. atk = 20000 + (int)((double)lev*2);
		super. skillDmg = atk*10;
		super. def = 15000;
		super. isBoss = true;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon25.png";
		super.dropItem = Item.whoAmI("atk6");
		super.dropRate = 0;
		super. pattern = "QWWWQEQ";
	}
}