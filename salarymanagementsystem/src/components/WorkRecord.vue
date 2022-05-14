<template>
  <div>
    <el-card class="main_card">
      <el-table
          ref="workRecordRef"
          :data="workRecordList"
          stripe
          style="width: 100%"
      >
        <el-table-column prop="id" label="id"></el-table-column>
        <el-table-column prop="employeeId" label="employeeId"></el-table-column>
        <el-table-column prop="date" label="date"></el-table-column>
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
      <el-form :model="workRecord">
        <el-form-item label="id" v-if="editStatus">
          <el-input v-model="workRecord.id" :disabled="true"></el-input>
        </el-form-item>
        <el-form-item label="employeeId">
          <el-input v-model="workRecord.employeeId"></el-input>
        </el-form-item>
        <el-form-item label="date">
          <el-date-picker
              v-model="workRecord.date"
              type="date"
              placeholder="选择日期时间">
          </el-date-picker>
<!--          <el-input-->
<!--              v-model="workRecord.date"-->
<!--          ></el-input>-->
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
      workRecordList: [],
      dialogTableVisible: false,
      workRecord: {},
      editStatus: "",
    };
  },
  created() {
    this.getAll();
  },
  methods: {
    getAll() {
      this.$http
          .get("/workRecord/getAll")
          .then((res) => {
            res = res.data;
            console.log(res);
            this.$message.success(res.message);
            this.workRecordList = res.data;
            for (let i=0; i<this.workRecordList.length; i++){
              this.workRecordList[i].date = new Date(parseInt(this.workRecordList[i].date)).toLocaleString();
            }
            console.log(this.workRecordList)
          })
          .catch((error) => {
            return this.$message.error("网络超时，登录失败");
          });
    },
    add() {
      this.editStatus = false;
      this.dialogTableVisible = true;
      this.workRecord = {};
    },
    edit(id) {
      this.editStatus = true;
      this.dialogTableVisible = true;

      this.$http
          .get("/workRecord/get?id=" + id)
          .then((res) => {
            res = res.data;
            console.log(res);
            this.workRecord = res.data;
            this.workRecord.date = this.workRecord.date.getTime();
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
            this.workRecord = {};
            this.workRecord.id = id;
            this.$http
                .post("/workRecord/delete", this.question)
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
      console.log(this.workRecord);
      this.workRecord.date = this.workRecord.date.getTime();
      this.$http
          .post("/workRecord/add", this.workRecord)
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
      this.workRecord.date = this.workRecord.date.getTime();
      this.$http
          .post("/workRecord/update", this.question)
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
