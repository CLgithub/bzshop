<template>
    <div>
        <Nav />
        <ParamsList />
    </div>
</template>
<script>
import Nav from "../../components/Nav";
import ParamsList from "./ParamsList";

export default {
    name: "Params",
    components: {
        Nav,
        ParamsList
    }
};
</script>
<style scoped>
</style>

