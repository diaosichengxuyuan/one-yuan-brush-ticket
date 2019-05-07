<template>
  <div id="mainContentArea">
    <form id="seatsSelectForm">
      <div class="seatsSelectClass">
        <input type="radio" id="secondSeat" name="seats" v-on:click="setVal('二等座')">
        <label for="secondSeat">二等座</label>
      </div>
      <div class="seatsSelectClass">
        <input type="radio" id="firstSeat" name="seats" v-on:click="setVal('一等座')">
        <label for="firstSeat">一等座</label>
      </div>
      <div class="seatsSelectClass">
        <input type="radio" id="businessSeat" name="seats" v-on:click="setVal('商务座')">
        <label for="businessSeat">商务座</label>
      </div>
      <div class="seatsSelectClass">
        <input type="radio" id="noSeat" name="seats" v-on:click="setVal('无座')">
        <label for="noSeat">无座</label>
      </div>
      <div class="seatsSelectClass">
        <input type="radio" id="hardSeat" name="seats" v-on:click="setVal('硬座')">
        <label for="hardSeat">硬座</label>
      </div>
      <div class="seatsSelectClass">
        <input type="radio" id="hardSleeperSeat" name="seats" v-on:click="setVal('硬卧')">
        <label for="hardSleeperSeat">硬卧</label>
      </div>
      <div class="seatsSelectClass">
        <input type="radio" id="softSleeperSeat" name="seats" v-on:click="setVal('软卧')">
        <label for="softSleeperSeat">软卧</label>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  name: "SeatsSelect",
  data() {
    return {};
  },
  methods: {
    setVal: function(seatsVal) {
      this.$emit("setSeatsVal", seatsVal);
    }
  }
};
</script>

<style scoped>
#mainContentArea {
  position: absolute;
  height: 440px;
  width: 670px;
  top: 178px;
  left: 568px;
  background-color: white;
}

.seatsSelectClass input {
  display: none;
  opacity: 0;
}


.seatsSelectClass label {
  line-height: 1;
  position: relative;
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-align: center;
  -webkit-align-items: center;
  -ms-flex-align: center;
  align-items: center;
  margin-left: 250px;
  margin-top: 30px;
  width: 100px;
}

.seatsSelectClass label:before {
  line-height: 20px;
  display: inline-block;
  width: 18px;
  height: 18px;
  margin-right: 8px;
  content: "";
  color: #fff;
  border: 1px solid #dce4e6;
  background-color: #f3f6f8;
  border-radius: 3px;
}

.seatsSelectClass input:checked + label:before {
  content: "\2713";
  color: #fff;
  background-color: #297ebb;
  border-radius: 3px;
  font-size: 16px;
  text-align: center;
  border-color: #297ebb;
}
</style>
