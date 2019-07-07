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
                {{ticket.date}}({{ticket.week}})&nbsp;&nbsp;{{ticket.train}}
                <br />
                {{ticket.startPlace}}({{ticket.startTime}}开) → {{ticket.endPlace}}({{ticket.endTime}}到)
              </a>
            </td>
            <td>{{ticket.status}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="errMsg">{{errMsg}}</div>
    <div class="turnPage">
      <input
        class="turnLeftPage"
        type="button"
        value="上一页"
        v-on:click="reducePage"
        v-show="turnLeftButtonShow"
      />
      <input
        class="turnRightPage"
        type="button"
        value="下一页"
        v-on:click="addPage"
        v-show="turnRightButtonShow"
      />
    </div>
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "GotTicketList",
  data() {
    return {
      errMsg: "",
      currentPage: 1,
      ticketList: [],
      turnLeftButtonShow: false,
      turnRightButtonShow: false
    };
  },
  created() {
    this.remoteGetTicketList();
  },
  methods: {
    remoteGetTicketList: function() {
      this.$http
        .post(Utils.getRemoteQueryAcquiredTicket(), {
          num: this.currentPage,
          size: 5
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
              this.ticketList = response.acquiredTicketResDTOList;
              if (this.ticketList && this.ticketList.length > 0) {
                this.turnLeftButtonShow = true;
                this.turnRightButtonShow = true;
              }
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
    addPage: function() {
      this.currentPage++;
      this.remoteGetTicketList();
    },
    reducePage: function() {
      if (this.currentPage == 1) {
        return;
      }

      this.currentPage--;
      this.remoteGetTicketList();
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

.errMsg {
  color: red;
  font-size: 8px;
  font-weight: bold;
  position: absolute;
  top: 300px;
  left: 1070px;
}
</style>