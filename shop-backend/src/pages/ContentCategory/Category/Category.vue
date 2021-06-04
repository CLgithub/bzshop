<template>
    <div>
        <el-tree
            :props="defaultProps"
            :load="loadTreeNodes"
            lazy
            highlight-current
            :expand-on-click-node="false"
            :render-content="renderContent"
            @node-click="handleNodeClick"
        ></el-tree>
        <!-- 添加的弹出框 -->
        <el-dialog
            title="添加名称"
            :visible.sync="categoryVisible"
            width="30%"
            :before-close="handleClose"
        >
            <span>
                <el-input v-model="categoryName" placeholder="请添加分类名称"></el-input>
            </span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="categoryVisible = false">取 消</el-button>
                <el-button type="primary" @click="sureHandler">确 定</el-button>
            </span>
        </el-dialog>
        <!-- 修改的弹出框 -->
        <el-dialog
            title="修改名称"
            :visible.sync="updateCategoryVisible"
            width="30%"
            :before-close="handleClose"
        >
            <span>
                <el-input v-model="updateCategoryName" placeholder="请输入新的名称"></el-input>
            </span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="updateCategoryVisible = false">取 消</el-button>
                <el-button type="primary" @click="sureUpdateHandler">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
export default {
    name: "Category",
    data() {
        return {
            defaultProps: {
                children: "children",
                label: "name"
            },
            categoryVisible: false,
            categoryName: "", // 分类名称
            currentCategory: {},
            updateCategoryVisible: false,
            updateCategoryName: "", // 修改名称
            currentUpdateCategory:{},
            node:null,
            resolve:null,
        };
    },
    mounted() {},
    methods: {
        loadTreeNodes(node, resolve) {
            this.node = node;
            this.resolve = resolve;
            if (node.level === 0) {
                // 第一次调用
                this.$api
                    .getSelectContentCategoryByParentId()
                    .then(res => {
                        if (res.status === 200) {
                            return resolve(res.data.data);
                        }
                    })
                    .catch(error => {
                        resolve([]);
                    });
            }
            if (node.level >= 1) {
                // 点击之后触发
                this.$api
                    .getSelectContentCategoryByParentId({ id: node.data.id })
                    .then(res => {
                        if (res.status === 200) {
                            return resolve(res.data.data);
                        }
                    })
                    .catch(error => {
                        resolve([]);
                    });
            }
        },
        renderContent(h, { node, data, store }) {
            return (
                <span class="custom-tree-node">
                    <span>{node.label}</span>
                    <span>
                        <el-button
                            size="mini"
                            type="text"
                            on-click={() => this.append(node,data)}
                        >
                            添加
                        </el-button>
                        <el-button
                            size="mini"
                            type="text"
                            on-click={() => this.remove(node, data)}
                        >
                            删除
                        </el-button>
                        <el-button
                            size="mini"
                            type="text"
                            on-click={() => this.update(node, data)}
                        >
                            修改
                        </el-button>
                    </span>
                </span>
            );
        },
        append(node,data) {
            this.categoryVisible = true;
            this.currentCategory = data;
        },
        /* 确定添加分类事件 */
        sureHandler() {
            this.$api
                .getInsertContentCategory({
                    parentId: this.currentCategory.id,
                    name: this.categoryName
                })
                .then(res => {
                    if (res.data.status === 200) {
                        this.categoryVisible = false;
                        // 刷新
                        this.loadTreeNodes(this.node,this.resolve);
                    }
                });
        },
        remove(node, data) {
            this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$api
                        .getDeleteContentCategoryById({
                            categoryId: data.id
                        })
                        .then(res => {
                            if (res.data.status === 200) {
                                this.$message({
                                    type: "success",
                                    message: "删除成功"
                                });
                                // 刷新页面 todo
                                this.loadTreeNodes(this.node,this.resolve);
                            } else {
                                this.$message({
                                    type: "error",
                                    message: "删除失败"
                                });
                            }
                        });
                })
                .catch(() => {
                    this.$message({
                        type: "info",
                        message: "已取消删除"
                    });
                });
        },
        update(node, data) {
            this.updateCategoryVisible = true;
            this.currentUpdateCategory = data;
        },
        /* 确定修改事件 */
        sureUpdateHandler() {
            this.$api.getUpdateContentCategory({
                id:this.currentUpdateCategory.id,
                name:this.updateCategoryName
            }).then(res => {
                if(res.data.status === 200){
                    this.updateCategoryVisible = false;
                    this.$message({
                        type: "success",
                        message: "修改成功"
                    });
                    // 刷新
                    this.loadTreeNodes(this.node,this.resolve);
                }else{
                    alert("修改失败")
                }
            });
        },
        handleNodeClick(data) {
            this.$emit("onItem",data)
        },
        handleClose() {
            this.categoryVisible = false;
        }
    }
};
</script>
<style scoped>
</style>

