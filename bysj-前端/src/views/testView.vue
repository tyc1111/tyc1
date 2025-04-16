<template>
    <div id="app">
      <h1>SQL Input Form</h1>
      <form @submit.prevent="submitSQL">
        <div class="textarea-container">
          <textarea v-model="sqlQuery" rows="10" placeholder="Enter SQL here..."></textarea>
          <pre v-html="formatWithLineNumbers(sqlQuery)"></pre>
        </div>
        <button type="submit">Submit</button>
      </form>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        sqlQuery: '', // 绑定textarea的数据
      };
    },
    methods: {
      submitSQL() {
        // 发送SQL语句到后端
        axios.post('/api/submit-sql', { sql: this.sqlQuery })
          .then(response => {
            // 处理响应
            console.log(response.data);
          })
          .catch(error => {
            // 处理错误
            console.error(error);
          });
      },
      formatWithLineNumbers(sqlText) {
        // 将SQL文本转换为带行号的HTML
        return sqlText.split('\n').map((line, index) => {
          return `<span class="line-number">${index + 1}</span> ${line}`;
        }).join('\n');
      }
    }
  };
  </script>
  
  <style>
  .textarea-container {
    position: relative;
  }
  textarea {
    width: 100%;
    padding-left: 30px; /* 留出空间显示行号 */
    box-sizing: border-box;
  }
  pre {
    position: absolute;
    top: 0;
    left: 0;
    margin: 0;
    pointer-events: none; /* 确保pre不会干扰textarea */
    color: #888;
  }
  .line-number {
    display: inline-block;
    width: 25px;
    text-align: right;
    margin-right: 5px;
  }
  </style>
  