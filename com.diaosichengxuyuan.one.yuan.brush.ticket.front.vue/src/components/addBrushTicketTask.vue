<template>
  <div>
    <div class="contentBody" v-on:click="hidePlaces">
      <div id="startPlace" class="searchConditon">
        出发地
        <input
          id="startPlaceInput"
          name="出发地"
          class="searchConditonClass"
          type="text"
          v-model="startPlaceVal"
          v-on:click.stop="showPlaces(1)"
          autocomplete="off"
          v-validate="'required'"
        />
        <span class="errorSpan" v-show="errors.has('出发地')">{{errors.first("出发地")}}</span>
      </div>
      <div id="endPlace" class="searchConditon">
        目的地
        <input
          id="endPlaceInput"
          name="目的地"
          class="searchConditonClass"
          type="text"
          v-model="endPlaceVal"
          v-on:click.stop="showPlaces(2)"
          autocomplete="off"
          v-validate="'required'"
        />
        <span class="errorSpan" v-show="errors.has('目的地')">{{errors.first("目的地")}}</span>
      </div>
      <div id="startDate" class="searchConditon">
        出发日期
        <input
          id="startDateInput"
          name="出发日期"
          class="searchConditonClass"
          type="text"
          v-model="startDateVal"
          autocomplete="off"
          v-validate="'required'"
        />
        <span class="errorSpan" v-show="errors.has('出发日期')">{{errors.first("出发日期")}}</span>
      </div>
      <div id="trains" class="searchConditon">
        车次(可多选)
        <input
          id="trainsInput"
          name="车次"
          class="searchConditonClass"
          type="text"
          v-model="trainNames"
          v-on:click.stop="showPlaces(3)"
          autocomplete="off"
          v-validate="'required'"
        />
        <span class="errorSpan" v-show="errors.has('车次')">{{errors.first("车次")}}</span>
      </div>
      <div id="seats" class="searchConditon">
        坐席(单选)
        <input
          id="seatsInput"
          name="坐席"
          class="searchConditonClass"
          type="text"
          v-model="seatsVal"
          v-on:click.stop="showPlaces(4)"
          autocomplete="off"
          v-validate="'required'"
        />
        <span class="errorSpan" v-show="errors.has('坐席')">{{errors.first("坐席")}}</span>
      </div>
      <div id="passengers" class="searchConditon">
        乘车人(可多选)
        <input
          id="passengersInput"
          name="乘车人"
          class="searchConditonClass"
          type="text"
          v-model="passengerNames"
          v-on:click.stop="showPlaces(5)"
          autocomplete="off"
          v-validate="'required'"
        />
        <span class="errorSpan" v-show="errors.has('乘车人')">{{errors.first("乘车人")}}</span>
      </div>
      <div id="phone" class="searchConditon">
        联系手机
        <input
          id="phoneInput"
          name="联系手机"
          class="searchConditonClass"
          type="text"
          v-model="phoneVal"
          autocomplete="off"
          v-validate="'required'"
        />
        <span class="errorSpan" v-show="errors.has('联系手机')">{{errors.first("联系手机")}}</span>
      </div>
      <div id="submitBrushTicketTask">
        <input id="submitBrushTicketTaskButton" type="button" value="提交" v-on:click="submit" />
      </div>
      <div id="brushTicketAgreement">
        点提交表示同意
        <a
          id="brushTicketAgreementLink"
          href="javascript:void(0);"
          v-on:click.stop="showPlaces(6)"
        >用户协议</a>
      </div>
      <div class="errMsg">{{errMsg}}</div>
    </div>
    <div id="placeArea">
      <StartPlace v-on:setStartPlaceVal="setStartPlaceVal($event)" v-if="startPlaceShow"></StartPlace>
      <EndPlace v-on:setEndPlaceVal="setEndPlaceVal($event)" v-if="endPlaceShow"></EndPlace>
      <Trains
        v-bind:startPlace="startPlaceVal"
        v-bind:endPlace="endPlaceVal"
        v-bind:startDate="startDateVal"
        v-on:setTrainsVal="setTrainsVal($event)"
        v-if="trainsShow"
      ></Trains>
      <SeatsSelect v-on:setSeatsVal="setSeatsVal($event)" v-if="seatsSelectShow"></SeatsSelect>
      <Passengers v-on:setPassengersVal="setPassengersVal($event)" v-if="passengersShow"></Passengers>
      <BrushTicketAgreement v-if="brushTicketAgreementShow"></BrushTicketAgreement>
    </div>
  </div>
</template>

<script>
import StartPlace from "@/components/startPlace";
import EndPlace from "@/components/endPlace";
import SeatsSelect from "@/components/seatsSelect";
import Passengers from "@/components/passengers";
import Trains from "@/components/trains";
import BrushTicketAgreement from "@/components/brushTicketAgreement";
import Utils from "../../static/utils.js";

