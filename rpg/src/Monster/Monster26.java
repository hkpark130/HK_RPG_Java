package Monster;

import java.util.Random;

import Item.Item;

public class Monster26 extends Monster{
	
	public Monster26(){
		Random rand = new Random();
		super. name = "¸¶¿Õ";
		super. skillName = "°­Å¸";
		super. lev = 26 + rand.nextInt(5);
		
		super. hp = 9999999 + lev*2;
		super. maxHp = 9999999 + lev*2;
		super. atk = 26500 + (int)((double)lev*2);
		super. skillDmg = atk*20;
		super. def = 10000;
		super. isBoss = true;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon26.png";
		super.dropItem = Item.whoAmI("atk6");
		super.dropRate = 0;
		super. pattern = "QWERREWQ";
	}
}