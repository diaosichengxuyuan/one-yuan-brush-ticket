<template>
  <div id="mainContentArea">
    <div class="errMsg">{{errMsg}}</div>
    <div class="ticketResultAreaClass">
      <table class="ticketResultAreaTable">
        <thead>
          <tr>
            <th class="ticketResultTh">车次</th>
            <th class="ticketResultTh">出发时间/到达时间</th>
            <th class="ticketResultTh">出发站/到达站</th>
            <th class="ticketResultTh">耗时</th>
            <th class="ticketResultTh">参考票价</th>
            <th class="ticketResultTh">余票</th>
            <th class="ticketResultTh">选择</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="ticket in ticketList">
            <td class="ticketResultTd">{{ticket.train}}</td>
            <td class="ticketResultTd">{{ticket.startTime}}/{{ticket.endTime}}</td>
            <td class="ticketResultTd">{{ticket.startStation}}/{{ticket.endStation}}</td>
            <td class="ticketResultTd">{{ticket.costTime}}</td>
            <td class="ticketResultTdSpecial">
              二等座
              <a class="ticketResultReminder">{{ticket.secondSeatPrice}}</a>
              <br />一等座
              <a class="ticketResultReminder">{{ticket.firstSeatPrice}}</a>
              <br />商务座
              <a class="ticketResultReminder">{{ticket.businessSeatPrice}}</a>
              <br />无座
              <a class="ticketResultReminder">{{ticket.noSeatPrice}}</a>
              <br />硬座
              <a class="ticketResultReminder">{{ticket.hardSeatPrice}}</a>
              <br />硬卧
              <a class="ticketResultReminder">{{ticket.hardSleeperSeatPrice}}</a>
              <br />软卧
              <a class="ticketResultReminder">{{ticket.softSleeperSeatPrice}}</a>
              <br />
            </td>
            <td class="ticketResultTd">
              余
              <a class="ticketResultReminder">{{ticket.secondeSeatRemain}}</a>张
              <br />余
              <a class="ticketResultReminder">{{ticket.firstSeatRemain}}</a>张
              <br />余
              <a class="ticketResultReminder">{{ticket.businessSeatRemain}}</a>张
              <br />余
              <a class="ticketResultReminder">{{ticket.noSeatRemain}}</a>张
              <br />余
              <a class="ticketResultReminder">{{ticket.hardSeatRemain}}</a>张
              <br />余
              <a class="ticketResultReminder">{{ticket.hardSleeperSeatRemain}}</a>张
              <br />余
              <a class="ticketResultReminder">{{ticket.softSleeperSeatRemain}}</a>张
              <br />
            </td>
            <td class="ticketResultTd">
              <input
                class="ticketResultSelect"
                type="checkbox"
                v-on:click="setVal($event, ticket)"
              />
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "Trains",
  props: ["startPlace", "endPlace", "startDate"],
  data() {
    return {
      errMsg: "",
      ticketsVal: [],
      ticketList: []
    };
  },
  created() {
    if (!this.startPlace) {
      this.errMsg = "请选择出发地";
      return;
    }

    if (!this.endPlace) {
      this.errMsg = "请选择目的地";
      return;
    }

    if (!this.startDate) {
      this.errMsg = "请选择出发日期";
      return;
    }

    if (isNaN(Date.parse(this.startDate))) {
      this.errMsg = "出发日期格式不正确";
      return;
    }

    this.$http
      .post(Utils.getRemoteQueryTicketPath(), {
        startPlace: this.startPlace,
        endPlace: this.endPlace,
        startDate: this.startDate,
        isStudent: false,
        isHighSpeed: false
      })
      .then(
        res => {
          const response = res.body;
          if (!response) {
            this.errMsg = "查询失败";
            return;
          }

          const statusCode = response.statusCode;
          if (statusCode == "200") {
            this.ticketList = response.ticketList;
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
  methods: {
    setVal: function(ele, val) {
      var isChecked = ele.currentTarget.checked;
      if (isChecked) {
        this.ticketsVal.push(val);
      } else {
        var index = this.ticketsVal.indexOf(val);
        if (index != -1) {
          this.ticketsVal.splice(index, 1);
        }
      }

      this.$emit("setTrainsVal", this.ticketsVal);
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

.ticketResultAreaClass {
  float: left;
  margin-left: 0px;
  margin-top: 0px;
}

.ticketResultAreaTable {
  border-collapse: collapse;
}

.ticketResultTh {
  text-align: center;
  background-color: #297ebb;
  width: 100px;
}

.ticketResultTd {
  text-align: center;
  border-bottom: 1px solid rgb(224, 194, 199);
  width: 100px;
}

.ticketResultTdSpecial {
  text-align: center;
  border-bottom: 1px solid rgb(224, 194, 199);
  width: 140px;
}

.ticketResultSelect {
  height: 18px;
  width: 18px;
}

.ticketResultReminder {
  color: red;
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