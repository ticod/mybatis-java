package mapper;

import model.Professor;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface ProfessorMapper {
    @Select({"<script>",
            "select * from professor",
            "<trim prefix=\"where\" prefixOverrides=\"AND || OR\">",
              "<if test=\"deptnoList != null\">",
              "and deptno in ",
              "<foreach collection=\"deptnoList\" item=\"deptno\" separator=\",\" open=\"(\" close=\")\">",
                "#{deptno}",
              "</foreach>",
              "</if>",
              "<if test=\"no != null\">and no = #{no}</if>",
              "<if test=\"position != null\">and position = #{position}</if>",
            "</trim>",
            "</script>"})
    List<Professor> select(Map map);
}
