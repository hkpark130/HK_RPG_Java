package Monster;

import java.util.Random;

import Item.Item;

public class Monster1 extends Monster{
	
	public Monster1(){
		Random rand = new Random();
		super. name = "슬라임";
		super. skillName = "강타";
		super. lev = 1 + rand.nextInt(5);
		super. skillDmg = 13 + (int)((double)lev*1.5);
		super. hp = 200 + lev*2;
		super. maxHp = 200 + lev*2;
		super. atk = 10 + (int)((double)lev*1.5);
		super. def = lev - 1;
		super. isBoss = false;
		super.exp = 10 + lev*10;
		super.imgSrc = "../reImages/mon1.png";
		
	}
}
