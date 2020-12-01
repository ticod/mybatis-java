package mybatisjava;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import model.Member;

public class Main1 {
	public static void main(String[] args) {
		SqlSessionFactory sqlMap = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mapper/mybatis-config.xml");
			sqlMap = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int x = 0;
		SqlSession session = sqlMap.openSession();

		/*
		 * openSession() : 커넥터 생성
		 * selectOne(String) : 레코드 1개 조회 - return T (Object)
		 * selectList(String) : 레코드 모두 조회 - return List<T>
		 */

		x = (Integer) session.selectOne("member.count");
		System.out.println("member 레코드 건수: " + x);

		System.out.println("member 레코드 조회 ==========");
		List<Member> list = session.selectList("member.list");
		for (Member m : list) {
			System.out.println(m);
		}

		System.out.println("member 레코드 중 id = 'admin' 조회");
		Member mem = session.selectOne("member.selectId", "admin");
		System.out.println(mem);

		System.out.println("member 레코드 중 name에 '트' 들어가는 레코드 조회");
		List<Member> list2 = session.selectList("member.selectName", "%트%");
		if (list2.isEmpty()) {
			System.out.println("empty");
		} else {
			for (Member m : list2) {
				System.out.println(m);
			}
		}

		System.out.println("member 레코드 중 name에 'us' 들어가는 레코드 조회");
		List<Member> list3 = session.selectList("member.selectName2", "us");
		for (Member m : list3) {
			System.out.println(m);
		}

		/*
		 * #{value} : 자료형을 판단하여 치환 - 트 -> '트', 1 -> 1 - 보안에 유리
		 * ${value} : 값을 그대로 전달 - 트 -> 트, 1 -> 1
		 */

		System.out.println("member 레코드 중 name에는 us가 들어가고, gender는 2(여성)인 레코드 조회");
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("name",  "%us%");
		parameter.put("gender", 2);
		list = session.selectList("member.selectNameGender", parameter);
		for (Member m : list) {
			System.out.println(m);
		}
	}
}
