package test.t201201;

import model.Professor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test1 {
	public static void main(String[] args) {
		SqlSessionFactory sqlMap = null;
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("mapper/mybatis-config.xml");
			sqlMap = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSession session = sqlMap.openSession();

		int count = session.selectOne("professor.count");
		System.out.println("professor 테이블 레코드 건수: " + count);

		System.out.println("professor 테이블 모든 레코드 조회");
		List<Professor> list = session.selectList("professor.selectAll");
		list.forEach(System.out::println);
		System.out.println();

		System.out.println("professor 테이블에서 101 학과 레코드 조회");
		list = session.selectList("professor.selectDeptno", 101);
		list.stream().forEach(System.out::println);
		System.out.println();

		System.out.println("professor 테이블에서 성이 김씨인 시간강사 레코드 조회");
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("name", "김%");
		parameter.put("position", "시간강사");
		list = session.selectList("professor.selectNamePosition", parameter);
		list.stream().forEach(System.out::println);
		System.out.println();
	}
}
