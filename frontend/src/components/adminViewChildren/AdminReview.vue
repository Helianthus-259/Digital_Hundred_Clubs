<template>
  <div class="contentContainer">
    <div class="contentBox">
      <t-layout>
        <t-header>
          <t-head-menu theme="light" :default-value=tabName style="margin-right: 40px" @change="changeHandler">
            <template #logo>
              <img height="28" src="https://tdesign.gtimg.com/site/baseLogo-light.png" alt="logo" />
            </template>
            <t-menu-item value="clubAnnualReview"> 社团年审 </t-menu-item>
            <t-menu-item value="clubAwardsReview"> 社团评优 </t-menu-item>
            <t-menu-item value="backboneAwardsReview"> 骨干评优 </t-menu-item>
          </t-head-menu>
        </t-header>
        <t-layout>
          <RouterView/>
        </t-layout>
      </t-layout>
    </div>
  </div>
</template>

<script setup>
import {ref} from "vue";
import store from "@/store/index.js";
import eventEmitter from "@/utils/eventEmitter.js";
import {RouterEventEnum} from "@/Enum/index.js";

const tabName = ref("clubAnnualReview");

const changeHandler=(value)=>{
  if(value === 'clubAnnualReview'){
    const selfRoute = store.state.parentRoute.review + store.state.routeTabs.clubAnnualReview
    console.log(selfRoute)
    eventEmitter.emit(RouterEventEnum.push, selfRoute)
  }else if(value === 'clubAwardsReview'){
    const selfRoute = store.state.parentRoute.review + store.state.routeTabs.clubAwardsReview
    console.log(selfRoute)
    eventEmitter.emit(RouterEventEnum.push, selfRoute)
  }else if(value === 'backboneAwardsReview'){
    const selfRoute = store.state.parentRoute.review + store.state.routeTabs.backboneAwardsReview
    console.log(selfRoute)
    eventEmitter.emit(RouterEventEnum.push, selfRoute)
  }
}
</script>

<style scoped>
  /* 标签栏样式 */

  /* 总内容 */
.contentContainer {
  margin-top: 50px;
}

.contentBox {
  width: 80%;
  margin: 0 auto;
  background-color: #f5f5f5;
  padding: 5px;
  box-sizing: border-box;
  border-radius: 4px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
  min-height: 600px;
}
</style>