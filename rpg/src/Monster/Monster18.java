package Monster;

import java.util.Random;

import Item.Item;

public class Monster18 extends Monster{
	
	public Monster18(){
		Random rand = new Random();
		super. name = "정예 궁사";
		super. skillName = "강타";
		super. lev = 26 + rand.nextInt(5);
		super. skillDmg = 77 + (int)((double)lev*2);
		super. hp = 70000 + lev*2;
		super. maxHp = 70000 + lev*2;
		super. atk = 5000 + (int)((double)lev*2);
		super. def = 3000;
		super. isBoss = false;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon18.png";
		super.dropItem = Item.whoAmI("def8");
		super.dropRate = 10;
	}
}