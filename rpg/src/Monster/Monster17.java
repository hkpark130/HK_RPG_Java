package Monster;

import java.util.Random;

import Item.Item;

public class Monster17 extends Monster{
	
	public Monster17(){
		Random rand = new Random();
		super. name = "정예병사";
		super. skillName = "강타";
		super. lev = 26 + rand.nextInt(5);
		super. skillDmg = 77 + (int)((double)lev*2);
		super. hp = 100000 + lev*2;
		super. maxHp = 100000 + lev*2;
		super. atk = 3000 + (int)((double)lev*2);
		super. def = 5000;
		super. isBoss = false;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon17.png";
		super.dropItem = Item.whoAmI("atk8");
		super.dropRate = 10;
	}
}