<style scoped>
/* 整体布局 */
.mainBox {
    width: 80%;
    margin: 0 auto;
    display: flex;
    height: 670px;
    justify-content: center;
    align-items: center;
}

/* 内容布局 */
.contentBox {
    width: 100%;
    display: flex;
    height: 650px;
}

/* 锚点栏 */
.navigationBox {
    width: 20%;
    background-color: #f5f5f5;
    border-right: 1px solid #e5e5e5;
    padding: 20px;
}

/* 介绍栏 */
.introductionBox {
    width: 80%;
    overflow-y: auto;
    padding: 20px;
}

/*滚动条样式*/
.introductionBox::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.introductionBox::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    /* 滚动条轨道背景色 */
}

.introductionBox::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.lintroductionBox::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

.section {
    margin-bottom: 20px;
    padding-top: 0;
}

.title {
    width: 80%;
    margin-left: auto;
    margin-right: auto;
}

.t-divider {
    font-size: 24px;
    color: #0eb3ff;
}

.content :deep(p) {
    text-indent: 2em;
}

.content :deep(img) {
    width: 80%;
    margin-left: auto;
    margin-right: auto;
    display: block;
    margin-bottom: 10px;
}
</style>

<template>
    <div class="mainBox">
        <div class="contentBox">
            <div class="navigationBox">
                <t-menu style="width: 100%;" :value="sideBarIndex" @change="onSideBarChange">
                    <t-menu-item v-for="(item, index) in clubIntroduction" :key="index" :value="index"
                        :content="item.label" />
                </t-menu>
            </div>
            <div class="introductionBox" ref="wrapper" @scroll="onScroll">
                <div v-for="(item, index) in clubIntroduction" :key="index" class="section">
                    <div class="title">
                        <t-divider align="center" :content="item.title"></t-divider>
                    </div>
                    <div class="content" v-html="item.content"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { ref, reactive, onUpdated } from 'vue';

const clubIntroduction = reactive([])

eventEmitter.emit(APIEventEnum.request, APIEnum.getClubIntroduction, { clubId: store.state.clubId })

eventEmitter.on(APIEventEnum.getClubIntroductionSuccess, (data) => {
    for (const element of data) {
        clubIntroduction.push({
            label: element.title,
            title: element.title,
            content: element.content,
        })
    }
})


// 侧边栏锚点控制
const sideBarIndex = ref(0)
const onSideBarChange = (index) => {
    sideBarIndex.value = index
    moveToActiveSideBar(index)
}
const wrapper = ref(null);
const offsetTopList = reactive([])

const getOffsetTopList = () => {
    if (wrapper.value) {
        const $title = wrapper.value.querySelectorAll('.title')
        $title.forEach((item) => {
            offsetTopList.push(item.offsetTop)
        })
    }
}

const moveToActiveSideBar = (index) => {
    if (wrapper.value) {
        wrapper.value.scrollTop = offsetTopList[index] - offsetTopList[0]
    }
}

// 监听图片加载完成事件，等图片加载完后再获取偏移量，锚点不会出现bug
const handleImageLoad = () => {
    // 移除所有事件监听器
    const imgs = wrapper.value.querySelectorAll('img')
    imgs.forEach(img => {
        img.removeEventListener('load', handleImageLoad)
    });

    // 图片加载完成后获取偏移量
    getOffsetTopList()
    moveToActiveSideBar(sideBarIndex.value)
}

onUpdated(() => {
    const imgs = wrapper.value.querySelectorAll('img');
    imgs.forEach(img => {
        // 如果图片已经加载完成，直接执行回调函数
        if (img.complete) {
            handleImageLoad()
        } else {
            // 否则，监听图片加载完成事件
            img.addEventListener('load', handleImageLoad);
        }
    })
})

const onScroll = (e) => {
    const threshold = offsetTopList[0]; // 下一个标题与顶部的距离
    const { scrollTop } = e.target
    if (scrollTop < threshold) {
        sideBarIndex.value = 0
        return
    }
    const index = offsetTopList.findIndex((top) => top > scrollTop && top - scrollTop <= threshold)

    if (index > -1) {
        sideBarIndex.value = index
    }
}
</script>