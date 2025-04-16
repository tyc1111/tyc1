<template>  
  <div>  
    
  <el-container>
    <el-header>SpringBootCodeGenerator(JAVA代码生成平台)，又名`tyc代码生成器`，For reducing the repetitive CRUD work</el-header>
    <el-container>
      <el-aside width="750px">
        
        <el-form ref="form" :model="form" label-width="80px">


        

          <el-form-item label="输入区域">
            <el-input id="text-input" type="textarea" v-model="form.sqlQuery" rows="12" class="el-textarea__inner" ></el-input>
            <!-- <textarea v-model="sqlQuery" rows="10" placeholder="Enter SQL here..."></textarea> -->
            <pre v-html="formatWithLineNumbers(form.sqlQuery)"></pre>
          </el-form-item>

          
          <hr>

          <el-form-item label="数据源">

            <el-select v-model="value" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled">
            </el-option>
            </el-select>
          </el-form-item>
  
  <el-form-item>
    <el-button type="primary" @click="open">立即创建</el-button>
  </el-form-item>
</el-form>

  <div style="margin: 20px 0;"></div>
  <el-checkbox label="result" :checked="true" disabled></el-checkbox>
  <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">四项全选</el-checkbox>

      <el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange">
        <el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>
      
      </el-checkbox-group>
      <hr>
      

      <el-row>
        <el-button type="primary" @click="combinedMethod" >代码生成</el-button>

        <el-dialog
          title="生成结果"
          :visible.sync="dialogVisible"
          width="30%">

          <el-tree  
          v-if="generatedTree.length > 0"  
          :data="generatedTree"  
          :props="defaultProps"  
          node-key="id"
          @node-click="handleNodeClick">
        </el-tree>  

          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="handleUpdateClick">确 定</el-button>
          </span>
        </el-dialog>
      </el-row>

    
    <el-button type="text" @click="downloadFile">下载文件</el-button>
  </el-aside>
      <el-main>
      <el-collapse v-model="activeName" accordion>
        <el-collapse-item title="注意一" name="1">
          <div>代码生成后若是生成的文件中只包含result.java文件，则是表单没有成功构建，请检查你输入的sql语句和数据源选择。</div>
          
        </el-collapse-item>
        <el-collapse-item title="注意二" name="2">
          <div>在构建表单时表名需要与输入的sql语句中的表名相同</div>
          
        </el-collapse-item>
        <el-collapse-item title="注意三" name="3">
          <div>没有点击生成代码按钮就点击下载文件按钮，则只会生成必需的result.java文件，以便对结果的返回。</div>

        </el-collapse-item>
        <el-collapse-item title="注意四" name="4">
          <div>本代码生成器目前只支持mysql数据库，oracle以及SQLserver数据库会在后续开发。</div>
          
        </el-collapse-item>
      </el-collapse>
      
      <hr>

      </el-main>
    </el-container>
  </el-container>

  </div>  
</template>

<script> 
import Vue from 'vue';
import axios from 'axios';  
  
Vue.prototype.$axios = axios;

const cityOptions = ['controller', 'service', 'mapper', 'entity'];

