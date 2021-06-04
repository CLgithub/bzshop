<template>
    <div class="product-list">
        <div>
            <el-button type="primary" @click="addProduct">添加商品</el-button>
        </div>
        <!-- 商品列表展示table -->
        <el-table v-if="productData.length>0" :data="productData" style="width: 100%">
            <el-table-column :show-overflow-tooltip="true" prop="id" label="商品ID"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="title" label="商品标题"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="image" label="商品图片"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="sellPoint" label="商品卖点"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="price" label="商品价格"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="cid" label="商品类目"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="num" label="商品库存"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="barcode" label="条形码"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="status" label="状态"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="created" label="创建日期"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="updated" label="更新日期"></el-table-column>
            <el-table-column label="操作" width="120" fixed="right">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        icon="el-icon-edit"
                        @click="handleEdit(scope.$index, scope.row)"
                        circle
                    ></el-button>
                    <el-button
                        size="mini"
                        icon="el-icon-delete"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)"
                        circle
                    ></el-button>
                </template>
            </el-table-column>
        </el-table>
        <div v-else>{{ timeoutMsg }}</div>
        <!-- 添加商品弹出框 -->
        <div class="add-dialog">
            <el-dialog
                title="编辑商品"
                :visible.sync="addDialogVisible"
                width="80%"
                :before-close="handleClose"
            >
                <div>{{ preUpdaetMsg }}</div>
                <el-form ref="form" :model="product" label-width="80px">
                    <el-form-item label="商品类目">
                        <el-button
                            @click="addProdcutCategoryHandler"
                            style="float:left"
                            type="primary"
                        >选择类目</el-button>
                        <span style="float:left;margin-left:10px;">{{ currentCategory.name }}</span>
                    </el-form-item>
                    <el-form-item label="商品标题">
                        <el-input v-model="product.title"></el-input>
                    </el-form-item>
                    <el-form-item label="商品卖点">
                        <el-input type="textarea" v-model="product.sellPoint"></el-input>
                    </el-form-item>
                    <el-form-item label="商品价格">
                        <el-input v-model="product.price"></el-input>
                    </el-form-item>
                    <el-form-item label="库存数量">
                        <el-input v-model="product.num"></el-input>
                    </el-form-item>
                    <el-form-item label="条形码"></el-form-item>
                    <el-form-item label="商品图片">
                        <el-button @click="uploadHandler" style="float:left" type="primary">上传图片</el-button>
                        <!-- 上传成功之后显示的图片 -->
                        <img class="upload-img" :src="uploadData.data" alt />
                        <!-- 上传图片弹出框 -->
                        <el-dialog
                            width="50%"
                            title="上传商品图片"
                            :visible.sync="productUploadVisible"
                            append-to-body
                        >
                            <ProdcutUpload @onUpload="getUploadData" />
                            <div slot="footer" class="dialog-footer">
                                <el-button @click="productUploadVisible">取 消</el-button>
                                <el-button type="primary" @click="productUploadVisible = false">确 定</el-button>
                            </div>
                        </el-dialog>
                    </el-form-item>
                    <el-form-item label="商品描述">
                        <vue-ueditor-wrap v-model="product.desc" :config="myConfig"></vue-ueditor-wrap>
                    </el-form-item>

                    <!-- 显示规格参数 -->
                    <div v-if="paramsData.length>0">
                        <div class="groupBox" v-for="(item,index) in paramsData" :key="index">
                            <div>
                                <h3>{{item.value}}</h3>
                            </div>
                            <div>
                                <el-form-item
                                    v-for="(element,index) in item.children"
                                    :key="index"
                                    :label="element.childValue"
                                >
                                    <el-input type="text" v-model="paramsGroup[element.childValue]"></el-input>
                                </el-form-item>
                            </div>
                        </div>
                    </div>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
                <!-- 类目选择弹出框 -->
                <el-dialog
                    width="50%"
                    title="商品类目选择"
                    :visible.sync="productCategoryVisible"
                    append-to-body
                >
                    <!-- 类目的tree -->
                    <el-tree
                        :load="loadTreeNodes"
                        :props="defaultProps"
                        lazy
                        highlight-current
                        @node-click="handleNodeClick"
                    ></el-tree>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="productCategoryVisibleHandler">取 消</el-button>
                        <el-button type="primary" @click="getParamsHandler">确 定</el-button>
                    </div>
                </el-dialog>
            </el-dialog>
            <!-- 分页管理 -->
            <ProdcutPagination @onsend="getData" />
        </div>
    </div>
</template>
<script>
import ProdcutUpload from "../ProductUpload";
import ProdcutPagination from "../ProductPagination";
import { setParams } from "../../../utils/params";

