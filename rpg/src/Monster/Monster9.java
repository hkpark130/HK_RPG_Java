package Monster;

import java.util.Random;

import Item.Item;

public class Monster9 extends Monster{
	
	public Monster9(){
		Random rand = new Random();
		super. name = "»÷µå¸Ç";
		super. skillName = "µ¹ÁÖ¸Ô";
		super. lev = 12 + rand.nextInt(5);
		super. skillDmg = 40 + (int)((double)lev*2);
		super. hp = 3000 + lev*2;
		super. maxHp = 3000 + lev*2;
		super. atk = 800 + (int)((double)lev*2);
		super. def = 1000;
		super. isBoss = false;
		super.exp = 120 + lev*10;
		super.imgSrc = "../reImages/mon9.png";
		super.dropItem = Item.whoAmI("def6");
		super.dropRate = 50;
	}
}