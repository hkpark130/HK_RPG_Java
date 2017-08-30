package Monster;

import java.util.Random;

import Item.Item;

public class Monster6 extends Monster{
	
	public Monster6(){
		Random rand = new Random();
		super. name = "Á»ºñ";
		super. skillName = "µ¶";
		super. lev = 7 + rand.nextInt(5);
		super. skillDmg = 23 + (int)((double)lev*2);
		super. hp = 400 + lev*2;
		super. maxHp = 400 + lev*2;
		super. atk = 35 + (int)((double)lev*2);
		super. def = 5;
		super. isBoss = false;
		super.exp = 70 + lev*10;
		super.imgSrc = "../reImages/mon6.png";
		super.dropItem = (rand.nextInt(2)==1)?Item.whoAmI("atk4"):Item.whoAmI("def4");
		super.dropRate = 10;
	}
}