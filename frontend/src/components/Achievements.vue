<template>
    <div class="achievements">
        <div v-for="(achievement, index) in achievements" :key="index" class="achievement">
            <div class="info">
                <h3>{{ achievement.name }}</h3>
                <p class="award">{{ achievement.award }}</p>
                <p class="time">获奖时间: {{ achievement.awardWiningTime }}</p>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox" v-model="selectedAchievements" :value="achievement"
                        @change="changeChecked" />
                    <span class="checkmark">
                        <span class="checkmark-icon"></span>
                    </span>
                </label>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'

const props = defineProps({
    achievements: {
        type: Array,
        required: true,
    },
    onChange: {
        type: Function,
        default: () => { },
    }
})

const selectedAchievements = ref([])

const changeChecked = () => {
    props.onChange(selectedAchievements.value)
}
</script>

<style scoped>
.achievements {
    display: flex;
    flex-direction: column;
    align-items: center;
    font-family: 'Helvetica Neue', Arial, sans-serif;
}

.achievement {
    display: flex;
    justify-content: space-between;
    align-items: center;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    padding: 20px;
    margin-bottom: 20px;
    width: 600px;
    transition: box-shadow 0.3s ease;
}

.achievement:hover {
    box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.info {
    flex: 1;
}

.info h3 {
    font-size: 20px;
    color: #333;
    margin-bottom: 8px;
}

.info .award {
    font-size: 16px;
    color: #666;
    margin-bottom: 4px;
}

.info .time {
    font-size: 14px;
    color: #999;
}

.checkbox {
    margin-left: 20px;
}

.checkbox label {
    display: block;
    position: relative;
    padding-left: 30px;
    cursor: pointer;
    user-select: none;
}

.checkbox input[type="checkbox"] {
    position: absolute;
    opacity: 0;
    cursor: pointer;
    height: 0;
    width: 0;
}

.checkmark {
    position: absolute;
    top: 0;
    left: 0;
    height: 20px;
    width: 20px;
    background-color: #eee;
    border-radius: 4px;
    transition: background-color 0.3s ease;
}

.checkmark-icon {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%) rotate(-45deg);
    width: 10px;
    height: 5px;
    border-bottom: 2px solid #fff;
    border-left: 2px solid #fff;
    opacity: 0;
    transition: opacity 0.3s ease;
}

.checkbox input[type="checkbox"]:checked~.checkmark {
    background-color: #2196f3;
}

.checkbox input[type="checkbox"]:checked~.checkmark .checkmark-icon {
    opacity: 1;
}
</style>