<template>
  <div id="mainContentArea">
    <table class="passengersAreaTable">
      <thead>
        <tr>
          <th class="passengersTh">姓名</th>
          <th class="passengersTh">属性</th>
          <th class="passengersTh">身份证</th>
          <th class="passengersTh">选择</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="passenger in passengerList">
          <td class="passengersTd">{{passenger.name}}</td>
          <td class="passengersTd">{{passenger.attribute}}</td>
          <td class="passengersTd">{{passenger.idCard}}</td>
          <td class="passengersTd">
            <input
              class="ticketResultSelect"
              type="checkbox"
              v-on:click="setVal($event, passenger.name)"
            >
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: "Passengers",
  data() {
    return {
      passengersVal: [],
      passengerList: [
        {
          name: "迈克尔杰克逊",
          attribute: "成人",
          idCard: "3723*********025"
        },
        {
          name: "杜甫",
          attribute: "成人",
          idCard: "3723*********025"
        },
        {
          name: "爱新觉罗弘历",
          attribute: "成人",
          idCard: "3723*********025"
        },
        {
          name: "C罗",
          attribute: "成人",
          idCard: "3723*********025"
        },
        {
          name: "爱因斯坦",
          attribute: "成人",
          idCard: "3723*********025"
        },
        {
          name: "张伯伦",
          attribute: "成人",
          idCard: "3723*********025"
        },
        {
          name: "乔布斯",
          attribute: "成人",
          idCard: "3723*********025"
        }
      ]
    };
  },
  methods: {
    setVal: function(ele, val) {
      var isChecked = ele.currentTarget.checked;
      if (isChecked) {
        this.passengersVal.push(val);
      } else {
        var index = this.passengersVal.indexOf(val);
        if (index != -1) {
          this.passengersVal.splice(index, 1);
        }
      }

      this.$emit("setPassengersVal", this.passengersVal);
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
  overflow-y: auto;
}

.passengersAreaTable {
  border-collapse: collapse;
}

.passengersTh {
  width: 200px;
  text-align: center;
  background-color: #297ebb;
}

.passengersTd {
  width: 200px;
  text-align: center;
  border-bottom: 1px solid rgb(224, 194, 199);
}

.passengersSelect {
  height: 18px;
  width: 18px;
}
</style>