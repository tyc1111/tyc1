package ${package.Mapper};

import ${package.Entity}.${entity};
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
* @author ${author}
*/
@Mapper
public interface ${table.mapperName} {

    /*
    * 单个ID删除操作
    * */
    @Delete("delete from ${table.name} where id = ${r"#{id}"}")
    public int delete(Integer id);

    /*
    * 批量删除操作
    * */
    void deleteAll(List${r"<Integer>"} ids);

    /*
    * 全部查询操作
    * */
    @Select("select * from emp")
    List<${entity}> selectAll();

    /*
    * 单体查询操作
    * */
    @Select("select * from emp where id = ${r"#{id}"}")
    ${entity} select(Integer id);

    /*
    * 插入操作
    * */
    @Insert("insert into ${table.name}(<#list table.fields as field>${field.name}<#sep>, </#sep></#list>) " +
    " values (<#list table.fields as field>${r"#{"}${field.propertyName}${r"}"}<#sep>, </#sep></#list>);")
    void insert(${entity} ${entity}1);

    /*
    * 更新操作
    * */
    void update(${entity} ${entity}1);




}

