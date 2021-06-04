<template>
    <div>
        <el-button
            class="addBtn"
            @click="addDialogVisible = true"
            type="primary"
            v-if="addBtnShow"
        >添加{{ addContent }}</el-button>
        <el-table stripe :data="contentData" style="width: 100%">
            <el-table-column :show-overflow-tooltip="true" prop="id" label="ID" width="40"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="title" label="标题"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="subTitle" label="子标题"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="content" label="描述"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="url" label="连接"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="pic" label="图片"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="created" label="创建日期"></el-table-column>
            <el-table-column :show-overflow-tooltip="true" prop="updated" label="更新日期"></el-table-column>
            <el-table-column prop="updateProduct" label="操作" width="120" fixed="right">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)"
                        icon="el-icon-delete"
                        circle
                    ></el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 添加内容弹出框 -->
        <el-dialog
            title="添加内容"
            :visible.sync="addDialogVisible"
            width="60%"
            :before-close="handleClose"
        >
            <span>
                <el-form ref="form" :model="form" label-width="80px">
                    <el-form-item label="标题">
                        <el-input v-model="form.title"></el-input>
                    </el-form-item>
                    <el-form-item label="子标题">
                        <el-input
                            v-model="form.subTitle"
                            type="textarea"
                            :rows="3"
                            placeholder="请输入内容"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="描述">
                        <el-input
                            v-model="form.content"
                            type="textarea"
                            :rows="3"
                            placeholder="请输入内容"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="URL">
                        <el-input v-model="form.url" type="textarea" :rows="3" placeholder="请输入内容"></el-input>
                    </el-form-item>
                    <el-form-item label="图片上传">
                        <el-button @click="uploadDialogVisible = true">上传图片</el-button>
                        <el-dialog
                            append-to-body
                            title="上传图片"
                            :visible.sync="uploadDialogVisible"
                            width="30%"
                            :before-close="handleClose"
                        >
                            <span>
                                <el-upload
                                    ref="upload"
                                    class="upload-demo"
                                    action="/product_api/file/upload"
                                    :limit="1"
                                    :on-preview="handlePreview"
                                    :on-remove="handleRemove"
                                    :file-list="fileList"
                                    :auto-upload="false"
                                    :on-success="uploadHandler"
                                >
                                    <el-button slot="trigger" size="small" type="primary" round>添加图片</el-button>
                                    <el-button
                                        style="margin-left: 10px;"
                                        size="small"
                                        type="success"
                                        round
                                        @click="submitUpload"
                                    >
                                        上传
                                        <i class="el-icon-upload el-icon--right"></i>
                                    </el-button>
                                    <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb</div>
                                </el-upload>
                            </span>
                            <span slot="footer" class="dialog-footer">
                                <el-button @click="uploadDialogVisible = false">取 消</el-button>
                                <el-button type="primary" @click="uploadDialogVisible = false">确 定</el-button>
                            </span>
                        </el-dialog>
                        <div class="upload" v-if="uploadImgData.data">
                            <img :src="uploadImgData.data" alt />
                        </div>
                    </el-form-item>
                    <el-form-item label="内容">
                        <vue-ueditor-wrap v-model="formDesc" :config="myConfig"></vue-ueditor-wrap>
                    </el-form-item>
                </el-form>
            </span>
            <span slot="footer" class="dialog-footer">
                <el-button @click="addDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="addDialogVisibleHandler">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
export default {
    name: "Content",
    data() {
        return {
            contentData: [],
            addContent: "",
            addBtnShow: false,
            addDialogVisible: false,
            uploadDialogVisible: false,
            form: {},
            fileList: [],
            uploadImgData: {},
            formDesc: "",
            myConfig: {
                autoHeightEnabled: false,
                initialFrameHeight: 240,
                initialFrameWidth: "100%",
                UEDITOR_HOME_URL: "/UEditor/"
            },
            currentCategory: {}
        };
    },
    methods: {
        http(item) {
            this.$api
                .getSelectTbContentAllByCategoryId({ categoryId: item.id })
                .then(res => {
                    if (res.data.status === 200) {
                        this.contentData = res.data.data.result;
                    } else {
                        this.$message({
                            type: "info",
                            message: "暂无数据"
                        });
                        this.contentData = [];
                    }
                });
        },
        getContent(item) {
            if (!item.isParent) {
                this.addBtnShow = true;
                this.addContent = item.name;
                this.currentCategory = item;
                this.http(item);
            } else {
                this.addBtnShow = false;
                this.addContent = "";
            }
        },
        handleClose() {
            this.addDialogVisible = false;
        },
        // 上传图片
        uploadHandler(response, file, fileList) {
            if (response.status === 200) {
                this.uploadImgData = response;
            }
        },
        submitUpload() {
            // 上传的触发事件
            this.$refs.upload.submit();
        },
        handlePreview() {},
        handleRemove() {},
        // 添加数据
        addDialogVisibleHandler() {
            this.$api
                .getInsertTbContent({
                    categoryId: this.currentCategory.id,
                    title: this.form.title,
                    subTitle: this.form.subTitle,
                    titleDesc: this.form.titleDesc,
                    url: this.form.url,
                    pic: this.uploadImgData.data,
                    content: this.formDesc
                })
                .then(res => {
                    if (res.data.status === 200) {
                        this.addDialogVisible = false;
                        // 刷新
                        this.http(this.currentCategory);
                    } else {
                        this.$message({
                            type: "error",
                            message: "添加失败"
                        });
                    }
                });
        },
        /* 删除 */
        handleDelete(index, row) {
            this.$confirm("此操作将永久删除该数据, 是否继续?", "提示", {
                confirmButtonText: "确定",
                cancelButtonText: "取消",
                type: "warning"
            })
                .then(() => {
                    this.$api
                        .getDeleteContentByIds({ ids: row.id })
                        .then(res => {
                            if (res.data.status === 200) {
                                this.$message({
                                    type: "success",
                                    message: "删除成功"
                                });
                                // 刷新
                                this.http(this.currentCategory);
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
                        message: "取消删除"
                    });
                });
        }
    }
};
</script>
<style scoped>
.upload img {
    width: 200px;
}
</style>

