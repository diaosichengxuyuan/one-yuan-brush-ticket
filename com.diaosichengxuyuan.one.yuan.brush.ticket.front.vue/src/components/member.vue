<template>
  <div id="mainLoginArea">
    <div class="status">
      状态&nbsp;&nbsp;
      <input type="text" readonly="readonly" v-model="status">
    </div>
    <div class="account">
      账号&nbsp;&nbsp;
      <input type="text" readonly="readonly" v-model="account">
    </div>
    <div class="period">
      有效期至&nbsp;&nbsp;
      <input type="text" readonly="readonly" v-model="period">
    </div>
    <div class="errMsg">{{loginErrMsg}}</div>
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "Member",
  data() {
    return {
      status: "",
      account: "",
      period: "",
      loginErrMsg: ""
    };
  },
  created() {
    this.$http.get(Utils.getRemoteQueryMemberPath()).then(
      res => {
        const response = res.body;
        if (!response) {
          this.loginErrMsg = "查询失败";
          return;
        }

        const statusCode = response.statusCode;
        if (statusCode == "200") {
          this.status = response.isMember ? "会员" : "非会员";
          this.account = response.accountId;
          this.period = response.periodValidity;
        } else if (response.message) {
          this.loginErrMsg = response.message;
        } else {
          this.loginErrMsg = "查询失败";
        }
      },
      res => {
        if (res && res.message) {
          this.loginErrMsg = res.message;
        } else {
          this.loginErrMsg = "登录失效，请重新登录！";
        }
      }
    );
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

#mainLoginArea .status {
  width: 500px;
  margin: auto;
  margin-top: 30px;
  text-align: center;
}

#mainLoginArea .status input {
  width: 300px;
  height: 30px;
  text-align: center;
  font-size: 20px;
  background-color: rgb(170, 164, 164);
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

#mainLoginArea .period {
  width: 500px;
  margin-top: 30px;
  text-align: center;
  margin-left: 330px;
}

#mainLoginArea .period input {
  width: 300px;
  height: 30px;
  text-align: center;
  font-size: 20px;
  background-color: rgb(170, 164, 164);
}

.errMsg {
  color: red;
  margin-left: 650px;
  font-size: 8px;
  font-weight: bold;
}
</style>