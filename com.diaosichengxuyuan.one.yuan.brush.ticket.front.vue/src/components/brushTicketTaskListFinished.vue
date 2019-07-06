<template>
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
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "BrushTicketTaskListFinished",
  data() {
    return {
      errMsg: "",
      startPlaceVal: "",
      endPlaceVal: "",
      startDateVal: "",
      trainsVal: [],
      seatsVal: "",
      passengersVal: [],
      phoneVal: ""
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
  }
};
</script>

<style scoped>
.contentBody {
  float: left;
  height: 500px;
  width: 1200px;
  margin-left: 60px;
  margin-top: 0px;
}

.contentBody .searchConditon {
  font-size: 15px;
  margin-top: 30px;
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

.errMsg {
  color: red;
  font-size: 8px;
  font-weight: bold;
  position: absolute;
  top: 350px;
  left: 1000px;
}
</style>