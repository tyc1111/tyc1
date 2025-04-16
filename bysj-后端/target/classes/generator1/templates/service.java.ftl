package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import java.util.List;
/**
* @author ${author}
*/

    public interface ${table.serviceName} {

        /*
        * 单个ID删除操作
        * */
        void delete(Integer id);

        /*
        * 批量删除操作
        * */
        void deleteAll(List${r"<Integer>"} ids);

        /*
        * 全部查询操作
        * */
        List<${entity}> selectAll();

        /*
        * 单体查询操作
        * */
        ${entity} select(Integer id);

        /*
        * 插入操作
        * */
        void insert(${entity} ${entity}1);

        /*
        * 更新操作
        * */
        void update(${entity} ${entity}1);
}
