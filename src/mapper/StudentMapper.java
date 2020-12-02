package mapper;

import java.util.List;
import java.util.Map;

import model.Student;
import org.apache.ibatis.annotations.*;

/*
interface 방식으로 mybatis 사용하기
  네임스페이스 : 인터페이스의 이름, StudentMapper
  id 속성값   : 메서드의 이름
  parameterType : 매개변수
  returnType : 리턴타입
 */

public interface StudentMapper {
    @Select({"<script>",
            "select * from student",
            "<if test='grade != null'>where grade = #{grade}</if>",
            "<if test='studno != null'>where studno = #{studno}</if>",
            "<if test='name != null'>where name = #{name}</if>",
            "</script>"})
    List<Student> select(Map map);

    @Insert("insert into student (studno, name, jumin, id) " +
            "values (#{studno}, #{name}, #{jumin}, #{id})")
    int insert(Student student);

    @Update("update student " +
            "set grade = #{grade}, weight = #{weight}, height = #{height} " +
            "where studno = #{studno}")
    int update(Student student);

    @Delete("delete from student where studno = #{studno}")
    int delete(@Param("studno") int studno);

    @Select("select * from student where major1 = #{major1} " +
            "and grade = #{grade}")
    List<Student> select2(@Param("major1") int i, @Param("grade") int j);
}
