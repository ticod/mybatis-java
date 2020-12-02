package mybatisjava;

import model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3 {
    private final static String NS = "student2.";
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
        List<Student> list = session.selectList(NS + "select1");
        list.forEach(System.out::println);

        // 1학년 학생 전체 레코드 조회
        System.out.println("1학년 학생 전체");
        Map<String, Object> map = new HashMap<>();
        map.put("grade", 1);
        list = session.selectList(NS + "select1", map);
        list.forEach(System.out::println);
        map.clear();

        // 981213 학번 레코드 조회
        System.out.println("981213 학번 학생");
        map.put("studno", 981213);
        Student s = session.selectOne(NS + "select1", map);
        System.out.println(s);
        map.clear();

        // 키가 180이상인 학생 조회
        System.out.println("키가 180이상인 학생");
        map.put("height", 180);
        list = session.selectList(NS + "select1", map);
        list.forEach(System.out::println);
        map.clear();

        // 1학년 학생 중, 키가 180이상인 학생 조회
        System.out.println("키가 180 이상인 1학년 학생");
        map.put("grade", 1);
        map.put("height", 180);
        list = session.selectList(NS + "select2", map);
        list.forEach(System.out::println);
        map.clear();

        // 1학년 학생 중, 키가 180이상이고, 몸무게가 80 이상인 학생 조회
        System.out.println("키 180 이상, 몸무게 80 이상인 1학년 학생");
        map.put("grade", 1);
        map.put("height", 180);
        map.put("weight", 80);
        list = session.selectList(NS + "select3", map);
        list.forEach(System.out::println);
        map.clear();

        // 101, 201, 301 학과에 속한 학생의 레코드 조회
        System.out.println("101, 201, 301 학과에 속한 학생");
        List<Integer> majorList = Arrays.asList(101, 201, 301);
        map.put("column", "major1");
        map.put("datas", majorList);
        list = session.selectList(NS + "select4", map);
        list.forEach(System.out::println);
        map.clear();

        // 몸무게가 75, 80, 95인 학생 레코드 조회
        System.out.println("몸무게가 75, 80, 95인 학생");
        List<Integer> weightList = Arrays.asList(75, 80, 95);
        map.put("column", "weight");
        map.put("datas", weightList);
        list = session.selectList(NS + "select4", map);
        list.forEach(System.out::println);
        map.clear();
    }
}
