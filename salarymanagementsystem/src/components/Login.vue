<template>
  <el-container>
    <el-card class="login_card">
      <div slot="header" class="clearfix">
        <span>Welcome</span>
      </div>
      <el-form ref="form" :model="userForm" label-width="80px">
        <el-form-item label-width="0">
          <el-input
              placeholder="username"
              v-model="userForm.username"
          ></el-input>
        </el-form-item>
        <el-form-item label-width="0">
          <el-input
              placeholder="password"
              v-model="userForm.password"
          ></el-input>
        </el-form-item>
      </el-form>
      <el-button
          class="signin_button"
          type="primary"
          plain
          @click="signin"
      >signin</el-button
      >
      <el-button class="login_button" type="success" plain @click="login"
      >login</el-button
      >
    </el-card>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      userForm: {
        username: "",
        password: "",
      },
    };
  },
  created() {},
  methods: {
    signin() {
      this.$http
          .post("/admin/register", this.userForm)
          .then((res) => {
            res = res.data;
            console.log(res);
            return this.$message(res.message);
          })
          .catch((error) => {
            return this.$message.error("网络超时，注册失败");
          });
    },
    login() {
      this.$http
          .post("/admin/login", this.userForm)
          .then((res) => {
            res = res.data;
            console.log(res);
            this.$message.success(res.message);
            if (res.code == "0000") {
              console.log(res.data.token);
              window.sessionStorage.setItem("token", res.data.token);
              // this.$emit("alter-username", this.userForm.username);
              return this.$router.push("/home");
            }
          })
          .catch((error) => {
            return this.$message.error("网络超时，登录失败");
          });
    },
  },
};
</script>

<style style="less" scoped>
.login_card {
  margin: auto auto;
  height: 300px;
  width: 400px;
}
</style>
