<template>
  <RouterView />
</template>

<script setup>
import { RouterView } from 'vue-router'
import store from './store'
import { onUnmounted } from 'vue';
import eventEmitter from './utils/eventEmitter';
import { APIEventEnum, RouterEventEnum, StoreEventEnum } from './Enum';

if (sessionStorage.getItem('store')) {
  store.replaceState(
    Object.assign({}, store.state, JSON.parse(sessionStorage.getItem('store')))
  )
}

window.addEventListener('beforeunload', (event) => {
  sessionStorage.setItem('store', JSON.stringify(store.state))
})

onUnmounted(() => {
  eventEmitter.off(StoreEventEnum.set, 'set')
  eventEmitter.off(RouterEventEnum.push, 'push')
  eventEmitter.off(RouterEventEnum.go, 'go')
  eventEmitter.off(APIEventEnum.request, 'request')
})
</script>