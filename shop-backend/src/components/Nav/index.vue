<template>
    <div class="topNav">
        <div id="topbanner" ref="topNav">
            <div class="con">
                <div class="Log">
                    <span>百战商城后台系统</span>
                </div>
                <div>
                    <router-link exact to="/" class="product">
                        <i class="el-icon-search"></i>
                        查询商品
                    </router-link>
                </div>
                <div>
                    <router-link to="/params" class="params">
                        <i class="el-icon-copy-document"></i>
                        规格参数
                    </router-link>
                </div>
                <div>
                    <router-link to="/contentcategory" class="contentcategory">
                        <i class="el-icon-postcard"></i>
                        内容分类管理
                    </router-link>
                </div>
                <!-- <div>
                    <span class="nick">iwen</span>
                    <el-button type="info" @click="logOutHandler" plain>退出</el-button>
                </div> -->
            </div>
        </div>
    </div>
</template>

<script>

import { mapMutations } from "vuex";
import Storage from "../../utils/Storage";

export default {
    name: "Nav",
    data() {
        return {};
    },
    methods:{
        ...mapMutations(["delToken"]),
        logOutHandler(){
            this.delToken();
            Storage.removeItem("token");
            this.$router.push("/login")
        }
    }
};
</script>
<style scoped>
#topbanner {
    width: 100%;
    height: 80px;
    background: rgb(42, 50, 61);
    margin: 0 auto;
}

.con {
    width: 1120px;
    height: 80px;
    margin: 0 auto;
}
html {
    padding: 0;
    margin: 0;
}

.topNav a {
    text-decoration: none;
    /* margin: 20px 20px 20px 20px; */
    color: aliceblue;
}

.con div {
    float: left;
    width: 220px;
    height: 80px;
    line-height: 80px;
}

.backcolor,
.classname {
    color: aliceblue;
    cursor: pointer;
}

#topbanner img {
    width: 100%;
    height: 100%;
}

.Log span {
    font-weight: 800;
    font-style: normal;
    color: aliceblue;
    font-size: 25px;
    display: block;
}
.topNav .active {
    color: green;
}

.nick{
    margin-right: 10px;
    color:#fff;
}

</style>

