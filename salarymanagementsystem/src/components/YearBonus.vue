<template>
  <div>
    <el-card class="main_card">
      <el-table
          ref="yearBonusRef"
          :data="yearBonusList"
          stripe
          style="width: 100%"
      >
        <el-table-column prop="id" label="id"></el-table-column>
        <el-table-column prop="name" label="name"></el-table-column>
        <el-table-column prop="bonus" label="bonus"></el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      yearBonusList: [],
    };
  },
  created() {
    this.getAll();
  },
  methods: {
    getAll() {
      this.$http
          .get("/employee/getYearBonus")
          .then((res) => {
            res = res.data;
            console.log(res);
            this.$message.success(res.message);
            this.yearBonusList = res.data;
          })
          .catch((error) => {
            return this.$message.error("网络超时，登录失败");
          });
    },
  },
};
</script>

<style lang="less" scoped>
.main_card {
  height: 100%;
  width: 100%;
}
</style>
