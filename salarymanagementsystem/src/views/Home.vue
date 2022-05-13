<template>
  <el-container>
    <el-header class="header_header">
      <el-card class="header_card"
      ><span>{{ username }}</span>
        <el-button class="logout_button" @click="logout"
        >logout</el-button
        >
      </el-card>
    </el-header>
    <el-container>
      <el-aside width="200px"
      ><el-menu class="main_menu" :router="true">
        <el-menu-item index="user">
          <i class="el-icon-menu"></i>
          <span slot="title">User</span>
        </el-menu-item>
        <el-menu-item index="question">
          <i class="el-icon-menu"></i>
          <span slot="title">Question</span>
        </el-menu-item>
        <!-- <el-menu-item index="3" disabled>
            <i class="el-icon-document"></i>
            <span slot="title">导航三</span>
        </el-menu-item>
        <el-menu-item index="4">
            <i class="el-icon-setting"></i>
            <span slot="title">导航四</span>
        </el-menu-item> -->
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
      username: "",
    };
  },
  created() {
    this.setName();
  },
  methods: {
    setName() {
      this.username = window.sessionStorage.getItem("username");
    },
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
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
