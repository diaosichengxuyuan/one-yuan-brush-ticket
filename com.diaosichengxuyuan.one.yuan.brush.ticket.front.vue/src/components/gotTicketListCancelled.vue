<template>
  <div id="mainContentArea" v-model="ticketDetail">
    <div
      class="ticketDetail"
    >{{ticketDetail.startDate}}({{ticketDetail.week}})&nbsp;&nbsp;{{ticketDetail.trains}}&nbsp;&nbsp;{{ticketDetail.startStation}}({{ticketDetail.startTime}}开) → {{ticketDetail.endStation}}({{ticketDetail.endTime}}到)</div>
    <div class="passengers hasCancel">
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
          <tr v-for="(passenger,index) in ticketDetail.passengers">
            <td>{{index+1}}</td>
            <td>{{passenger.name}}</td>
            <td>{{passenger.cardType}}</td>
            <td>{{passenger.cardNumber }}</td>
            <td>{{passenger.ticketType}}</td>
            <td>{{passenger.seats }}</td>
            <td>{{passenger.carriage}}</td>
            <td>{{passenger.seatNumber}}</td>
            <td>{{passenger.price }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "GotTicketListCancelled",
  data() {
    return {
      ticketDetail: {
        id: this.id,
        startDate: "2019-01-19",
        week: "周六",
        trains: "G11",
        startStation: "北京南站",
        endStation: "上海虹桥站",
        startTime: "9:15",
        endTime: "14:49",
        status:
          this.id == 1
            ? "未支付"
            : this.id == 2 || this.id == 3
            ? "已支付"
            : "已取消",
        passengers: [
          {
            name: "刘XX",
            cardType: "中国居民身份证",
            cardNumber: "3723*********025",
            ticketType: "成人票",
            seats: "二等座",
            carriage: "04",
            seatNumber: "11B号",
            price: "553.0元"
          },
          {
            name: "张XX",
            cardType: "中国居民身份证",
            cardNumber: "3723*********025",
            ticketType: "成人票",
            seats: "二等座",
            carriage: "04",
            seatNumber: "11B号",
            price: "553.0元"
          }
        ]
      }
    };
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

.hasCancel {
  background-color: rgb(175, 173, 168);
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
</style>
