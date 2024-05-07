<style scoped>
.fixedLabelBar {
    display: flex;
    align-items: center;
    width: 100%;
    height: 50px;
    background-color: #fff;
    position: fixed;
    top: 0;
    left: 0;
    z-index: 999;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
}
</style>

<template>
    <div class="fixedLabelBar">
        <slot></slot>
    </div>
</template>

<script setup>

</script>