<template>
  <div id="mainLoginArea">
    <div class="instruction" v-show="mainLoginAreaShow">已登录</div>
    <div class="account" v-show="mainLoginAreaShow">
      账号&nbsp;&nbsp;
      <input id="accountId" type="text" readonly="readonly" v-model="accountId" />
    </div>
    <div class="logout" v-show="mainLoginAreaShow">
      <input id="logoutId" type="button" value="退出登录" v-on:click="remoteLogout" />
    </div>
    <div class="errMsg">{{errMsg}}</div>
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "LoginSuccess",
  data() {
    return {
      accountId: "",
      errMsg: "",
      mainLoginAreaShow: false
    };
  },
  created() {
    this.accountId = this.$route.params.accountId;
    if (this.accountId) {
      this.mainLoginAreaShow = true;
    }
  },
  methods: {
    remoteLogout() {
      this.$http.post(Utils.getRemoteLogoutPath(), {}).then(
        res => {
          const response = res.body;
          if (!response) {
            this.errMsg = "退出失败";
            return;
          }

          const statusCode = response.statusCode;
          if (statusCode == "200") {
            this.gotoLogin();
          } else if (response.message) {
            this.errMsg = response.message;
          } else {
            this.errMsg = "退出失败";
          }
        },
        res => {
          if (res && res.message) {
            this.errMsg = res.message;
          } else {
            this.errMsg = "退出失败";
          }
        }
      );
    },
    gotoLogin: function() {
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

#mainLoginArea .logout {
  width: 500px;
  margin-top: 180px;
  margin-left: 380px;
  text-align: center;
}

#mainLoginArea .logout input {
  font-size: 20px;
  background-color: #ff7300;
  outline: none;
  width: 300px;
  height: 35px;
}

.errMsg {
  color: red;
  font-size: 8px;
  font-weight: bold;
  position: absolute;
  top: 530px;
  left: 700px;
}
</style>