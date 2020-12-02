package mybatisjava;

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

public class Main2 {
    private final static String NS = "student.";
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

        /* Student 테이블에 레코드 추가 */
        Student st = new Student();
        st.setStudno(1001);
        st.setName("홍길동");
        st.setGrade(1);
        st.setId("hongkd2");
        st.setJumin("1234561234567");
        st.setMajor1(101);

        int cnt = session.insert(NS + "insert", st);
        System.out.println("Student 테이블에 레코드 추가 " + cnt);
        Student dispst = session.selectOne(NS + "selectNo", st.getStudno());
        System.out.println(dispst);

        /* 수정 */
        st.setGrade(2);
        st.setWeight(80);
        st.setHeight(175);
        st.setProfno(1001);
        cnt = session.update(NS + "update", st);
        dispst = session.selectOne(NS + "selectNo", st.getStudno());
        System.out.println(dispst);
//        session.commit(); // commit을 하지 않으면 반영되지 않는다!

        /* 삭제 */
        cnt = session.delete(NS + "delete", st.getStudno());
        dispst = session.selectOne(NS + "selectNo", st.getStudno());
//        session.commit();
        System.out.println("=".repeat(50));

        /* 학년이 2학년 이상인 학생의 정보 출력하기 */
        Map<String, Object> map = new HashMap<>();
        map.put("col", "grade");
        map.put("value", 2);
        List<Student> list = session.selectList(NS + "select2", map);
        list.forEach(System.out::println);
        System.out.println("=".repeat(50));

        map.clear();
        map.put("col", "height");
        map.put("value", 180);
        list = session.selectList(NS + "select2", map);
        list.forEach(System.out::println);
    }
}
