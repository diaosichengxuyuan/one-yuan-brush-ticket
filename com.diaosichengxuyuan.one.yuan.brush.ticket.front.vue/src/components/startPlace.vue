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
export default {
  name: "StartPlace",
  data() {
    return {
      azCities1:["北京", "上海", "天津", "重庆", "长沙", "长春", "成都", "福州", "广州", "贵阳", "呼和浩特", "哈尔滨", "合肥", "杭州", "海口", "济南", "昆明", "拉萨", "兰州", "南宁", "南京", "南昌", "沈阳", "石家庄", "太原", "乌鲁木齐", "武汉", "西宁", "西安", "银川", "郑州", "深圳", "厦门"],
      azCities2:["阿尔山", "安康", "阿克苏", "阿里河", "阿拉山口", "安平", "安庆", "安顺", "鞍山", "安阳", "昂昂溪", "阿城", "北京北", "北京东", "北京", "北京南", "北京西", "北安", "蚌埠", "白城", "北海", "白河", "白涧", "宝鸡", "重庆北", "重庆", "重庆南", "重庆西", "长春", "长春南", "长春西", "成都东", "成都南", "成都", "长沙", "长沙南", "大明湖", "大安北", "大成", "丹东", "东方红", "东莞", "东大虎山", "敦化", "敦煌", "德惠", "东京城", "二道沟门", "二道湾", "鄂尔多斯", "二龙", "二龙山屯", "峨眉", "二密河", "恩平", "安达", "安德", "安定", "安多", "安广", "敖汉", "艾河", "安化", "艾家村", "鳌江", "安家", "阿金", "滨江", "博克图", "百色", "白山市", "北台", "包头东", "包头北", "屯市", "本溪", "白云", "鄂博", "白银", "西亳州", "赤壁", "常德", "承德", "长甸", "赤峰", "茶陵", "苍南", "昌平", "崇仁", "昌图", "长汀镇", "曹县", "都江堰", "大连北", "大理", "大连", "定南", "大庆", "东胜", "大石桥", "大同", "东营", "大杨树", "都匀", "安靖", "阿克陶", "阿龙山", "安陆", "阿木尔", "阿南庄", "安庆西", "鞍山西", "安塘", "博鳌北", "碚白", "壁关", "蚌埠南", "巴楚", "板城", "北戴河", "保定", "宝坻", "八达岭", "巴东", "柏果", "楚雄南", "陈相屯", "长治北", "池州", "长征", "常州", "郴州", "长治", "沧州", "崇左", "赤壁北", "查布嘎", "邓州", "达州", "德州", "德安", "大安", "大坝", "大板", "大巴电", "白到", "保达", "坂城", "定边", "安亭北", "阿图什", "安图", "安溪", "阿巴嘎旗", "阿城北", "阿尔山北", "安江东", "阿勒泰", "安仁", "安顺西", "安图西", "布海", "白河东", "贲红", "宝华山", "白河县", "白芨沟", "碧鸡关北", "滘碧江", "白鸡坡", "笔架山", "八角台", "长城", "长冲", "承德东", "赤峰西", "嵯岗", "柴岗", "长葛", "柴沟堡", "城固", "陈官营", "成高子", "草海东", "边井", "德伯斯", "打柴沟", "德昌", "滴道", "大磴沟", "刀尔", "登得耳布尔", "杜尔伯特", "东方", "丹凤", "东丰", "安阳东", "保康", "白奎堡", "白狼", "百浪", "博乐", "宝拉格", "巴林", "宝林", "北流", "勃利", "布列开", "宝龙山", "柴河", "册亨", "草河口", "崔黄口", "巢湖", "蔡家沟", "成吉思汗", "岔江", "蔡家坡", "昌乐", "超梁沟", "慈利", "都格", "大官屯", "大关", "东光", "东海", "大灰厂", "大红旗", "大禾塘", "德惠西", "东海县", "达家沟", "东津", "百里峡", "八面城", "班猫箐", "八面通", "北马圈子", "北票南", "白旗", "宝泉岭", "白泉", "巴山", "白水江", "", "白沙坡", "昌黎", "长岭子", "晨明", "长农", "昌平北", "常平", "长坡岭", "辰清", "蔡山", "楚山", "长寿", "磁山", "杜家", "大口屯", "东来", "德令哈", "大陆号", "带岭", "大林", "达拉特旗", "独立屯", "豆罗", "达拉", "特西", "大连西", "白石山", "白水镇", "包头", "东坂田", "泊头", "北屯", "本溪湖", "博兴", "八仙筒", "白音", "察干", "背荫河", "北营", "苍石", "草市", "察素齐", "长山屯", "长汀", "朝天南", "昌图西", "春湾", "磁县", "岑溪", "辰溪", "磁西", "东明村", "洞庙河", "东明县", "大拟", "大平房", "大盘石", "大埔", "大堡", "大庆东", "大其拉哈", "道清", "对青山", "巴彦高勒", "白音他拉", "鲅鱼圈", "白银市", "白音胡硕", "巴中", "霸州", "北宅", "博白", "八步", "栟茶", "保定东", "长兴南", "磁窑", "春阳", "城阳", "创业村", "朝阳川", "朝阳地", "朝阳南", "长垣", "朝阳镇", "滁州北", "常州北", "德清西", "大庆西", "东升", "砀山", "独山登", "沙河", "读书铺", "大石头", "东胜西", "大石寨", "东台", "定陶", "八方山", "白沟", "滨海", "滨海北", "滨海港", "滨海西", "宝鸡南", "北井子", "白马井", "北票", "宝清", "璧山", "滁州", "潮州", "常庄", "曹子里", "车转湾", "郴州西", "沧州西", "澄城", "承德县北", "承德南", "成都西", "曹妃", "甸东", "灯塔", "大田边东", "通化", "丹徒", "大屯", "东湾", "大武口", "低窝铺", "大王滩", "大湾子", "大兴沟", "大兴", "白沙铺", "白水县", "板塘", "白文东", "宾西北", "本溪新城", "宾阳", "白洋淀", "百宜", "白音华南", "巴中东", "彬州", "曹妃甸港城固北", "查干湖", "巢湖东", "从江", "蔡家崖", "茶卡", "长临河", "茶陵南", "常平东", "常平南", "长庆桥", "定西", "甸心", "东乡", "代县", "定襄", "东戌", "东辛庄", "丹阳", "德阳", "大雁", "当阳", "丹阳北", "滨州", "宾州", "霸州西", "长寿北", "长寿湖", "常山", "潮汕", "长沙西", "朝天", "长汀南", "长武", "长兴", "苍溪", "楚雄", "朝阳", "大英东", "东淤地", "大营", "定远", "岱岳", "大元", "大营镇", "大营子", "大战场", "德州东", "东至", "低庄", "长阳", "潮阳", "朝阳湖", "崇州", "城子坦", "东镇", "道州", "东庄", "兑镇", "豆庄", "定州", "大竹园", "大杖子", "豆张庄", "东安", "东德保", "都昌", "东岔", "东城南", "东戴河", "丹东西", "东二道河", "大丰", "大方南", "东港北", "大孤山", "东莞", "鼎湖东", "鼎湖山", "道滘", "垫江", "洞井", "董家口", "大苴", "洞口", "达连河", "大荔", "大朗镇", "得莫利", "大青沟", "德清", "东胜", "东砀山南", "大石头南", "当涂东", "大通西", "大旺", "定西北", "德兴", "东德兴", "丹霞山", "大冶北", "都匀东", "大邑", "东营南", "大余", "定州东", "端州", "大足南"],
      azCities3:["待补充"],
      azCities4:["待补充"],
      azCities5:["待补充"],
      azCities6:["待补充"],
      azContent1Show: true,
      azContent2Show: false,
      azContent3Show: false,
      azContent4Show: false,
      azContent5Show: false,
      azContent6Show: false
    };
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
      var startPlaceVal;
      switch (rowIndex) {
        case 1:
          startPlaceVal = this.azCities1[colIndex];
          break;
        case 2:
          startPlaceVal = this.azCities2[colIndex];
          break;
        case 3:
          startPlaceVal = this.azCities3[colIndex];
          break;
        case 4:
          startPlaceVal = this.azCities4[colIndex];
          break;
        case 5:
          startPlaceVal = this.azCities5[colIndex];
          break;
        case 6:
          startPlaceVal = this.azCities6[colIndex];
          break;
        default:
          break;
      }
      this.$emit("setStartPlaceVal", startPlaceVal);
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