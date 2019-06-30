<template>
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
        </tr>
      </thead>
      <tbody>
        <tr v-for="ticket in ticketList">
          <td class="ticketResultTd">{{ticket.trains}}</td>
          <td class="ticketResultTd">{{ticket.startTime}}/{{ticket.endTime}}</td>
          <td class="ticketResultTd">{{ticket.startStation}}/{{ticket.endStation}}</td>
          <td class="ticketResultTd">{{ticket.costTime}}</td>
          <td class="ticketResultTd">
            二等座
            <a class="ticketResultReminder">{{ticket.secondSeatPrice}}</a>
            <br>一等座
            <a class="ticketResultReminder">{{ticket.firstSeatPrice}}</a>
            <br>商务座
            <a class="ticketResultReminder">{{ticket.businessSeatPrice}}</a>
            <br>无座
            <a class="ticketResultReminder">{{ticket.noSeatPrice}}</a>
            <br>硬座
            <a class="ticketResultReminder">{{ticket.hardSeatPrice}}</a>
            <br>硬卧
            <a class="ticketResultReminder">{{ticket.hardSleeperSeatPrice}}</a>
            <br>软卧
            <a class="ticketResultReminder">{{ticket.softSleeperSeatPrice}}</a>
            <br>
          </td>
          <td class="ticketResultTd">
            余
            <a class="ticketResultReminder">{{ticket.secondSeatRemain}}</a>张
            <br>余
            <a class="ticketResultReminder">{{ticket.firstSeatRemain}}</a>张
            <br>余
            <a class="ticketResultReminder">{{ticket.businessSeatRemain}}</a>张
            <br>余
            <a class="ticketResultReminder">{{ticket.noSeatRemain}}</a>张
            <br>余
            <a class="ticketResultReminder">{{ticket.hardSeatRemain}}</a>张
            <br>余
            <a class="ticketResultReminder">{{ticket.hardSleeperSeatRemain}}</a>张
            <br>余
            <a class="ticketResultReminder">{{ticket.softSleeperSeatRemain}}</a>张
            <br>
          </td>
        </tr>
      </tbody>
    </table>
    <div class="errMsg">{{errMsg}}</div>
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "TicketSearchResult",
  data() {
    return {
      errMsg: "",
      ticketList: []
    };
  },
  created() {
    const startPlace = this.$route.query.startPlace;
    const endPlace = this.$route.query.endPlace;
    const startDate = this.$route.query.startDate;
    var isStudent = this.$route.query.isStudent;
    var isHighSpeed = this.$route.query.isHighSpeed;
    if (!startPlace || !endPlace || !startPlace) {
      this.errMsg = "查询参数不规范";
      return;
    }

    if (!isStudent) {
      isStudent = false;
    }
    if (!isHighSpeed) {
      isHighSpeed = false;
    }

    this.$http
      .post(Utils.getRemoteQueryTicketPath(), {
        startPlace: startPlace,
        endPlace: endPlace,
        startDate: startDate,
        isStudent: isStudent,
        isHighSpeed: isHighSpeed
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
  }
};
</script>

<style scoped>
.ticketResultAreaClass {
  float: left;
  width: 1200px;
  margin-left: 30px;
  margin-top: 20px;
}

.ticketResultAreaTable {
  border-collapse: collapse;
}

.ticketResultTh {
  width: 200px;
  text-align: center;
  background-color: #297ebb;
}

.ticketResultTd {
  width: 200px;
  text-align: center;
  border-bottom: 1px solid rgb(224, 194, 199);
}

.ticketResultReminder {
  color: red;
}

.errMsg {
  color: red;
  margin-top: 50px;
  margin-left: 500px;
  font-size: 8px;
  font-weight: bold;
}
</style>