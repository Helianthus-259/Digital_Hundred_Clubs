<style scoped>
.introductionContainer {
    width: 100%;
    height: 680px;
    background: #ffffff;
    overflow-y: auto;
}

/*滚动条样式*/
.introductionContainer::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.introductionContainer::-webkit-scrollbar-track {
    background-color: #f1f1f1;
    /* 滚动条轨道背景色 */
}

.introductionContainer::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.introductionContainer::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

/* 介绍栏 */
.introductionBox {
    width: 80%;
    margin: 0 auto;
    display: flex;
    flex-wrap: wrap;
}

.item-container {
    width: 100%;
    display: flex;
    flex-direction: column;
}

.editing .item-container {
    flex-direction: row;
}

.previewBox {
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
}

.editing .previewBox {
    width: 50%;
}

.editing .editBox {
    width: 50%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
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
    font-size: 16px;
}

.content :deep(img) {
    width: 80%;
    margin-left: auto;
    margin-right: auto;
    display: block;
    margin-bottom: 10px;
}

.affix {
    position: fixed;
    top: 100px;
    right: 50px;
    width: 50px;
    min-height: 50px;
    background: #ffffff;
    border-radius: 25px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    border: 1px solid #e6e6e6;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    cursor: pointer;
    z-index: 999;
}

.affix-item {
    margin: 10px 0;
    width: 40px;
    height: 40px;
    background: #ffffff;
    border-radius: 50%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>

<template>
    <div class="introductionContainer">
        <div class="affix">
            <div class="affix-item" @click="togglePanel">
                <t-popup content="编辑" placement="left" show-arrow>
                    <t-icon v-show="!isPanelExpanded" name="edit"></t-icon>
                </t-popup>
                <t-popup content="保存" placement="left" show-arrow>
                    <t-icon v-show="isPanelExpanded" name="save"></t-icon>
                </t-popup>
            </div>
            <div class="affix-item" v-show="isPanelExpanded">
                <t-popup content="插入新块" placement="left" show-arrow>
                    <t-icon name="article"></t-icon>
                </t-popup>
            </div>
            <div class="affix-item" v-show="isPanelExpanded">
                <t-popup content="删除选中块" placement="left" show-arrow>
                    <t-icon name="delete"></t-icon>
                </t-popup>
            </div>
        </div>
        <div class="introductionBox" :class="{ editing: isEditing }">
            <div v-for="(item, index) in clubInfo" :key="index" class="item-container">
                <div class="previewBox">
                    <div class="title">
                        <t-divider align="center" :content="item.title"></t-divider>
                    </div>
                    <div class="content" v-html="item.content"></div>
                </div>
                <div class="editBox" v-show="isEditing">
                    <t-input style="margin: 10px 0;" v-model="item.title" />
                    <t-textarea autosize v-model="item.content" />
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import eventEmitter from '@/utils/eventEmitter';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
const route = useRoute(); // 获取当前路由参数

const clubInfo = ref(null)

// 调用后端接口，获取社团信息
eventEmitter.emit(APIEventEnum.request, APIEnum.getClubIntroduction, { clubId: route.params.cid })

eventEmitter.on(APIEventEnum.getClubIntroductionSuccess, (data) => {
    clubInfo.value = data
})

const isPanelExpanded = ref(false)
const isEditing = ref(false)
const togglePanel = () => {
    isPanelExpanded.value = !isPanelExpanded.value
    isEditing.value = !isEditing.value
}
</script>