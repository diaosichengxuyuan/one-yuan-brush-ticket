<template>
  <div id="mainLoginArea">
    <div class="instruction">已登录</div>
    <div class="account">
      账号&nbsp;&nbsp;
      <input id="accountId" type="text" readonly="readonly" v-model="accountId">
    </div>
    <div class="loginOut">
      <input id="loginOutId" type="button" value="退出登录" v-on:click="loginOut">
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginSuccess",
  data() {
    return {
      accountId: "121xxxxx26@qq.com"
    };
  },
  methods: {
    loginOut: function() {
      this.$router.push({
        name: "Login"
      });
    }
  }
};
</script>

<style scoped>
#mainLoginArea {
  float: left;
  width: 1200px;
  margin-left: -10px;
  margin-top: 30px;
  font-size: 20px;
}

#mainLoginArea .instruction {
  width: 500px;
  margin: auto;
  text-align: center;
  margin-top: 10px;
  font-size: 25px;
}

#mainLoginArea .account {
  width: 500px;
  margin: auto;
  margin-top: 30px;
  text-align: center;
}

#mainLoginArea .account input {
  width: 300px;
  height: 30px;
  text-align: center;
  font-size: 20px;
  background-color: rgb(170, 164, 164);
}

#mainLoginArea .loginOut {
  width: 500px;
  margin-top: 180px;
  margin-left: 380px;
  text-align: center;
}

#mainLoginArea .loginOut input {
  font-size: 20px;
  background-color: #ff7300;
  outline: none;
  width: 300px;
  height: 35px;
}
</style>