<template>
    <div class="step-container">
        <div class="step-items">
            <div v-for="(item, index) in count" :key="index" class="step-item" @click="togglePopup(index)">
                <div class="step-box">
                    <div v-if="index !== 0" class="step-line" :class="getlineClass(index)"></div>
                    <t-popover :visible="showPopup && popupIndex === index" placement="top" theme="light"
                        :content="steps[index].idea">
                        <div class="step-circle" :class="getCircleClass(index)">
                            <div class="inner-circle" :class="getInnerCircleClass(index)"></div>
                        </div>
                    </t-popover>
                </div>
                <div class="step-title">{{ getTitle(index) }}</div>
                <div class="step-content">{{ steps[index].content }}</div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const props = defineProps({
    count: {
        type: Number,
        required: true
    },
    current: {
        type: Number,
        default: 0,
    },
    currentStatus: {
        type: String,
        default: 'process',
        validator: (value) => ['process', 'finish', 'error'].includes(value)
    },
    steps: {
        type: Array,
        required: true
    }
})

const showPopup = ref(false)
const popupIndex = ref(null)

const getTitle = (index) => {
    if (index < props.current) {
        return '完成'
    } else if (index === props.current) {
        return '当前步骤'
    } else {
        return '未完成'
    }
}

const getlineClass = (index) => {
    if (index < props.current) {
        return 'step-line--finished'
    } else if (index === props.current) {
        return `step-line--finished--${props.currentStatus}`
    } else if (index == props.current + 1) {
        return `step-line--${props.currentStatus}--default`
    } else {
        return 'step-line--default'
    }
}

const getCircleClass = (index) => {
    if (index < props.current) {
        return 'step-circle--finished'
    } else if (index === props.current) {
        return `step-circle--${props.currentStatus}`
    } else {
        return 'step-circle--default'
    }
}

const getInnerCircleClass = (index) => {
    if (index < props.current) {
        return 'inner-circle--finished'
    } else if (index === props.current && props.currentStatus === 'process') {
        return 'inner-circle--process'
    } else if (index === props.current && props.currentStatus === 'error') {
        return 'inner-circle--error'
    } else if (index === props.current && props.currentStatus === 'finish') {
        return 'inner-circle--finished'
    }
    return ''
}

const togglePopup = (index) => {
    if (props.steps[index].idea) {
        showPopup.value = !showPopup.value
        popupIndex.value = index
    }
}
</script>

<style scoped>
.step-container {
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    width: 100%;
}

.step-items {
    display: flex;
    justify-content: space-around;
    align-items: center;
    width: 100%;
    position: relative;
    z-index: 2;
}

.step-item {
    display: flex;
    flex-direction: column;
    align-items: center;
    cursor: pointer;
    position: relative;
    width: 100%;
}

.step-box {
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    width: 100%;
}

.step-line {
    width: 100%;
    height: 2px;
    position: absolute;
    top: 50%;
    transform: translate(-50%, -50%);
    z-index: 0;
}

.step-line--default {
    background: #e5e5e5;
}

.step-line--process {
    background: #2196f3
}

.step-line--finished {
    background: #4caf50;
}

.step-line--finished--default {
    background: linear-gradient(90deg, #4caf50, #e5e5e5);
}

.step-line--error--default {
    background: linear-gradient(90deg, #ff9800, #e5e5e5);
}

.step-line--process--default {
    background: linear-gradient(90deg, #2196f3, #e5e5e5);
}

.step-line--finished--error {
    background: linear-gradient(90deg, #4caf50, #ff9800);
}

.step-line--finished--process {
    background: linear-gradient(90deg, #4caf50, #2196f3);
}

.step-line--finished--finished {
    background: #4caf50;
}

.step-circle {
    width: 30px;
    height: 30px;
    border-radius: 50%;
    background-color: #e5e5e5;
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
    z-index: 5;
}

.step-circle--default {
    border: #e5e5e5 2px solid;
}

.step-circle--process {
    border: #2196f3 2px solid;
}

.step-circle--finished {
    border: #4caf50 2px solid;
}

.step-circle--error {
    border: #ff9800 2px solid;
}

.inner-circle {
    width: 20px;
    height: 20px;
    border-radius: 50%;
}

.inner-circle--process {
    background-color: #2196f3;
}

.inner-circle--finished {
    background-color: #4caf50;
    display: flex;
    justify-content: center;
    align-items: center;
}

.inner-circle--finished::after {
    content: '✓';
    color: white;
    font-size: 14px;
}

.inner-circle--error {
    background-color: #ff9800;
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
}

.inner-circle--error::after {
    content: '✖';
    color: white;
    font-size: 14px;
}

.step-title {
    font-size: 14px;
    font-weight: bold;
    margin-top: 8px;
    text-align: center;
}

.step-content {
    font-size: 12px;
    margin-top: 4px;
    text-align: center;
}
</style>