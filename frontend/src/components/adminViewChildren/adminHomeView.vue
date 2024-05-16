<style scoped>
/* 固定内容选项卡 */
.fixedTabBar {
    position: absolute;
    top: 250px;
    left: 10%;
    z-index: 998;
}

.searchBox {
    cursor: pointer;
}

.searchBox:hover {
    color: #002ead;
}

/* 主体内容样式 */
.mainContainer {
    margin-top: 50px;
}

/* 轮播图 */
.swiperContainer {
    width: 80%;
    margin: auto;
    height: 200px;
    background-color: #f5f5f5;
    justify-content: center;
    align-items: center;
    display: flex;
}

.swiper {
    min-width: 50%;
    height: 100%;
}

/* 内容容器 */
.contentContainer {
    width: 80%;
    margin-top: 50px;
    margin-left: auto;
    margin-right: auto;
    min-height: 400px;
    background-color: #ffffff;
    display: flex;
}

/* 用来占位置 */
.fillBox {
    width: 20%;
}

/* 正在的内容展示 */
.showBox {
    width: 80%;
}
</style>

<template>
    <!-- 固定内容选项卡 -->
    <div class="fixedTabBar" id="tabBar">
        <t-tabs style="width: 80%;" :value="routerNames" :list="routerlist" :space-evenly="false" @change="onChange" />
        <div style="width: 80%;">
            <t-input type="search">
                <template #suffixIcon>
                    <div class="searchBox" @click="onSearch">
                        <SearchIcon />
                    </div>
                </template>
            </t-input>
            <div v-if="checkedShow">
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

    <!-- 主体内容 -->
    <div class="mainContainer">
        <!-- 年度最佳社团轮播图 -->
        <div class="swiperContainer" id="swiperContainer">
            <div class="swiper">
                <t-swiper :autoplay="true" :navigation="{ showControls: true, paginationPosition: 'bottom-right' }"
                    height="200" @change="handleChange">
                    <t-swiper-item v-for="(item, index) in swiperList" :key="index" style="height: 200px">
                        <img :src="item" class="img" />
                    </t-swiper-item>
                </t-swiper>
            </div>
        </div>
        <div class="contentContainer">
            <div class="fillBox"></div>
            <div class="showBox">
                <RouterView />
            </div>
        </div>
    </div>


</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { SearchIcon } from 'tdesign-icons-vue-next';
import eventEmitter from '../../utils/eventEmitter';
import { RouterEventEnum, StoreEnum, StoreEventEnum, TypeEventEnum } from '@/Enum';
import store from '@/store';

// 轮播图
const imageCdn = 'https://tdesign.gtimg.com/mobile/demos';
const swiperList = [
    `${imageCdn}/swiper1.png`,
    `${imageCdn}/swiper2.png`,
    `${imageCdn}/swiper1.png`,
];

const handleChange = (index, context) => {
};

// 选项卡

// 控制contentContainer以及复选框中的页面展示
const parentRoute = store.state.parentRoute.admin
const routerNames = ref(store.state.routeTabs.adminClubTabs);
const checkedShow = ref(routerNames.value === 'adminClub')
const routerlist = [
    {
        label: '社团情况',
        value: 'adminClub',
    },
    {
        label: '最新动态',
        value: 'adminNews',
    },
]

const onChange = (value) => {
    const selfRoute = parentRoute + 'admin/' + value;
    routerNames.value = value
    eventEmitter.emit(RouterEventEnum.push, selfRoute)
    checkedShow.value = !checkedShow.value
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setRouteTabs, { owner: 'adminClubTabs', value: value })
}

// 控制选项卡的吸顶
onMounted(() => {
    const swiperContainer = document.getElementById('swiperContainer')
    const tabBar = document.getElementById('tabBar')
});

const handleScroll = () => {
    const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
    const tabBarrOffsetTop = swiperContainer.offsetHeight;
    if (scrollTop > tabBarrOffsetTop) {
        tabBar.style.position = 'fixed'
        tabBar.style.top = '50px'
    } else {
        tabBar.style.position = 'absolute'
        tabBar.style.top = '250px'
    }
}

window.addEventListener('scroll', handleScroll);

onUnmounted(() => {
    window.removeEventListener('scroll', handleScroll);
});

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
</script>