<template>
    <div class="product">
        <Nav />
        <ProductList />
    </div>
</template>

<script>
import Nav from "../../components/Nav";
import ProductList from "./ProductList"

export default {
    name: "home",
    components: {
        Nav,
        ProductList
    }
};
</script>
<style scoped>

</style>