export default { 
  data() {
      return {
        generatedTree: [          
           {            
            label: 'controller',
            children: [{
              label: 'controller.java'
            }]
          },
          {  
            label: 'service',
            children: [{
              label: 'Impl',
              children: [{
                label:'serviceImpl.java'
              }]
            },
            {
              label:'service.java'
            }]
          },
          {
            label: 'mapper',
            children: [{
              label: 'xml',
              children: [{
                label:'mapper.xml'
              }]
            },
            {
              label:'mapper.java'
            }]
          },
          {
            
            label: 'entity',
            children: [
            {
              label:'entity.java'
            }]
          },
        {
          label: 'util',
          children: [{
            label: 'result.java'
            
          }]

        }], // 用于存储生成的树形结构
        defaultProps: {
          children: 'children',
          label: 'label'
        },
        dialogVisible: false,
        activeName: '1',
        checkAll: false,
        checkedCities: ['controller', 'service'],
        cities: cityOptions,
        isIndeterminate: true,
        value: '',
        options: [{
          value: '选项1',
          label: 'Mysql'
          
        }, {
          value: '选项2',
          label: 'Oracle',
          disabled: true
        }, {
          value: '选项3',
          label: 'Sql Server',
          disabled: true
        }],
       
        form: {
          sqlQuery: "CREATE TABLE IF NOT EXISTS Employees (\n" +
                "id INT PRIMARY KEY AUTO_INCREMENT,\n " +
                "first_name VARCHAR(40),\n " +
                "last_name VARCHAR(40));",
          tablename:''
          
        }
      }
    },
  methods: {  
    async downloadFile() {  
      try {  
        // 假设你有一个API接口返回文件的URL或者二进制数据  
        const response = await this.$axios.get('http://localhost:8080/tyc/download/file', {  
          responseType: 'blob' // 告诉axios我们期望返回一个blob对象  
        });  
  
        // 创建一个Blob对象  
        const url = window.URL.createObjectURL(new Blob([response.data]));  
  
        // 创建一个a标签用于下载  
        const link = document.createElement('a');  
        link.href = url;  
        link.setAttribute('download', 'folder.zip'); // 设置下载文件名  
        document.body.appendChild(link);  
        link.click();  
  
        // 清理  
        window.URL.revokeObjectURL(url);  
        document.body.removeChild(link);  
      } catch (error) {  
        // console.error('下载文件失败:', error);
        alert("文件没找到")  
        // 处理错误，例如显示一个错误消息给用户  
      }  
    },

    onSubmit:function(value) {
        console.log('submit!');
        this.form.tablename = value;
        alert(JSON.stringify(this.form));
    },

    handleCheckAllChange(val) {
        this.checkedCities = val ? cityOptions : [];
        this.isIndeterminate = false;
    },

    handleCheckedCitiesChange(value) {
        let checkedCount = value.length;
        this.checkAll = checkedCount === this.cities.length;
        this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
    },

    async submitCities() {  
    try {  
    const apiUrl = 'http://localhost:8080/tyc/define'; // 替换为你的后端API地址  
      
    // 使用URLSearchParams构建查询字符串  
    const params = new URLSearchParams();  
    this.checkedCities.forEach(city => {  
      params.append('cities[]', city); // 注意这里使用了cities[]来传递数组  
    });  
      
    // 将查询字符串添加到URL后面  
    const fullUrl = `${apiUrl}?${params.toString()}`;  
      
    // 发送GET请求  
    const response = await axios.get(fullUrl);  
    console.log('提交成功:', response.data); // 处理响应数据  
    // 在这里可以根据需要更新组件的状态或执行其他操作  
  } catch (error) {  
    console.error('提交失败:', error); // 处理错误  
    // 在这里可以显示错误消息给用户  
  }  
  },
  

  open() {
        this.$prompt('请选择表名', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消'
          
        }).then(({ value }) => {
          if (value && value.trim() !== '') {  
          this.$message({
            type: 'success',
            message: '表名为: ' + value
          });
          const data = {  
            sql: this.form.sqlQuery,  
            tableName: value  
          };  

          const jsonString = JSON.stringify(data);  
          fetch('http://localhost:8080/tyc/createtable', {  
            method: 'POST', // 或者 'PUT'，取决于你的需求  
            headers: {  
              'Content-Type': 'application/json' // 告诉后端你发送的是JSON数据  
            },  
            body: jsonString // 发送JSON字符串  
          })  
          .then(response => response.json()) // 解析响应为JSON（如果需要的话）  
          .then(data => {  
            console.log('Success:', data);  
          })  
          .catch((error) => {  
            console.error('Error:', error);  
          });
          
          this.onSubmit(value);


        } else {  
        // 如果表名为空，显示错误消息  
        this.$message({  
          type: 'error',  
          message: '表名不能为空！'  
        });  
        }  
        }).catch(() => {  
          this.cancelButtonText = false;  
          console.log('用户取消了操作');  
          // 用户点击了取消按钮，可以在这里处理取消逻辑  
        });

          
    },

    formatWithLineNumbers(sqlText) {
      // 将SQL文本转换为带行号的HTML
      return sqlText.split('\n').map((line, index) => {
        return `<span class="line-number">${index + 1}</span>`;
      }).join('\n');
    },

    combinedMethod(){
      
      
       
      const filteredTree = this.filterTree(this.generatedTree, this.checkedCities,'result.java');
       
      // 更新 generatedTree 为过滤后的树  
      this.generatedTree = filteredTree;
       
      this.dialogVisible = true
      this.submitCities();
    },

    filterTree(tree, checkedLabels, fixedLabel) {  
    // 检查一个节点是否应该保留其子树  
    function shouldKeepSubtree(node) {  
    return node.label === fixedLabel || checkedLabels.includes(node.label);  
    }  
  
    // 过滤树形结构  
    function filterNodes(nodes) {  
      return nodes.filter(node => {  
      // 如果节点应该保留其子树，则保留该节点  
      if (shouldKeepSubtree(node)) {  
        return true;  
      }  
  
      // 如果有子节点，则递归过滤子节点  
      if (node.children) {  
        node.children = filterNodes(node.children);  
      }  
  
      // 如果过滤后的子节点数组不为空，则保留该节点（即使它的标签不在checkedLabels中）  
      return node.children && node.children.length > 0;  
    });  
  }  
  
  // 过滤树的根节点  
  return filterNodes(tree);
    
  },  

    handleNodeClick(generatedTree) {
        console.log(generatedTree);
  },
  handleUpdateClick(){
    this.dialogVisible = false;
    this.$forceUpdate();
  } 










    
  }
      
}  

</script>

<style>
.el-row {
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .el-header, .el-footer {
    background-color: #c0c0c0;
    color: #000000;
    text-align: center;
    line-height: 60px;
  }
  
  .el-aside {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    line-height: 200px;
  }
  
  .el-main {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  
  body > .el-container {
    margin-bottom: 40px;
  }
  
  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }
  
  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

pre {
  position: absolute;
  top: 0;

  margin-top: 12px;
  pointer-events: none; /* 确保pre不会干扰textarea */
  color: #888;
}
.line-number {


  user-select: none; /* 禁止用户选择行号 */  
  text-align: right;
  margin-right: 5px;
  line-height: 1; 
  
}
.el-textarea__inner, pre {  
  font-size: 16px !important; /* 与el-textarea__inner保持一致 */  
  line-height: 1.5; /* 根据需要调整行高 */  
}

</style>