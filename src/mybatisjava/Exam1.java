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

import model.Student;

public class Exam1 {
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

		int count = session.selectOne("student.count");
		System.out.println("student 레코드 건수: " + count);

		System.out.println("student 레코드 조회");
		List<Student> list = session.selectList("student.selectList");
		list.stream().forEach(System.out::println);
		System.out.println();

		System.out.println("student 1학년 학생 레코드 조회");
		list = session.selectList("student.selectGrade", 1);
		list.stream().forEach(System.out::println);
		System.out.println();

		System.out.println("student 이름이 두 자인 학생 레코드 조회");
		list = session.selectList("student.selectName", "__");
		list.stream().forEach(System.out::println);
		System.out.println();

		System.out.println("student 3학년 학생 중, 주민번호 기준 여성 학생 레코드 조회");
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("grade", 3);
		parameter.put("gender", 2);
		list = session.selectList("student.selectGradeGender", parameter);
		list.stream().forEach(System.out::println);
		System.out.println();
	}
}