export default {
  name: "AddBrushTicketTask",
  data() {
    return {
      startPlaceVal: "",
      endPlaceVal: "",
      startDateVal: "",
      trains: [],
      trainNames: "",
      seatsVal: "",
      passengerIds: [],
      passengerNames: [],
      phoneVal: "",
      startPlaceShow: false,
      endPlaceShow: false,
      trainsShow: false,
      seatsSelectShow: false,
      passengersShow: false,
      brushTicketAgreementShow: false,
      errMsg: ""
    };
  },
  mounted: function() {
    laydate.render({
      elem: "#startDateInput",
      type: "date",
      format: "yyyy-MM-dd",
      done: value => {
        this.startDateVal = value;
      }
    });
  },
  methods: {
    setStartPlaceVal: function(startPlaceVal) {
      this.startPlaceVal = startPlaceVal;
    },
    setEndPlaceVal: function(endPlaceVal) {
      this.endPlaceVal = endPlaceVal;
    },
    setSeatsVal: function(seatsVal) {
      this.seatsVal = seatsVal;
    },
    setPassengersVal: function(passengersVal) {
      let passengerIds = [];
      let passengerNames = [];

      for (let index in passengersVal) {
        passengerIds.push(passengersVal[index].id);
        passengerNames.push(passengersVal[index].name);
      }

      this.passengerIds = passengerIds;
      this.passengerNames = passengerNames;
    },
    setTrainsVal: function(trainsVal) {
      let trainNames = [];
      let trains = [];

      for (let index in trainsVal) {
        trainNames.push(trainsVal[index].train);
        trains.push({
          train: trainsVal[index].train,
          startSaleTime: trainsVal[index].startSaleTime,
          endSaleTime: trainsVal[index].endSaleTime
        });
      }

      this.trainNames = trainNames;
      this.trains = trains;
    },
    showPlaces: function(index) {
      this.startPlaceShow = false;
      this.endPlaceShow = false;
      this.trainsShow = false;
      this.seatsSelectShow = false;
      this.passengersShow = false;
      this.brushTicketAgreementShow = false;
      switch (index) {
        case 1:
          this.startPlaceShow = true;
          break;
        case 2:
          this.endPlaceShow = true;
          break;
        case 3:
          this.trainsShow = true;
          break;
        case 4:
          this.seatsSelectShow = true;
          break;
        case 5:
          this.passengersShow = true;
          break;
        case 6:
          this.brushTicketAgreementShow = true;
          break;
        default:
          break;
      }
    },
    hidePlaces: function() {
      this.startPlaceShow = false;
      this.endPlaceShow = false;
      this.trainsShow = false;
      this.seatsSelectShow = false;
      this.passengersShow = false;
      this.brushTicketAgreementShow = false;
    },
    submit: function() {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.remoteSubmit();
        }
      });
    },
    remoteSubmit: function() {
      debugger;
      this.$http
        .post(Utils.getRemoteInsertTaskPath(), this.getInputParams())
        .then(
          res => {
            const response = res.body;
            if (!response) {
              this.errMsg = "提交抢票任务失败";
              return;
            }

            const statusCode = response.statusCode;
            if (statusCode == "200") {
              this.gotoBrushTicketTaskList();
            } else if (response.message) {
              this.errMsg = response.message;
            } else {
              this.errMsg = "提交抢票任务失败";
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
    getInputParams: function() {
      let task = {
        startPlace: this.startPlaceVal,
        endPlace: this.endPlaceVal,
        startDate: this.startDateVal,
        seat: this.seatsVal,
        phone: this.phoneVal
      };

      for (let index in this.trains) {
        task["taskTrainReqDTOList[" + index + "].train"] = this.trains[
          index
        ].train;
        task[
          "taskTrainReqDTOList[" + index + "].startSaleTime"
        ] = Utils.formatDatetime(this.trains[index].startSaleTime);
        task[
          "taskTrainReqDTOList[" + index + "].endSaleTime"
        ] = Utils.formatDatetime(this.trains[index].endSaleTime);
      }

      for (let index in this.passengerIds) {
        task["passengerIdList[" + index + "]"] = this.passengerIds[index];
      }

      return task;
    },
    gotoBrushTicketTaskList: function() {
      this.$router.push({
        name: "BrushTicketTaskList"
      });
    }
  },
  components: {
    StartPlace,
    EndPlace,
    Trains,
    SeatsSelect,
    Passengers,
    BrushTicketAgreement
  }
};
</script>

<style scoped>
.contentBody {
  float: left;
  height: 500px;
  width: 500px;
  margin-left: 0px;
  margin-top: 0px;
}

.contentBody .searchConditon {
  font-size: 15px;
  margin-top: 30px;
  margin-left: 50px;
  height: 26px;
}

.contentBody .searchConditonClass {
  height: 20px;
  width: 295px;
  margin-left: 15px;
  font-size: 15px;
  text-align: center;
  outline: none;
  border: solid 1.4px;
  border-color: rgb(170, 164, 164);
}

#startPlace {
  margin-left: 66px;
  width: 400px;
}

#endPlace {
  margin-left: 66px;
  width: 400px;
}

#startDate {
  margin-left: 51px;
  width: 420px;
}

#trains {
  margin-left: 27px;
  width: 420px;
}

#seats {
  margin-left: 43px;
  width: 420px;
}

#passengers {
  margin-left: 14px;
  width: 420px;
}

#phone {
  margin-left: 55px;
  width: 420px;
}

#submitBrushTicketTask {
  width: 300px;
  height: 35px;
  margin-top: 30px;
  margin-left: 130px;
}

#submitBrushTicketTaskButton {
  -webkit-appearance: none;
  height: 30px;
  width: 305px;
  color: black;
  background-color: #ff7300;
  font-size: 15px;
  outline: none;
}

#brushTicketAgreement {
  font-size: 10px;
  font-weight: bold;
  margin-left: 200px;
}

#brushTicketAgreementLink {
  color: red;
  text-decoration: none;
}

.errorSpan {
  color: red;
  margin-left: 150px;
  font-size: 8px;
  font-weight: bold;
}

.errMsg {
  color: red;
  font-size: 8px;
  font-weight: bold;
  position: absolute;
  top: 585px;
  left: 510px;
}
</style>