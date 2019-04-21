<template>
  <div>
    <div class="contentBody">
      <div id="startPlace" class="searchConditon">
        出发地
        <input id="startPlaceInput" class="searchConditonClass" type="text" v-model="startPlaceVal" v-on:click="showPlaceAndDate(1)">
      </div>
      <div id="endPlace" class="searchConditon">
        目的地
        <input id="endPlaceInput" class="searchConditonClass" type="text" v-model="endPlaceVal" v-on:click="showPlaceAndDate(2)">
      </div>
      <div id="startDate" class="searchConditon">
        出发日期
        <input id="startDateInput" class="searchConditonClass startDateInputClass" type="text">
      </div>
      <div id="studentSelect">
        学生
        <input id="studentSelectInput" type="checkbox">
      </div>
      <div id="highSpeedTrainSelect">
        高铁/动车
        <input id="highSpeedTrainSelectInput" type="checkbox">
      </div>
      <div id="ticketSearchClick">
        <input id="ticketSearchButton" type="button" value="查询">
      </div>
      <!-- <script>
                $("#ticketSearchButton").click(function () {
                    var startPlace = document.getElementById("startPlaceInput");
                    var endPlace = document.getElementById("endPlaceInput");
                    var startDate = document.getElementById("startDateInput");
                    var isStudent = document.getElementById("studentSelectInput");
                    var isHighSpeed = document.getElementById("highSpeedTrainSelectInput");
                    var isValidateSuccess = CommonUtils.validateAndPrompt([startPlace, endPlace, startDate]);
                    if (!isValidateSuccess) {
                        return;
                    }

                    var startPlaceVal = startPlace.value;
                    var endPlaceVal = endPlace.value;
                    var startDateVal = startDate.value;
                    var isStudentVal = isStudent.checked;
                    var isHighSpeedVal = isHighSpeed.checked;
                    location.href = "http://localhost:3000/ticketSearchResult?startPlace=" + startPlaceVal + "&endPlace=" + endPlaceVal + "&startDate=" + startDateVal + "&isStudent=" + isStudentVal + "&isHighSpeed=" + isHighSpeedVal;
                });
      </script>-->
    </div>
    <div>
      <StartPlace v-on:setStartPlaceVal="setStartPlaceVal($event)" v-show="startPlaceShow"></StartPlace>
      <EndPlace v-on:setEndPlaceVal="setEndPlaceVal($event)" v-show="endPlaceShow"></EndPlace>
      <!-- <script>
                $("#startPlace .searchConditonClass").click(function () {
                    $(this).css("border-color", CommonUtils.getDefaultGrayColor());
                    $("#mainContentArea").load("/html/startPlace.htm");
                });
                $("#endPlace .searchConditonClass").click(function () {
                    $(this).css("border-color", CommonUtils.getDefaultGrayColor());
                    $("#mainContentArea").load("/html/endPlace.htm");
                });
                Calendar.create({
                    classN: 'startDateInputClass',
                    callBack: function (bindElem, dateObj) {
                        document.getElementById("startDateInput").value = dateObj.year + '-' + dateObj.month + '-' + dateObj.date;
                    }
                });
      </script>-->
    </div>
  </div>
</template>

<script>
import StartPlace from "@/components/startPlace";
import EndPlace from "@/components/endPlace";

export default {
  name: "TicketSearch",
  data() {
    return {
      startPlaceVal: "",
      endPlaceVal: "",
      startPlaceShow: false,
      endPlaceShow: false
    };
  },
  methods: {
    setStartPlaceVal: function(startPlaceVal) {
      this.startPlaceVal = startPlaceVal;
    },
    setEndPlaceVal: function(endPlaceVal) {
      this.endPlaceVal = endPlaceVal;
    },
    showPlaceAndDate(index) {
      this.startPlaceShow = false;
      this.endPlaceShow = false;
      switch (index) {
        case 1:
          this.startPlaceShow = true;
          break;
        case 2:
          this.endPlaceShow = true;
          break;
        default:
          break;
      }
    }
  },
  components: {
    StartPlace,
    EndPlace
  }
};
</script>

<style scoped>
.contentBody {
  float: left;
  height: 500px;
  width: 500px;
  margin-left: 60px;
  margin-top: 10px;
}

.contentBody .searchConditon {
  font-size: 20px;
  margin-top: 50px;
  margin-left: 50px;
}

.contentBody .searchConditonClass {
  height: 30px;
  width: 300px;
  margin-left: 15px;
  font-size: 20px;
  text-align: center;
  outline: none;
  border: solid 1.4px;
  border-color: rgb(170, 164, 164);
}

#startPlace {
  margin-left: 68px;
  width: 400px;
}

#endPlace {
  margin-left: 68px;
  width: 400px;
}

#startDate {
  width: 420px;
}

#studentSelect {
  float: left;
  font-size: 20px;
  margin-top: 40px;
  margin-left: 160px;
  width: 100px;
}

#studentSelectInput {
  zoom: 220%;
  margin-left: 5px;
  vertical-align: middle;
}

#highSpeedTrainSelect {
  float: left;
  font-size: 20px;
  margin-top: 40px;
  margin-left: 40px;
  width: 150px;
}

#highSpeedTrainSelectInput {
  zoom: 220%;
  margin-left: 5px;
  vertical-align: middle;
}

#ticketSearchClick {
  width: 300px;
  height: 50px;
  margin-top: 150px;
  margin-left: 150px;
}

#ticketSearchButton {
  -webkit-appearance: none;
  height: 35px;
  width: 300px;
  color: black;
  background-color: #ff7300;
  font-size: 20px;
  outline: none;
}
</style>