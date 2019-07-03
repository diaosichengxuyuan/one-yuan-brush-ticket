<template>
  <div id="mainContentArea">
    <div class="errMsg">{{errMsg}}</div>
    <table class="passengersAreaTable">
      <thead>
        <tr>
          <th class="passengersTh">姓名</th>
          <th class="passengersTh">属性</th>
          <th class="passengersTh">身份证</th>
          <th class="passengersTh">选择</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="passenger in passengerList">
          <td class="passengersTd">{{passenger.name}}</td>
          <td class="passengersTd">{{passenger.attribute}}</td>
          <td class="passengersTd">{{passenger.idCard}}</td>
          <td class="passengersTd">
            <input
              class="ticketResultSelect"
              type="checkbox"
              v-on:click="setVal($event, passenger)"
            />
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "Passengers",
  data() {
    return {
      errMsg: "",
      passengersVal: [],
      passengerList: []
    };
  },
  created() {
    this.$http.get(Utils.getRemoteQueryPassengerPath()).then(
      res => {
        const response = res.body;
        if (!response) {
          this.errMsg = "查询乘客信息失败";
          return;
        }

        const statusCode = response.statusCode;
        if (statusCode == "200") {
          this.passengerList = response.passengerList;
        } else if (response.message) {
          this.errMsg = response.message;
        } else {
          this.errMsg = "查询乘客信息失败";
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
  methods: {
    setVal: function(ele, val) {
      var isChecked = ele.currentTarget.checked;
      if (isChecked) {
        this.passengersVal.push(val);
      } else {
        var index = this.passengersVal.indexOf(val);
        if (index != -1) {
          this.passengersVal.splice(index, 1);
        }
      }

      this.$emit("setPassengersVal", this.passengersVal);
    }
  }
};
</script>

<style scoped>
#mainContentArea {
  position: absolute;
  height: 440px;
  width: 670px;
  top: 178px;
  left: 568px;
  background-color: white;
  overflow-y: auto;
}

.passengersAreaTable {
  border-collapse: collapse;
}

.passengersTh {
  width: 200px;
  text-align: center;
  background-color: #297ebb;
}

.passengersTd {
  width: 200px;
  text-align: center;
  border-bottom: 1px solid rgb(224, 194, 199);
}

.passengersSelect {
  height: 18px;
  width: 18px;
}

.errMsg {
  color: red;
  font-size: 8px;
  font-weight: bold;
  position: absolute;
  top: 100px;
  left: 220px;
}
</style>