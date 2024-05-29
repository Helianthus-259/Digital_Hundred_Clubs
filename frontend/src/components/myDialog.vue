<style scoped>
.custom-dialog-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 1000;
}

.custom-dialog {
    background-color: white;
    border-radius: 8px;
    padding: 20px;
    box-sizing: border-box;
}

.dialog-header {
    display: flex;
    justify-content: center;
    align-items: center;
    border-bottom: 1px solid #ccc;
    padding-bottom: 10px;
    margin-bottom: 10px;
}

.dialog-body {
    max-width: calc(60vw);
    min-width: calc(40vw);
    max-height: calc(100vh - 250px);
    min-height: 250px;
    overflow-y: auto;
}

/* 滚动条样式 */
.dialog-body::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.dialog-body::-webkit-scrollbar-track {
    background-color: #ffffff;
    /* 滚动条轨道背景色 */
}

.dialog-body::-webkit-scrollbar-thumb {
    background-color: #888;
    /* 滚动条滑块颜色 */
    border-radius: 4px;
    /* 滚动条滑块圆角 */
}

.dialog-body::-webkit-scrollbar-thumb:hover {
    background-color: #555;
    /* 鼠标hover时滑块颜色 */
}

.dialog-footer {
    display: flex;
    justify-content: center;
    align-items: center;
    border-top: 1px solid #ccc;
    padding-top: 10px;
    margin-top: 10px;
}
</style>

<template>
    <div v-if="isOpen" class="custom-dialog-overlay">
        <div class="custom-dialog">
            <div class="dialog-header">
                <slot name="header"></slot>
            </div>
            <div class="dialog-body">
                <slot></slot>
            </div>
            <div class="dialog-footer">
                <slot name="footer"></slot>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const isOpen = ref(false);

const openDialog = () => {
    isOpen.value = true;
};

const closeDialog = () => {
    isOpen.value = false;
};
defineExpose({ openDialog, closeDialog });
</script>