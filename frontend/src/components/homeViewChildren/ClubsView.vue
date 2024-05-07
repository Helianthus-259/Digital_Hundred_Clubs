<style scoped>
.mainContainer {
    width: 100%;
    height: 100%;
}
</style>

<template>
    <div class="mainContainer">
        <Waterfall :list="clubsView">
            <template #item="{ item, url, index }">
                <div class="card" @click="handleRouter(item.clubID)">
                    <div style="cursor: pointer;">
                        <LazyImg :url="url" />
                    </div>
                    <p class="text">{{ item.name }}</p>
                    <p class="text">{{ item.introduction.substring(0, 10) + "..." }}</p>
                </div>
            </template>
        </Waterfall>
    </div>
</template>

<script setup>
import { LazyImg, Waterfall } from 'vue-waterfall-plugin-next'
import 'vue-waterfall-plugin-next/dist/style.css'
import { ref } from 'vue'
import eventEmitter from '@/utils/eventEmitter';
import { APIEnum, APIEventEnum, RouterEventEnum, StoreEnum, StoreEventEnum, TypeEventEnum } from '@/Enum';
import store from '@/store';

const clubs = ref([])

// 实际展示的数据
const clubsView = ref([])

// 在created生命周期中调用接口
if (store.state.clubsData.length > 0) {
    clubs.value = store.state.clubsData
    clubsView.value = clubs.value
} else {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getClubsInfo, {})
}

const handleRouter = (clubID) => {
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setParentRoute, { owner: 'club', value: clubID })
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setClubID, clubID)
    eventEmitter.emit(RouterEventEnum.push, `/club/${clubID}/`)
}

eventEmitter.on(APIEventEnum.getClubsInfoSuccess, (data) => {
    clubs.value = data
    clubsView.value = clubs.value
})

eventEmitter.on(TypeEventEnum.addType, (type) => {
    const add = clubs.value.filter(item => item.type === type)
    clubsView.value.push(...add)
})

eventEmitter.on(TypeEventEnum.removeType, (type) => {
    const remove = clubs.value.filter(item => item.type === type)
    clubsView.value = clubsView.value.filter(item => !remove.includes(item))
})


</script>