package Monster;

import java.util.Random;

import Item.Item;

public class Monster21 extends Monster{
	
	public Monster21(){
		Random rand = new Random();
		super. name = "ªÁ≥‰√º";
		super. skillName = "∞≠≈∏";
		super. lev = 26 + rand.nextInt(5);
		super.skillRate = 15;
		super. hp = 500000 + lev*2;
		super. maxHp = 500000 + lev*2;
		super. atk = 10000 + (int)((double)lev*2);
		super. skillDmg = atk*3;
		
		super. def = 5000;
		super. isBoss = false;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon21.png";
		super.dropItem = Item.whoAmI("atk6");
		super.dropRate = 10;
		super. pattern = "QQWEQ";
	}
}