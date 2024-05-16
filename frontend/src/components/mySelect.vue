<style scoped>
.select-container {
    display: inline-block;
    position: relative;
    font-family: Arial, sans-serif;
}

.select-label {
    font-size: 14px;
    font-weight: bold;
    margin-bottom: 5px;
}

.select-wrapper {
    position: relative;
}

.select-input {
    width: 200px;
    padding: 8px 12px;
    font-size: 14px;
    border: 1px solid #ccc;
    border-radius: 4px;
    appearance: none;
    background-color: #fff;
}

.select-input:disabled {
    background-color: #f5f5f5;
    color: #aaa;
    cursor: not-allowed;
}

.select-arrow {
    position: absolute;
    top: 50%;
    right: 12px;
    transform: translateY(-50%);
    width: 0;
    height: 0;
    border-left: 5px solid transparent;
    border-right: 5px solid transparent;
    border-top: 5px solid #888;
    pointer-events: none;
}
</style>

<template>
    <div class="select-container">
        <label :for="id" class="select-label">{{ label }}</label>
        <div class="select-wrapper">
            <select :id="id" :value="modelValue" @change="handleChange($event)" class="select-input"
                :disabled="disabled">
                <option value="" disabled selected>{{ placeholder }}</option>
                <option v-for="option in options" :key="option.value" :value="option.value">
                    {{ option.label }}
                </option>
            </select>
            <div class="select-arrow"></div>
        </div>
    </div>
</template>

<script setup>
import { nanoid } from 'nanoid';
import { computed } from 'vue';

const props = defineProps({
    label: {
        type: String,
        default: '',
    },
    modelValue: {
        type: [String, Number],
        required: true,
    },
    options: {
        type: Array,
        required: true,
        validator: (options) => {
            return options.every((option) => 'value' in option && 'label' in option);
        },
    },
    placeholder: {
        type: String,
        default: '请选择',
    },
    disabled: {
        type: Boolean,
        default: false,
    },
});

const id = computed(() => nanoid());

const emit = defineEmits(['update:modelValue']);

const handleChange = (event) => {
    emit('update:modelValue', event.target.value);
};
</script>
