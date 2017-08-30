package Item;

public class mpPotion extends Item{
	public mpPotion(){
		super.name = "MP Potion";
		super.codeName = "mpPotion";
		super.grade = 0;
		super.quantity = 1;
		super.type = "potion";
		super.cost = 50;
		super.src = "../reImages/mp.jpg";
		super.effect = 50;
		super.fixCost = 50;
		super.fixEffect = 50;
		Item.itemSetUp(this);
	}
}
