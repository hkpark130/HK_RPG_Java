package Monster;

import java.util.Random;

import Item.Item;

public class Monster23 extends Monster{
	
	public Monster23(){
		Random rand = new Random();
		super. name = "분신2";
		super. skillName = "강타";
		super. lev = 26 + rand.nextInt(5);
		
		super. hp = 1000000 + lev*2;
		super. maxHp = 10000000 + lev*2;
		super. atk = 15000 + (int)((double)lev*2);
		super. skillDmg = atk*10;
		super. def = 10000;
		super. isBoss = true;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon23.png";
		super.dropItem = Item.whoAmI("def10");
		super.dropRate = 10;
		super. pattern = "QWEREW";
	}
}