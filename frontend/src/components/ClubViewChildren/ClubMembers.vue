<style scoped>
/* 总容器 */
.container {
    display: flex;
    flex-direction: column;
    height: 670px;
    overflow: hidden;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f7f7f7;
    padding: 20px;
    box-sizing: border-box;
}

/* 上半部分 */
.upper-section {
    display: flex;
    justify-content: space-between;
    flex: 1;
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    padding: 20px;
}

/* 主席框 */
.president-section {
    width: 400px;
    height: 400px;
    border-radius: 8px;
    text-align: center;
    padding: 20px;
    box-sizing: border-box;
    background-color: #f5f5f5;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s ease;
}

.president-section:hover {
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
}

.president-section img {
    max-width: 100%;
    max-height: 200px;
    border-radius: 50%;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

/* 其他干部框 */
.executive-section {
    flex: 1;
    margin-left: 50px;
    height: 400px;
    border-radius: 8px;
    padding: 20px;
    box-sizing: border-box;
    background-color: #f5f5f5;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    overflow-x: auto;
    white-space: nowrap;
    transition: box-shadow 0.3s ease;
}

.executive-section:hover {
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
}

/* 横向滚动条样式 */
.executive-section::-webkit-scrollbar {
    height: 8px;

    background-color: transparent;
}

.executive-section::-webkit-scrollbar-thumb {
    background-color: #ccc;
    border-radius: 4px;
    transition: background-color 0.3s ease;
}

.executive-section::-webkit-scrollbar-thumb:hover,
.executive-section::-webkit-scrollbar-thumb:active {
    background-color: #999;
}

.executive-section div {
    display: inline-block;
    width: 200px;
    height: 250px;
    border-radius: 8px;
    text-align: center;
    padding: 10px;
    box-sizing: border-box;
    margin-right: 20px;
    background-color: #fff;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s ease;
}

.executive-section div:hover {
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
}

.executive-section div img {
    max-width: 100%;
    max-height: 150px;
    border-radius: 50%;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

/* 下半部分 */
.member-section {
    padding: 20px;
    box-sizing: border-box;
    text-align: center;
    background-color: #fff;
    border-radius: 8px;
    margin-top: 20px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    transition: box-shadow 0.3s ease;
}

.member-section:hover {
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.2);
}

.charts {
    margin-top: 20px;
}

/* 文字 */
h2,
h3,
h4 {
    color: #333;
}

p {
    color: #666;
}

/* 骨架屏样式 */
.skeleton {
    background-color: #f0f0f0;
    border-radius: 50%;
    animation: skeleton-loading 1s infinite alternate;
    display: inline-block;
    vertical-align: middle;
}

@keyframes skeleton-loading {
    0% {
        background-color: #f0f0f0;
    }

    100% {
        background-color: #e0e0e0;
    }
}
</style>

<template>
    <div class="container">
        <div class="upper-section">
            <div class="president-section">
                <span v-show="!president.imageLoaded" class="skeleton"
                    :style="{ width: '200px', height: '200px' }"></span>
                <img v-show="president.imageLoaded" :src="president.imageUrl" @load="president.imageLoaded = true" />
                <h2>{{ president.stName }}</h2>
                <p>联系方式: {{ president.contact }}</p>
            </div>

            <div class="executive-section">
                <h3>其他干部</h3>
                <div v-for="executive in executives" :key="executive.stName">
                    <span v-show="!executive.imageLoaded" class="skeleton"
                        :style="{ width: '150px', height: '150px' }"></span>
                    <img v-show="executive.imageLoaded" :src="executive.imageUrl" :alt="executive.stName"
                        @load="executive.imageLoaded = true" />
                    <h4>{{ executive.stName }}</h4>
                    <p>职位: {{ positions[executive.position].name }}</p>
                </div>
            </div>
        </div>

        <div class="member-section">
            <h3>社团成员</h3>
            <p>成员总数: {{ memberCount }}</p>
            <p>成员组成: {{ memberComposition }}</p>
            <div class="charts">
                <!-- 这里可以插入图表组件 -->
            </div>
        </div>
    </div>
</template>

<script setup>
import { APIEnum, APIEventEnum } from '@/Enum';
import store from '@/store';
import eventEmitter from '@/utils/eventEmitter';
import { onUnmounted, reactive, ref } from 'vue';

const president = reactive({
    imageUrl: '',
    stName: '',
    contact: '',
    imageLoaded: false
})
const executives = ref([])

const positions = JSON.parse(localStorage.getItem('enumList')).positions

const memberCount = ref(0)
const memberComposition = ref('')

eventEmitter.emit(APIEventEnum.request, APIEnum.getClubMembers, { clubId: store.state.clubId })

eventEmitter.on(APIEventEnum.getClubMembersSuccess, 'getClubMembersSuccess', (members) => {
    president.imageUrl = members.president.imageUrl
    president.stName = members.president.stName
    president.contact = members.president.contact
    executives.value = members.executives.map(exec => ({ ...exec, imageLoaded: false }))
    memberCount.value = members.others.totalMembers
    memberComposition.value = '干部：' + (1 + members.executives.length) + '人，普通成员：' + (members.others.totalMembers - (1 + members.executives.length)) + '人'
})

onUnmounted(() => {
    eventEmitter.off(APIEventEnum.getClubMembersSuccess, 'getClubMembersSuccess')
})
</script>