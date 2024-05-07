<style scoped>
.dialog {
    width: 60vw;
    border: none;
    box-sizing: border-box;
}

.dialog-header {
    display: flex;
    justify-content: center;
    align-items: center;
}

.dialog-body {
    height: calc(100vh - 300px);
    overflow-y: auto;
}

/* 滚动条样式 */
.dialog-body::-webkit-scrollbar {
    width: 8px;
    /* 滚动条宽度 */
}

.dialog-body::-webkit-scrollbar-track {
    background-color: #f1f1f1;
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
}
</style>

<template>
    <dialog ref="dialogRef" class="dialog" :open="isOpen" @close="closeDialog">
        <div class="dialog-header">
            <slot name="header"></slot>
        </div>
        <t-divider></t-divider>
        <div class="dialog-body">
            <slot></slot>
        </div>
        <t-divider></t-divider>
        <div class="dialog-footer">
            <slot name="footer"></slot>
        </div>
    </dialog>
</template>

<script setup>
import { ref } from 'vue';

const isOpen = ref(false);
const dialogRef = ref(null);

const openDialog = () => {
    isOpen.value = true;
    dialogRef.value.showModal();
};

const closeDialog = () => {
    isOpen.value = false;
    dialogRef.value.close();
};

defineExpose({ openDialog, closeDialog });
</script>