<style scoped>
.clubContainer {
  width: 100%;
  height: 100%;
}

.lazy-img {
  width: 100%;
  height: 500px;
  background-color: #f0f0f0;
  animation: skeleton-loading 1s infinite alternate;
}

@keyframes skeleton-loading {
  0% {
    background-color: #f0f0f0;
  }

  100% {
    background-color: #e0e0e0;
  }
}
</style>

<template>
  <div class="clubContainer">
    <Waterfall :list="clubsView">
      <template #item="{ item, index }">
        <div class="card" @click="handleRouter(item.clubId)">
          <div style="cursor: pointer;">
            <img v-lazy="item.imageUrl" class="lazy-img" />
          </div>
          <p class="text">{{ item.clubName }}</p>
          <p class="text" v-html="item.clubDescription === null ? item.clubDescription :
                        item.clubDescription[0].content.slice(0, 10) + '...'"></p>
        </div>
      </template>
    </Waterfall>
  </div>
</template>

<script setup>
import { Waterfall } from 'vue-waterfall-plugin-next'
import 'vue-waterfall-plugin-next/dist/style.css'
import { onUnmounted, ref } from 'vue'
import eventEmitter from '@/utils/eventEmitter';
import { APIEnum, APIEventEnum, RouterEventEnum, StoreEnum, StoreEventEnum, TypeEventEnum } from '@/Enum';
import store from '@/store';

const clubs = ref([])

// 实际展示的数据
const clubsView = ref([])

// 在created生命周期中调用接口
if (store.state.clubsData.length > 0) {
  clubs.value = store.state.clubsData
  clubsView.value = clubs.value.filter(club => { return club.clubStatus !== null })
} else {
  eventEmitter.emit(APIEventEnum.request, APIEnum.getClubsInfo, {})
}


const handleRouter = (clubId) => {
  eventEmitter.emit(StoreEventEnum.set, StoreEnum.setParentRoute, { owner: 'club', value: clubId })
  eventEmitter.emit(StoreEventEnum.set, StoreEnum.setClubId, clubId)
  eventEmitter.emit(RouterEventEnum.push, `/club/${clubId}/`)
}

eventEmitter.on(APIEventEnum.getClubsInfoSuccess, 'getClubsInfoSuccess', (data) => {
  clubs.value = data
  clubsView.value = clubs.value.filter(club => { return club.clubStatus !== null })
})

eventEmitter.on(TypeEventEnum.addType, 'addType', (type) => {
  const add = clubs.value.filter(item => item.clubCategory === +type)
  clubsView.value.push(...add)
})

eventEmitter.on(TypeEventEnum.removeType, 'removeType', (type) => {
  const remove = clubs.value.filter(item => item.clubCategory === +type)
  clubsView.value = clubsView.value.filter(item => !remove.includes(item))
})

onUnmounted(() => {
  eventEmitter.off(APIEventEnum.getClubsInfoSuccess, 'getClubsInfoSuccess')
  eventEmitter.off(TypeEventEnum.addType, 'addType')
  eventEmitter.off(TypeEventEnum.removeType, 'removeType')
})
</script>