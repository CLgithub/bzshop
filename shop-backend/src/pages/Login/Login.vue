<template>
    <div class="login">
        <el-card class="box-card">
            <el-form
                :model="ruleForm"
                status-icon
                ref="ruleForm"
                label-width="100px"
                class="demo-ruleForm"
            >
                <el-form-item label="用户名" prop="pass">
                    <el-input type="text" v-model="ruleForm.username" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="密  码" prop="checkPass">
                    <el-input type="password" v-model="ruleForm.password" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('ruleForm')">登陆</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>
<script>
import { mapMutations } from "vuex";
import Storage from "../../utils/Storage";

export default {
    name: "Login",
    data() {
        return {
            ruleForm: {
                username: "",
                password: ""
            }
        };
    },
    methods: {
        ...mapMutations(["setToken"]),
        submitForm(formName) {
            this.$api.getLogin({
                    username: this.ruleForm.username,
                    password: this.ruleForm.password
                })
                .then(res => {
                    this.setToken(res.data.token);
                    Storage.setItem("token", res.data.token);
                    this.$router.push("/");
                });
        }
    }
};
</script>
<style scoped>
.login {
    text-align: center;
}

.box-card {
    width: 480px;
    margin: 0 auto;
    margin-top: 100px;
}
</style>
