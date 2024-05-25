<style scoped></style>

<template>
    <t-row>

    </t-row>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import eventEmitter from '@/utils/eventEmitter';
import { useRoute } from 'vue-router';

const route = useRoute();
const clubId = route.params.cid;

// 获取社团信息
eventEmitter.emit(APIEventEnum.request, APIEnum.getClubEvaluateInfo, { clubId })

eventEmitter.on(APIEventEnum.getClubEvaluateInfoSuccess, (data) => {
    console.log(data)
})
</script>