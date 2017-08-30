package Monster;

import java.util.Random;

import Item.Item;

public class Monster13 extends Monster{
	
	public Monster13(){
		Random rand = new Random();
		super. name = "마왕군병사";
		super. skillName = "동상";
		super. lev = 21 + rand.nextInt(5);
		super. skillDmg = 67 + (int)((double)lev*2);
		super. hp = 30000 + lev*2;
		super. maxHp = 30000 + lev*2;
		super. atk = 2000 + (int)((double)lev*2);
		super. def = 4000;
		super. isBoss = false;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon13.png";
		super.dropItem = Item.whoAmI("atk7");
		super.dropRate = 10;
	}
}