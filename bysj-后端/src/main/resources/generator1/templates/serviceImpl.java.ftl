package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
* @author ${author}
*/
@Service
public class ${table.serviceImplName} implements ${table.serviceName} {
    @Autowired
    private ${table.mapperName} ${table.mapperName}1;

    /*
    * 单个ID删除操作
    * */
    @Override
    public void delete(Integer id){
        ${table.mapperName}1.delete(id);
    }

    /*
    * 批量删除操作
    * */
    @Override
    public void deleteAll(List${r"<Integer>"} ids) {
        ${table.mapperName}1.deleteAll(ids);
    }

    /*
    * 全部查询操作
    * */
    @Override
    public List<${entity}> selectAll() {
        return ${table.mapperName}1.selectAll();
    }

    /*
    * 单体查询操作
    * */
    @Override
    public ${entity} select(Integer id) {
        return ${table.mapperName}1.select(id);
    }

    /*
    * 插入操作
    * */
    @Override
    public void insert(${entity} ${entity}1) {
        ${table.mapperName}1.insert(${entity}1);
    }

    /*
    * 更新操作
    * */
    @Override
    public void update(${entity} ${entity}1) {
        ${table.mapperName}1.update(${entity}1);
    }

}

