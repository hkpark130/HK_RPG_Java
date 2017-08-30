package Monster;

import java.util.Random;

import Item.Item;

public class Monster5 extends Monster {

	public Monster5(){
		Random rand = new Random();
		super. name = "°õ";
		super. skillName = "¹ÚÄ¡±â";
		super. lev = 6 + rand.nextInt(5);
		super. skillDmg = 16 + (int)((double)lev*1.5);
		super. hp = 500 + lev*2;
		super. maxHp = 500 + lev*2;
		super. atk = 40 + (int)((double)lev*1.5);
		super. def = 40 - 2;
		super. isBoss = false;
		super.exp = 35 + lev*10;
		super.imgSrc = "../reImages/mon5.png";
		super.dropItem = (rand.nextInt(2)==1)?Item.whoAmI("atk4"):Item.whoAmI("def4");
		super.dropRate = 20;
	}
}