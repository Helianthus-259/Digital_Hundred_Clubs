<style scoped>
.activityCard {
    width: 400px;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    margin: 20px auto;
}

.activityCard:hover {
    cursor: pointer;
    background: #f5f5f5;
}

.activityImage {
    width: 100%;
    height: 200px;
    object-fit: cover;
}

.activityDetails {
    padding: 0 18px;
}

.activityTitle {
    font-size: 18px;
    font-weight: bold;
    margin-bottom: 8px;
}

.activityContent {
    font-size: 14px;
    color: #666;
    margin-bottom: 12px;
}

.activityTime {
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
    <div v-for="activity in activities" :key="activity.activityID" class="activityCard"
        @click="handleActivityClick(activity)">
        <img :src="activity.imageUrl" alt="Activity Image" class="activityImage" />
        <div class="activity-details">
            <h3 class="activityTitle">{{ activity.activityName }}</h3>
            <hr />
            <p class="activityContent">{{ activity.activityIntroduction }}
                <span class="activityContent">{{ activity.activityLocation }}</span>
            </p>
            <hr />
            <p class="activityTime">{{ formatDate(activity.activityStartTime) + '-' +
                formatDate(activity.activityEndTime) }}</p>
        </div>
    </div>
</template>

<script setup>
const props = defineProps({
    activities: {
        type: Array,
        required: true,
    },
    onClick: {
        type: Function,
        default: () => { },
    }
});

const formatDate = (dateString) => {
    const date = new Date(dateString);
    return date.toLocaleString();
};

const handleActivityClick = (activity) => {
    props.onClick(activity)
}
</script>