<template>
    <div>
        社团编辑
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
const route = useRoute();
console.log(route.params.cid);
</script>