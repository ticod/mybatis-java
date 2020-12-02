package test.t201202;

import mapper.ProfessorMapper;
import model.Professor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Test1 {
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

//    public static void main(String[] args) {
//        SqlSession session = sqlMap.openSession();
//        List<Professor> list = null;
//        Map<String, Object> parameter = new HashMap<>();
//        List<Integer> deptnoList = null;
//
//        System.out.println("\n 1. 모든 교수 정보 조회하기");
//        list = session.getMapper(ProfessorMapper.class).select(null);
//        list.forEach(System.out::println);
//
//        System.out.println("\n 2. 101 학과 교수 정보 조회하기");
//        deptnoList = Arrays.asList(101);
//        parameter.put("deptnoList", deptnoList);
//        list = session.getMapper(ProfessorMapper.class).select(parameter);
//        list.forEach(System.out::println);
//        parameter.clear();
//
//        System.out.println("\n 3. 교수 번호가 1001번인 교수 정보 조회하기");
//        parameter.put("no", 1001);
//        list = session.getMapper(ProfessorMapper.class).select(parameter);
//        list.forEach(System.out::println);
//        parameter.clear();
//
//        System.out.println("\n 4. 101 학과의 정교수 정보 조회하기");
//        deptnoList = Arrays.asList(101);
//        parameter.put("deptnoList", deptnoList);
//        parameter.put("position", "정교수");
//        list = session.getMapper(ProfessorMapper.class).select(parameter);
//        list.forEach(System.out::println);
//        parameter.clear();
//
//        System.out.println("\n 5. 정교수 정보 조회하기");
//        parameter.put("position", "정교수");
//        list = session.getMapper(ProfessorMapper.class).select(parameter);
//        list.forEach(System.out::println);
//        parameter.clear();
//
//        System.out.println("\n 6. 101,201 학과 교수 정보 조회하기");
//        deptnoList = Arrays.asList(101, 201);
//        parameter.put("deptnoList", deptnoList);
//        list = session.getMapper(ProfessorMapper.class).select(parameter);
//        list.forEach(System.out::println);
//        parameter.clear();
//
//        System.out.println("\n 7. 101,201 학과 조 교수 정보 조회하기");
//        deptnoList = Arrays.asList(101, 201);
//        parameter.put("deptnoList", deptnoList);
//        parameter.put("position", "조교수");
//        list = session.getMapper(ProfessorMapper.class).select(parameter);
//        list.forEach(System.out::println);
//        parameter.clear();
//    }

    public static void main(String[] args) {
        System.out.println("\n 1. 모든 교수 정보 조회하기");
        select().forEach(System.out::println);

        System.out.println("\n 2. 101 학과 교수 정보 조회하기");
        select(Arrays.asList(101)).forEach(System.out::println);

        System.out.println("\n 3. 교수 번호가 1001번인 교수 정보 조회하기");
        select(1001).forEach(System.out::println);

        System.out.println("\n 4. 101 학과의 정교수 정보 조회하기");
        select(Arrays.asList(101), "정교수").forEach(System.out::println);

        System.out.println("\n 5. 정교수 정보 조회하기");
        select("정교수").forEach(System.out::println);

        System.out.println("\n 6. 101,201 학과 교수 정보 조회하기");
        select(Arrays.asList(101, 201)).forEach(System.out::println);

        System.out.println("\n 7. 101,201 학과 조 교수 정보 조회하기");
        select(Arrays.asList(101, 201), "조교수").forEach(System.out::println);
    }

    private static List<Professor> select() {
        return select(null, null, null);
    }

    private static List<Professor> select(Integer no) {
        return select(no, null, null);
    }

    private static List<Professor> select(List<Integer> deptnoList) {
        return select(null, deptnoList, null);
    }

    private static List<Professor> select(String position) {
        return select(null, null, position);
    }

    private static List<Professor> select(Integer no, String position) {
        return select(no, null, position);
    }

    private static List<Professor> select(Integer no, List<Integer> deptnoList) {
        return select(no, deptnoList, null);
    }

    private static List<Professor> select(List<Integer> deptnoList, String position) {
        return select(null, deptnoList, position);
    }

    private static List<Professor> select(Integer no, List<Integer> deptnoList, String position) {
        SqlSession session = sqlMap.openSession();
        Map<String, Object> parameter = new HashMap<>();
        if (no != null) parameter.put("no", no);
        if (deptnoList != null) parameter.put("deptnoList", deptnoList);
        if (position != null) parameter.put("position", position);
        parameter.put("deptno", deptnoList);
        parameter.put("position", position);
        return session.getMapper(ProfessorMapper.class).select(parameter);
    }
}
