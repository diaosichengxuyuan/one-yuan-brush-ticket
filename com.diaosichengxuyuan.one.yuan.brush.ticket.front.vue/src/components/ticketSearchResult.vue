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
            <a class="ticketResultReminder">{{ticket.secondeSeatRemain}}</a>张
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
  </div>
</template>

<script>
export default {
  name: "TicketSearchResult",
  data() {
    return {
      ticketList: [
        {
          trains: "G102",
          startTime: "06:43",
          endTime: "12:40",
          startStation: "北京南",
          endStation: "上海虹桥",
          costTime: "5小时57分",
          secondSeatPrice: "￥553",
          secondeSeatRemain: "21",
          firstSeatPrice: "￥772",
          firstSeatRemain: "18",
          businessSeatPrice: "￥1500",
          businessSeatRemain: "0",
          noSeatPrice: "",
          noSeatRemain: "",
          hardSeatPrice: "",
          hardSeatRemain: "",
          hardSleeperSeatPrice: "",
          hardSleeperSeatRemain: "",
          softSleeperSeatPrice: "",
          softSleeperSeatRemain: ""
        },
        {
          trains: "G103",
          startTime: "06:43",
          endTime: "12:40",
          startStation: "北京南",
          endStation: "上海虹桥",
          costTime: "5小时57分",
          secondSeatPrice: "￥553",
          secondeSeatRemain: "21",
          firstSeatPrice: "￥772",
          firstSeatRemain: "18",
          businessSeatPrice: "￥1500",
          businessSeatRemain: "0",
          noSeatPrice: "",
          noSeatRemain: "",
          hardSeatPrice: "",
          hardSeatRemain: "",
          hardSleeperSeatPrice: "",
          hardSleeperSeatRemain: "",
          softSleeperSeatPrice: "",
          softSleeperSeatRemain: ""
        },
        {
          trains: "G104",
          startTime: "06:43",
          endTime: "12:40",
          startStation: "北京南",
          endStation: "上海虹桥",
          costTime: "5小时57分",
          secondSeatPrice: "￥553",
          secondeSeatRemain: "21",
          firstSeatPrice: "￥772",
          firstSeatRemain: "18",
          businessSeatPrice: "￥1500",
          businessSeatRemain: "0",
          noSeatPrice: "",
          noSeatRemain: "",
          hardSeatPrice: "",
          hardSeatRemain: "",
          hardSleeperSeatPrice: "",
          hardSleeperSeatRemain: "",
          softSleeperSeatPrice: "",
          softSleeperSeatRemain: ""
        },
        {
          trains: "G105",
          startTime: "06:43",
          endTime: "12:40",
          startStation: "北京南",
          endStation: "上海虹桥",
          costTime: "5小时57分",
          secondSeatPrice: "￥553",
          secondeSeatRemain: "21",
          firstSeatPrice: "￥772",
          firstSeatRemain: "18",
          businessSeatPrice: "￥1500",
          businessSeatRemain: "0",
          noSeatPrice: "",
          noSeatRemain: "",
          hardSeatPrice: "",
          hardSeatRemain: "",
          hardSleeperSeatPrice: "",
          hardSleeperSeatRemain: "",
          softSleeperSeatPrice: "",
          softSleeperSeatRemain: ""
        },
        {
          trains: "G106",
          startTime: "06:43",
          endTime: "12:40",
          startStation: "北京南",
          endStation: "上海虹桥",
          costTime: "5小时57分",
          secondSeatPrice: "￥553",
          secondeSeatRemain: "21",
          firstSeatPrice: "￥772",
          firstSeatRemain: "18",
          businessSeatPrice: "￥1500",
          businessSeatRemain: "0",
          noSeatPrice: "",
          noSeatRemain: "",
          hardSeatPrice: "",
          hardSeatRemain: "",
          hardSleeperSeatPrice: "",
          hardSleeperSeatRemain: "",
          softSleeperSeatPrice: "",
          softSleeperSeatRemain: ""
        },
        {
          trains: "G107",
          startTime: "06:43",
          endTime: "12:40",
          startStation: "北京南",
          endStation: "上海虹桥",
          costTime: "5小时57分",
          secondSeatPrice: "￥553",
          secondeSeatRemain: "21",
          firstSeatPrice: "￥772",
          firstSeatRemain: "18",
          businessSeatPrice: "￥1500",
          businessSeatRemain: "0",
          noSeatPrice: "",
          noSeatRemain: "",
          hardSeatPrice: "",
          hardSeatRemain: "",
          hardSleeperSeatPrice: "",
          hardSleeperSeatRemain: "",
          softSleeperSeatPrice: "",
          softSleeperSeatRemain: ""
        }
      ]
    };
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
</style>