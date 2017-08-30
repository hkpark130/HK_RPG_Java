package Monster;

import java.util.Random;

import Item.Item;

public class Monster11 extends Monster{
	
	public Monster11(){
		Random rand = new Random();
		super. name = "À¯Àû°ñ·½";
		super. skillName = "½º¸Å½¬";
		super. lev = 11 + rand.nextInt(5);
		super. skillDmg = 57 + (int)((double)lev*2);
		super. hp = 10000 + lev*2;
		super. maxHp = 10000 + lev*2;
		super. atk = 2000 + (int)((double)lev*2);
		super. def = 1500;
		super. isBoss = false;
		super.exp = 207 + lev*10;
		super.imgSrc = "../reImages/mon11.png";
		super.dropItem = Item.whoAmI("atk6");
		super.dropRate = 50;
	}
}