package Monster;

import java.util.Random;

import Item.Item;

public class Monster2 extends Monster {

	public Monster2(){
		Random rand = new Random();
		super. name = "도적";
		super. skillName = "치명타";
		super. lev = 3 + rand.nextInt(5);
		super. skillDmg = 16 + (int)((double)lev*1.5);
		super. hp = 200 + lev*2;
		super. maxHp = 200 + lev*2;
		super. atk = 15 + (int)((double)lev*1.5);
		super. def = lev - 2;
		super. isBoss = false;
		super.exp = 35 + lev*10;
		super.imgSrc = "../reImages/mon2.png";
		super.dropItem = Item.whoAmI("atk2");
		super.dropRate = 10;
	}
}
