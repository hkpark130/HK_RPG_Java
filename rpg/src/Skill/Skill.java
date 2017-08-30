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

	public static boolean userSkillSet(int lev){			//스킬아직 4개 뿐이라서 4번만 반복함
		boolean flag = false;
		for(int i = 0; i<Skill.skillCount ;i++){					//나중에 스킬더 만들면 갯수만큼 i<2에서 더 늘려야 함. 기억해!!!
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
	 * 스킬 객체 구하려면
	 * 스킬 셋에서 추가한 만큼 범위를 늘려야 하고
	 * 배틀 컨트롤러에 버튼 활성화 해야함
	 * 유저 스킬 초기화 로드 부분에서도 변경 필요
	 * */
}
