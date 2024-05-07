<template>
  <RouterView />
</template>

<script setup>
import { RouterView } from 'vue-router'
import store from './store'

if (sessionStorage.getItem('store')) {
  store.replaceState(
    Object.assign({}, store.state, JSON.parse(sessionStorage.getItem('store')))
  )
}

window.addEventListener('beforeunload', (event) => {
  sessionStorage.setItem('store', JSON.stringify(store.state))
})
</script>