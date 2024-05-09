<style scoped>
.noticeCard {
    width: 400px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    margin: 20px auto;
}

.noticeImage {
    width: 100%;
    height: 200px;
    object-fit: cover;
}

.noticeDetails {
    padding: 0 18px;
}

.noticeTitle {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 8px;
}

.noticeContent {
    font-size: 14px;
    color: #666;
    margin-bottom: 12px;
}

.releaseTime {
    font-size: 12px;
    color: #999;
    text-align: right;
}

hr {
    border: none;
    border-top: 1px solid #ddd;
    margin: 6px 0;
}
</style>

<template>
    <div v-for="notice in notices" :key="notice.noticeID" class="noticeCard">
        <img :src="notice.imageUrl" alt="Notice Image" class="noticeImage" />
        <div class="noticeDetails">
            <h3 class="noticeTitle">{{ notice.title }}</h3>
            <hr />
            <p class="noticeContent">{{ notice.content }}</p>
            <hr />
            <p class="releaseTime">{{ formatDate(notice.publishTime) }}</p>
        </div>
    </div>
</template>

<script setup>
const props = defineProps({
    notices: {
        type: Array,
        required: true,
    },
});

const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleString();
};
</script>