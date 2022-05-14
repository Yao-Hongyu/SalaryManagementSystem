<template>
  <el-container>
    <el-header class="header_header">
    </el-header>
    <el-container>
      <el-aside width="200px"
      ><el-menu class="main_menu" :router="true">
        <el-menu-item index="employee">
          <i class="el-icon-menu"></i>
          <span slot="title">Employee</span>
        </el-menu-item>
        <el-menu-item index="employeeType">
          <i class="el-icon-menu"></i>
          <span slot="title">EmployeeType</span>
        </el-menu-item>
        <el-menu-item index="monthSalary">
          <i class="el-icon-menu"></i>
          <span slot="title">MonthSalary</span>
        </el-menu-item>
        <el-menu-item index="workRecord">
          <i class="el-icon-menu"></i>
          <span slot="title">WorkRecord</span>
        </el-menu-item>
        <el-menu-item index="allowance">
          <i class="el-icon-menu"></i>
          <span slot="title">Allowance</span>
        </el-menu-item>
      </el-menu></el-aside
      >
      <el-main class="main_container"
      ><router-view></router-view
      ></el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
    };
  },
  created() {
  },
  methods: {
  },
};
</script>

<style lang="less" scoped>
.header_header {
  padding: 0px;
}

.header_card {
  width: 100%;
  text-align: right;
  position: absolute;
}
.logout_button {
  position: relative;
  margin: 0 10px;
}
.main_container {
  padding: 0px;
}
</style>
