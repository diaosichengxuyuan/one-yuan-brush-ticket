<template>
  <div id="mainContentArea" v-model="ticketDetail">
    <div
      class="ticketDetail"
      v-show="mainContentAreaShow"
    >{{ticketDetail.date}}({{ticketDetail.week}})&nbsp;&nbsp;{{ticketDetail.train}}&nbsp;&nbsp;{{ticketDetail.startPlace}}({{ticketDetail.startTime}}开) → {{ticketDetail.endPlace}}({{ticketDetail.endTime}}到)</div>
    <div class="passengers hasPay" v-show="mainContentAreaShow">
      <table>
        <thead>
          <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>证件类型</th>
            <th>证件号码</th>
            <th>票种</th>
            <th>席别</th>
            <th>车厢</th>
            <th>席位号</th>
            <th>票价(元)</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(passenger,index) in ticketDetail.acquiredTicketDetailResDTOList">
            <td>{{index+1}}</td>
            <td>{{passenger.name}}</td>
            <td>{{passenger.cardType}}</td>
            <td>{{passenger.cardNumber }}</td>
            <td>{{passenger.attribute}}</td>
            <td>{{passenger.seat}}</td>
            <td>{{passenger.carriage}}</td>
            <td>{{passenger.seatNumber}}</td>
            <td>{{passenger.price}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="errMsg">{{errMsg}}</div>
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "GotTicketListHasPay",
  data() {
    return {
      errMsg: "",
      ticketDetail: {},
      mainContentAreaShow: false
    };
  },
  created() {
    this.$http
      .get(
        Utils.getRemoteQueryAcquiredTicketByIdPath() +
          "?id=" +
          this.$route.query.id
      )
      .then(
        res => {
          const response = res.body;
          if (!response) {
            this.errMsg = "查询失败";
            return;
          }

          const statusCode = response.statusCode;
          if (statusCode == "200") {
            this.ticketDetail = response;
            this.mainContentAreaShow = true;
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
.ticketDetail {
  float: left;
  width: 1200px;
  margin-left: 0px;
  margin-top: 30px;
  font-size: 30px;
  text-align: center;
}

.passengers {
  float: left;
  width: 1200px;
  margin-left: 0px;
  margin-top: 30px;
}

.hasPay {
  background-color: rgb(83, 187, 83);
}

.passengers table {
  border-collapse: collapse;
  border: 1px solid black;
}

.passengers th,
td {
  height: 40px;
  width: 400px;
  text-align: center;
  border-bottom: 1px solid black;
}

.payDetail {
  float: left;
  width: 1200px;
  margin-left: 0px;
  margin-top: 30px;
  font-size: 30px;
  text-align: center;
  color: red;
}

.errMsg {
  color: red;
  font-size: 8px;
  font-weight: bold;
  position: absolute;
  top: 200px;
  left: 50px;
}
</style>