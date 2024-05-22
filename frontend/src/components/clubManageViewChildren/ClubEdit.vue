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
    margin: 10px 0;
}

.previewBox {
    width: 100%;
    padding: 0 20px;
    box-sizing: border-box;
}


.editBox {
    width: 100%;
    padding: 20px;
    box-sizing: border-box;
}

.editBox:hover {
    background-color: #f5f5f5;
}

.editBox.selected {
    background-color: #f5f5f5;
    border-radius: 4px;
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
    height: 60px;
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
    transition: all 0.3s ease;
    /* 添加过渡动画效果 */
}

.affixExpanded {
    height: 180px;
    background: #f5f5f5;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.6);
    transition: all 0.3s ease;
    /* 添加过渡动画效果 */
}

.affix-item {
    margin: 10px 0;
    width: 40px;
    height: 40px;
    background: #ffffff;
    border-radius: 50%;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
    display: flex;
    justify-content: center;
    align-items: center;
}

.affixExpanded .affix-item {
    background-color: #f5f5f5;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.6);
}


.affixExpanded .affix-item:hover,
.affix-item:hover {
    background-color: #e6e6e6;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.6);
    cursor: pointer;
}

.ghost {
    border: solid 1px rgb(19, 41, 239);
}

.chosenClass {
    background-color: #f1f1f1;
}
</style>

<template>
    <div class="introductionContainer">
        <div class="affix" :class="{ affixExpanded: isPanelExpanded }">
            <div class="affix-item" @click="togglePanel">
                <t-popup content="编辑" placement="left" show-arrow>
                    <t-icon v-show="!isPanelExpanded" name="edit"></t-icon>
                </t-popup>
                <t-popup content="保存" placement="left" show-arrow>
                    <t-icon v-show="isPanelExpanded" name="save"></t-icon>
                </t-popup>
            </div>
            <div class="affix-item" v-show="isPanelExpanded" @click="addNewBlock">
                <t-popup content="插入新块" placement="left" show-arrow>
                    <t-icon name="article"></t-icon>
                </t-popup>
            </div>
            <div class="affix-item" v-show="isPanelExpanded" @click="deleteSelectedBlock">
                <t-popup content="删除选中块" placement="left" show-arrow>
                    <t-icon name="delete"></t-icon>
                </t-popup>
            </div>
        </div>
        <div class="introductionBox">
            <draggable-component :list="clubInfo" ghost-class="ghost" chosen-class="chosenClass" animation="300"
                @start="onStart" @end="onEnd" :disabled="!isEditing" item-key="id">
                <template #item="{ element, index }">
                    <div class="item-container">
                        <div class="previewBox" v-if="!isEditing">
                            <div class="title">
                                <t-divider align="center" :content="element.title"></t-divider>
                            </div>
                            <div class="content" v-html="element.content"></div>
                        </div>
                        <div class="editBox" v-else @click="selectItem(index)">
                            <t-input @pointerdown.stop.native style="margin: 10px 0;" v-model="element.title" />
                            <ckeditor :editor="editor" v-model="element.content" :config="editorConfig"></ckeditor>
                        </div>
                    </div>
                </template>
            </draggable-component>
        </div>
    </div>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import eventEmitter from '@/utils/eventEmitter';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { ref } from 'vue';
import { useRoute } from 'vue-router';
import draggableComponent from 'vuedraggable';

const route = useRoute(); // 获取当前路由参数

const clubInfo = ref([])

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

const editor = ClassicEditor
const editorConfig = {
    // 配置编辑器
    toolbar: {
        items: [
            'heading',
            '|',
            'bold',
            'italic',
            '|',
            'bulletedList',
            'numberedList',
            '|',
            'link',
            'uploadImage',
            '|',
            'undo',
            'redo'
        ]
    },
    heading: {
        options: [
            { model: 'paragraph', title: '正文', class: 'ck-heading_paragraph' },
            { model: 'heading2', view: 'h2', title: '二级标题', class: 'ck-heading_heading2' },
            { model: 'heading3', view: 'h3', title: '三级标题', class: 'ck-heading_heading3' },
        ]
    }
}

const addNewBlock = () => {
    // 添加新块
    clubInfo.value.push({
        title: '',
        content: ''
    })
}

const selectedIndex = ref(-1);

const selectItem = (index) => {
    // Remove the 'selected' class from all item containers
    document.querySelectorAll('.editBox').forEach(el => el.classList.remove('selected'));

    // Add the 'selected' class to the clicked item container
    const editBox = document.querySelectorAll('.editBox')[index];
    editBox.classList.add('selected');

    // Update the selectedIndex
    selectedIndex.value = index;
}

const deleteSelectedBlock = () => {
    // 删除选中块
    const selectedIndex = clubInfo.value.findIndex((item, index) => {
        const editBox = document.querySelectorAll('.editBox')[index];
        return editBox.classList.contains('selected');
    });
    // If a selected item container is found, remove it from the clubInfo array
    if (selectedIndex !== -1) {
        clubInfo.value.splice(selectedIndex, 1);
    }
}

const onStart = () => {
    console.log('dragging started');
}

const onEnd = () => {
    console.log('dragging ended');

}
</script>