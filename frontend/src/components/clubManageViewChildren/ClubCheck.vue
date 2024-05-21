<template>
    <div>
        入社审核
    </div>
</template>

<script setup>
import { useRoute } from 'vue-router';
const route = useRoute();
console.log(route.params.cid);
</script>