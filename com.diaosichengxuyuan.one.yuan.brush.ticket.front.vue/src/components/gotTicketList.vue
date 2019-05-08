<template>
  <div class="mainContentArea">
    <div id="ticketListId" class="ticketList">
      <table>
        <tbody>
          <tr v-for="ticket in ticketList">
            <td>
              <a
                href="javascript:void(0);"
                v-on:click="jumpToTicketDetail(ticket.id,ticket.status)"
              >
                {{ticket.startDate}}({{ticket.week}})&nbsp;&nbsp;{{ticket.trains}}
                <br>
                {{ticket.startStation}}({{ticket.startTime}}开) → {{ticket.endStation}}({{ticket.endTime}}到)
              </a>
            </td>
            <td>{{ticket.status}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="turnPage">
      <input class="turnLeftPage" type="button" value="上一页" v-on:click="reducePage">
      <input class="turnRightPage" type="button" value="下一页" v-on:click="addPage">
    </div>
  </div>
</template>

<script>
export default {
  name: "GotTicketList",
  data() {
    return {
      currentPage: 1,
      ticketList: [
        {
          id: 1,
          startDate: "2019-01-19",
          week: "周六",
          trains: "G11",
          startStation: "北京南站",
          endStation: "上海虹桥站",
          startTime: "9:15",
          endTime: "14:49",
          status: "未支付"
        },
        {
          id: 2,
          startDate: "2019-01-19",
          week: "周六",
          trains: "G21",
          startStation: "北京南站",
          endStation: "上海虹桥站",
          startTime: "9:15",
          endTime: "14:49",
          status: "已支付"
        },
        {
          id: 3,
          startDate: "2019-01-19",
          week: "周六",
          trains: "G31",
          startStation: "北京南站",
          endStation: "上海虹桥站",
          startTime: "9:15",
          endTime: "14:49",
          status: "已支付"
        },
        {
          id: 4,
          startDate: "2019-01-19",
          week: "周六",
          trains: "G41",
          startStation: "北京南站",
          endStation: "上海虹桥站",
          startTime: "9:15",
          endTime: "14:49",
          status: "已取消"
        },
        {
          id: 5,
          startDate: "2019-01-19",
          week: "周六",
          trains: "G51",
          startStation: "北京南站",
          endStation: "上海虹桥站",
          startTime: "9:15",
          endTime: "14:49",
          status: "已取消"
        }
      ]
    };
  },
  methods: {
    getTicketList: function() {
      for (var index in this.ticketList) {
        var ticket = this.ticketList[index];
        ticket.trains = "G" + ticket.id + this.currentPage;
      }
    },
    addPage: function() {
      this.currentPage++;
      this.getTicketList();
    },
    reducePage: function() {
      this.currentPage--;
      this.getTicketList();
    },
    jumpToTicketDetail: function(id, status) {
      var componentName = "GotTicketList";
      if ("未支付" == status) {
        componentName = "GotTicketListNotPay";
      } else if ("已支付" == status) {
        componentName = "GotTicketListHasPay";
      } else if ("已取消" == status) {
        componentName = "GotTicketListCancelled";
      }
      debugger;
      this.$router.push({
        name: componentName,
        query: {
          id: id
        }
      });
    }
  }
};
</script>

<style scoped>
.mainContentArea {
  float: left;
  width: 1200px;
  margin-left: 20px;
  margin-top: 30px;
}

.ticketList {
  float: left;
  width: 800px;
  margin-left: 200px;
}

.ticketList table {
  border-collapse: collapse;
  border: 1px solid black;
}

.ticketList td {
  height: 55px;
  width: 400px;
  text-align: center;
  border-bottom: 1px solid black;
}

.ticketList a {
  color: black;
  text-decoration: none;
}

.turnPage {
  position: absolute;
  left: 500px;
  top: 500px;
  width: 300px;
}

.turnPage input {
  -webkit-appearance: none;
  height: 25px;
  width: 50px;
  color: black;
  font-size: 10px;
  font-weight: bold;
  background-color: #ff7300;
  outline: none;
}

.turnPage .turnRightPage {
  margin-left: 130px;
}
</style>