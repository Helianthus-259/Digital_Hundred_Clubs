<style scoped>
.newsContainer {
    width: 100%;
    height: 400px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.newestBox {
    width: 400px;
    height: 400px;
    position: relative;
    background: #f5f5f5;
}

.newestBox::before {
    content: 'NEWEST';
    position: absolute;
    top: 0;
    right: 0;
    transform: rotate(30deg) translate(25%, 0);
    font-size: 24px;
    font-family: 'Franklin Gothic Medium', 'Arial Narrow', Arial, sans-serif;
    z-index: 5;
}

.newsBox {
    width: calc(100% - 400px);
    height: 400px;

    .news {
        box-sizing: border-box;
        width: 100%;
        height: 350px;
        padding: 10px;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .pagination {
        width: calc(100%-20px);
        margin-left: 10px;
        margin-right: 10px;
        height: 50px;
        display: flex;
        justify-content: center;
        align-items: center;
    }
}
</style>

<template>
    <div class="newsContainer">
        <div class="newestBox">
            <div style="height: 350px; width: 100%; display: flex; justify-content: center; align-items: center;">
                <img @click="go2Activity(lastestActivity.activityId)" style="width: 90%; cursor: pointer;"
                    :src="lastestActivity.imageUrl">
            </div>
            <div style="height: 50px; width: 100%; display: flex; justify-content: center; align-items: center;">
                {{ lastestActivity.activityName }}
            </div>
        </div>
        <div class="newsBox">
            <div class="news">
                <t-row :gutter="[16, 24]">
                    <t-col v-for="activity in activitiesView" :key="activity.activityId" :span="4">
                        <t-link theme="primary" underline @click="go2Activity(activity.activityId)">
                            <template #prefixIcon>
                                <t-icon name="jump"></t-icon>
                            </template>
                            {{ activity.activityName }}
                        </t-link>
                    </t-col>
                </t-row>
            </div>
            <div class="pagination">
                <t-pagination v-model="current" v-model:pageSize="pSize" :total="activityNumber" :showPageSize="false"
                    @current-change="onCurrentChange" />
                <t-button shape="square" variant="text" @click="loadMoreData">
                    <template #icon>
                        <t-popup content="加载更多" placement="top" showArrow>
                            <t-icon name="more" />
                        </t-popup>
                    </template>
                </t-button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { APIEnum, APIEventEnum, RouterEventEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { onMounted, onUnmounted, reactive, ref } from 'vue';

let pNumber = 0
const pSize = 12

const activities = ref([])
const activityNumber = ref(0)
const activitiesView = ref([])

const lastestActivity = reactive({
    activityId: -1,
    activityName: '',
    imageUrl: '',
})

const current = ref(1);
const onCurrentChange = (index, pageInfo) => {
    activitiesView.value = activities.value.slice(index * pSize - pSize, index * pSize)
};

const loadMoreData = () => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getLatestActivities, { pNumber, pSize })
}

const go2Activity = (activityId) => {
    eventEmitter.emit(RouterEventEnum.push, store.state.parentRoute.activity + activityId, true)
}

onMounted(() => {
    eventEmitter.emit(APIEventEnum.request, APIEnum.getLatestActivities, { pNumber, pSize })
    eventEmitter.on(APIEventEnum.getLatestActivitiesSuccess, 'getLatestActivitiesSuccess', (data) => {
        activities.value.push(...data)
        activityNumber.value = activities.value.length
        if (!pNumber) {
            lastestActivity.activityId = data[0].activityId
            lastestActivity.activityName = data[0].activityName
            lastestActivity.imageUrl = data[0].imageUrl
        }
        pNumber++
        activitiesView.value = activities.value.slice(current.value * pSize - pSize, current.value * pSize)
        if (data.length < pSize) {
            eventEmitter.off(APIEventEnum.getLatestActivitiesSuccess, 'getLatestActivitiesSuccess')
        }
    })
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getLatestActivitiesSuccess, 'getLatestActivitiesSuccess')
})
</script>