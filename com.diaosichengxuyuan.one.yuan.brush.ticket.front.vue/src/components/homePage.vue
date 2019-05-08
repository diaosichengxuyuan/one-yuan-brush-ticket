<template>
  <div id="propaSlogan">{{description}}</div>
</template>

<script>
export default {
  name: "HomePage",
  data() {
    return {
      description: "完全免费，请放心使用！！！"
    };
  }
};
</script>

<style scoped>
#propaSlogan {
  width: 1200px;
  font-size: 50px;
  color: red;
  text-align: center;
  line-height: 500px;
}
</style>