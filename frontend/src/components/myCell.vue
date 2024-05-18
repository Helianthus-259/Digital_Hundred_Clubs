<style scoped>
.cell {
    cursor: pointer;
    height: 42px;
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>

<template>
    <div v-for="(item, index) in items" :key="index">
        <div class="cell" :style="{
            backgroundColor: index === selectedIndex && panelIndex === currentPanelIndex ? selectedBgColor : index === hoverIndex ? hoverBgColor : defaultBgColor,
        }" @mouseover="hoverIndex = index" @mouseleave="hoverIndex = null" @click="handleCellClick(index, item)">
            <slot :item="item">{{ item.clubName }}</slot>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({
    items: {
        type: Array,
        required: true,
    },
    defaultBgColor: {
        type: String,
        default: '#fff',
    },
    hoverBgColor: {
        type: String,
        default: '#f5f5f5',
    },
    selectedBgColor: {
        type: String,
        default: '#e0e0e0',
    },
    panelIndex: {
        type: Number,
        default: -1,
    },
    currentPanelIndex: {
        type: Number,
        default: -1,
    },
    onClick: {
        type: Function,
        default: () => { },
    },
})

const hoverIndex = ref(null)
const selectedIndex = ref(null)

const handleCellClick = (index, item) => {
    selectedIndex.value = index
    const pd = props.panelIndex
    props.onClick(item, pd)
}
</script>