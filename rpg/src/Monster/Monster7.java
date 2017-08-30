package Monster;

import java.util.Random;

import Item.Item;

public class Monster7 extends Monster{
	
	public Monster7(){
		Random rand = new Random();
		super. name = "나무정령";
		super. skillName = "가지치기";
		super. lev = 7 + rand.nextInt(5);
		super. skillDmg = 30 + (int)((double)lev*2);
		super. hp = 250 + lev*2;
		super. maxHp = 250 + lev*2;
		super. atk = 20 + (int)((double)lev*1.5);
		super. def = 0;
		super. isBoss = false;
		super.exp = 85 + lev*10;
		super.imgSrc = "../reImages/mon7.png";
		super.dropItem = Item.whoAmI("def3");
		super.dropRate = 0;
	}
}