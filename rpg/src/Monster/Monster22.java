package Monster;

import java.util.Random;

import Item.Item;

public class Monster22 extends Monster{
	
	public Monster22(){
		Random rand = new Random();
		super. name = "분신1";
		super. skillName = "강타";	
		super. lev = 26 + rand.nextInt(5);

		super. hp = 1000000 + lev*2;
		super. maxHp = 1000000 + lev*2;
		super. atk = 11000 + (int)((double)lev*2);
		super. skillDmg = atk*10;
		
		super. def = 5000;
		super. isBoss = true;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon22.png";
		super.dropItem = Item.whoAmI("atk10");
		super.dropRate = 10;
		super. pattern = "QWQQER";
	}
}