export default {
    name: "ProductList",
    data() {
        return {
            productData: [],
            preUpdaetMsg: "", // 预更新信息提示
            productCategoryVisible: false, // 商品类目对话框
            productUploadVisible: false, // 商品上传图片对话框
            addDialogVisible: false, // 添加商品对话框
            paramsData: [], // 规格参数数组
            timeoutMsg: "", // 数据获取失败的提示
            // 商品对象
            product: {},
            paramsGroup: {}, // 规格参数自选项数据
            updateFlagid: "",
            currentCategory: {}, // 当前选择商品类目
            // uedite配置
            myConfig: {
                autoHeightEnabled: false,
                initialFrameHeight: 240,
                initialFrameWidth: "100%",
                serverUrl: "http://35.201.165.105:8000/controller.php",
                UEDITOR_HOME_URL: "/UEditor/"
            },
            // prodcutCategoryData: [],
            defaultProps: {
                children: "children",
                label: "name"
            },
            uploadData: {} // 上传图片数据
        };
    },
    components: {
        ProdcutUpload,
        ProdcutPagination
    },
    mounted() {
        /* 商品列表请求 */
        this.http();
    },
    methods: {
        /* 商品列表请求 */
        http() {
            this.$api.prodcutList({ page: 1 }).then(res => {
                if (res.data.status === 200) {
                    if (res.data.data.result) {
                        this.productData = res.data.data.result;
                    } else {
                        this.timeoutMsg = res.data.data;
                    }
                } else {
                    console.log("请求失败");
                }
            });
        },
        /* 编辑事件 */
        handleEdit(index, row) {
            this.addDialogVisible = true;
            this.updateFlagid = row.id;
            // 预更新
            this.$api.getPreUpdateItem({ itemId: row.id }).then(res => {
                if (res.data.status === 200) {
                    if (res.data.data.itemCat) {
                        this.currentCategory.name = res.data.data.itemCat;
                        this.product = res.data.data.item;
                        this.product.desc = res.data.data.itemDesc;
                        this.uploadData.data = res.data.data.item.image;
                    }else{
                        this.preUpdaetMsg = res.data.data;
                    }
                } else {
                    alert("预更新失败");
                }
            });
        },
        /* 商品删除事件 */
        handleDelete(index, row) {
            this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$api
                        .getDeleteItemById({ itemId: row.id })
                        .then(res => {
                            if (res.data.status === 200) {
                                this.$message({
                                    type: "success",
                                    message: "删除成功!"
                                });
                                this.http();
                            } else {
                                this.$message({
                                    type: "error",
                                    message: "删除失败!"
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
        /* 添加商品事件 */
        addProduct() {
            this.addDialogVisible = true;
        },
        /* 添加商品提交事件 */
        submitForm() {
            // 处理规格参数
            if (this.updateFlagid) {
                // 修改
                this.$api
                    .getUpdateProduct({
                        id: this.updateFlagid,
                        cid: this.currentCategory.id,
                        title: this.product.title,
                        sellPoint: this.product.sellPoint,
                        price: this.product.price,
                        num: this.product.num,
                        desc: this.product.desc,
                        image: this.uploadData.data
                    })
                    .then(res => {
                        if (res.data.status === 200) {
                            this.addDialogVisible = false;
                            this.http();
                            // 初始化信息
                            this.currentCategory = {};
                            this.product = {};
                            this.uploadData = {};
                            this.updateFlagid = "";
                        } else {
                            alert("修改失败");
                        }
                    });
            } else {
                // 提交
                this.$api
                    .insertTbItem({
                        cid: this.currentCategory.id,
                        title: this.product.title,
                        sellPoint: this.product.sellPoint,
                        price: this.product.price,
                        num: this.product.num,
                        desc: this.product.desc,
                        image: this.uploadData.data,
                        // itemParams: encodeURIComponent(
                        //     setParams(this.paramsData, this.paramsGroup)
                        // )
                    })
                    .then(res => {
                        if (res.data.status !== 200) {
                            alert("添加产品失败");
                        }
                        this.addDialogVisible = false;
                        // 刷新页面
                        this.http();
                        // 初始化信息
                        this.currentCategory = {};
                        this.product = {};
                        this.uploadData = {};
                    });
            }
        },
        /* 添加商品重置事件 */
        resetForm() {},
        /* 添加商品类目事件 */
        addProdcutCategoryHandler() {
            this.productCategoryVisible = true;
            // 网络请求 商品类目列表
            // this.$api.getSelectItemCategoryByParentId().then(res => {
            //     if (res.data.status === 200) {
            //         this.prodcutCategoryData = res.data.data;
            //     }
            // });
        },
        /* tree的懒加载 */
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
        handleClose() {
            this.addDialogVisible = false;
        },
        /* 选择类目取消事件 */
        productCategoryVisibleHandler() {
            this.productCategoryVisible = false;
            this.currentCategory = "";
        },
        /* 添加产品规格参数 */
        getParamsHandler() {
            this.$api
                .getGroupParamData({ id: this.currentCategory.id })
                .then(res => {
                    if (res.data.status === 200) {
                        this.productCategoryVisible = false;
                        console.log(res.data.data.paramData);
                        this.paramsData = JSON.parse(res.data.data.paramData);
                    } else {
                        alert("规格参数不存在");
                    }
                });
        },
        /* 上传图片事件 */
        uploadHandler() {
            this.productUploadVisible = true;
        },
        /* 上传图片回传事件 */
        getUploadData(data) {
            if (data.status === 200) {
                this.uploadData = data;
            } else {
                alert("上传失败");
            }
        },
        /* 分页事件 */
        getData(data) {
            this.productData = data;
        }
    }
};
</script>
<style scoped>
.product-list {
    width: 1200px;
    margin: 0 auto;
    margin-top: 10px;
}
.upload-img {
    width: 200px;
    float: left;
    margin-left: 10px;
}
</style>

