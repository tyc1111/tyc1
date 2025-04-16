package ${package.Controller};

import com.example.springbootcodegenerator.tyc.entity.${entity};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springbootcodegenerator.util.Result;
import java.util.List;
import ${package.Service}.${table.serviceName};
import org.springframework.web.bind.annotation.RequestMapping;

<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

/**
* @author ${author}
*/
@RestController
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")

public class ${table.controllerName} {
        @Autowired
        private ${table.serviceName} ${table.serviceName}1;

        /*
        * 单体删除操作
        * */
        @DeleteMapping("/delete/{id}")
        public Result delete(@PathVariable Integer id){
                ${table.serviceName}1.delete(id);
                return Result.success();

        }

        /*
        * 批量删除操作
        * */
        @DeleteMapping("/deleteAll/{ids}")
        public Result deleteAll(@PathVariable List${r"<Integer>"} ids){
                ${table.serviceName}1.deleteAll(ids);
                return Result.success();
        }

        /*
        * 全部查询操作
        * */
        @GetMapping("/selectALl")
        public Result selectAll(){
                List<${entity}> ${entity}1 = ${table.serviceName}1.selectAll();
                return Result.success(${entity}1);
        }

        /*
        * 单体查询操作
        * */
        @GetMapping("/select/{id}")
        public Result select(@PathVariable Integer id){
                ${entity} ${entity}1 = ${table.serviceName}1.select(id);
                return Result.success(${entity}1);
        }

        /*
        * 插入操作
        * */
        @PostMapping("/insert")
        public Result insert(@RequestBody ${entity} ${entity}1){
                ${table.serviceName}1.insert(${entity}1);
                return Result.success();
        }

        @PutMapping("/update")
        public Result update(@RequestBody ${entity} ${entity}1){
                ${table.serviceName}1.update(${entity}1);
                return Result.success();
        }

}


