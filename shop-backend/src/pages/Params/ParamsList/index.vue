<template>
    <div class="params">
        <div>
            <el-button type="primary" @click="addParamsHandler">增加规格参数</el-button>
        </div>
        <el-table v-if="paramsData.length>0" :data="paramsData" style="width: 100%">
            <el-table-column :show-overflow-tooltip="true" width="60px" prop="id" label="ID"></el-table-column>
            <el-table-column
                :show-overflow-tooltip="true"
                width="80px"
                prop="itemCatId"
                label="类目ID"
            ></el-table-column>
            <el-table-column
                :show-overflow-tooltip="true"
                width="500px"
                prop="paramData"
                label="规格(只显示分组名称)"
            ></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="created" label="创建日期"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="updated" label="更新日期"></el-table-column>
            <el-table-column label="操作" width="120" fixed="right">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        icon="el-icon-delete"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)"
                    ></el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 添加规格参数弹出框 -->
        <el-dialog width="50%" title="商品类目选择" :visible.sync="paramsVisible">
            <!-- 类目的tree -->
            <el-tree
                :load="loadTreeNodes"
                :props="defaultProps"
                lazy
                highlight-current
                @node-click="handleNodeClick"
            ></el-tree>
            <el-dialog width="50%" title="添加分组" :visible.sync="groupsVisible" append-to-body>
                <ParamsAdd @onParamsAdd="getParamsAdd" />
            </el-dialog>
            <div slot="footer" class="dialog-footer">
                <el-button @click="paramsVisibleHandler">取 消</el-button>
                <el-button type="primary" @click="groupsVisible = true">确定并添加分组</el-button>
            </div>
        </el-dialog>
    </div>
</template>
<script>
import ParamsAdd from "../ParamsAdd";

export default {
    name: "Params",
    data() {
        return {
            paramsData: [],
            paramsVisible: false, // 添加规格参数弹出框
            groupsVisible: false, // 添加分组弹出框
            currentCategory: {}, // 分类数据
            defaultProps: {
                children: "children",
                label: "name"
            }
        };
    },
    components: {
        ParamsAdd
    },
    mounted() {
        this.http();
    },
    methods: {
        http() {
            this.$api.getSelectItemParamAll().then(res => {
                if (res.data.status === 200) {
                    this.paramsData = res.data.data.result;
                }
            });
        },
        addParamsHandler() {
            this.paramsVisible = true;
        },
        /* 添加规格参数取消弹出框事件 */
        paramsVisibleHandler() {
            this.paramsVisible = false;
        },
        loadTreeNodes(node, resolve) {
            if (node.level === 0) {
                // 第一次调用
                this.$api.getSelectItemCategoryByParentId().then(res => {
                    if (res.status === 200) {
                        return resolve(res.data.data);
                    } else {
                        alert("请求失败");
                    }
                });
            }
            if (node.level >= 1) {
                // 点击之后触发
                this.$api
                    .getSelectItemCategoryByParentId({ id: node.data.id })
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
        /* 商品类目tree的点击事件 */
        handleNodeClick(data) {
            this.currentCategory = data;
        },
        /* 获取用户输入的规格参数 */
        getParamsAdd(data) {
            console.log(data);
            /**
             * 获取回传数据
             * 调用接口，上传数据
             */
            this.$api
                .getInsertItemParam({
                    itemCatId: this.currentCategory.id,
                    paramData: data
                })
                .then(res => {
                    if (res.data.status === 200) {
                        this.paramsVisible = false;
                        this.groupsVisible = false;
                        this.http();
                    } else {
                        alert("增加失败");
                    }
                });
        },
        /* 规格参数删除事件 */
        handleDelete(index, row) {
            this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$api
                        .getDeleteItemParamById({ id: row.id })
                        .then(res => {
                            if (res.data.status === 200) {
                                this.$message({
                                    type: "success",
                                    message: "已删除"
                                });
                                this.http();
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
        }
    }
};
</script>
<style scoped>
.params {
    width: 1200px;
    margin: 0 auto;
    margin-top: 10px;
}
</style>

