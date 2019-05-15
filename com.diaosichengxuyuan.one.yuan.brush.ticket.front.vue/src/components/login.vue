<template>
  <div>
    <div id="mainLoginArea" v-on:click="hidePlaces">
      <div class="instruction">请使用12306账号密码登录</div>
      <div class="accountAndPassword">
        账号&nbsp;&nbsp;
        <input
          id="accountId"
          name="账号"
          type="text"
          v-model="accountId"
          autocomplete="off"
          v-validate="'required'"
        >
        <span class="errorSpan" v-show="errors.has('账号')">{{errors.first("账号")}}</span>
      </div>
      <div class="accountAndPassword">
        密码&nbsp;&nbsp;
        <input
          id="passwordId"
          name="密码"
          type="password"
          v-model="password"
          autocomplete="off"
          v-validate="'required'"
        >
        <span class="errorSpan" v-show="errors.has('密码')">{{errors.first("密码")}}</span>
      </div>
      <div class="protocal">
        <input
          id="protocalBoxId"
          name="用户协议"
          type="checkbox"
          v-model="isAgree"
          v-validate="'required'"
        >同意
        <a
          id="protocalLinkId"
          class="protocalLink"
          href="javascript:void(0);"
          v-on:click.stop="showPlaces"
        >用户协议</a>
      </div>
      <div class="login">
        <input id="loginId" type="button" value="登录" v-on:click="login">
      </div>
    </div>
    <div id="mainContentArea" v-if="agreementShow">
      <BrushTicketAgreement></BrushTicketAgreement>
    </div>
  </div>
</template>

<script>
import BrushTicketAgreement from "@/components/brushTicketAgreement";

export default {
  name: "Login",
  data() {
    return {
      accountId: "",
      password: "",
      isAgree: false,
      agreementShow: false
    };
  },
  methods: {
    login: function() {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.$router.push({
            name: "LoginSuccess"
          });
        }
      });
    },
    showPlaces: function() {
      this.agreementShow = true;
    },
    hidePlaces: function(event) {
      this.agreementShow = false;
    }
  },
  components: {
    BrushTicketAgreement
  }
};
</script>

<style scoped>
#mainContentArea /deep/ #mainContentArea {
  width: 400px;
  left: 838px;
}

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

#mainLoginArea .accountAndPassword {
  width: 500px;
  margin: auto;
  margin-top: 30px;
  text-align: center;
  height: 50px;
}

#mainLoginArea .accountAndPassword input {
  width: 300px;
  height: 30px;
  text-align: center;
  font-size: 20px;
  outline: none;
  border: solid 1.4px;
  border-color: rgb(170, 164, 164);
}

#mainLoginArea .protocal {
  width: 500px;
  margin: auto;
  margin-top: 30px;
  text-align: center;
}

#mainLoginArea .protocal input {
  zoom: 220%;
  vertical-align: middle;
}

#mainLoginArea .protocal a {
  color: red;
  text-decoration: none;
}

#mainLoginArea .login {
  width: 500px;
  margin-top: 30px;
  margin-left: 380px;
  text-align: center;
}

#mainLoginArea .login input {
  font-size: 20px;
  background-color: #ff7300;
  outline: none;
  width: 300px;
  height: 35px;
}

.protocalContent {
  position: absolute;
  width: 400px;
  height: 400px;
  left: 840px;
  top: 190px;
  overflow-y: auto;
  display: none;
}

.errorSpan {
  color: red;
  margin-left: 120px;
  font-size: 8px;
  font-weight: bold;
}
</style>