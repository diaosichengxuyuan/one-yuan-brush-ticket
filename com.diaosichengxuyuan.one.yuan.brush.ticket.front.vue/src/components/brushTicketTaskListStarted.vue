<template>
  <div>
    <div class="brushBody">
      <table>
        <tbody>
          <tr>
            <td id="trains_nosale">{{nosaleData}}</td>
            <td id="trains_brushing">{{brushingData}}</td>
            <td id="trains_remaining">{{remainingData}}</td>
          </tr>
          <tr>
            <td>
              <div class="nosale" v-show="nosaleShow">
                <br />
                <br />未开售
              </div>
            </td>
            <td>
              <div class="brushing" v-show="brushingShow"></div>
            </td>
            <td>
              <div class="remaining" v-show="remainingShow"></div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <div class="contentBody">
      <div id="startPlace" class="searchConditon">
        出发地
        <input
          id="startPlaceInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="startPlaceVal"
        />
      </div>
      <div id="endPlace" class="searchConditon">
        目的地
        <input
          id="endPlaceInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="endPlaceVal"
        />
      </div>
      <div id="startDate" class="searchConditon">
        出发日期
        <input
          id="startDateInput"
          class="searchConditonClass startDateInputClass"
          type="text"
          readonly="readonly"
          v-model="startDateVal"
        />
      </div>
      <div id="trains" class="searchConditon">
        车次(可多选)
        <input
          id="trainsInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="trainsVal"
        />
      </div>
      <div id="seats" class="searchConditon">
        坐席(单选)
        <input
          id="seatsInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="seatsVal"
        />
      </div>
      <div id="passengers" class="searchConditon">
        乘车人(可多选)
        <input
          id="passengersInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="passengersVal"
        />
      </div>
      <div id="phone" class="searchConditon">
        联系手机
        <input
          id="phoneInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="phoneVal"
        />
      </div>
      <div class="errMsg">{{errMsg}}</div>
      <div id="stopBrushTicketTask">
        <input id="stopBrushTicketTaskButton" type="button" value="停止" v-on:click="remoteStop" />
      </div>
    </div>
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "BrushTicketTaskListStarted",
  data() {
    return {
      errMsg: "",
      startPlaceVal: "",
      endPlaceVal: "",
      startDateVal: "",
      trainsVal: [],
      seatsVal: "",
      passengersVal: [],
      phoneVal: "",
      nosaleData: "G101",
      brushingData: "G102",
      remainingData: "G103",
      nosaleShow: true,
      brushingShow: true,
      remainingShow: true
    };
  },
  created() {
    this.$http
      .get(Utils.getRemoteQueryTaskByIdPath() + "?id=" + this.$route.query.id)
      .then(
        res => {
          const response = res.body;
          if (!response) {
            this.errMsg = "查询失败";
            return;
          }

          const statusCode = response.statusCode;
          if (statusCode == "200") {
            this.startPlaceVal = response.startPlace;
            this.endPlaceVal = response.endPlace;
            this.startDateVal = response.startDate;
            this.seatsVal = response.seat;
            this.phoneVal = response.phone;

            if (response.taskTrainResDTOList) {
              for (let index in response.taskTrainResDTOList) {
                this.trainsVal.push(response.taskTrainResDTOList[index].train);
              }
            }

            if (response.passengerDTOList) {
              for (let index in response.passengerDTOList) {
                this.passengersVal.push(response.passengerDTOList[index].name);
              }
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
  methods: {
    remoteStop: function() {
      this.$http
        .post(Utils.getRemoteStopTaskPath(), {
          id: this.$route.query.id
        })
        .then(
          res => {
            const response = res.body;
            if (!response) {
              this.errMsg = "停止任务失败";
              return;
            }

            const statusCode = response.statusCode;
            if (statusCode == "200") {
              this.gotoBrushTicketTaskList();
            } else if (response.message) {
              this.errMsg = response.message;
            } else {
              this.errMsg = "停止任务失败";
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
    gotoBrushTicketTaskList: function() {
      this.$router.push({
        name: "BrushTicketTaskList"
      });
    }
  }
};
</script>

<style scoped>
.brushBody {
  float: left;
  width: 1200px;
  margin-left: 0px;
}

.brushBody table {
  border-collapse: collapse;
}

.brushBody td {
  height: 80px;
  width: 400px;
  text-align: center;
  font-weight: bold;
}

.brushBody img {
  height: 100%;
  width: 25%;
}

.contentBody {
  float: left;
  width: 1200px;
  margin-left: 0px;
  margin-top: 0px;
}

.contentBody .searchConditon {
  font-size: 15px;
  margin-top: 15px;
  margin-left: 50px;
}

.contentBody .searchConditonClass {
  height: 20px;
  width: 300px;
  margin-left: 15px;
  font-size: 15px;
  text-align: center;
  background-color: rgb(170, 164, 164);
}

#startPlace {
  margin-left: 366px;
  width: 400px;
}

#endPlace {
  margin-left: 366px;
  width: 400px;
}

#startDate {
  margin-left: 351px;
  width: 420px;
}

#trains {
  margin-left: 327px;
  width: 420px;
}

#seats {
  margin-left: 343px;
  width: 420px;
}

#passengers {
  margin-left: 314px;
  width: 440px;
}

#phone {
  margin-left: 355px;
  width: 420px;
}

#stopBrushTicketTask {
  position: absolute;
  width: 30px;
  height: 35px;
  left: 850px;
  top: 570px;
}

#stopBrushTicketTaskButton {
  -webkit-appearance: none;
  height: 35px;
  width: 60px;
  color: black;
  background-color: #ff7300;
  font-size: 15px;
  outline: none;
}

.nosale {
  width: 100px;
  height: 100px;
  position: relative;
  left: 170px;
  top: 45px;
  transform: translate(-50%, -50%);
  border-radius: 50%;
  border: 2px solid #ff7300;
  overflow: hidden;
  background-color: rgb(227, 228, 210);
}

.brushing {
  width: 100px;
  height: 100px;
  position: relative;
  left: 170px;
  top: 45px;
  transform: translate(-50%, -50%);
  border-radius: 50%;
  border: 2px solid #ff7300;
  overflow: hidden;
  background-color: rgb(227, 228, 210);
}

.brushing:after {
  content: "\2002\2002\2002抢\2002\2002票\2002\2002\2002中";
  font-size: 15px;
  display: block;
  background-image: linear-gradient(
    44deg,
    rgba(0, 255, 51, 0) 50%,
    #ff7300 100%
  );
  width: 50%;
  height: 50%;
  animation: brushing-beam 5s infinite;
  animation-timing-function: linear;
  transform-origin: bottom right;
}

@keyframes brushing-beam {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

.remaining {
  width: 100px;
  height: 100px;
  position: relative;
  left: 170px;
  top: 45px;
  transform: translate(-50%, -50%);
  border-radius: 50%;
  border: 2px solid #ff7300;
  overflow: hidden;
  background-color: rgb(227, 228, 210);
}

.remaining:after {
  content: "\2002\2002\2002\2002\2002\2002余票\2002监控\2002中";
  font-size: 15px;
  display: block;
  background-image: linear-gradient(
    44deg,
    rgba(0, 255, 51, 0) 50%,
    #ff7300 100%
  );
  width: 50%;
  height: 50%;
  animation: remaining-beam 5s infinite;
  animation-timing-function: linear;
  transform-origin: bottom right;
}

@keyframes remaining-beam {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}

.errMsg {
  color: red;
  font-size: 8px;
  font-weight: bold;
  position: absolute;
  top: 450px;
  left: 1000px;
}
</style>