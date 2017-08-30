package Skill;

import java.lang.reflect.Constructor;

import User.User;
import rpg.App;
	
public class Skill {
	public static final int skillCount = 4; 
	public String name = "";
	public String codeName = "Skill1";
	public int atk = 0;
	public int cost = 0;
	public int minLev = 1;

	
	public static Skill whoAmI(String name){
		try {	
			Class cls = Class.forName("Skill."+name);
			Constructor constr = cls.getConstructor();	
			Skill retobj = (Skill) constr.newInstance();
			return retobj;
		} catch (Throwable e) {}
		return null;
	}


	public void setUp(String skillName) {
		Skill skill = Skill.whoAmI(skillName);
		this.atk = skill.atk + (int)(  User.player.str*2 ) ; 	
	}

	public static boolean userSkillSet(int lev){			//��ų���� 4�� ���̶� 4���� �ݺ���
		boolean flag = false;
		for(int i = 0; i<Skill.skillCount ;i++){					//���߿� ��ų�� ����� ������ŭ i<2���� �� �÷��� ��. �����!!!
			if(Skill.whoAmI("Skill"+(i+1)).minLev <= lev){
				User.player.skills.put("Skill"+(i+1), Skill.whoAmI("Skill"+(i+1)));
				User.player.skills.get("Skill"+(i+1)).setUp("Skill"+(i+1));
				flag = true;
			}
			
		}
		if(flag)
			return true;
		return false;
	}
	/*
	 * ��ų ��ü ���Ϸ���
	 * ��ų �¿��� �߰��� ��ŭ ������ �÷��� �ϰ�
	 * ��Ʋ ��Ʈ�ѷ��� ��ư Ȱ��ȭ �ؾ���
	 * ���� ��ų �ʱ�ȭ �ε� �κп����� ���� �ʿ�
	 * */
}
