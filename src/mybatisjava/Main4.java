package mybatisjava;

import mapper.StudentMapper;
import model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main4 {
    private final static String NS = "mapper.StudentMapper.";
    private static SqlSessionFactory sqlMap;

    static {
        InputStream input = null;

        try {
            input = Resources.getResourceAsStream("mapper/mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlMap = new SqlSessionFactoryBuilder().build(input);
    }

    public static void main(String[] args) {
        SqlSession session = sqlMap.openSession();

        // 학생 전체 레코드 조회
        System.out.println("학생 전체");
        List<Student> list = session.getMapper(StudentMapper.class).select(null);
        list.forEach(System.out::println);

        // 1학년 학생 레코드 조회
        System.out.println("1학년 학생");
        Map<String, Object> map = new HashMap<>();
        map.put("grade", 1);
        list = session.getMapper(StudentMapper.class).select(map);
        list.forEach(System.out::println);
        map.clear();

        // 981213 학번 학생 레코드 조회
        System.out.println("981213 학번 학생");
        map = new HashMap<>();
        map.put("studno", 981213);
        list = session.getMapper(StudentMapper.class).select(map);
        list.forEach(System.out::println);
        map.clear();

        // 김삿갓 학생 추가하기
        System.out.println("김삿갓 학생 추가");
        Student st = new Student();
        st.setStudno(1002);
        st.setName("김삿갓");
        st.setJumin("1234562345678");
        st.setId("kimsk");
        int result = session.getMapper(StudentMapper.class).insert(st);
        System.out.println(result + "건 추가");

        // 김삿갓 학생 조회하기
        System.out.println("김삿갓 학생");
        map = new HashMap<>();
        map.put("name", "김삿갓");
        list = session.getMapper(StudentMapper.class).select(map);
        list.forEach(System.out::println);
        map.clear();

        System.out.println("김삿갓 학생 수정");
        st.setGrade(1);
        st.setWeight(77);
        st.setHeight(177);
        result = session.getMapper(StudentMapper.class).update(st);
        System.out.println(result + "건 수정");

        // 김삿갓 학생 조회하기
        System.out.println("김삿갓 학생");
        map = new HashMap<>();
        map.put("name", "김삿갓");
        list = session.getMapper(StudentMapper.class).select(map);
        list.forEach(System.out::println);
        map.clear();
        
        System.out.println("김삿갓 학생 삭제");
        result = session.getMapper(StudentMapper.class).delete(1002);
        System.out.println(result + "건 삭제");

        // 김삿갓 학생 조회하기
        System.out.println("김삿갓 학생");
        map = new HashMap<>();
        map.put("name", "김삿갓");
        list = session.getMapper(StudentMapper.class).select(map);
        list.forEach(System.out::println);
        map.clear();

        // 101학과 중 1학년 학생 조회
        System.out.println("101 학과 중 1학년 학생");
        list = session.getMapper(StudentMapper.class).select2(101, 1);
        list.forEach(System.out::println);
        map.clear();
    }
}
