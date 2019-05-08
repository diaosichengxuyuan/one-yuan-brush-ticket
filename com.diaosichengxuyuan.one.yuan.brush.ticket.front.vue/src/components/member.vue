<template>
  <div id="mainLoginArea">
    <div class="status">
      状态&nbsp;&nbsp;
      <input type="text" readonly="readonly" value="会员">
    </div>
    <div class="account">
      账号&nbsp;&nbsp;
      <input type="text" readonly="readonly" value="121xxxxx26@qq.com">
    </div>
    <div class="period">
      有效期至&nbsp;&nbsp;
      <input type="text" readonly="readonly" value="2019-12-31">
    </div>
  </div>
</template>

<script>
export default {
  name: "Member",
  data() {
    return {};
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
</style>