<template>
    <div>
        <Nav />
        <div class="content">
            <div class="content-left">
                <Category @onItem="getItem"/>
            </div>
            <div class="content-right">
                <Content :item="item"/>
            </div>
        </div>
    </div>
</template>
<script>
import Nav from "../../components/Nav";
import Category from "./Category/Category";
import Content from "./Content/Content";

export default {
    name: "ContentCategory",
    data(){
        return{
            item:{}
        }
    },
    components: {
        Nav,
        Category,
        Content
    },
    methods:{
        getItem(data){
            for(var i = 0;i<this.$children.length;i++){
                if(this.$children[i].getContent){
                    this.$children[i].getContent(data);
                }
            }
            
        }
    }
};
</script>
<style scoped>
.content {
    width: 70%;
    height: 200px;
    margin: 0 auto;
    margin-top: 10px;
}

.content-left {
    float: left;
    width: 25%;
    height: 100%;
}

.content-right {
    float: left;
    width: 75%;
    height: 100%;
}

.custom-tree-node {
    display: inline-block;
    text-align: right;
    width: 100%;
}
.upload img {
    width: 200px;
}
</style>

