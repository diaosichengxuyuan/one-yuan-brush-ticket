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
        >
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
        >
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
        >
        <span class="errorSpan" v-show="errors.has('出发日期')">{{errors.first("出发日期")}}</span>
      </div>
      <div id="studentSelect">
        学生
        <input id="studentSelectInput" type="checkbox" v-model="isStudent">
      </div>
      <div id="highSpeedTrainSelect">
        高铁/动车
        <input id="highSpeedTrainSelectInput" type="checkbox" v-model="isHighSpeed">
      </div>
      <div id="ticketSearchClick">
        <input id="ticketSearchButton" type="button" value="查询" v-on:click="validate">
      </div>
    </div>
    <div id="placeArea">
      <StartPlace v-on:setStartPlaceVal="setStartPlaceVal($event)" v-if="startPlaceShow"></StartPlace>
      <EndPlace v-on:setEndPlaceVal="setEndPlaceVal($event)" v-if="endPlaceShow"></EndPlace>
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
      startDateVal: "",
      isStudent: false,
      isHighSpeed: false,
      startPlaceShow: false,
      endPlaceShow: false
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
    showPlaces: function(index) {
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
    },
    hidePlaces: function(event) {
      this.startPlaceShow = false;
      this.endPlaceShow = false;
    },
    validate: function() {
      this.$validator.validateAll().then(result => {
        if (result) {
          this.routeToSearchResult();
        }
      });
    },
    routeToSearchResult: function() {
      this.$router.push({
        name: "TicketSearchResult",
        query: {
          startPlace: this.startPlaceVal,
          endPlace: this.endPlaceVal,
          startDate: this.startDateVal,
          isStudent: this.isStudent,
          isHighSpeed: this.isHighSpeed
        }
      });
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
  margin-left: 0px;
  margin-top: 10px;
}

.contentBody .searchConditon {
  font-size: 20px;
  margin-top: 50px;
  margin-left: 50px;
  height: 45px;
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

.errorSpan {
  color: red;
  margin-left: 150px;
  font-size: 8px;
  font-weight: bold;
}
</style>