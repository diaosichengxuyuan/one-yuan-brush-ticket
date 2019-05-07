<template>
  <div>
    <div class="brushBody">
      <table>
        <tbody>
          <tr>
            <td id="trains_nosale">G101</td>
            <td id="trains_brushing">G102</td>
            <td id="trains_remaining">G103</td>
          </tr>
          <tr>
            <td>
              <img id="jpg_nosale" src="../assets/images/nosale.jpg">
            </td>
            <td>
              <img id="jpg_brushing" src="../assets/images/brushing.jpg">
            </td>
            <td>
              <img id="jpg_remaining" src="../assets/images/remaining.jpg">
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
        >
      </div>
      <div id="endPlace" class="searchConditon">
        目的地
        <input
          id="endPlaceInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="endPlaceVal"
        >
      </div>
      <div id="startDate" class="searchConditon">
        出发日期
        <input
          id="startDateInput"
          class="searchConditonClass startDateInputClass"
          type="text"
          readonly="readonly"
          v-model="startDateVal"
        >
      </div>
      <div id="trains" class="searchConditon">
        车次(可多选)
        <input
          id="trainsInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="trainsVal"
        >
      </div>
      <div id="seats" class="searchConditon">
        坐席(单选)
        <input
          id="seatsInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="seatsVal"
        >
      </div>
      <div id="passengers" class="searchConditon">
        乘车人(可多选)
        <input
          id="passengersInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="passengersVal"
        >
      </div>
      <div id="phone" class="searchConditon">
        联系手机
        <input
          id="phoneInput"
          class="searchConditonClass"
          type="text"
          readonly="readonly"
          v-model="phoneVal"
        >
      </div>
      <div id="stopBrushTicketTask">
        <input id="stopBrushTicketTaskButton" type="button" value="停止" v-on:click="stop">
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "BrushTicketTaskListStarted",
  data() {
    return {
      startPlaceVal: "北京",
      endPlaceVal: "上海",
      startDateVal: "2019-08-08",
      trainsVal: "G202",
      seatsVal: "二等座",
      passengersVal: "周杰伦",
      phoneVal: "13611070029"
    };
  },
  methods: {
    stop: function() {
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
</style>
