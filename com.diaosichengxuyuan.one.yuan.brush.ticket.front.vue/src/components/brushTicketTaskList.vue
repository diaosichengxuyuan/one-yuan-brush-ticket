<template>
  <div class="mainContentArea">
    <div class="taskInstruction">
      只允许添加
      <br>5个任务!!!
      <br>
      <br>无用的任务
      <br>请删除!!!
    </div>
    <div id="taskListId" class="taskList">
      <table>
        <tbody>
          <tr v-for="task in taskList">
            <td>
              <a
                href="javascript:void(0);"
                v-on:click="jumpToBrushTicketDetail(task.id,task.status)"
              >
                {{task.startPlace}}→{{task.endPlace}}
                <br>
                {{task.startDate}}
              </a>
            </td>
            <td>{{task.status}}</td>
            <td>
              <a
                class="taskListDeleteLink"
                href="javascript:void(0);"
                v-on:click="deleteById(task.id)"
              >删除</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="AddTask">
      <input type="button" value="添加" v-on:click="jumpToAddBrushTicketTask">
    </div>
  </div>
</template>

<script>
export default {
  name: "BrushTicketTaskList",
  data() {
    return {
      taskList: [
        {
          id: 1,
          startPlace: "北京",
          endPlace: "上海",
          startDate: "2019年5月26日",
          status: "已停止"
        },
        {
          id: 2,
          startPlace: "杭州",
          endPlace: "武汉",
          startDate: "2018年7月26日",
          status: "已启动"
        },
        {
          id: 3,
          startPlace: "长沙",
          endPlace: "广州",
          startDate: "2019年3月20日",
          status: "已结束"
        },
        {
          id: 4,
          startPlace: "天津",
          endPlace: "南京",
          startDate: "2018年2月21日",
          status: "已结束"
        }
      ]
    };
  },
  methods: {
    jumpToAddBrushTicketTask: function() {
      this.$router.push({
        name: "AddBrushTicketTask"
      });
    },
    deleteById: function(id) {
      for (var index in this.taskList) {
        if (this.taskList[index].id == id) {
          this.taskList.splice(index, 1);
          return;
        }
      }
    },
    jumpToBrushTicketDetail: function(id, status) {
      var componentName = "BrushTicketTaskList";
      if ("已停止" == status) {
        componentName = "BrushTicketTaskListStopped";
      } else if ("已启动" == status) {
        componentName = "BrushTicketTaskListStarted";
      } else if ("已结束" == status) {
        componentName = "BrushTicketTaskListFinished";
      }
      debugger
      this.$router.push({
        name: componentName,
        query: {
          id: id
        }
      });
    }
  }
};
</script>

<style scoped>
.mainContentArea {
  float: left;
  width: 1200px;
  margin-left: 60px;
  margin-top: 30px;
}

.taskInstruction {
  float: left;
  height: 300px;
  width: 190px;
  font-size: 30px;
  color: red;
}

.taskList {
  float: left;
  width: 800px;
}

.taskList table {
  border-collapse: collapse;
  border: 1px solid black;
}

.taskList td {
  height: 55px;
  width: 200px;
  text-align: center;
  border-bottom: 1px solid black;
}

.taskList a {
  color: black;
  text-decoration: none;
}

.taskList .taskListDeleteLink {
  color: red;
}

.AddTask {
  position: absolute;
  left: 560px;
  top: 550px;
}

.AddTask input {
  -webkit-appearance: none;
  height: 35px;
  width: 80px;
  color: black;
  background-color: #ff7300;
  font-size: 18px;
  outline: none;
}
</style>
