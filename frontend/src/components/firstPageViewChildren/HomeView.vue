<style scoped>
/* 主体内容样式 */
.mainContainer {
    background: #f5f5f5;
    display: flex;
    width: 100%;
    height: 95%;
    position: fixed;
    top: 50px;
    left: 0;
    z-index: 10;
}

.sideContainer {
    width: 15%;
    background-color: #ffffff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    margin: 20px;
}

.sideBar {
    width: 100%;
    display: flex;
    justify-content: center;
    padding: 10px 0;
}

.checkBoxes {
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding: 10px 0;
}

.mainContent {
    width: 85%;
    overflow-y: auto;
    padding: 20px;
}

/*滚动条样式*/
.mainContent::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.mainContent::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    /* 滚动条轨道背景色 */
    border-radius: 4px;
}

.mainContent::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.mainContent::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

/* 轮播图 */
.swiperContainer {
    width: 95%;
    min-height: 400px;
    margin: 0 auto;
    background-color: #ffffff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    padding: 20px;
}

.swiper {
    width: 100%;
    padding: 20px 20px;
}

/* 内容容器 */
.contentContainer {
    width: 95%;
    margin-top: 20px;
    margin-left: auto;
    margin-right: auto;
    min-height: 400px;
    background-color: #ffffff;
    display: flex;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    padding: 20px;
}

/* 正在的内容展示 */
.showBox {
    width: 100%;
}
</style>

<template>
    <!-- 主体内容 -->
    <div class="mainContainer">
        <div class="sideContainer">
            <div class="sideBar">
                <t-menu :value="routerNames" @change="onChange">
                    <t-menu-item v-for="(item, index) in routerlist" :value="item.value" :content="item.label">
                    </t-menu-item>
                </t-menu>
            </div>
            <t-divider />
            <div class="">
                <div class="checkBoxes" v-if="checkedShow">
                    <t-checkbox label="学术类" default-checked icon="rectangle"
                        @change="(checked) => checkChange(checked, '学术类')" />
                    <t-checkbox label="体育类" default-checked icon="rectangle"
                        @change="(checked) => checkChange(checked, '体育类')" />
                    <t-checkbox label="艺术类" default-checked icon="rectangle"
                        @change="(checked) => checkChange(checked, '艺术类')" />
                    <t-checkbox label="公益类" default-checked icon="rectangle"
                        @change="(checked) => checkChange(checked, '公益类')" />
                    <t-checkbox label="科技类" default-checked icon="rectangle"
                        @change="(checked) => checkChange(checked, '科技类')" />
                    <t-checkbox label="其他类" default-checked icon="rectangle"
                        @change="(checked) => checkChange(checked, '其他类')" />
                </div>
            </div>
        </div>
        <div class="mainContent">
            <!-- 年度最佳社团轮播图 -->
            <div class="swiperContainer">
                <t-swiper class="swiper" type="card" :navigation="{ placement: 'outside', showSlideBtn: 'always' }">
                    <t-swiper-item v-for="(item, index) in topTenClubs" :key="index">
                        <div style="display: flex; justify-content: center;  background: #ffffff; height: 100%; ">
                            <img style="cursor: pointer;" @click="go2ClubDetail(item.clubId)" :src="item.imageUrl" />
                        </div>
                    </t-swiper-item>
                </t-swiper>
            </div>
            <div class="contentContainer">
                <div class="showBox">
                    <RouterView />
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { onMounted, onUnmounted, ref } from 'vue'
import eventEmitter from '../../utils/eventEmitter';
import { APIEnum, APIEventEnum, RouterEventEnum, StoreEnum, StoreEventEnum, TypeEventEnum } from '@/Enum';
import store from '@/store';

// 轮播图
const topTenClubs = ref([])

const handleChange = (index, context) => {
};

// 选项卡

// 控制contentContainer以及复选框中的页面展示
const parentRoute = store.state.parentRoute.home
const routerNames = ref(store.state.routeTabs.homeTabs);
const checkedShow = ref(routerNames.value === 'clubs')
const routerlist = [
    {
        label: '社团情况',
        value: 'clubs',
    },
    {
        label: '最新动态',
        value: 'news',
    },
]

const onChange = (value) => {
    const selfRoute = parentRoute + value;
    routerNames.value = value
    eventEmitter.emit(RouterEventEnum.push, selfRoute)
    checkedShow.value = !checkedShow.value
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'homeTabs', value: value })
}

// 搜索信息
const onSearch = (value) => {
    console.log(1111111111111)
    console.log(sprotChecked.value);
}

// 复选框被选中后更改clubsView中的社团展示
const checkChange = (checked, type) => {
    if (checked) {
        eventEmitter.emit(TypeEventEnum.addType, type);
    } else {
        eventEmitter.emit(TypeEventEnum.removeType, type);
    }
}

// 点击轮播图图片跳转到社团详情页
const go2ClubDetail = (clubId) => {
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setParentRoute, { owner: 'club', value: clubId })
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setClubId, clubId)
    eventEmitter.emit(RouterEventEnum.push, `/club/${clubId}/`)
}

onMounted(() => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getTopTenClubs)
    eventEmitter.on(APIEventEnum.getTopTenClubsSuccess, 'getTopTenClubsSuccess', (data) => {
        topTenClubs.value.push(...data)
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getTopTenClubsSuccess, 'getTopTenClubsSuccess')
})
</script>