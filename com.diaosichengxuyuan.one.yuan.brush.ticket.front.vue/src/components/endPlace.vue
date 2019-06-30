<template>
  <div id="mainContentArea">
    <ul class="azTitleClass">
      <li v-bind:class="{azliClass:azContent1Show}" v-on:click="azTitleClick(1)">热门</li>
      <li v-bind:class="{azliClass:azContent2Show}" v-on:click="azTitleClick(2)">A－E</li>
      <li v-bind:class="{azliClass:azContent3Show}" v-on:click="azTitleClick(3)">F－J</li>
      <li v-bind:class="{azliClass:azContent4Show}" v-on:click="azTitleClick(4)">K－O</li>
      <li v-bind:class="{azliClass:azContent5Show}" v-on:click="azTitleClick(5)">P－T</li>
      <li v-bind:class="{azliClass:azContent6Show}" v-on:click="azTitleClick(6)">U－Z</li>
    </ul>
    <ul class="azContentClass" v-show="azContent1Show">
      <li v-on:click="setVal(1,index)" v-for="(azCity,index) in azCities1">{{azCity}}</li>
    </ul>
    <ul class="azContentClass" v-show="azContent2Show">
      <li v-on:click="setVal(2,index)" v-for="(azCity,index) in azCities2">{{azCity}}</li>
    </ul>
    <ul class="azContentClass" v-show="azContent3Show">
      <li v-on:click="setVal(3,index)" v-for="(azCity,index) in azCities3">{{azCity}}</li>
    </ul>
    <ul class="azContentClass" v-show="azContent4Show">
      <li v-on:click="setVal(4,index)" v-for="(azCity,index) in azCities4">{{azCity}}</li>
    </ul>
    <ul class="azContentClass" v-show="azContent5Show">
      <li v-on:click="setVal(5,index)" v-for="(azCity,index) in azCities5">{{azCity}}</li>
    </ul>
    <ul class="azContentClass" v-show="azContent6Show">
      <li v-on:click="setVal(6,index)" v-for="(azCity,index) in azCities6">{{azCity}}</li>
    </ul>
  </div>
</template>

<script>
import Utils from "../../static/utils.js";

export default {
  name: "EndPlace",
  data() {
    return {
      azCities1: [],
      azCities2: [],
      azCities3: [],
      azCities4: [],
      azCities5: [],
      azCities6: [],
      azContent1Show: true,
      azContent2Show: false,
      azContent3Show: false,
      azContent4Show: false,
      azContent5Show: false,
      azContent6Show: false
    };
  },
  created() {
    this.$http.get(Utils.getRemotePlacePath()).then(
      res => {
        const response = res.body;
        if (!response) {
          return;
        }

        const statusCode = response.statusCode;
        if (statusCode == "200") {
          this.azCities1 = response.hotPlaces;
          this.azCities2 = response.places1;
          this.azCities3 = response.places2;
          this.azCities4 = response.places3;
          this.azCities5 = response.places4;
          this.azCities6 = response.places5;
        }
      },
      res => {}
    );
  },
  methods: {
    azTitleClick: function(index) {
      this.azContent1Show = false;
      this.azContent2Show = false;
      this.azContent3Show = false;
      this.azContent4Show = false;
      this.azContent5Show = false;
      this.azContent6Show = false;
      switch (index) {
        case 1:
          this.azContent1Show = true;
          break;
        case 2:
          this.azContent2Show = true;
          break;
        case 3:
          this.azContent3Show = true;
          break;
        case 4:
          this.azContent4Show = true;
          break;
        case 5:
          this.azContent5Show = true;
          break;
        case 6:
          this.azContent6Show = true;
          break;
        default:
          break;
      }
    },
    setVal: function(rowIndex, colIndex) {
      var endPlaceVal;
      switch (rowIndex) {
        case 1:
          endPlaceVal = this.azCities1[colIndex];
          break;
        case 2:
          endPlaceVal = this.azCities2[colIndex];
          break;
        case 3:
          endPlaceVal = this.azCities3[colIndex];
          break;
        case 4:
          endPlaceVal = this.azCities4[colIndex];
          break;
        case 5:
          endPlaceVal = this.azCities5[colIndex];
          break;
        case 6:
          endPlaceVal = this.azCities6[colIndex];
          break;
        default:
          break;
      }
      this.$emit("setEndPlaceVal", endPlaceVal);
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

#mainContentArea ul {
  padding: 0;
  margin: 0;
  list-style-position: outside;
  list-style: none;
  z-index: 100;
}

#mainContentArea li {
  padding: 2px 0 0 0;
  margin: 0;
  height: 25px;
  line-height: 25px;
  float: left;
  cursor: pointer;
  display: block;
  font-size: 12px;
  overflow: hidden;
  text-align: center;
}

.azTitleClass {
  height: 28px;
  background-color: #297ebb;
}

.azTitleClass li {
  width: 111.6px;
  color: white;
}

.azContentClass {
  height: 413px;
  display: none;
  overflow: scroll;
  display: block;
}

.azContentClass li {
  width: 50px;
}

.azliClass {
  background-color: #684462;
}
</style>