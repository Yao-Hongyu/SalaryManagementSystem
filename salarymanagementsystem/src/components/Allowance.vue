<template>
  <div>
    <el-card class="main_card">
      <el-table
          ref="allowanceRef"
          :data="allowanceList"
          stripe
          style="width: 100%"
      >
        <el-table-column prop="id" label="id"></el-table-column>
        <el-table-column prop="employeeId" label="employeeId"></el-table-column>
        <el-table-column prop="date" label="date">
        </el-table-column>
        <el-table-column prop="duration" label="duration">
        </el-table-column>
        <el-table-column prop="description" label="description">
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
      <el-form :model="allowance">
        <el-form-item label="id" v-if="editStatus">
          <el-input v-model="allowance.id" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="employeeId">
          <el-input v-model="allowance.employeeId"></el-input>
        </el-form-item>
        <el-form-item label="date">
          <el-date-picker
              v-model="allowance.date"
              type="date"
              placeholder="选择日期时间">
          </el-date-picker>
          <!--                    <el-input v-model="allowance.date"></el-input>-->
        </el-form-item>
        <el-form-item label="duration">
          <el-input v-model="allowance.duration"></el-input>
        </el-form-item>
        <el-form-item label="description">
          <el-input v-model="allowance.description"></el-input>
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
      allowanceList: [],
      dialogTableVisible: false,
      allowance: {},
      editStatus: "",
    };
  },
  created() {
    this.getAll();
  },
  methods: {
    getAll() {
      this.$http
          .get("/allowance/getAll")
          .then((res) => {
            res = res.data;
            console.log(res);
            this.$message.success(res.message);
            this.allowanceList = res.data;
            for (let i = 0; i < this.allowanceList.length; i++) {
              this.allowanceList[i].date = new Date(parseInt(this.allowanceList[i].date)).toLocaleString();
            }
          })
          .catch((error) => {
            return this.$message.error("网络超时，登录失败");
          });
    },
    add() {
      this.editStatus = false;
      this.dialogTableVisible = true;
      this.allowance = {};
    },
    edit(id) {
      this.editStatus = true;
      this.dialogTableVisible = true;

      this.$http
          .get("/allowance/get?id=" + id)
          .then((res) => {
            res = res.data;
            console.log(res);
            this.allowance = res.data;
            this.allowance.date = new Date(parseInt(this.allowance.date)).toLocaleString();
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
            this.allowance = {};
            this.allowance.id = id;
            this.$http
                .post("/allowance/delete", this.allowance)
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
      console.log(this.allowance)
      this.allowance.date = this.allowance.date.getTime();
      this.$http
          .post("/allowance/add", this.allowance)
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
      this.allowance.date = this.allowance.date.getTime();
      this.$http
          .post("/allowance/update", this.allowance)
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
  },
};
</script>

<style lang="less" scoped>
.main_card {
  height: 100%;
  width: 100%;
}
</style>
