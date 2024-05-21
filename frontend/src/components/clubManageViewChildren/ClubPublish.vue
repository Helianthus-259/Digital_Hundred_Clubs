<template>
    <div>
        发布活动/通知
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
const route = useRoute();
console.log(route.params.cid);
</script>