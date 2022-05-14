<template>
  <div>
    <el-card class="main_card">
      <el-table
          ref="employeeTypeListRef"
          :data="employeeTypeList"
          stripe
          style="width: 100%"
      >
        <el-table-column prop="id" label="id"></el-table-column>
        <el-table-column prop="name" label="name"></el-table-column>
        <el-table-column prop="level" label="level">
        </el-table-column>
        <el-table-column prop="baseSalary" label="baseSalary">
        </el-table-column>
        <el-table-column label="operation">
          <template slot-scope="scope"
          >
            <el-button
                @click="edit(scope.row.id)"
                type="primary"
                style="width: 100px"
            >edit
            </el-button
            >
            <el-button
                @click="remove(scope.row.id)"
                type="danger"
                style="width: 100px"
            >delete
            </el-button
            >
          </template
          >
        </el-table-column
        >
      </el-table>
    </el-card
    >
    <el-card>
      <el-button @click="add" style="width: 100%; border-width: 0px"
      >Add
      </el-button
      >
    </el-card>
    <el-dialog
        title="Add"
        :visible.sync="dialogTableVisible"
        :close-on-click-modal="false"
    >
      <el-form :model="employeeType">
        <el-form-item label="id" v-if="editStatus">
          <el-input v-model="employeeType.id" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="name">
          <el-input v-model="employeeType.name"></el-input>
        </el-form-item>
        <el-form-item label="level">
          <el-input v-model="employeeType.level"></el-input>
        </el-form-item>
        <el-form-item label="baseSalary">
          <el-input v-model="employeeType.baseSalary"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit">submit</el-button>
          <el-button
              @click="
                            () => {
                                this.dialogTableVisible = false;
                            }
                        "
          >cancel
          </el-button
          >
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      employeeTypeList: [],
      dialogTableVisible: false,
      employeeType: {},
      editStatus: "",
    };
  },
  created() {
    this.getAll();
  },
  methods: {
    getAll() {
      this.$http
          .get("/employeeType/getAll")
          .then((res) => {
            res = res.data;
            console.log(res);
            this.$message.success(res.message);
            this.employeeTypeList = res.data;
          })
          .catch((error) => {
            return this.$message.error("网络超时，登录失败");
          });
    },
    add() {
      this.editStatus = false;
      this.dialogTableVisible = true;
      this.question = {};
    },
    edit(id) {
      this.editStatus = true;
      this.dialogTableVisible = true;

      this.$http
          .get("/employeeType/get?id=" + id)
          .then((res) => {
            res = res.data;
            console.log(res);
            this.employeeType = res.data;
            this.$message.success(res.message);
          })
          .catch((error) => {
            return this.$message.error("网络超时，登录失败");
          });
    },
    remove(id) {
      this.$confirm("Are you sure?", "Tip", {
        confirmButtonText: "Sure",
        cancelButtonText: "Cancel",
        type: "warning",
      })
          .then(() => {
            this.employeeType = {};
            this.employeeType.id = id;
            this.$http
                .post("/employeeType/delete", this.question)
                .then((res) => {
                  res = res.data;
                  if (res.code != "0000")
                    return this.$message.error(res.message);
                  this.$message.success(res.message);
                  this.dialogTableVisible = false;
                  this.getAll();
                })
                .catch((error) => {
                  return this.$message.error("网络超时，提交失败");
                });
            this.$message({
              type: "success",
              message: "Remove success!",
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "Canceled",
            });
          });
      this.getAll();
    },
    submit() {
      console.log(this.editStatus);
      if (this.editStatus) this.update();
      else this.upload();
    },
    upload() {
      console.log("upload");
      this.$http
          .post("/employeeType/add", this.employeeType)
          .then((res) => {
            res = res.data;
            if (res.code != "0000")
              return this.$message.error(res.message);
            this.$message.success(res.message);
            this.dialogTableVisible = false;
            this.getAll();
          })
          .catch((error) => {
            return this.$message.error("网络超时，提交失败");
          });
    },
    update() {
      console.log("update");
      this.$http
          .post("/employeeType/update", this.question)
          .then((res) => {
            res = res.data;
            if (res.code != "00000")
              return this.$message.error(res.message);
            this.$message.success(res.message);
            this.dialogTableVisible = false;
            this.getAll();
          })
          .catch((error) => {
            return this.$message.error("网络超时，提交失败");
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
