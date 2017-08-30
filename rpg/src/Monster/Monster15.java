package Monster;

import java.util.Random;

import Item.Item;

public class Monster15 extends Monster{
	
	public Monster15(){
		Random rand = new Random();
		super. name = "마왕군주술사";
		super. skillName = "파이어 볼";
		super. lev = 21 + rand.nextInt(5);
		super.skillRate = 20;
		
		super. hp = 20000 + lev*2;
		super. maxHp = 20000 + lev*2;
		super. atk = 2500 + (int)((double)lev*2);
		super. skillDmg =atk*3;
		super. def = 2000;
		super. isBoss = false;
		super.exp = 263 + lev*10;
		super.imgSrc = "../reImages/mon15.png";
		super.dropItem = Item.whoAmI("atk6");
		super.dropRate = 10;
		super. pattern = "QWEEER";
	}
}