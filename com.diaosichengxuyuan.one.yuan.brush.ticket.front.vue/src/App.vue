<template>
  <div>
    <div class="fixedUpperBody">
      <img class="zhaocaiClass" src="./assets/images/zhaocai.jpg">
      <div id="currentDailyNum" class="currentDataClass">当前活跃{{currentDailyNum}}人</div>
      <div id="currentMembers" class="currentDataClass">当前会员{{currentMembers}}人</div>
      <div id="brushTicketOrderNum" class="currentDataClass">抢票订单{{brushTicketOrderNum}}个</div>
      <div id="whetherLoggedIn" class="whetherOwnClass">{{whetherLoggedIn}}</div>
      <div id="whetherMember" class="whetherOwnClass">{{whetherMember}}</div>
      <img class="jinbaoClass" src="./assets/images/jinbao.jpg">
    </div>
    <div class="fixedMiddleBody">
      <a href="http://localhost:3000/ticketSearch">车票查询</a>
      <a class="mainMenuClass" href="http://localhost:3000/addBrushTicketTask">添加抢票任务</a>
      <a class="mainMenuClass" href="http://localhost:3000/brushTicketTaskList">抢票任务列表</a>
      <a class="mainMenuClass" href="http://localhost:3000/gotTicketList">已抢到票列表</a>
      <a id="loginMenu" href="http://localhost:3000/login">登录</a>
      <a class="loginMenuClass" href="http://localhost:3000/member">会员</a>
      <a class="loginMenuClass">个人中心</a>
    </div>
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "App",
  data(){
    return {
      currentDailyNum:0,
      currentMembers:0,
      brushTicketOrderNum:0,
      whetherLoggedIn:"未登录",
      whetherMember:"非会员"
    }
  },
  methods:{
    calculateSiteData:function(){
      this.currentDailyNum = Math.ceil(Math.random() * 1000);
      this.currentMembers = Math.ceil(Math.random() * 1000);
      this.brushTicketOrderNum = Math.ceil(Math.random() * 1000);
    }
  },
  created(){
    setInterval(this.calculateSiteData,1000);
  }
};
</script>

<style>
body {
  overflow-x: hidden;
  background-color: rgb(237, 240, 224);
}

div {
  width: 1200px;
  margin-bottom: 5px;
  margin-left: 30px;
}

/* 上部展示栏 */
.fixedUpperBody {
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
  margin-left: 55px;
}

#currentMembers {
  margin-top: 60px;
  margin-left: 10px;
}

#brushTicketOrderNum {
  margin-top: 20px;
  margin-left: 10px;
}

.fixedUpperBody .whetherOwnClass {
  float: left;
  width: 50px;
  margin-top: 10px;
  font-family: "Times New Roman", Times, serif;
}

#whetherLoggedIn {
  margin-left: 120px;
  background-color: rgb(248, 82, 82);
}

#whetherMember {
  margin-left: 0 px;
  background-color: rgb(248, 82, 82);
}

/* 中部导航栏 */
.fixedMiddleBody {
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
