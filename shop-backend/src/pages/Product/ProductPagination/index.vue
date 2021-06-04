<template>
    <div>
        <el-pagination
            class="pagination"
            background
            layout="prev, pager, next"
            :total="1000"
            :current-page="currentPage"
            @current-change="handleCurrentChange"
        ></el-pagination>
    </div>
</template>
<script>
export default {
    name: "pagination",
    data() {
        return {
            tableData: [],
            currentPage: 1
        };
    },
    methods: {
        handleCurrentChange(currentPage) {
            this.$api.prodcutList({ page: currentPage }).then(res => {
                res.data.data.result.map((element, index) => {
                    element.updated = element.updated.split(".")[0];
                    element.created = element.created.split(".")[0];
                });
                this.tableData = res.data.data.result;
                this.$emit("onsend", this.tableData);
            });
        }
    }
};
</script>
<style scoped>
.pagination {
    margin: 20px 0;
}
</style>
