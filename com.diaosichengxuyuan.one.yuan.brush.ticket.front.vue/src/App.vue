<template>
  <div>
    <div class="fixedUpperBody">
      <img class="zhaocaiClass" src="./assets/images/zhaocai.jpg" />
      <div id="currentDailyNum" class="currentDataClass">当前登录{{currentDailyNum}}人</div>
      <div id="currentMembers" class="currentDataClass">当前会员{{currentMembers}}人</div>
      <div id="brushTicketOrderNum" class="currentDataClass">抢票订单{{brushTicketOrderNum}}个</div>
      <img class="jinbaoClass" src="./assets/images/jinbao.jpg" />
    </div>
    <div class="fixedMiddleBody">
      <router-link to="/ticketSearch">车票查询</router-link>
      <router-link class="mainMenuClass" to="/addBrushTicketTask">添加抢票任务</router-link>
      <router-link class="mainMenuClass" to="/brushTicketTaskList">抢票任务列表</router-link>
      <router-link class="mainMenuClass" to="/gotTicketList">已抢到票列表</router-link>
      <router-link id="loginMenu" to="/login">登录</router-link>
      <router-link class="loginMenuClass" to="/member">会员</router-link>
      <router-link class="loginMenuClass" to="/">个人中心</router-link>
    </div>
    <router-view />
  </div>
</template>

<script>
import Utils from "../static/utils.js";

export default {
  name: "App",
  data() {
    return {
      currentDailyNum: 0,
      currentMembers: 0,
      brushTicketOrderNum: 0
    };
  },
  created() {
    this.remoteQueryData();
    setInterval(this.remoteQueryData, 5000);
  },
  methods: {
    remoteQueryData() {
      this.$http.get(Utils.getRemoteQueryDataPath()).then(
        res => {
          const response = res.body;
          if (!response) {
            return;
          }
        
          const statusCode = response.statusCode;
          if (statusCode == "200") {
            this.currentDailyNum = response.taskNum;
            this.currentMembers = response.userNum;
            this.brushTicketOrderNum = response.memberNum;
          }
        },
        res => {}
      );
    }
  }
};
</script>

<style>
body {
  overflow-x: hidden;
  background-color: rgb(237, 240, 224);
}

div {
  margin-bottom: 5px;
  margin-left: 30px;
}

/* 上部展示栏 */
.fixedUpperBody {
  width: 1200px;
  background-color: rgb(158, 241, 247);
  float: left;
}

.fixedUpperBody .zhaocaiClass {
  height: 100px;
  width: 120px;
  float: left;
}

.fixedUpperBody .jinbaoClass {
  height: 100px;
  width: 120px;
  float: right;
}

.fixedUpperBody .currentDataClass {
  float: left;
  width: 200px;
  font-size: 23px;
  font-weight: bold;
  font-family: "Courier New", Courier, monospace;
}

#currentDailyNum {
  margin-top: 10px;
  margin-left: 130px;
}

#currentMembers {
  margin-top: 45px;
  margin-left: 50px;
}

#brushTicketOrderNum {
  margin-top: 20px;
  margin-left: 50px;
}

.fixedUpperBody .whetherOwnClass {
  float: left;
  width: 50px;
  margin-top: 10px;
  font-family: "Times New Roman", Times, serif;
}

/* 中部导航栏 */
.fixedMiddleBody {
  width: 1200px;
  height: 30px;
  background-color: rgb(30, 146, 255);
  float: left;
  font-family: "Times New Roman", Times, serif;
  margin-top: 5px;
}

.fixedMiddleBody a {
  text-decoration: none;
  font-size: 20px;
  color: black;
}

.fixedMiddleBody .mainMenuClass {
  margin-left: 50px;
}

#loginMenu {
  margin-left: 310px;
}

.fixedMiddleBody .loginMenuClass {
  margin-left: 50px;
}
</style>
