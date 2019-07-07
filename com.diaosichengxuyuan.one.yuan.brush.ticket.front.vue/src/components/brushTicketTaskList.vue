<template>
  <div class="mainContentArea">
    <div class="taskInstruction" v-show="taskInstructionShow">
      只允许添加
      <br />5个任务!!!
      <br />
      <br />无用的任务
      <br />请删除!!!
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
                <br />
                {{task.startDate}}
              </a>
            </td>
            <td>{{task.status}}</td>
            <td>
              <a
                class="taskListDeleteLink"
                href="javascript:void(0);"
                v-on:click="remoteDeleteById(task.id)"
              >删除</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="errMsg">{{errMsg}}</div>
    <div class="AddTask">
      <input type="button" value="添加" v-on:click="jumpToAddBrushTicketTask" v-show="addButtonShow" />
    </div>
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "BrushTicketTaskList",
  data() {
    return {
      errMsg: "",
      taskList: [],
      taskInstructionShow: false,
      addButtonShow: false
    };
  },
  created() {
    this.remoteQueryAll();
  },
  methods: {
    jumpToAddBrushTicketTask: function() {
      this.$router.push({
        name: "AddBrushTicketTask"
      });
    },
    remoteQueryAll: function() {
      this.$http.get(Utils.getRemoteQueryTaskPath()).then(
        res => {
          const response = res.body;
          if (!response) {
            this.errMsg = "查询失败";
            return;
          }

          const statusCode = response.statusCode;
          if (statusCode == "200") {
            this.taskList = response.taskResDTOList;
            if (this.taskList && this.taskList.length > 0) {
              this.taskInstructionShow = true;
              this.addButtonShow = true;
            }
          } else if (response.message) {
            this.errMsg = response.message;
          } else {
            this.errMsg = "查询失败";
          }
        },
        res => {
          if (res && res.message) {
            this.errMsg = res.message;
          } else {
            this.errMsg = "登录失效，请重新登录！";
          }
        }
      );
    },
    remoteDeleteById: function(id) {
      this.$http
        .post(Utils.getRemoteDeleteTaskPath(), {
          id: id
        })
        .then(
          res => {
            const response = res.body;
            if (!response) {
              this.errMsg = "删除失败";
              return;
            }

            const statusCode = response.statusCode;
            if (statusCode == "200") {
              this.remoteQueryAll();
            } else if (response.message) {
              this.errMsg = response.message;
            } else {
              this.errMsg = "删除失败";
            }
          },
          res => {
            if (res && res.message) {
              this.errMsg = res.message;
            } else {
              this.errMsg = "登录失效，请重新登录！";
            }
          }
        );
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
  left: 590px;
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

.errMsg {
  color: red;
  font-size: 8px;
  font-weight: bold;
  position: absolute;
  top: 300px;
  left: 950px;
}
</style>