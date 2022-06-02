<template>
    <div>
        <el-card class="main_card">
            <el-table
                ref="monthSalaryRef"
                :data="monthSalaryList"
                stripe
                style="width: 100%"
            >
                <el-table-column prop="employeeId" label="employeeId"> </el-table-column>
                <el-table-column prop="salary" label="salary"> </el-table-column>
                <el-table-column label="operation">
                    <template slot-scope="scope"
                        ><el-button
                            @click="edit(scope.row.employeeId)"
                            type="primary"
                            style="width: 100px"
                            >edit</el-button
                        >
                        <el-button
                            @click="remove(scope.row.employeeId)"
                            type="danger"
                            style="width: 100px"
                            >delete</el-button
                        ></template
                    ></el-table-column
                >
            </el-table></el-card
        >
        <el-card>
            <el-button @click="add" style="width: 100%; border-width: 0px"
                >Add</el-button
            >
        </el-card>
        <el-dialog
            title="Add"
            :visible.sync="dialogTableVisible"
            :close-on-click-modal="false"
        >
            <el-form :model="monthSalary">
                <el-form-item label="employeeId">
                    <el-input v-model="monthSalary.employeeId"></el-input>
                </el-form-item>
                <el-form-item label="salary">
                    <el-input v-model="monthSalary.salary"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submit">submit</el-button>
                    <el-button
                        @click="
                            () => {
                                this.dialogTableVisible = false;
                            }
                        "
                        >cancel</el-button
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
            monthSalaryList: [],
            dialogTableVisible: false,
            monthSalary: {},
            editStatus: "",
        };
    },
    created() {
        this.getAll();
    },
    methods: {
        getAll() {
            this.$http
                .get("/monthSalary/getAll")
                .then((res) => {
                    res = res.data;
                    console.log(res);
                    this.$message.success(res.message);
                    this.monthSalaryList = res.data;
                })
                .catch((error) => {
                    return this.$message.error("网络超时，登录失败");
                });
        },
        add() {
            this.editStatus = false;
            this.dialogTableVisible = true;
            this.monthSalary = {};
        },
        edit(id) {
            this.editStatus = true;
            this.dialogTableVisible = true;

            this.$http
                .get("/monthSalary/get?id=" + id)
                .then((res) => {
                    res = res.data;
                    console.log(res);
                    this.monthSalary = res.data;
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
                    this.monthSalary = {};
                    this.monthSalary.employeeId = id;
                    this.$http
                        .post("/monthSalary/delete", this.monthSalary)
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
                .post("/monthSalary/add", this.monthSalary)
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
                .post("/monthSalary/update", this.monthSalary)
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
