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
    padding: 0;
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
            <img src="@/assets/数字百团logo.png"
                style="width: 100px; height: auto; margin: 5px auto 0 auto; display: block;" alt="logo">
            <t-divider style="margin: 0;" />
            <div class="sideBar">
                <t-menu :value="routerNames" @change="onChange">
                    <t-menu-item v-for="(item, index) in routerlist" :value="item.value" :content="item.label">
                    </t-menu-item>
                </t-menu>
            </div>
            <t-divider style="margin: 0;" />
            <div v-if="checkedShow">
                <div style="margin:5px 0 5px 5px; font-size: 16px;">搜索：</div>
                <t-input v-model="searchValue" placeholder="输入社团名称" style="width: 90%; margin: 8px;"
                    :onEnter="onSearch">
                    <template #suffix-icon>
                        <t-icon name="search" @click="onSearch" />
                    </template>
                </t-input>
                <div style="margin:5px 0 5px 5px; font-size: 16px;">筛选：</div>
                <div class="checkBoxes">
                    <t-checkbox v-for="item in enumList" :key="item.code" :label="item.name" default-checked
                        icon="rectangle" @change="(checked) => checkChange(checked, item.code)" />
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
const searchValue = ref('')
const onSearch = () => {
    eventEmitter.emit(TypeEventEnum.search, searchValue.value)
}

// 复选框被选中后更改clubsView中的社团展示
const checkChange = (checked, type) => {
    if (checked) {
        eventEmitter.emit(TypeEventEnum.addType, type);
    } else {
        eventEmitter.emit(TypeEventEnum.removeType, type);
    }
    searchValue.value = '';
}

// 点击轮播图图片跳转到社团详情页
const go2ClubDetail = (clubId) => {
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setParentRoute, { owner: 'club', value: clubId })
    eventEmitter.emit(StoreEventEnum.set, StoreEnum.setClubId, clubId)
    eventEmitter.emit(RouterEventEnum.push, `/club/${clubId}/`)
}

// 社团类型类别枚举
const enumList = ref([])

onMounted(() => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getTopTenClubs)
    eventEmitter.emit(APIEventEnum.request, APIEnum.getDepartmentList)
    if (localStorage.getItem('enumList')) {
        enumList.value = JSON.parse(localStorage.getItem('enumList')).clubCategories
    } else {
        eventEmitter.emit(APIEventEnum.request, APIEnum.getEnumList)
    }

    eventEmitter.on(APIEventEnum.getEnumListSuccess, 'getEnumListSuccess', (data) => {
        enumList.value = data.clubCategories
    })
    eventEmitter.on(APIEventEnum.getTopTenClubsSuccess, 'getTopTenClubsSuccess', (data) => {
        topTenClubs.value.push(...data)
    })
    eventEmitter.on(APIEventEnum.getDepartmentListSuccess, 'getDepartmentListSuccess', (data) => {
        console.log(data)
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getTopTenClubsSuccess, 'getTopTenClubsSuccess')
    eventEmitter.off(APIEventEnum.getEnumListSuccess, 'getEnumListSuccess')
})
</script>