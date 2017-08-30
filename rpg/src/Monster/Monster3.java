package Monster;

import java.util.Random;

import Item.Item;

public class Monster3 extends Monster {

	public Monster3(){
		Random rand = new Random();
		super. name = "요정";
		super. skillName = "마술";
		super. lev = 3 + rand.nextInt(5);
		super. skillDmg = 16 + (int)((double)lev*1.5);
		super. hp = 50 + lev*2;
		super. maxHp = 50 + lev*2;
		super. atk = 5 + (int)((double)lev*1.5);
		super. def = 0;
		super. isBoss = false;
		super.exp = 35 + lev*10;
		super.imgSrc = "../reImages/mon3.png";
		super.dropItem = Item.whoAmI("Clothes");
		super.dropRate = 0;
	}
}
