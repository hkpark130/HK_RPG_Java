package Monster;

import java.util.Random;

import Item.Item;

public class Monster10 extends Monster{
	
	public Monster10(){
		Random rand = new Random();
		super. name = "¾àÅ»ÀÚ";
		super. skillName = "µ¹ÁÖ¸Ô";
		super. lev = 10 + rand.nextInt(5);
		super. skillDmg = 40 + (int)((double)lev*2);
		super. hp = 5000 + lev*2;
		super. maxHp = 5000 + lev*2;
		super. atk = 15 + (int)((double)lev*2);
		super. def = lev;
		super. isBoss = false;
		super.exp = 100 + lev*10;
		super.imgSrc = "../reImages/mon10.png";
		super.dropItem = Item.whoAmI("def6");
		super.dropRate = 50;
	}
}